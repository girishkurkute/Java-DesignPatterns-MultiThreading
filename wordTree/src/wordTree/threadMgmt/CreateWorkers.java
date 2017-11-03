package wordTree.threadMgmt;

import wordTree.store.Results;
import wordTree.util.FileProcessor;
import wordTree.util.MyLogger;
import wordTree.util.Node;
import wordTree.util.RedBlackTree;

/**
 * Class which:
 * <br> 
 * creates the threads for populating the tree and then deleting the words specified
 * <br>and<br>
 * gives count of words read. 
 * @author hloya, Girish
 *
 */
public class CreateWorkers
{
	/**
	 * Data Members
	 */
	private FileProcessor inputFileProc;
	private Results outputResult;
	private RedBlackTree commonTree;
	private int wordCount;
	private int distinctWordCount;
	private int characterCount;

	/**
	 * Default Constructor which initializes the tree
	 */
	public CreateWorkers()
	{
		commonTree = new RedBlackTree();
		MyLogger.writeMessage("CreateWorkers class default constructor was called", MyLogger.DebugLevel.CONSTRUCTOR);
	}

	/**
	 * Parameterized Constructor which takes 2 parameters and initializes local copies of them.
	 * @param fileProcessor - instance of FileProcessor class needed to read data from input file.
	 * @param results - instance of Results class needed to store the result into an output file/display the result on the screen.
	 */
	public CreateWorkers(FileProcessor fileProcessor, Results results)
	{
		//calls the default constructor
		this();
		inputFileProc = fileProcessor;
		outputResult = results;
		outputResult.writeToScreen("CreateWorkers class parameterized constructor was called", MyLogger.DebugLevel.CONSTRUCTOR);
	}

	/**
	 * Creates threads responsible for filling up the tree with words read from file as nodes.
	 * @param numberOfThreads - specifies the number of threads that need to be created
	 */
	public void startPopulateWorkers(int numberOfThreads)
	{
		int i = 1;
		while(i <= numberOfThreads)
		{
			PopulateThread popT = new PopulateThread(inputFileProc, outputResult, commonTree);
			Thread popWorker = new Thread(popT, "popWorker"+i);
			popWorker.start();
			i++;
		}

		callJoinMethod();
	}

	/**
	 * Creates threads responsible for deleting the words passed as parameters from the tree.
	 * @param numberOfThreads - specifies the number of threads that need to be created
	 * @param deleteWords - contains the words that need to be deleted
	 */
	public void startDeleteWorkers(int numberOfThreads, String deleteWords[])
	{
		int i = 1;
		while(i <= numberOfThreads)
		{
			DeleteThread delT = new DeleteThread(deleteWords[i-1], outputResult, commonTree);
			Thread delWorker = new Thread(delT, "delWorker"+i);
			delWorker.start();
			i++;
		}

		callJoinMethod();
	}
	
	/**
	 * Join Method that uses Thread.Join() internally to make the threads wait before continuing their execution.
	 */
	private void callJoinMethod() 
	{
		ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
		Thread[] threadsList = new Thread[currentGroup.activeCount()];
		currentGroup.enumerate(threadsList);
		
		for(int x = 1; x < threadsList.length; x++)
		{
			Thread t = threadsList[x];
			try 
			{
				t.join();
			} 
			catch (InterruptedException e) 
			{
				System.err.println("Thread Interrupted");
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
	
	/**
	 * Method that fetches the counts - word count, distinct words count and character count of the input file and then stores the count in a file.
	 */
	public void getCounts()
	{
		wordCount = 0;
		distinctWordCount = 0;
		characterCount = 0;
		calculateCountsRecursively(commonTree.getRoot());
		outputResult.storeNewResult("The total number of words: " + wordCount);
		outputResult.storeNewResult("The total number of characters: " + characterCount);
		outputResult.storeNewResult("The total number of distinct words: "+ distinctWordCount);
		outputResult.writeScheduleToFile();
	}
	
	/**
	 * Method used by getCounts() to traverse the tree recursively and calculate the required counts.
	 * @param root - Root Node of the Tree
	 */
	private void calculateCountsRecursively(Node root)
	{
		if(root != null)
		{
			calculateCountsRecursively(root.getLeft()); 
			wordCount += root.getWordOccurances();
			if(root.getWordOccurances() != 0)
				distinctWordCount += 1;
			characterCount += root.getWordOccurances()*root.getWord().length();
			calculateCountsRecursively(root.getRight());
		}
	}
}
