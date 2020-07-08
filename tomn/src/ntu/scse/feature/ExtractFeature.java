/******************************************************************************************
 * Copyright (c) 2018 Xiaoshi Zhong
 * All rights reserved. This program and the accompanying materials are made available
 * under the terms of the GNU lesser Public License v3 which accompanies this distribution,
 * and is available at http://www.gnu.org/licenses/lgpl.html
 * 
 * Contributors : Xiaoshi Zhong, zhongxiaoshi@gmail.com
 * ****************************************************************************************/

package ntu.scse.feature;

import java.io.File;
import java.util.List;

import ntu.scse.struct.Article;
import ntu.scse.struct.CONST;
import ntu.scse.struct.TaggedToken;
import ntu.scse.tool.ParseTimeML;
import ntu.scse.tool.TimexProcess;
import ntu.scse.util.IOProcess;

public class ExtractFeature {
	private ParseTimeML parseTimeML;
	
	public ExtractFeature(){
		parseTimeML = new ParseTimeML();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 * TimeBank
		 * */
		// train
		String inputTmlDir = "resources/te3/TimeBank/tml/";
		String outputFeatureDir = "resources/te3/TimeBank/feature/";
		String outputShellDir = "command/";
		String modelDir = "resources/te3/TimeBank/model/";
		String modelFilename = "TimeBank.model";
		String crfLogDir = "resources/te3/TimeBank/crflog/";
		boolean isTrain = CONST.CRF_TRAIN;
		
		// test
//		String inputTmlDir = "resources/te3/te3-platinum/tml/";
//		String outputFeatureDir = "resources/te3/te3-platinum/feature/";
//		String outputScriptDir = "resources/te3/te3-platinum/script/";
//		String modelDir = "resources/te3/TimeBank/model/";
//		String modelFilename = "TimeBank.model";
//		String crfLogDir = "resources/te3/te3-platinum/crflog/";
//		boolean isTrain = CONST.CRF_TEST;
		
		if(args.length >= 7){
			inputTmlDir = args[0];
			outputFeatureDir = args[1];
			outputShellDir = args[2];
			modelDir = args[3];
			modelFilename = args[4];
			crfLogDir = args[5];
			isTrain = Boolean.valueOf(args[6]);
		}else{
			System.err.println("Could not find arguments. Please try again.");
			System.exit(0);
		}
		
		ExtractFeature extractor = new ExtractFeature();
		extractor.extractFeature(inputTmlDir, outputFeatureDir, outputShellDir, modelDir, modelFilename, crfLogDir, isTrain);
	}
	
