package ntu.scse.tool;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;

import ntu.scse.struct.BasicTaggedToken;
import ntu.scse.struct.TaggedToken;
import ntu.scse.tool.TomnRegex;
import ntu.scse.tool.TomnRegex.TokenType;

public class InduceTokenType {
	public static Set<TokenType> getTokenType(BasicTaggedToken taggedToken){
		return getTokenType(taggedToken.getToken(), taggedToken.getPos());
	}
	
	public static Set<TokenType> getTokenType(TaggedToken taggedToken){
		return getTokenType(taggedToken.getToken(), taggedToken.getPos());
	}
	
	public static Set<TokenType> getTokenType(String token, String pos){
		Set<TokenType> tokenTypeSet = new HashSet<TokenType>();
		
		Matcher mYear1 = TomnRegex.YEAR_PATTERN_1.matcher(token);
		Matcher mYear2 = TomnRegex.YEAR_PATTERN_2.matcher(token);
		Matcher mMidYear = TomnRegex.YEAR_MID_PATTERN.matcher(token);
		Matcher mEraYear = TomnRegex.ERA_YEAR_PATTERN.matcher(token);
		if(mYear1.matches() || mYear2.matches() || mMidYear.matches() || mEraYear.matches())
			tokenTypeSet.add(TokenType.YEAR);
			
		Matcher mYearYear = TomnRegex.YEAR_YEAR_PATTERN.matcher(token);
		if(mYearYear.matches())
			tokenTypeSet.add(TokenType.YEAR_YEAR);
			
		Matcher mSeason = TomnRegex.SEASON_PATTERN.matcher(token);
		Matcher mSeasonMid = TomnRegex.SEASON_MID_PATTERN.matcher(token);
		if(mSeason.matches() || mSeasonMid.matches() )
			tokenTypeSet.add(TokenType.SEASON);
		
		Matcher mMonth = TomnRegex.MONTH_PATTERN.matcher(token);
		Matcher mMidMonth = TomnRegex.MONTH_MID_PATTERN.matcher(token);
		if(mMonth.matches() || mMidMonth.matches())
			tokenTypeSet.add(TokenType.MONTH);
		Matcher mMonthAbbr = TomnRegex.MONTH_ABBR_PATTERN.matcher(token);
		if(mMonthAbbr.matches())
			tokenTypeSet.add(TokenType.MONTH_ABBR);
		Matcher mMonthMonth = TomnRegex.MONTH_MONTH_PATTERN.matcher(token);
		if(mMonthMonth.matches())
			tokenTypeSet.add(TokenType.MONTH_MONTH);
		
		Matcher mYearMonth1 = TomnRegex.YEAR_MONTH_PATTERN_1.matcher(token);
		Matcher mYearMonth2 = TomnRegex.YEAR_MONTH_PATTERN_2.matcher(token);
		if(mYearMonth1.matches() || mYearMonth2.matches())
			tokenTypeSet.add(TokenType.YEAR_MONTH);
		
		Matcher mWeek = TomnRegex.WEEK_PATTERN.matcher(token);
		if(mWeek.matches())
			tokenTypeSet.add(TokenType.WEEK);
		
		Matcher mWeekAbbr = TomnRegex.WEEK_ABBR_PATTERN.matcher(token);
		if(mWeekAbbr.matches())
			tokenTypeSet.add(TokenType.WEEK_ABBR);
		
		Matcher mWeekWeek = TomnRegex.WEEK_WEEK_PATTERN.matcher(token);
		if(mWeekWeek.matches())
			tokenTypeSet.add(TokenType.WEEK_WEEK);
		
		Matcher mDate1 = TomnRegex.DATE_PATTERN_1.matcher(token);
		Matcher mDate2 = TomnRegex.DATE_PATTERN_2.matcher(token);
		Matcher mDate3 = TomnRegex.DATE_PATTERN_3.matcher(token);
		if(mDate1.matches() || mDate2.matches() || mDate3.matches())
			tokenTypeSet.add(TokenType.DATE);
		
		Matcher mTime1 = TomnRegex.TIME_PATTERN_1.matcher(token);
		Matcher mTime2 = TomnRegex.TIME_PATTERN_2.matcher(token);
		if(mTime1.matches() || mTime2.matches())
			tokenTypeSet.add(TokenType.TIME);
		Matcher mTimeTime = TomnRegex.TIME_TIME_PATTERN.matcher(token);
		if(mTimeTime.matches())
			tokenTypeSet.add(TokenType.TIME_TIME);
		
		Matcher mHalfDay1 = TomnRegex.HALFDAY_PATTERN_1.matcher(token);
		Matcher mHalfDay2 = TomnRegex.HALFDAY_PATTERN_2.matcher(token);
		if(mHalfDay1.matches() || mHalfDay2.matches())
			tokenTypeSet.add(TokenType.HALFDAY);
		Matcher mHalfDayHalfDay = TomnRegex.HALFDAY_HALFDAY_PATTERN.matcher(token);
		if(mHalfDayHalfDay.matches())
			tokenTypeSet.add(TokenType.HALFDAY_HALFDAY);
		
		Matcher mTimeZone = TomnRegex.TIME_ZONE_PATTERN.matcher(token);
		if(mTimeZone.matches())
			tokenTypeSet.add(TokenType.TIME_ZONE);
		
		Matcher mEra = TomnRegex.ERA_PATTERN.matcher(token);
		if(mEra.matches())
			tokenTypeSet.add(TokenType.ERA);
		
		Matcher mTimeUnit = TomnRegex.TIME_UNIT_PATTERN.matcher(token);
		if(mTimeUnit.matches())
			tokenTypeSet.add(TokenType.TIME_UNIT);
		
		Matcher mDuration = TomnRegex.DURATION_PATTERN.matcher(token);
		if(mDuration.matches())
			tokenTypeSet.add(TokenType.DURATION);
		
		Matcher mDurationDuration1 = TomnRegex.DURATION_DURATION_PATTERN_1.matcher(token);
		Matcher mDurationDuration2 = TomnRegex.DURATION_DURATION_PATTERN_2.matcher(token);
		if(! mDuration.matches() && ( mDurationDuration1.matches() || mDurationDuration2.matches()))
			tokenTypeSet.add(TokenType.DURATION_DURATION);
		
		Matcher mDayTime = TomnRegex.DAY_TIME_PATTERN.matcher(token);
		Matcher mDayTimeMid = TomnRegex.DAY_TIME_MID_PATTERN.matcher(token);
		if(mDayTime.matches() || mDayTimeMid.matches())
			tokenTypeSet.add(TokenType.DAY_TIME);
		
		Matcher mTimeline = TomnRegex.TIMELINE_PATTERN.matcher(token);
		if(mTimeline.matches())
			tokenTypeSet.add(TokenType.TIMELINE);
		
		Matcher mHoliday = TomnRegex.HOLIDAY_PATTERN.matcher(token);
		if(mHoliday.matches())
			tokenTypeSet.add(TokenType.HOLIDAY);
		
		Matcher mPeriod = TomnRegex.PERIOD_PATTERN.matcher(token);
		if(mPeriod.matches())
			tokenTypeSet.add(TokenType.PERIOD);
		
		Matcher mDecade = TomnRegex.DECADE_PATTERN.matcher(token);
		Matcher mDecadeMid = TomnRegex.DECADE_MID_PATTERN.matcher(token);
		if(mDecade.matches() || mDecadeMid.matches())
			tokenTypeSet.add(TokenType.DECADE);
		
		Matcher mDigit1 = TomnRegex.DIGIT_PATTERN_1.matcher(token);
		Matcher mDigit2 = TomnRegex.DIGIT_PATTERN_2.matcher(token);
		Matcher mBasicNum1 = TomnRegex.BASIC_NUMBER_PATTERN_1.matcher(token);
		Matcher mBasicNum2 = TomnRegex.BASIC_NUMBER_PATTERN_2.matcher(token);
		Matcher mOrdinal1 = TomnRegex.ORDINAL_PATTERN_1.matcher(token);
		Matcher mOrdinal2 = TomnRegex.ORDINAL_PATTERN_2.matcher(token);
		if(mYear1.matches() || mYear2.matches())
			;
		else if(mDigit1.matches() || mDigit2.matches() || mBasicNum1.matches() || mBasicNum2.matches() || mOrdinal1.matches() || mOrdinal2.matches())
			tokenTypeSet.add(TokenType.NUMERAL);
		
		Matcher mDigitDigit = TomnRegex.DIGIT_DIGIT_PATTERN.matcher(token);
		Matcher mBasicNumNum = TomnRegex.BASIC_NUMBER_NUMBER_PATTERN.matcher(token);
		Matcher mOrdinalOrdinal = TomnRegex.ORDINAL_ORDINAL_PATTERN.matcher(token);
		if(! mYearYear.matches() && ! mBasicNum2.matches() && (mDigitDigit.matches() || mBasicNumNum.matches() || mOrdinalOrdinal.matches()))
			tokenTypeSet.add(TokenType.NUMERAL_NUMERAL);
		
		Matcher mModifier = TomnRegex.MODIFIER_PATTERN.matcher(token);
		if(tokenTypeSet.isEmpty() && mModifier.matches())
			tokenTypeSet.add(TokenType.MODIFIER);
		
		Matcher mLinkage = TomnRegex.LINKAGE_PATTERN.matcher(token);
		if(mLinkage.matches())
			tokenTypeSet.add(TokenType.LINKAGE);

		return tokenTypeSet;
	}
	
