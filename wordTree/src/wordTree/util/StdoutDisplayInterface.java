package wordTree.util;

import wordTree.util.MyLogger.DebugLevel;

/**
 * Interface that allows to print data on the Standard Output
 * @author hloya
 *
 */
public interface StdoutDisplayInterface 
{
	/**
	 * Method that should be overriden to write to standard output
	 * @param s - contains the string that will be written to standard output
	 * @param dLvl - specifies the debug level for which the message has to be printed
	 */
	public void writeToScreen(String s, DebugLevel dLvl);

}