	public void extractFeature(String inputTmlDir, String outputFeatureDir, String outputShellDir, String modelDir, String modelFilename, String crfLogDir, boolean isTrain){
		inputTmlDir = IOProcess.checkPath(inputTmlDir);
		outputFeatureDir = IOProcess.checkPath(outputFeatureDir);
		outputShellDir = IOProcess.checkPath(outputShellDir);
		modelDir = IOProcess.checkPath(modelDir);
		crfLogDir = IOProcess.checkPath(crfLogDir);
		
		IOProcess.createPath(modelDir);
		IOProcess.createPath(crfLogDir);
		
		/**
		 * train feature dir and test feature dir
		 * */
		String outputTrainFeatureDir = IOProcess.checkPath(outputFeatureDir);
		String outputTestFeatureDir = IOProcess.checkPath(outputFeatureDir);
		
		/**
		 * train feature file name
		 * */
		String[] items = inputTmlDir.split("/");
		String trainFeatureFilename = outputTrainFeatureDir + items[items.length - 2] + ".feature";
		
		String trainModelFilename = modelDir + modelFilename;
		
		File[] tmlFiles = IOProcess.getFiles(inputTmlDir);
		if(tmlFiles == null)
			IOProcess.findNoPath(inputTmlDir);
		
		StringBuffer trainFeatSb = new StringBuffer();
		
		StringBuffer trainBShellSb = new StringBuffer();
		StringBuffer testBShellSb = new StringBuffer();
		
		/**
		 * outputBatchDir = "resources/te3/TimeBank/batch/";
		 * */
		
		for(File tmlFile : tmlFiles){
			String tmlFilename = tmlFile.getName();
			StringBuffer testFeatSb = new StringBuffer();
			
			Article article = parseTimeML.parseTimeML(tmlFile, isTrain);
			String text = article.getText();
			List<TaggedToken> taggedTokenList = article.getTaggedTokenList();
			
			/**
			 * Identify time token
			 * */
			List<Integer> timeWordList = TimexProcess.identifyTimeWord(taggedTokenList);
			/**
			 * Identify modifiers and numeral
			 * */
			TimexProcess.checkModifierAndNumeral(taggedTokenList, timeWordList);
			
			/**
			 * For each token, extract its features for CRF
			 * */
			for(int i = 0; i < taggedTokenList.size(); i++){
				TaggedToken taggedToken = taggedTokenList.get(i);
				String token = taggedToken.getToken();
				String lemma = taggedToken.getLemma();
				String posTag = taggedToken.getPos();
				
				boolean isTimex = taggedToken.getIsTimex();
				boolean isTimeToken = taggedToken.getIsTimeToken();
				boolean isModifier = taggedToken.getIsModifier();
				boolean isNumeral = taggedToken.getIsNumeral();
				boolean hasModifiedTimeToken = taggedToken.getHasModifiedTimeToken();
				
				boolean isYear = taggedToken.isYear();
				boolean isDate = taggedToken.isDate();
				boolean isTime = taggedToken.isTime();
				boolean isDecade = taggedToken.isDecade();
				
				/**
				 * TOMN tag as labeling tag
				 * */
				if(isTimex && isTimeToken){
					trainFeatSb.append(CONST.TOMN_T);
					testFeatSb.append(CONST.TOMN_T);
				}else if(isTimex && isNumeral){
					trainFeatSb.append(CONST.TOMN_N);
					testFeatSb.append(CONST.TOMN_N);
				}else if(isTimex && isModifier){
					trainFeatSb.append(CONST.TOMN_M);
					testFeatSb.append(CONST.TOMN_M);
				}else if(isTimex){
					trainFeatSb.append(CONST.TOMN_M);
					testFeatSb.append(CONST.TOMN_M);
				}else{
					trainFeatSb.append(CONST.TOMN_O);
					testFeatSb.append(CONST.TOMN_O);
				}
				
				/**
				 * TOMN scheme features
				 * */
				if(isTimex || isTimeToken || isModifier || isNumeral){
					
					if(isTimeToken){
						trainFeatSb.append("\t" + CONST.TOMN_T + "=1");
						testFeatSb.append("\t" + CONST.TOMN_T + "=1");
					}
					if(isModifier){
						trainFeatSb.append("\t" + CONST.TOMN_M + "=1");
						testFeatSb.append("\t" + CONST.TOMN_M + "=1");
					}
					if(isNumeral){
						trainFeatSb.append("\t" + CONST.TOMN_N + "=1");
						testFeatSb.append("\t" + CONST.TOMN_N + "=1");
					}
					
					if(hasModifiedTimeToken){
						trainFeatSb.append("\t" + CONST.MODIFIED_TIMETOKEN + "=1");
						testFeatSb.append("\t" + CONST.MODIFIED_TIMETOKEN + "=1");
					}
				}else{
					trainFeatSb.append("\t" + CONST.TOMN_O + "=1");
					testFeatSb.append("\t" + CONST.TOMN_O + "=1");
				}
				
				/**
				 * lemma features
				 * */
				if(isYear){
					trainFeatSb.append("\t" + CONST.LEMMA + "=" + CONST.YEAR);
					testFeatSb.append("\t" + CONST.LEMMA + "=" + CONST.YEAR);
				}
				
				if(isDate){
					trainFeatSb.append("\t" + CONST.LEMMA + "=" + CONST.DATE);
					testFeatSb.append("\t" + CONST.LEMMA + "=" + CONST.DATE);
				}
				
				if(isTime){
					trainFeatSb.append("\t" + CONST.LEMMA + "=" + CONST.TIME);
					testFeatSb.append("\t" + CONST.LEMMA + "=" + CONST.TIME);
				}
				
				if(isDecade){
					trainFeatSb.append("\t" + CONST.LEMMA + "=" + CONST.DECADE);
					testFeatSb.append("\t" + CONST.LEMMA + "=" + CONST.DECADE);
				}
				
				if(isNumeral){
					trainFeatSb.append("\t" + CONST.LEMMA + "=" + CONST.NUMERAL);
					testFeatSb.append("\t" + CONST.LEMMA + "=" + CONST.NUMERAL);
				}
				
				if(! isYear && ! isDate && ! isTime && ! isDecade && ! isNumeral){
					trainFeatSb.append("\t" + CONST.LEMMA + "=" + lemma);
					testFeatSb.append("\t" + CONST.LEMMA + "=" + lemma);
				}
				
				/**
				 * POS tags
				 * */
				trainFeatSb.append("\t" + CONST.POS + "=" + posTag);
				testFeatSb.append("\t" + CONST.POS + "=" + posTag);
				
				/**
				 * Previous word: w[-1]
				 * */
				if (i - 1 >= 0) {
					TaggedToken prevTaggedToken = taggedTokenList.get(i - 1);
					setFeature(trainFeatSb, prevTaggedToken, -1);
					setFeature(testFeatSb, prevTaggedToken, -1);
				}

				/**
				 * Next word: w[1]
				 * */
				if (i + 1 < taggedTokenList.size()) {
					TaggedToken subTaggedToken = taggedTokenList.get(i + 1);
					setFeature(trainFeatSb, subTaggedToken, 1);
					setFeature(testFeatSb, subTaggedToken, 1);
				}

				/**
				 * The previous previous word: w[-2]
				 * */
				if (i - 2 >= 0) {
					TaggedToken prev2TaggedToken = taggedTokenList.get(i - 2);
					setFeature(trainFeatSb, prev2TaggedToken, -2);
					setFeature(testFeatSb, prev2TaggedToken, -2);
				}

				/**
				 * The next next word: w[2]
				 * */
				if (i + 2 < taggedTokenList.size()) {
					TaggedToken sub2TaggedToken = taggedTokenList.get(i + 2);
					setFeature(trainFeatSb, sub2TaggedToken, 2);
					setFeature(testFeatSb, sub2TaggedToken, 2);
				}
				
				
				if(i == 0){
					trainFeatSb.append("\t__BOS__");
					testFeatSb.append("\t__BOS__");
				}
				if(i == taggedTokenList.size() - 1){
					trainFeatSb.append("\t__EOS__\n");
					testFeatSb.append("\t__EOS__");
				}
				
				trainFeatSb.append("\n");
				testFeatSb.append("\n");
			}
			
			timeWordList.clear();
			taggedTokenList.clear();
			if(! isTrain){
				String testFeatureFilename = outputTestFeatureDir + tmlFilename.replaceAll(".tml", ".feature");
				IOProcess.saveFile(testFeatureFilename, testFeatSb.toString());
//				testBShellSb.append("crfsuite tag -m " + trainModelFilename + " " + testFeatureFilename + " > " + crfLogDir + tmlFilename.replaceAll(".tml", ".log") + "\n");
			}	
			
		}
		
		if(isTrain){
			IOProcess.saveFile(trainFeatureFilename, trainFeatSb.toString());
			
//			trainBShellSb.append("crfsuite learn -m " + trainModelFilename + " " + trainFeatureFilename);
//			IOProcess.saveFile(outputShellDir + items[items.length - 3] + "-" + items[items.length - 2] + "-learn-train-feature.sh", trainBShellSb.toString());
//		}else{
//			IOProcess.saveFile(outputShellDir + items[items.length - 3] + "-" + items[items.length - 2] + "-tag-test-feature.sh", testBShellSb.toString());
		}
	}
	
