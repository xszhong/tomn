/******************************************************************************************
 * Copyright (c) 2018 Xiaoshi Zhong
 * All rights reserved. This program and the accompanying materials are made available
 * under the terms of the GNU lesser Public License v3 which accompanies this distribution,
 * and is available at http://www.gnu.org/licenses/lgpl.html
 * 
 * Contributors : Xiaoshi Zhong, zhongxiaoshi@gmail.com
 * ****************************************************************************************/

package ntu.scse.struct;

public class BasicTaggedToken {
	private String token;
	private String lemma;
	private String pos;
	
	private int tokenPosition;
	
	private int beginCharPosition;
	private int endCharPosition;
	
	public BasicTaggedToken(String token, String pos){
		this.token = token;
		this.pos = pos;
	}
	
	public BasicTaggedToken(String token, String lemma, String pos){
		this.token = token;
		this.lemma = lemma;
		this.pos = pos;
	}
	public BasicTaggedToken(String token, String lemma, String pos, int beginCharPosition, int endCharPosition){
		this(token, lemma, pos);
		this.beginCharPosition = beginCharPosition;
		this.endCharPosition = endCharPosition;
	}
	
	public BasicTaggedToken(String token, String lemma, String pos, int tokenPosition, int beginCharPosition, int endCharPosition){
		this(token, lemma, pos, beginCharPosition, endCharPosition);
		this.tokenPosition = tokenPosition;
	}
	
	public String getToken(){
		return token;
	}
	public String getLemma(){
		return lemma;
	}
	public String getPos(){
		return pos;
	}
	public void setPos(String pos){
		this.pos = pos;
	}
	
	public int getTokenPosition(){
		return tokenPosition;
	}
	
	public int getBeginCharPosition(){
		return beginCharPosition;
	}
	public void setBeginCharPosition(int beginCharPosition){
		this.beginCharPosition = beginCharPosition;
	}
	
	public int getEndCharPosition(){
		return endCharPosition;
	}
	public void setEndCharPosition(int endCharPosition){
		this.endCharPosition = endCharPosition;
	}
	
}
