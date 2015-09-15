/**
 * NumberTools.java
 * 
 * @author Aleistar Markoczy
 */
package mkz.jmathtrainer.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Static functions with numbers
 */
public class NumberTools 
{
	
	/** The Constant DOUBLE_PATTERN. */
	static final char[] DOUBLE_PATTERN = {'0','1','2','3','4','5','6','7','8','9','.','-'};
	
	/** The Constant DOUBLE_SEP. */
	static final char DOUBLE_SEP = '.';
	
	/**
	 * Checks if is double number.
	 *
	 * @param s the s
	 * @return true, if is double number
	 */
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
	
	/**
	 * Create Random integer.
	 *
	 * @param min the min
	 * @param max the max
	 * @return the int
	 */
	public static int randomInteger(int min, int max)
	{
		return ThreadLocalRandom.current().nextInt((max - min) + 1) + min;
	}
	
	/**
	 * Create Random double.
	 *
	 * @param min the min
	 * @param max the max
	 * @param places the number of decimal places
	 * @return the double
	 */
	public static double randomDouble(double min, double max, int places)
	{
		return round(ThreadLocalRandom.current().nextDouble((max - min) + 1) + min,places);
	}
	
	/**
	 * Round a number to n decimal places. Original code: 
	 * http://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
	 *
	 * @param value the value
	 * @param places the number of decimal places
	 * @return the result
	 */
	public static double round(double value, int places) 
	{
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}
