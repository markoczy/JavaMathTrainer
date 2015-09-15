package mkz.jmathtrainer.util;

import java.util.ArrayList;
import java.util.Arrays;

import mkz.jmathtrainer.core.equation.Equation.OperationEnum;

/**
 * The Class EquationTools.
 */
public class EquationTools 
{
	
	/** The Constant EQUATION_PATTERN. */
	static final char[] EQUATION_PATTERN = {'0','1','2','3','4','5','6','7','8','9','.','+','-','*','/',' '};
	
	/**
	 * Checks if a string is an equation.
	 * (TODO Could do more but OK for now...)
	 * 
	 * @param s the s
	 * @return true, if is equation
	 */
	//
	public static boolean isEquation(String s)
	{
		if(s==null) return false;
		
		for(char c:s.toCharArray())
		{
			boolean rVal=false;
			
			for(char p:EQUATION_PATTERN)
			{
				if(p==c) rVal=true;
			}
			
			if(!rVal) return false;
		}
		
		return true;
	}
	
	/**
	 * Solve a equation.
	 *
	 * @param operations the operations
	 * @param values the values
	 * @return the double
	 */
	public static double solveEquation(OperationEnum[] operations,Double[] values)
	{
		try
		{
			ArrayList<OperationEnum> lOperations=new ArrayList<OperationEnum>(Arrays.asList(operations));
			ArrayList<Double> lValues=new ArrayList<Double>(Arrays.asList(values));
	
			int first=0;
			while((first=getFirstOf(lOperations, OperationEnum.DIVISION))!=-1)
			{
				double result=solveOperation(lOperations.get(first),lValues.get(first),lValues.get(first+1));
				
				lValues.set(first, result);
				lValues.remove(first+1);
				
				lOperations.remove(first);
			}
			while((first=getFirstOf(lOperations, OperationEnum.MULTIPLICATION))!=-1)
			{
				double result=solveOperation(lOperations.get(first),lValues.get(first),lValues.get(first+1));
				
				lValues.set(first, result);
				lValues.remove(first+1);
				
				lOperations.remove(first);
			}
			while((first=getFirstOf(lOperations, OperationEnum.SUBTRACTION))!=-1)
			{
				double result=solveOperation(lOperations.get(first),lValues.get(first),lValues.get(first+1));
				
				lValues.set(first, result);
				lValues.remove(first+1);
				
				lOperations.remove(first);
			}
			while((first=getFirstOf(lOperations, OperationEnum.ADDITION))!=-1)
			{
				double result=solveOperation(lOperations.get(first),lValues.get(first),lValues.get(first+1));
				
				lValues.set(first, result);
				lValues.remove(first+1);
				
				lOperations.remove(first);
			}
			
			return lValues.get(0);
		
		}
		catch(Exception e)
		{
			IO.SysOutE("Unable to solve equation: "+e.getMessage());
			return 0;
		}
		
	}
	
	
	/**
	 * Wrapper for {@link#getEquationString(OperationEnum[] operations,Double[] values, boolean intOnly)}
	 * shows the numbers as double values (round must be done before)
	 *
	 * @param operations the operations
	 * @param values the values
	 * @return the equation string
	 */
	public static String getEquationString(OperationEnum[] operations,Double[] values)
	{
		return getEquationString(operations,values, false);
	}
	
	/**
	 * Gets the equation string.
	 *
	 * @param operations the operations
	 * @param values the values
	 * @param intOnly the int only
	 * @return the equation string
	 */
	public static String getEquationString(OperationEnum[] operations,Double[] values, boolean intOnly)
	{
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<operations.length;i++)
		{
			if(intOnly)
			{
				sb.append((int)values[i].intValue());
			}
			else
			{
				sb.append(values[i]);
			}
			sb.append(" ");
			sb.append(getOperationString(operations[i]));
			sb.append(" ");
		}
		
		
		if(intOnly)
		{
			sb.append((int)values[values.length-1].intValue());
		}
		else
		{
			sb.append(values[values.length-1]);
		}
		
		return sb.toString();
		
	}
	
	/**
	 * Gets the operation string.
	 *
	 * @param operation the operation
	 * @return the operation string
	 */
	public static String getOperationString(OperationEnum operation)
	{
		switch(operation)
		{
		case ADDITION:
			return "+";
		case SUBTRACTION:
			return "-";
		case MULTIPLICATION:
			return "*";
		case DIVISION:
			return "/";
		default:
			IO.SysOutW("Unhandled operation:" + operation);
			return "";
		}
	}
	
	/**
	 * Solves a operation.
	 *
	 * @param operation the operation
	 * @param v1 the v1
	 * @param v2 the v2
	 * @return the double
	 */
	private static double solveOperation(OperationEnum operation, double v1, double v2)
	{
		switch(operation)
		{
		case ADDITION:
			return v1+v2;
		case SUBTRACTION:
			return v1-v2;
		case MULTIPLICATION:
			return v1*v2;
		case DIVISION:
			return v1/v2;
		default:
			IO.SysOutW("Unhandled operation:" + operation);
			return 0;
		}
	}
	
	/**
	 * Gets the first operation by type.
	 *
	 * @param vec the vec
	 * @param op the op
	 * @return the first of
	 */
	static int getFirstOf(ArrayList<OperationEnum> vec, OperationEnum op)
	{
		IO.SysOutV("Try get first of, op is: "+op);
		
		if (vec.size() < 1)
		{
			IO.SysOutV("No more operations");
			return -1;
		}
	
		for (int i = 0; i < vec.size(); i++)
		{
			IO.SysOutV("it="+i+", operator = " +vec.get(i));

			if (vec.get(i) == op)
			{
				IO.SysOutV("Operator match");
				return i;
			}
	
		}
	
		IO.SysOutV("No operator match");
		return -1;
	}
	
	/**
	 * Create random operation.
	 *
	 * @param validOps the valid ops
	 * @return the operation enum
	 */
	public static OperationEnum randomOperation(ArrayList<OperationEnum> validOps)
	{
		int op=NumberTools.randomInteger(0, validOps.size()-1);
		
		return validOps.get(op);
		
	}
	
}
