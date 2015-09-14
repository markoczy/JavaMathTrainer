package mkz.jmathtrainer.core.equation;

import mkz.jmathtrainer.core.config.GameConfig.DifficultyEnum;
import mkz.jmathtrainer.util.EquationTools;

public class Equation {

	public String equation=null;
	public Double value=null;
	public DifficultyEnum difficulty=DifficultyEnum.MEDIUM;
	
	boolean isValid()
	{
		boolean rVal=true;
		
		rVal&=equation!=null;
		rVal&=value!=null;
		
		if(rVal) rVal&=EquationTools.isEquation(equation);
		
		return rVal;
	}
	
	public enum OperationEnum
	{
		ADDITION,SUBTRACTION,MULTIPLICATION,DIVISION
	}
	
	
	
}
