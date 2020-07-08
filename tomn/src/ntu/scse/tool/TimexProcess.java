package ntu.scse.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ntu.scse.struct.CONST;
import ntu.scse.struct.TaggedToken;
import ntu.scse.tool.TomnRegex.TokenType;

public class TimexProcess {	
	public static List<Integer> identifyTimeWord(List<TaggedToken> taggedTokenList){
		List<Integer> timeTokenList = new ArrayList<Integer>();
		
		for(int i = 0; i < taggedTokenList.size(); i++){
			TaggedToken taggedToken = taggedTokenList.get(i);
			String token = taggedToken.getToken().toLowerCase();
			String pos = taggedToken.getPos();
			
			Set<TokenType> synTypeSet = InduceTokenType.getTokenType(token, pos);
			taggedToken.setTokenTypeSet(synTypeSet);

			if(taggedToken.isHalfDay()){
				if(token.equals("am")){
					boolean isHalfDay = true;
					if(i == 0 || (! InduceTokenType.isNumeral(taggedTokenList.get(i - 1)) && ! InduceTokenType.isTime(taggedTokenList.get(i - 1))))
						isHalfDay = false;
					
					if(! isHalfDay)
						taggedToken.removeTokenType();
				}	
			}else if(taggedToken.isTimeZone()){
				boolean isTimeZone = true;
				if(i == 0 || ! taggedTokenList.get(i - 1).isTime() && ! taggedTokenList.get(i - 1).isHalfDay() && ! taggedTokenList.get(i - 1).isNumeral())
					isTimeZone = false;
				
				if(! isTimeZone)
					taggedToken.removeTokenType();
			}else if(taggedToken.isEra()){
				boolean isEra = true;
				if(i == 0 || ! taggedTokenList.get(i - 1).isNumeral())
					isEra = false;
				
				if(! isEra)
					taggedToken.removeTokenType();
			}
			
			if(taggedToken.isTimeToken()){
				timeTokenList.add(Integer.valueOf(i));
				taggedToken.setIsTimeToken(CONST.IS_TIMETOKEN);
			}
			
			if(taggedToken.isModifier())
				taggedToken.setIsModifier(CONST.IS_MODIFIER);
			
			if(taggedToken.isNumeral())
				taggedToken.setIsNumeral(CONST.IS_NUMERAL);
		}
		
		return timeTokenList;
	}
	
	public static List<TaggedToken> checkModifierAndNumeral(List<TaggedToken> taggedTokenList, List<Integer> timeTokenList){
		/**
		 * For each time word, look at its left side and right side to find the modifiers and numeral
		 * */
		int BeginBound = 0;
		int EndBound = taggedTokenList.size() - 1;
		for(int i = 0; i < timeTokenList.size(); i++){
			int timeTokenPosition = timeTokenList.get(i);
			
			int leftBound = BeginBound;
			int rightBound = EndBound;
			if(i > 0)
				leftBound = timeTokenList.get(i - 1) + 1;
			if(i < timeTokenList.size() - 1)
				rightBound = timeTokenList.get(i + 1) - 1;
			
			/**
			 * Look at its left
			 * */
			for(int j = timeTokenPosition - 1; j >= leftBound; j--){
				TaggedToken leftTaggedToken = taggedTokenList.get(j);
				
				if(leftTaggedToken.isNumeral() || leftTaggedToken.isModifier())
					leftTaggedToken.setHasModifiedTimeToken(CONST.HAS_MODIFIED_TIMETOKEN);
				else
					break;
			}
			
			/** Look at its right*/
			for(int j = timeTokenPosition + 1; j <= rightBound; j++){
				TaggedToken rightTaggedToken = taggedTokenList.get(j);
				
				if(rightTaggedToken.isNumeral() || rightTaggedToken.isModifier())
					rightTaggedToken.setHasModifiedTimeToken(CONST.HAS_MODIFIED_TIMETOKEN);
				else
					break;
			}
		}
		/**
		 * Finish finding the modifiers and numeral
		 * */
		
		return taggedTokenList;
	}
}
