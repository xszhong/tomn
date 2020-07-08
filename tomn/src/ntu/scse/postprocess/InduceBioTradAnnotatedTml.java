/******************************************************************************************
 * Copyright (c) 2018 Xiaoshi Zhong
 * All rights reserved. This program and the accompanying materials are made available
 * under the terms of the GNU lesser Public License v3 which accompanies this distribution,
 * and is available at http://www.gnu.org/licenses/lgpl.html
 * 
 * Contributors : Xiaoshi Zhong, zhongxiaoshi@gmail.com
 * ****************************************************************************************/

package ntu.scse.postprocess;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ntu.scse.struct.Article;
import ntu.scse.struct.CONST;
import ntu.scse.struct.TaggedToken;
import ntu.scse.tool.ParseTimeML;
import ntu.scse.tool.TimeML;
import ntu.scse.tool.TimexProcess;
import ntu.scse.util.IOProcess;

public class InduceBioTradAnnotatedTml {
	private ParseTimeML parseTimeML;
	
	public InduceBioTradAnnotatedTml(){
		parseTimeML = new ParseTimeML();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 * TimeBank
		 * */
		String inputTmlDir = "resources/te3/te3-platinum/tml/";
		String crfLogDir = "resources/te3/te3-platinum/crflog/";
		String outputTimeMLDir = "resources/te3/te3-platinum/TimeML-output/";
		String outputShellDir = "command/evaluation/";
		String evaluationDir = "evaluation/";
		
		if(args.length >= 4){
			inputTmlDir = args[0];
			crfLogDir = args[1];
			outputTimeMLDir = args[2];
			outputShellDir = args[3];
			evaluationDir = args[4];
		}else{
			System.err.println("Do not input the correct arguments, please check and try again.");
			System.exit(0);
		}
		
		InduceBioTradAnnotatedTml inducer = new InduceBioTradAnnotatedTml();
		inducer.induceAnnotatedTimeML(inputTmlDir, crfLogDir, outputTimeMLDir, outputShellDir, evaluationDir);
	}
	
