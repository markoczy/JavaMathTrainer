package mkz.jmathtrainer.util;

public class IO 
{
	private static int mErrorLevel=3; // TODO Error level -> settings

	private static final int LOG_FATAL=0;
	private static final int LOG_ERROR=1;
	private static final int LOG_WARN=2;
	private static final int LOG_DEBUG=3;
	private static final int LOG_VERBOSE=4;
	
	public static void SysOutV(String message)
	{
		SysOut(message, LOG_VERBOSE);
	}
	
	public static void SysOutD(String message)
	{
		SysOut(message, LOG_DEBUG);
	}
	
	public static void SysOutW(String message)
	{
		SysOut(message, LOG_WARN);
	}
	
	public static void SysOutE(String message)
	{
		SysOut(message, LOG_ERROR);
	}
	
	public static void SysOutF(String message)
	{
		SysOut(message, LOG_FATAL);
	}
	
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
