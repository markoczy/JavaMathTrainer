package mkz.jmathtrainer.util;

/**
 * Static methods to output debug messages.
 */
public class IO 
{
	
	/** The m error level. */
	private static int mErrorLevel=3; // TODO Error level -> settings

	/** The Constant LOG_FATAL. */
	private static final int LOG_FATAL=0;
	
	/** The Constant LOG_ERROR. */
	private static final int LOG_ERROR=1;
	
	/** The Constant LOG_WARN. */
	private static final int LOG_WARN=2;
	
	/** The Constant LOG_DEBUG. */
	private static final int LOG_DEBUG=3;
	
	/** The Constant LOG_VERBOSE. */
	private static final int LOG_VERBOSE=4;
	
	/**
	 * System out "verbose"
	 *
	 * @param message the message
	 */
	public static void SysOutV(String message)
	{
		SysOut(message, LOG_VERBOSE);
	}
	
	/**
	 * System out "debug"
	 *
	 * @param message the message
	 */
	public static void SysOutD(String message)
	{
		SysOut(message, LOG_DEBUG);
	}
	
	/**
	 * System out "warning"
	 *
	 * @param message the message
	 */
	public static void SysOutW(String message)
	{
		SysOut(message, LOG_WARN);
	}
	
	/**
	 * System out "error"
	 *
	 * @param message the message
	 */
	public static void SysOutE(String message)
	{
		SysOut(message, LOG_ERROR);
	}
	
	/**
	 * System out "fatal error"
	 *
	 * @param message the message
	 */
	public static void SysOutF(String message)
	{
		SysOut(message, LOG_FATAL);
	}
	
	/**
	 * System out - Shows current method tree
	 * and a debug message (if provided that the Application
	 * error level is greater or equal to the message error level)
	 *
	 * @param message the message
	 * @param errorLevel the error level
	 */
	private static void SysOut(String message, int errorLevel)
	{
		if(mErrorLevel<errorLevel)
		{
			return;
		}
		
		StringBuilder msg = new StringBuilder();
		
		msg.append(getErrorLvTxt(errorLevel));
		msg.append(".");
		
		StackTraceElement[] el=Thread.currentThread().getStackTrace();

		if(el.length>3)
		{
			StackTraceElement e3=el[3];
			
			msg.append(e3.getClassName());
			msg.append("::");
			msg.append(e3.getMethodName());
			msg.append(": ");
			msg.append(message);
		}
		
		System.out.println(msg.toString());
	}
	
	/**
	 * Gets the error level text.
	 *
	 * @param level the level
	 * @return the error level text
	 */
	private static String getErrorLvTxt(int level)
	{
		switch(level)
		{
		case(LOG_VERBOSE):
			return "Verbose"; 
		case(LOG_DEBUG):
			return "Debug"; 
		case(LOG_WARN):
			return "WARNING"; 
		case(LOG_ERROR):
			return "ERROR"; 
		case(LOG_FATAL):
			return "!!FATAL!!"; 
		default:
			return "<UNKNOWN>"; 
		}
	}
	
}