	public void induceAnnotatedTimeML(String inputTmlDir, String crfLogDir, String outputTimeMLDir, String outputShellDir, String evaluationDir){
		inputTmlDir = IOProcess.checkPath(inputTmlDir);
		crfLogDir = IOProcess.checkPath(crfLogDir);
		outputTimeMLDir = IOProcess.checkPath(outputTimeMLDir);
		outputShellDir = IOProcess.checkPath(outputShellDir);
		evaluationDir = IOProcess.checkPath(evaluationDir);
		
		IOProcess.createPath(outputShellDir);
		
		String[] paths = inputTmlDir.split("/");
		
		StringBuffer sb = new StringBuffer();
		sb.append("cd " + evaluationDir + "tools\n");
		sb.append("python TE3-evaluation.py " + inputTmlDir + " " + outputTimeMLDir + " 0 > " + evaluationDir + "result/bio-trad/" + paths[paths.length - 3] + ".log");
		IOProcess.saveFile(outputShellDir + paths[paths.length - 3] + "-test.sh", sb.toString());
		
		File[] inputTmlFiles = IOProcess.getFiles(inputTmlDir);
		if(inputTmlFiles == null)
			IOProcess.findNoPath(inputTmlDir);
		
		for(File tmlFile : inputTmlFiles){
			String tmlFilename = tmlFile.getName();
			
			String docId = TimeML.extractTimeMLDocId(tmlFile);
			String dct = TimeML.extractTimeMLDCT(tmlFile);
			String date = TimeML.extractTimeMLDCT(tmlFile);
			
			Article article = parseTimeML.parseTimeML(tmlFile, CONST.CRF_TEST);
			String text = article.getText();
			List<TaggedToken> taggedTokenList = article.getTaggedTokenList();
			List<Integer> timeWordList = TimexProcess.identifyTimeWord(taggedTokenList);
			TimexProcess.checkModifierAndNumeral(taggedTokenList, timeWordList);
			
			BufferedReader br = IOProcess.newReader(crfLogDir + tmlFilename.replaceAll(".tml", ".log"));
			String predictTag = null;
			
			StringBuffer tmlSb = new StringBuffer();
			
			int textLastCharPosition = 0;
			
			int timexBeginTokenPosition = 0;
			int timexEndTokenPosition = 0;
			
			boolean isPreviousTokenTimex = false;
			
			int tid = 1;
			String type = "DATE";
			String value = date;
			
			for(TaggedToken taggedToken : taggedTokenList){
				int tokenPosition = taggedToken.getTokenPosition();
				int tokenBeginCharPosition = taggedToken.getBeginCharPosition();
//				int tokenEndCharPosition = taggedToken.getEndCharPosition();
				
				try {
					predictTag = br.readLine().trim();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally { }
				
				if(predictTag.equals(CONST.BILOU_B)){
					if(isPreviousTokenTimex){
						List<Integer> list = induceTimex(tmlSb, text, taggedTokenList, textLastCharPosition, timexBeginTokenPosition, timexEndTokenPosition, tid, type, value);
						tid += list.get(0);
						textLastCharPosition = list.get(1);
					}
					
					timexBeginTokenPosition = tokenPosition;
					timexEndTokenPosition = tokenPosition;
					
					isPreviousTokenTimex = true;
				}else if(predictTag.equals(CONST.BILOU_I)){
					if(isPreviousTokenTimex)
						timexEndTokenPosition = tokenPosition;
					else{
						timexBeginTokenPosition = tokenPosition;
						timexEndTokenPosition = tokenPosition;
					}
					
					isPreviousTokenTimex = true;
				}else if(predictTag.equals(CONST.BILOU_O)){
					if(isPreviousTokenTimex){
						List<Integer> list = induceTimex(tmlSb, text, taggedTokenList, textLastCharPosition, timexBeginTokenPosition, timexEndTokenPosition, tid, type, value);
						tid += list.get(0);
						textLastCharPosition = list.get(1);
					}
					
					isPreviousTokenTimex = false;
				}
			}
			
			tmlSb.append(text.substring(textLastCharPosition));
			
			TimeML.saveToTimeML(docId, dct, tmlSb.toString(), outputTimeMLDir + tmlFilename);
		}
	}	
	
	private List<Integer> induceTimex(StringBuffer tmlSb, String text, List<TaggedToken> taggedTokenList, int textLastCharPosition, int timexBeginTokenPosition, int timexEndTokenPosition, int tid, String type, String value){
		int timexCount = 0;
		
		if(timexBeginTokenPosition > timexEndTokenPosition){
			System.out.println("------------------------------------------------------");
			System.out.println("timex begin token position:" + timexBeginTokenPosition);
			System.out.println("timex end token position:" + timexEndTokenPosition);
		}
		
		TaggedToken timexBeginTaggedToken = taggedTokenList.get(timexBeginTokenPosition);
		TaggedToken timexEndTaggedToken = taggedTokenList.get(timexEndTokenPosition);
		
		int timexBeginCharPosition = timexBeginTaggedToken.getBeginCharPosition();
		int timexEndCharPosition = timexEndTaggedToken.getEndCharPosition();
		
		String timexEndToken = timexEndTaggedToken.getToken();
		
		if(textLastCharPosition <= timexBeginCharPosition){
			tmlSb.append(text.substring(textLastCharPosition, timexBeginCharPosition));
			textLastCharPosition = timexBeginCharPosition;
		}else{
			System.out.println("-----------------------------------------------");
			System.out.println("timex begin token position:" + timexBeginTokenPosition);
			System.out.println("timex end token position:" + timexEndTokenPosition);
			System.out.println("text last char position:" + textLastCharPosition);
			System.out.println("timex begin char position:" + timexBeginCharPosition);
			System.out.println(text.substring(timexBeginCharPosition, textLastCharPosition));
		}
		
		for(int index = timexBeginTokenPosition; index <= timexEndTokenPosition; index ++){
			TaggedToken temTaggedToken = taggedTokenList.get(index);
			if(temTaggedToken.isYearYear() || temTaggedToken.isYearMonth() || temTaggedToken.isMonthMonth() || temTaggedToken.isWeekWeek() || temTaggedToken.isTimeTime()
				|| temTaggedToken.isHalfDayHalfDay() || temTaggedToken.isNumeralNumeral()){
				
				int temBeginCharPosition = temTaggedToken.getBeginCharPosition();
				String[] items = temTaggedToken.getToken().split("-");
				String timex = text.substring(textLastCharPosition, temBeginCharPosition + items[0].length());
				tmlSb.append(TimeML.induceTIMEX3(tid, type, value, timex) + "-");
				tid ++;
				timexCount++;
				
				textLastCharPosition = temBeginCharPosition + items[0].length() + "-".length();
			}else if(temTaggedToken.isLinkage()){
				if(index == timexBeginTokenPosition || index == timexEndTokenPosition)
					continue;
				
				TaggedToken prevTaggedToken = taggedTokenList.get(index - 1);
				TaggedToken nextTaggedToken = taggedTokenList.get(index + 1);
				
				String timex = text.substring(textLastCharPosition, prevTaggedToken.getEndCharPosition());
				tmlSb.append(TimeML.induceTIMEX3(tid, type, value, timex));
				tid++;
				timexCount++;
				
				tmlSb.append(text.subSequence(prevTaggedToken.getEndCharPosition(), nextTaggedToken.getBeginCharPosition()));
				textLastCharPosition = nextTaggedToken.getBeginCharPosition();
			}else if(temTaggedToken.isComma()){

			}
		}
	
		if(timexEndToken.endsWith("'s")){
			String timex = text.substring(textLastCharPosition, timexEndCharPosition - 2);
			tmlSb.append(TimeML.induceTIMEX3(tid, type, value, timex));
			textLastCharPosition = timexEndCharPosition - 2;
		}else if(timexEndToken.endsWith("s") && (timexEndTokenPosition + 1 < taggedTokenList.size()) && taggedTokenList.get(timexEndTokenPosition + 1).getToken().equals("'")){
			String timex = text.substring(textLastCharPosition, taggedTokenList.get(timexEndTokenPosition + 1).getEndCharPosition());
			tmlSb.append(TimeML.induceTIMEX3(tid, type, value, timex));
			textLastCharPosition = taggedTokenList.get(timexEndTokenPosition + 1).getEndCharPosition();
		}else{
			String timex = text.substring(textLastCharPosition, timexEndCharPosition);
			tmlSb.append(TimeML.induceTIMEX3(tid, type, value, timex));
			textLastCharPosition = timexEndCharPosition;
		}
		tid ++;
		timexCount++;
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(timexCount);
		list.add(textLastCharPosition);
		
		return list;
	}
}
