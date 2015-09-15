package mkz.jmathtrainer.core.equation;

import mkz.jmathtrainer.core.config.GameConfig.DifficultyEnum;
import mkz.jmathtrainer.util.EquationTools;

/**
 * The Class Equation.
 */
public class Equation {

	/** The equation string. */
	public String equation=null;
	
	/** The value. */
	public Double value=null;
	
	/** The difficulty. */
	public DifficultyEnum difficulty=DifficultyEnum.MEDIUM;
	
	/**
	 * Checks if is valid.
	 *
	 * @return true, if is valid
	 */
	boolean isValid()
	{
		boolean rVal=true;
		
		rVal&=equation!=null;
		rVal&=value!=null;
		
		if(rVal) rVal&=EquationTools.isEquation(equation);
		
		return rVal;
	}
	
	/**
	 * The Enum OperationEnum.
	 */
	public enum OperationEnum
	{
		/** The addition. */
		ADDITION,
		/** The subtraction. */
		SUBTRACTION,
		/** The multiplication. */
		MULTIPLICATION,
		/** The division. */
		DIVISION
	}
	
	
	
}
