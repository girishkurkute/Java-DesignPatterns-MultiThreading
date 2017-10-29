package wordTree.util;

/**
 * Interface that allows to print data on the Standard Output
 * @author hloya
 *
 */
public interface StdoutDisplayInterface 
{
	/**
	 *  Method that should be overriden to write to standard output
	 * @param s - contains the string that will be written to standard output
	 */
	public void writeToStdout(String s);

}
