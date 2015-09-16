package mkz.jmathtrainer.core.config;

/**
 * The Class GameConfig.
 */
public class GameConfig 
{
	
	/** The addition enabled. */
	public boolean additionEnabled=true;
	
	/** The subtraction enabled. */
	public boolean subtractionEnabled=true;
	
	/** The multiplication enabled. */
	public boolean multiplicationEnabled=true;
	
	/** The division enabled. */
	public boolean divisionEnabled=true;
	
	/** The difficulty. */
	public DifficultyEnum difficulty=DifficultyEnum.MEDIUM;
	
	/** The time in seconds. */
	public int timeInSeconds=120;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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
	
	/**
	 * The Enum DifficultyEnum.
	 */
	public enum DifficultyEnum
	{
		
		/** The easy. */
		EASY,
		/** The medium. */
		MEDIUM,
		/** The hard. */
		HARD
	};
}
