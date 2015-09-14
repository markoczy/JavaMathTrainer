package mkz.jmathtrainer.core.equation;

import java.util.ArrayList;

import mkz.jmathtrainer.core.config.GameConfig;
import mkz.jmathtrainer.core.config.GameConfig.DifficultyEnum;
import mkz.jmathtrainer.core.equation.Equation.OperationEnum;
import mkz.jmathtrainer.util.EquationTools;
import mkz.jmathtrainer.util.IO;
import mkz.jmathtrainer.util.NumberTools;

public class EquationFactory 
{
	public static Equation createEquation(GameConfig aConfig)
	{
		switch(aConfig.difficulty)
		{
		case EASY:
			return createEasyEquation(aConfig.additionEnabled,aConfig.subtractionEnabled,
					aConfig.multiplicationEnabled,aConfig.divisionEnabled);
		case MEDIUM:
			return createMediumEquation(aConfig.additionEnabled,aConfig.subtractionEnabled,
					aConfig.multiplicationEnabled,aConfig.divisionEnabled);
		case HARD:
			return createHardEquation(aConfig.additionEnabled,aConfig.subtractionEnabled,
					aConfig.multiplicationEnabled,aConfig.divisionEnabled);
		default:
			return null;
				
		}
		
	}
	
	private static Equation createEasyEquation(boolean additionEnabled,boolean subtractionEnabled,
			boolean multiplicationEnabled,boolean divisionEnabled)
	{
		boolean createSequence=additionEnabled || subtractionEnabled;
		boolean createDouble=multiplicationEnabled || divisionEnabled;
		
		if(!createSequence || !createDouble)
		{
			IO.SysOutE("Unable to create equation: no operations selected!");
		}
		
		Equation rVal=null;

		while(rVal==null)
		{
			int choice = NumberTools.randomInteger(0, 1);
			
			if(choice==0)
			{
				if(createSequence)
				{
					rVal=createArithmeticSequence(DifficultyEnum.EASY,additionEnabled,subtractionEnabled);
				}
			}
			else
			{
				if(createDouble)
				{
					rVal=createGeometricDouble(DifficultyEnum.EASY,multiplicationEnabled,divisionEnabled);
				}
			}
		}

		return rVal;
	}
	
	private static Equation createMediumEquation(boolean additionEnabled,boolean subtractionEnabled,
			boolean multiplicationEnabled,boolean divisionEnabled)
	{
		boolean createSequence=additionEnabled || subtractionEnabled;
		boolean createDouble=multiplicationEnabled || divisionEnabled;
		
		if(!createSequence || !createDouble)
		{
			IO.SysOutE("Unable to create equation: no operations selected!");
		}
		
		Equation rVal=null;

		while(rVal==null)
		{
			int choice = NumberTools.randomInteger(0, 1);
			
			if(choice==0)
			{
				if(createSequence)
				{
					rVal=createArithmeticSequence(DifficultyEnum.MEDIUM,additionEnabled,subtractionEnabled);
				}
			}
			else
			{
				if(createDouble)
				{
					rVal=createGeometricDouble(DifficultyEnum.MEDIUM,multiplicationEnabled,divisionEnabled);
				}
			}
		}

		return rVal;
	}
	
	// TODO Not implemented yet
	private static Equation createHardEquation(boolean additionEnabled,boolean subtractionEnabled,
			boolean multiplicationEnabled,boolean divisionEnabled)
	{
		Equation rVal;
		rVal=createGeometricDouble(DifficultyEnum.HARD,multiplicationEnabled,divisionEnabled);
		return rVal;
	}
	
