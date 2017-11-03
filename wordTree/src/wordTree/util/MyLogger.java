package wordTree.util;

public class MyLogger
{

	/*DEBUG_VALUE=4 [Print to stdout everytime a constructor is called]
      DEBUG_VALUE=3 [Print to stdout everytime a thread's run() method is called]
      DEBUG_VALUE=2 [YOU DECIDE and explain in README.txt]
      DEBUG_VALUE=1 [YOU DECIDE and explain in README.txt]
      DEBUG_VALUE=0 [No output should be printed from the application, except the line "The average preference value is X.Y"]
	 */

	public static enum DebugLevel {RELEASE, TBD_1, TBD_2, THREAD_RUN, CONSTRUCTOR};

	private static DebugLevel debugLevel;


	/**
	 * Set the debug level allowing only that level messages to be printed.
	 * @param levelIn - integer value that contains the debugger level
	 */
	public static void setDebugValue (int levelIn) 
	{
		switch (levelIn) 
		{
		case 4: debugLevel = DebugLevel.CONSTRUCTOR; break;
		case 3: debugLevel = DebugLevel.THREAD_RUN; break;
		case 2: debugLevel = DebugLevel.TBD_2; break;
		case 1: debugLevel = DebugLevel.TBD_1; break;
		case 0: debugLevel = DebugLevel.RELEASE; break;
		}
	}

	public static void setDebugValue (DebugLevel levelIn) 
	{
		debugLevel = levelIn;
	}

	// @return None
	public static void writeMessage (String  message, DebugLevel levelIn ) 
	{
		if (levelIn == debugLevel)
			System.out.println(message);
	}

	/**
	 * @return String
	 */
	public String toString() 
	{
		return "Debug Level is " + debugLevel;
	}
}