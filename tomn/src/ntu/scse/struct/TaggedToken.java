/******************************************************************************************
 * Copyright (c) 2018 Xiaoshi Zhong
 * All rights reserved. This program and the accompanying materials are made available
 * under the terms of the GNU lesser Public License v3 which accompanies this distribution,
 * and is available at http://www.gnu.org/licenses/lgpl.html
 * 
 * Contributors : Xiaoshi Zhong, zhongxiaoshi@gmail.com
 * ****************************************************************************************/

package ntu.scse.struct;

import java.util.Set;

import ntu.scse.tool.InduceTokenType;
import ntu.scse.tool.TomnRegex.TokenType;

public class TaggedToken extends BasicTaggedToken {
	/**
	 * For TOMN scheme
	 * */
	private boolean isTimex;
	
	private boolean isTimeToken;
	private boolean isModifier;
	private boolean isNumeral;
	
	private boolean hasModifiedTimeToken;
	
	private Set<TokenType> tokenTypeSet;
	
	/**
	 * For BILOU scheme
	 * */
	private String bilouTag;
	
	/**
	 * For BIO scheme
	 * */
	private String bioTag;
	
	
	public TaggedToken(String token, String tag){
		super(token, tag);
	}
	
	public TaggedToken(String token, String lemma, String tag, int tokenPosition, int beginCharPosition, int endCharPosition){
		super(token, lemma, tag, tokenPosition, beginCharPosition, endCharPosition);
	}
	
	public boolean getIsTimex(){
		return isTimex;
	}
	public void setIsTimex(boolean isTimex){
		this.isTimex = isTimex;
	}
	
	public boolean getIsTimeToken(){
		return isTimeToken;
	}
	public void setIsTimeToken(boolean isTimeToken){
		this.isTimeToken = isTimeToken;
	}
	
	public boolean getIsModifier(){
		return isModifier;
	}
	public void setIsModifier(boolean isModifier){
		this.isModifier = isModifier;
	}
	
	public boolean getIsNumeral(){
		return isNumeral;
	}
	public void setIsNumeral(boolean isNumeral){
		this.isNumeral = isNumeral;
	}
	
	public boolean getHasModifiedTimeToken(){
		return hasModifiedTimeToken;
	}
	public void setHasModifiedTimeToken(boolean hasModifiedTimeToken){
		this.hasModifiedTimeToken = hasModifiedTimeToken;
	}
	
	public String getBilouTag(){
		return bilouTag;
	}
	public void setBilouTag(String bilouTag){
		this.bilouTag = bilouTag;
	}
	
	public String getBioTag(){
		return bioTag;
	}
	public void setBioTag(String bioTag){
		this.bioTag = bioTag;
	}
	
	/***************************************************
	 * *************************************************/
	
	public Set<TokenType> getTokenTypeSet(){
		return tokenTypeSet;
	}
	public void setTokenTypeSet(Set<TokenType> tokenTypeSet){
		this.tokenTypeSet = tokenTypeSet;
	}
	public void addTokenType(TokenType sutimeType){
		tokenTypeSet.add(sutimeType);
	}
	public void removeTokenType(TokenType sutimeType){
		this.tokenTypeSet.remove(sutimeType);
	}
	public void removeTokenType(){
		this.tokenTypeSet.clear();
	}
	
	public boolean isYear(){
		return InduceTokenType.isYear(tokenTypeSet);
	}
	
	public boolean isYearYear(){
		return InduceTokenType.isYearYear(tokenTypeSet);
	}
	
	public boolean isSeason(){
		return InduceTokenType.isSeason(tokenTypeSet);
	}
	
	public boolean isMonth(){
		return InduceTokenType.isMonth(tokenTypeSet);
	}
	
	public boolean isMonthMonth(){
		return InduceTokenType.isMonthMonth(tokenTypeSet);
	}
	
	public boolean isYearMonth(){
		return InduceTokenType.isYearMonth(tokenTypeSet);
	}
	
	public boolean isWeek(){
		return InduceTokenType.isWeek(tokenTypeSet);
	}
	
	public boolean isWeekWeek(){
		return InduceTokenType.isWeekWeek(tokenTypeSet);
	}
	
	public boolean isDate(){
		return InduceTokenType.isDate(tokenTypeSet);
	}
	
	public boolean isTime(){
		return InduceTokenType.isTime(tokenTypeSet);
	}
	
	public boolean isTimeTime(){
		return InduceTokenType.isTimeTime(tokenTypeSet);
	}
	
	public boolean isHalfDay(){
		return InduceTokenType.isHalfDay(tokenTypeSet);
	}
	
	public boolean isHalfDayHalfDay(){
		return InduceTokenType.isHalfDayHalfDay(tokenTypeSet);
	}
	
	public boolean isTimeZone(){
		return InduceTokenType.isTimeZone(tokenTypeSet);
	}
	
	public boolean isEra(){
		return InduceTokenType.isEra(tokenTypeSet);
	}
	
	public boolean isTimeUnit(){
		return InduceTokenType.isTimeUnit(tokenTypeSet);
	}
	
	public boolean isDuration(){
		return InduceTokenType.isDuration(tokenTypeSet);
	}
	
	public boolean isDurationDuration(){
		return InduceTokenType.isDurationDuration(tokenTypeSet);
	}
	
	public boolean isDayTime(){
		return InduceTokenType.isDayTime(tokenTypeSet);
	}
	
	public boolean isTimeline(){
		return InduceTokenType.isTimeline(tokenTypeSet);
	}
	
	public boolean isHoliday(){
		return InduceTokenType.isHoliday(tokenTypeSet);
	}

	public boolean isPeriod(){
		return InduceTokenType.isPeriod(tokenTypeSet);
	}
	
	public boolean isDecade(){
		return InduceTokenType.isDecade(tokenTypeSet);
	}
	
	public boolean isNumeral(){
		return InduceTokenType.isNumeral(tokenTypeSet);
	}
	
	public boolean isNumeralNumeral(){
		return InduceTokenType.isNumeralNumeral(tokenTypeSet);
	}
	
	public boolean isModifier(){
		if(InduceTokenType.isModifier(tokenTypeSet) || isLinkage() || isComma())
			return true;
		return false;
	}
	
	public boolean isLinkage(){
		return InduceTokenType.isLinkage(tokenTypeSet);
	}
	
	public boolean isComma(){
		return InduceTokenType.isComma(tokenTypeSet);
	}
	
	public boolean isTimeToken(){
		return InduceTokenType.isTimeToken(tokenTypeSet);
	}
}