	/***************************************************************/
	public static boolean isYear(TokenType tokenType){
		if(tokenType.equals(TokenType.YEAR) || tokenType.equals(TokenType.YEAR_YEAR))
			return true;
		return false;
	}
	public static boolean isYear(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet)
			if(isYear(tokenType))
				return true;
		return false;
	}
	public static boolean isYear(TaggedToken taggedToken){
		return isYear(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isYearYear(TokenType tokenType){
		if(tokenType.equals(TokenType.YEAR_YEAR))
			return true;
		return false;
	}
	public static boolean isYearYear(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet)
			if(isYearYear(tokenType))
				return true;
		return false;
	}
	public static boolean isYearYear(TaggedToken taggedToken){
		return isYearYear(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isSeason(TokenType tokenType){
		if(tokenType.equals(TokenType.SEASON))
			return true;
		return false;
	}
	public static boolean isSeason(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet)
			if(isSeason(tokenType))
				return true;
		return false;
	}
	public static boolean isSeason(TaggedToken taggedToken){
		return isSeason(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isMonth(TokenType tokenType){
		if(tokenType.equals(TokenType.MONTH) || tokenType.equals(TokenType.MONTH_ABBR) || tokenType.equals(TokenType.MONTH_MONTH) || tokenType.equals(TokenType.YEAR_MONTH))
			return true;
		return false;
	}
	public static boolean isMonth(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet)
			if(isMonth(tokenType))
				return true;
		return false;
	}
	public static boolean isMonth(TaggedToken taggedToken){
		return isMonth(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isMonthMonth(TokenType tokenType){
		if(tokenType.equals(TokenType.MONTH_MONTH))
			return true;
		return false;
	}
	public static boolean isMonthMonth(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet)
			if(isMonthMonth(tokenType))
				return true;
		return false;
	}
	public static boolean isMonthMonth(TaggedToken taggedToken){
		return isMonthMonth(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isYearMonth(TokenType tokenType){
		if(tokenType.equals(TokenType.YEAR_MONTH))
			return true;
		return false;
	}
	public static boolean isYearMonth(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet)
			if(isYearMonth(tokenType))
				return true;
		return false;
	}
	public static boolean isYearMonth(TaggedToken taggedToken){
		return isYearMonth(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isWeek(TokenType tokenType){
		if(tokenType.equals(TokenType.WEEK) || tokenType.equals(TokenType.WEEK_ABBR) || tokenType.equals(TokenType.WEEK_WEEK))
			return true;
		return false;
	}
	public static boolean isWeek(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet)
			if(isWeek(tokenType))
				return true;
		return false;
	}
	public static boolean isWeek(TaggedToken taggedToken){
		return isWeek(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isWeekWeek(TokenType tokenType){
		if(tokenType.equals(TokenType.WEEK_WEEK))
			return true;
		return false;
	}
	public static boolean isWeekWeek(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet)
			if(isWeekWeek(tokenType))
				return true;
		return false;
	}
	public static boolean isWeekWeek(TaggedToken taggedToken){
		return isWeekWeek(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isDate(TokenType tokenType){
		if(tokenType.equals(TokenType.DATE))
			return true;
		return false;
	}
	public static boolean isDate(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet)
			if(isDate(tokenType))
				return true;
		return false;
	}
	public static boolean isDate(TaggedToken taggedToken){
		return isDate(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isTime(TokenType tokenType){
		if(tokenType.equals(TokenType.TIME) || tokenType.equals(TokenType.TIME_TIME))
			return true;
		return false;
	}
	public static boolean isTime(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet)
			if(isTime(tokenType))
				return true;
		return false;
	}
	public static boolean isTime(TaggedToken taggedToken){
		return isTime(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isTimeTime(TokenType tokenType){
		if(tokenType.equals(TokenType.TIME_TIME))
			return true;
		return false;
	}
	public static boolean isTimeTime(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet)
			if(isTimeTime(tokenType))
				return true;
		return false;
	}
	public static boolean isTimeTime(TaggedToken taggedToken){
		return isTimeTime(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isHalfDay(TokenType tokenType){
		if(tokenType.equals(TokenType.HALFDAY) || tokenType.equals(TokenType.HALFDAY_HALFDAY))
			return true;
		return false;
	}
	public static boolean isHalfDay(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet)
			if(isHalfDay(tokenType))
				return true;
		return false;
	}
	public static boolean isHalfDay(TaggedToken taggedToken){
		return isHalfDay(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isHalfDayHalfDay(TokenType tokenType){
		if(tokenType.equals(TokenType.HALFDAY_HALFDAY))
			return true;
		return false;
	}
	public static boolean isHalfDayHalfDay(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet)
			if(isHalfDayHalfDay(tokenType))
				return true;
		return false;
	}
	public static boolean isHalfDayHalfDay(TaggedToken taggedToken){
		return isHalfDayHalfDay(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isTimeZone(TokenType tokenType){
		if(tokenType.equals(TokenType.TIME_ZONE))
			return true;
		return false;
	}
	public static boolean isTimeZone(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet)
			if(isTimeZone(tokenType))
				return true;
		return false;
	}
	public static boolean isTimeZone(TaggedToken taggedToken){
		return isTimeZone(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isEra(TokenType tokenType){
		if(tokenType.equals(TokenType.ERA))
			return true;
		return false;
	}
	public static boolean isEra(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet)
			if(isEra(tokenType))
				return true;
		return false;
	}
	public static boolean isEra(TaggedToken taggedToken){
		return isEra(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isTimeUnit(TokenType tokenType){
		if(tokenType.equals(TokenType.TIME_UNIT))
			return true;
		return false;
	}
	public static boolean isTimeUnit(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet)
			if(isTimeUnit(tokenType))
				return true;
		return false;
	}
	public static boolean isTimeUnit(TaggedToken taggedToken){
		return isTimeUnit(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isDuration(TokenType tokenType){
		if(tokenType.equals(TokenType.DURATION) || tokenType.equals(TokenType.DURATION_DURATION))
			return true;
		return false;
	}
	public static boolean isDuration(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet)
			if(isDuration(tokenType))
				return true;
		return false;
	}
	public static boolean isDuration(TaggedToken taggedToken){
		return isDuration(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isDurationDuration(TokenType tokenType){
		if(tokenType.equals(TokenType.DURATION_DURATION))
			return true;
		return false;
	}
	public static boolean isDurationDuration(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet)
			if(isDurationDuration(tokenType))
				return true;
		return false;
	}
	public static boolean isDurationDuration(TaggedToken taggedToken){
		return isDurationDuration(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isDayTime(TokenType tokenType){
		if(tokenType.equals(TokenType.DAY_TIME))
			return true;
		return false;
	}
	public static boolean isDayTime(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet)
			if(isDayTime(tokenType))
				return true;
		return false;
	}
	public static boolean isDayTime(TaggedToken taggedToken){
		return isDayTime(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isTimeline(TokenType tokenType){
		if(tokenType.equals(TokenType.TIMELINE))
			return true;
		return false;
	}
	public static boolean isTimeline(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet)
			if(isTimeline(tokenType))
				return true;
		return false;
	}
	public static boolean isTimeline(TaggedToken taggedToken){
		return isTimeline(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isHoliday(TokenType tokenType){
		if(tokenType.equals(TokenType.HOLIDAY))
			return true;
		return false;
	}
	public static boolean isHoliday(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet)
			if(isHoliday(tokenType))
				return true;
		return false;
	}
	public static boolean isHoliday(TaggedToken taggedToken){
		return isHoliday(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isPeriod(TokenType tokenType){
		if(tokenType.equals(TokenType.PERIOD))
			return true;
		return false;
	}
	public static boolean isPeriod(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet)
			if(isPeriod(tokenType))
				return true;
		return false;
	}
	public static boolean isPeriod(TaggedToken taggedToken){
		return isPeriod(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isDecade(TokenType tokenType){
		if(tokenType.equals(TokenType.DECADE))
			return true;
		return false;
	}
	public static boolean isDecade(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet)
			if(isDecade(tokenType))
				return true;
		return false;
	}
	public static boolean isDecade(TaggedToken taggedToken){
		return isDecade(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isNumeral(TokenType tokenType){
		if(tokenType.equals(TokenType.NUMERAL) || tokenType.equals(TokenType.NUMERAL_NUMERAL))
			return true;
		return false;
	}
	public static boolean isNumeral(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet)
			if(isNumeral(tokenType))
				return true;
		return false;
	}
	public static boolean isNumeral(TaggedToken taggedToken){
		return isNumeral(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isNumeralNumeral(TokenType tokenType){
		if(tokenType.equals(TokenType.NUMERAL_NUMERAL))
			return true;
		return false;
	}
	public static boolean isNumeralNumeral(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet)
			if(isNumeralNumeral(tokenType))
				return true;
		return false;
	}
	public static boolean isNumeralNumeral(TaggedToken taggedToken){
		return isNumeralNumeral(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isModifier(TokenType tokenType){
		if(tokenType.equals(TokenType.MODIFIER))
			return true;
		return false;
	}
	public static boolean isModifier(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet){
			if(isModifier(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isModifier(TaggedToken taggedToken){
		return isModifier(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isLinkage(TokenType tokenType){
		if(tokenType.equals(TokenType.LINKAGE))
			return true;
		return false;
	}
	public static boolean isLinkage(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet){
			if(isLinkage(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isLinkage(TaggedToken taggedToken){
		return isLinkage(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isComma(TokenType tokenType){
		if(tokenType.equals(TokenType.COMMA))
			return true;
		return false;
	}
	public static boolean isComma(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet){
			if(isComma(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isComma(TaggedToken taggedToken){
		return isComma(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isTimeToken(TokenType tokenType){
		if(isYear(tokenType) || isSeason(tokenType) || isMonth(tokenType) || isWeek(tokenType) || isDate(tokenType) || isTime(tokenType) || isHalfDay(tokenType)
			|| isTimeZone(tokenType) || isEra(tokenType) || isTimeUnit(tokenType) || isDuration(tokenType) || isDayTime(tokenType) || isTimeline(tokenType)
			|| isHoliday(tokenType) || isPeriod(tokenType) || isDecade(tokenType))
				return true;
		return false;
	}
	public static boolean isTimeToken(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet)
			if(isTimeToken(tokenType))
				return true;
		return false;
	}
	public static boolean isTimeToken(TaggedToken taggedToken){
		return isTimeToken(taggedToken.getTokenTypeSet());
	}
}
