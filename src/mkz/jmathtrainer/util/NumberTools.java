package mkz.jmathtrainer.util;

import java.util.concurrent.ThreadLocalRandom;

public class NumberTools 
{
	static final char[] DOUBLE_PATTERN = {'0','1','2','3','4','5','6','7','8','9','.','-'};
	static final char DOUBLE_SEP = '.';
	
	public static boolean isDoubleNumber(String s)
	{
		if(s==null) return false;
		
		int sepCount=0;
		
		for(char c:s.toCharArray())
		{
			boolean rVal=false;
			
			for(char p:DOUBLE_PATTERN)
			{
				if(p==c) rVal=true;
			}
			
			if(c==DOUBLE_SEP) sepCount++;
			
			if(!rVal) return false;
		}
		
		return sepCount<=1;
	}
	
	public static int randomInteger(int min, int max)
	{
		return ThreadLocalRandom.current().nextInt((max - min) + 1) + min;
	}
	
	public static double randomDouble(double min, double max, int places)
	{
		return round(ThreadLocalRandom.current().nextDouble((max - min) + 1) + min,places);
	}
	
	// Original code:
	//
	// http://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
	public static double round(double value, int places) 
	{
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}
