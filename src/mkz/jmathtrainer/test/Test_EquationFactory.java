package mkz.jmathtrainer.test;

import mkz.jmathtrainer.core.config.GameConfig.DifficultyEnum;
import mkz.jmathtrainer.core.equation.Equation.OperationEnum;
import mkz.jmathtrainer.core.equation.EquationFactory;
import mkz.jmathtrainer.util.EquationTools;
import mkz.jmathtrainer.util.IO;

public class Test_EquationFactory 
{
	public static void main(String[] args)
	{
//		for(int i=0;i<1000;i++)
//		{
//			IO.SysOutD("Random int[0,100] = "+NumberTools.randomInteger(0, 100));
//			IO.SysOutD("Random double[0,100] = "+NumberTools.randomDouble(0, 100, 2));
//		}
		
		OperationEnum[] ops={OperationEnum.ADDITION,OperationEnum.DIVISION};
		Double[] vals={1.0,4.0,2.0};
		
		double result=EquationTools.solveEquation(ops, vals);
		
		IO.SysOutD("Result = "+result);
		
		EquationFactory.createArithmeticSequence(DifficultyEnum.MEDIUM, true, true);
		
	}
}