	// XXX -> private
	public static Equation createArithmeticSequence(DifficultyEnum difficulty,boolean additionEnabled,boolean subtractionEnabled)
	{
		Equation rVal=new Equation();
		
		ArrayList<OperationEnum> ops = new ArrayList<OperationEnum>();
		
		if(additionEnabled) ops.add(OperationEnum.ADDITION);
		if(subtractionEnabled) ops.add(OperationEnum.SUBTRACTION);
		
		switch(difficulty)
		{
		case EASY:
		{
			int number1=NumberTools.randomInteger(20, 100);
			int number2=NumberTools.randomInteger(5, number1);
			
			OperationEnum op=EquationTools.randomOperation(ops);
			
			OperationEnum[] operations={op};
			Double[] values={(double)number1,(double)number2};
			
			rVal.value=EquationTools.solveEquation(operations, values); 
			rVal.equation=EquationTools.getEquationString(operations, values,true);
			
			IO.SysOutD("Equation string: "+EquationTools.getEquationString(operations, values,true));
			break;
		}
			
		case MEDIUM:
		{
			int number1=NumberTools.randomInteger(5, 100);
			int number2=NumberTools.randomInteger(5, 100);
			int number3=NumberTools.randomInteger(5, 100);
		
			OperationEnum op=EquationTools.randomOperation(ops);
			OperationEnum op2=EquationTools.randomOperation(ops);
			
			OperationEnum[] operations={op,op2};
			Double[] values={(double)number1,(double)number2,(double)number3};
			
			rVal.value=EquationTools.solveEquation(operations, values); 
			rVal.equation=EquationTools.getEquationString(operations, values,true);
			
			IO.SysOutD("Equation string: "+EquationTools.getEquationString(operations, values,true));
			IO.SysOutD("Result = "+rVal.value);
			break;
		}
		case HARD:
		}
		
		return rVal;
	}
	
	// XXX -> private
	public static Equation createGeometricDouble(DifficultyEnum difficulty,boolean multiplicationEnabled,boolean divisionEnabled)
	{
		Equation rVal=new Equation();
		
		ArrayList<OperationEnum> ops = new ArrayList<OperationEnum>();
		
		if(multiplicationEnabled) ops.add(OperationEnum.MULTIPLICATION);
		if(divisionEnabled) ops.add(OperationEnum.DIVISION);
		
		// XXX could be DRYer...
		switch(difficulty)
		{
		case EASY:
		{
			int number1=NumberTools.randomInteger(2, 12);
			int number2=NumberTools.randomInteger(2, 12);
			
			OperationEnum op=EquationTools.randomOperation(ops);
			
			if(op==OperationEnum.DIVISION)
			{
				OperationEnum[] operations={OperationEnum.MULTIPLICATION};
				Double[] values={(double)number1,(double)number2};
				rVal.value=(double)number2;
//				IO.SysOutD("value = "+rVal.value);
				number2=(int)EquationTools.solveEquation(operations, values); 
				
				values=new Double[]{(double)number2,(double)number1};
				operations=new OperationEnum[]{op};
				rVal.equation=EquationTools.getEquationString(operations, values, true);
			}
			else
			{
				OperationEnum[] operations={op};
				Double[] values={(double)number1,(double)number2};
				
				rVal.value=EquationTools.solveEquation(operations, values);
				rVal.equation=EquationTools.getEquationString(operations, values, true);
			}

			break;
		}
			
		case MEDIUM:
		{
			int number1=NumberTools.randomInteger(2, 100);
			int number2=NumberTools.randomInteger(2, 100);
			
			OperationEnum op=EquationTools.randomOperation(ops);
			
			if(op==OperationEnum.DIVISION)
			{
				OperationEnum[] operations={OperationEnum.MULTIPLICATION};
				Double[] values={(double)number1,(double)number2};
				rVal.value=(double)number2;
				number2=(int)EquationTools.solveEquation(operations, values); 
				
				values=new Double[]{(double)number2,(double)number1};
				operations=new OperationEnum[]{op};
				rVal.equation=EquationTools.getEquationString(operations, values, true);
			}
			else
			{
				OperationEnum[] operations={op};
				Double[] values={(double)number1,(double)number2};
				
				rVal.value=EquationTools.solveEquation(operations, values);
				rVal.equation=EquationTools.getEquationString(operations, values, true);
			}
			break;
		}
		case HARD:
			double number1=NumberTools.randomDouble(2, 100, 2);
			double number2=NumberTools.randomDouble(2, 100, 2);
			
			OperationEnum op=EquationTools.randomOperation(ops);
			
			if(op==OperationEnum.DIVISION)
			{
				OperationEnum[] operations={OperationEnum.MULTIPLICATION};
				Double[] values={number1,number2};
				rVal.value=number2;
				number2=EquationTools.solveEquation(operations, values); 
				
				values=new Double[]{number2,number1};
				operations=new OperationEnum[]{op};
				rVal.equation=EquationTools.getEquationString(operations, values, false);
			}
			else
			{
				OperationEnum[] operations={op};
				Double[] values={number1,number2};
				
				rVal.value=EquationTools.solveEquation(operations, values);
				rVal.equation=EquationTools.getEquationString(operations, values, false);
			}
			break;
		}
		
		return rVal;
	}
}
