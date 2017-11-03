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
	 * Main method responsible for giving the count of the words in input file using threads
	 * @param args - Input given from command line which contains location of Input file, Output file, Number of Threads, Words to be Deleted and Debug Level 
	 */
	public static void main(String[] args) 
	{
		String inputFilePath = "";
		String outputFilePath = "";
		int NUM_THREADS = 0;
		String[] deleteWords;
		int debugVal = -1;

		if (5 == args.length)
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

			String[] tempDelWords = args[3].split("\\s+");

			if(tempDelWords.length == NUM_THREADS)
			{
				for (int i = 0; i < NUM_THREADS; i++)
				{
					/*if(!tempDelWords[i].matches("[a-zA-Z]+"))
					{
						System.err.println("The delete words are not just alphabets");
						System.exit(1);	
					}*/

					deleteWords[i] = tempDelWords[i];
				}
			}
			else
			{
				System.err.println("The delete words are not equal to NUM_THREADS specified");
				System.exit(1);
			}

			if(args[4].matches("[0-4]"))
			{
				debugVal = Integer.parseInt(args[4]);
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
			workers.startDeleteWorkers(NUM_THREADS, deleteWords);
			workers.getCounts();
		}
		else
		{
			System.err.println("Invalid number of arguments, please recheck");
			System.exit(1);
		}

	}
}