/******************************************************************************************
 * Copyright (c) 2018 Xiaoshi Zhong
 * All rights reserved. This program and the accompanying materials are made available
 * under the terms of the GNU lesser Public License v3 which accompanies this distribution,
 * and is available at http://www.gnu.org/licenses/lgpl.html
 * 
 * Contributors : Xiaoshi Zhong, zhongxiaoshi@gmail.com
 * ****************************************************************************************/

package ntu.scse.struct;

public class CONST {
	public static final String REGEX_FILE = "/Users/Mathew/Java/projects/tomn/resources/tomnregex/TomnRegex.txt";
	/**
	 * For TimeML tags
	 * */
	public static final String TIMEX3 = "TIMEX3";
	public static final boolean IS_TIMEX = true;
	public static final boolean ISNOT_TIMEX = false;
	
	/**
	 * TOMN scheme
	 * T: time token
	 * M: modifier
	 * N: numeral
	 * O: out of time expression
	 * */
	public static final String TOMN_T = "T";
	public static final String TOMN_M = "M";
	public static final String TOMN_N = "N";
	public static final String TOMN_O = "O";
	
	public static final boolean IS_TIMETOKEN = true;
	public static final boolean ISNOT_TIMETOKEN = false;
	public static final boolean IS_MODIFIER = true;
	public static final boolean IS_NUMERAL = true;
	
	public static final boolean HAS_MODIFIED_TIMETOKEN = true;
	
	/**
	 * BILOU scheme
	 * B: Beginning word of a chunk
	 * I: Inside of a chunk
	 * L: Last word of a chunk 
	 * U: Unit-length chunk
	 * O: Out of a chunk
	 * */
	public static final String BILOU_B = "B";
	public static final String BILOU_I = "I";
	public static final String BILOU_L = "L";
	public static final String BILOU_O = "O";
	public static final String BILOU_U = "U";
	
	/**
	 * BIO scheme
	 * B: Beginning word of a chunk
	 * I: Inside of a chunk
	 * O: Out of a chunk
	 * */
	public static final String BIO_B = "B";
	public static final String BIO_I = "I";
	public static final String BIO_O = "O";
	
	/**
	 * For features
	 * */
	public static final String LEMMA = "lemma";
	public static final String POS = "pos";
	public static final String YEAR = "YEAR";
	public static final String DATE = "DATE";
	public static final String TIME = "TIME";
	public static final String DURATION = "DURATION";
	public static final String DECADE = "DECADE";
	public static final String NUMERAL = "NUMERAL";
	public static final String MODIFIED_TIMETOKEN = "RECORD";
	
	/**
	 * Train or test setting for CRFSuite
	 * */
	public static final boolean CRF_TRAIN = true;
	public static final boolean CRF_TEST = false;

}
