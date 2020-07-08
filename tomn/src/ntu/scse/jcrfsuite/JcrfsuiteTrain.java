/******************************************************************************************
 * Copyright (c) 2018 Xiaoshi Zhong
 * All rights reserved. This program and the accompanying materials are made available
 * under the terms of the GNU lesser Public License v3 which accompanies this distribution,
 * and is available at http://www.gnu.org/licenses/lgpl.html
 * 
 * Contributors : Xiaoshi Zhong, zhongxiaoshi@gmail.com
 * ****************************************************************************************/

package ntu.scse.jcrfsuite;

import java.io.IOException;

import com.github.jcrfsuite.CrfTrainer;

public class JcrfsuiteTrain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String inputFeatureFile = "resources/te3/TimeBank/feature/TimeBank.feature";
		String outputModelFile = "resources/te3/TimeBank/model/TmeBank.model";
		
		if(args.length >= 2) {
			inputFeatureFile = args[0];
			outputModelFile = args[1];
//		}else{
//			System.err.println("Usage: " + JcrfsuiteTrain.class.getCanonicalName() + " <training feature file> <model file>");
//			System.exit(0);
		}
		
		JcrfsuiteTrain train = new JcrfsuiteTrain();
		train.jcrfsuiteTrain(inputFeatureFile, outputModelFile);

	}
	
	public void jcrfsuiteTrain(String inputFeatureFile, String outputModelFile) {
		try {
			CrfTrainer.train(inputFeatureFile, outputModelFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { }
	}

}
