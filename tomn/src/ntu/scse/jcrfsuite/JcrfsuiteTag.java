/******************************************************************************************
 * Copyright (c) 2018 Xiaoshi Zhong
 * All rights reserved. This program and the accompanying materials are made available
 * under the terms of the GNU lesser Public License v3 which accompanies this distribution,
 * and is available at http://www.gnu.org/licenses/lgpl.html
 * 
 * Contributors : Xiaoshi Zhong, zhongxiaoshi@gmail.com
 * ****************************************************************************************/

package ntu.scse.jcrfsuite;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.github.jcrfsuite.CrfTagger;
import com.github.jcrfsuite.util.Pair;

import ntu.scse.util.IOProcess;
import ntu.scse.util.Setting;

public class JcrfsuiteTag {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String inputModelFile = "resources/te3/TimeBank/model/TimeBank.model";
		String inputTestDir = "resources/te3/te3-platinum/feature/";
		String outputTagDir = "resources/te3/te3-platinum/crflog/";
		
		if(args.length >= 3) {
			inputModelFile = args[0];
			inputTestDir = args[1];
			outputTagDir = args[2];
//		}else {
//			System.err.println("Usage: " + JcrfsuiteTag.class.getCanonicalName() + " <model file> <test folder> <predicted tag folder>");
//			System.exit(0);
		}
		
		JcrfsuiteTag tag = new JcrfsuiteTag();
		tag.jcrfsuiteTagFiles(inputModelFile, inputTestDir, outputTagDir);

	}
	
	public void jcrfsuiteTag(String inputModelFile, String inputTestFile, String outputTagFile) {
		CrfTagger crfTagger = new CrfTagger(inputModelFile);
		StringBuffer sb = new StringBuffer();
		
		try {
			List<List<Pair<String, Double>>> tagProbList = crfTagger.tag(inputTestFile);
			for(List<Pair<String, Double>> tagProbs : tagProbList) {
				for(Pair<String, Double> tagProb: tagProbs) {
					String prediction = tagProb.first;					
					sb.append(prediction + Setting.NEWLINE);
				}
				sb.append(Setting.NEWLINE);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { }
		
		IOProcess.saveFile(outputTagFile, sb.toString());
	}
	
	public void jcrfsuiteTagFiles(String inputModelFile, String inputTestDir, String outputTagDir) {
		inputTestDir = IOProcess.checkPath(inputTestDir);
		outputTagDir = IOProcess.checkPath(outputTagDir);
		
		CrfTagger crfTagger = new CrfTagger(inputModelFile);
		
		File[] inputTestFiles = IOProcess.getFiles(inputTestDir);
		for(File inputTestFile : inputTestFiles) {
			String filename = inputTestFile.getName();
			StringBuffer sb = new StringBuffer();
			try {
				List<List<Pair<String, Double>>> tagProbList = crfTagger.tag(inputTestFile.toString());
				for(List<Pair<String, Double>> tagProbs : tagProbList) {
					for(Pair<String, Double> tagProb: tagProbs) {
						String prediction = tagProb.first;					
						sb.append(prediction + Setting.NEWLINE);
					}
					sb.append(Setting.NEWLINE);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally { }
			
			IOProcess.saveFile(outputTagDir + filename.replaceAll(".feature", ".log"), sb.toString());
		}
	}

}
