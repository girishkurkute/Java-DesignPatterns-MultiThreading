package wordTree.driver;

import wordTree.store.Results;
import wordTree.threadMgmt.CreateWorkers;
import wordTree.util.FileProcessor;
import wordTree.util.MyLogger;

/**
 * Class containing the main method which is the starting point of code execution
 * @author hloya
 *
 */
public class Driver 
{
	/**
	 * Main method responsible for creating the tree and then calling the delete function
	 * @param args - Input given from command line which contains location of Input file, Delete file, and the Output Files for the 3 trees
	 */
	public static void main(String[] args) 
	{
		String inputFilePath = "";
		String outputFilePath = "";
		int NUM_THREADS = 0;
		String[] deleteWords;
		int debugVal = -1;

		if (args.length > 3)
		{
			inputFilePath = args[0];
			outputFilePath	= args[1];

			if(args[2].matches("[1-3]"))
			{
				NUM_THREADS = Integer.parseInt(args[2]);
			}
			else
			{
				System.err.println("The third parameter should be a digit in range of 1-3 specifying the number of threads desired");
				System.exit(1);
			}

			deleteWords = new String[NUM_THREADS];

			//not proper check below, need to work on it more to make it dynamic
			if (args.length == 4 + NUM_THREADS)
			{
				for (int i = 0; i < NUM_THREADS; i++)
				{
					if(!args[i+3].matches("[a-zA-Z]+"))
					{
						System.err.println("The number of delete words are not equal to NUM_THREADS specified");
						System.exit(1);	
					}
					
					deleteWords[i] = args[i+3];
				}
				/*for (int i = 0; i < NUM_THREADS; i++)
				{
					deleteWords[i] = args[i+3];
				}*/

				if(args[3+NUM_THREADS].matches("[0-4]"))
				{
					debugVal = Integer.parseInt(args[3+NUM_THREADS]);
					MyLogger.setDebugValue(debugVal);
				}
				else
				{
					System.err.println("The last parameter should be a digit in range of 0-4 specifying debug level desired");
					System.exit(1);
				}

				FileProcessor fileProc = new FileProcessor(inputFilePath);
				Results result = new Results(outputFilePath);
				CreateWorkers workers = new CreateWorkers(fileProc, result);
				workers.startPopulateWorkers(NUM_THREADS);
			}
			else
			{
				System.err.println("Invalid number of arguments, please recheck");
				System.exit(1);
			}
		}
		else
		{
			System.err.println("Invalid number of arguments, please recheck");
			System.exit(1);
		}

	}
}