	private void setFeature(StringBuffer sb, TaggedToken taggedToken, int index){
//		String token = taggedToken.getToken();
		String lemma = taggedToken.getLemma();
		String posTag = taggedToken.getPos();
		
		boolean isTimeToken = taggedToken.getIsTimeToken();
		boolean isModifier = taggedToken.getIsModifier();
		boolean isNumeral = taggedToken.getIsNumeral();
		boolean hasModifiedTimeToken = taggedToken.getHasModifiedTimeToken();
		
		boolean isYear = taggedToken.isYear();
		boolean isDate = taggedToken.isDate();
		boolean isTime = taggedToken.isTime();
		boolean isDecade = taggedToken.isDecade();
		
		
		/**
		 * TOMN scheme features
		 * */
		if (isTimeToken || isModifier || isNumeral) {

			if (isTimeToken) {
				sb.append("\t" + CONST.TOMN_T + "[" + index + "]=1");
			}
			if (isModifier) {
				sb.append("\t" + CONST.TOMN_M + "[" + index + "]=1");
			}
			if (isNumeral) {
				sb.append("\t" + CONST.TOMN_N + "[" + index + "]=1");
			}
		}else{
			sb.append("\t" + CONST.TOMN_O + "[" + index + "]=1");
		}
		
		/**
		 * lemma features
		 * */
		if (isYear) {
			sb.append("\t" + CONST.LEMMA + "[" + index + "]=" + CONST.YEAR);
		}
		if (isDate) {
			sb.append("\t" + CONST.LEMMA + "[" + index + "]=" + CONST.DATE);
		}
		if (isTime) {
			sb.append("\t" + CONST.LEMMA + "[" + index + "]=" + CONST.TIME);
		}
		if (isDecade) {
			sb.append("\t" + CONST.LEMMA + "[" + index + "]=" + CONST.DECADE);
		}
		if (isNumeral) {
			sb.append("\t" + CONST.LEMMA + "[" + index + "]=" + CONST.NUMERAL);
		}
		if (!isYear && !isDate && !isTime && !isDecade && !isNumeral) {
			sb.append("\t" + CONST.LEMMA + "[" + index + "]=" + lemma);
		}
		
		/**
		 * POS tag
		 * */
		sb.append("\t" + CONST.POS + "[" + index + "]=" + posTag);
	}
}
