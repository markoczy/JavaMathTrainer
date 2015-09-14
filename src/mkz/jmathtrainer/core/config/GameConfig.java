package mkz.jmathtrainer.core.config;

public class GameConfig 
{
	public boolean additionEnabled=true;
	public boolean subtractionEnabled=true;
	public boolean multiplicationEnabled=true;
	public boolean divisionEnabled=true;
	
	public DifficultyEnum difficulty=DifficultyEnum.MEDIUM;
	
	public int timeInSeconds=120;
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("## GameConfig: additionEnabled = ");
		sb.append(additionEnabled);
		sb.append(" # subtractionEnabled = ");
		sb.append(subtractionEnabled);
		sb.append(" # multiplicationEnabled = ");
		sb.append(multiplicationEnabled);
		sb.append(" # divisionEnabled = ");
		sb.append(divisionEnabled);
		sb.append(" # difficulty = ");
		sb.append(difficulty);
		sb.append(" # time = ");
		sb.append(timeInSeconds);
		sb.append("[s]");
		sb.append(" ##");
		
		return sb.toString();
	}
	
	public enum DifficultyEnum
	{
		EASY,MEDIUM,HARD
	};
}
