package wordTree.threadMgmt;

import wordTree.store.Results;
import wordTree.util.FileProcessor;
import wordTree.util.Node;
import wordTree.util.RedBlackTree;

public class CreateWorkers
{
	private FileProcessor inputFileProc;
	private Results outputResult;
	private RedBlackTree commonTree;
	private int wordCount;
	private int distinctWordCount;
	private int characterCount;

	public CreateWorkers()
	{
		commonTree = new RedBlackTree();
	}

	public CreateWorkers(FileProcessor fileProcessor, Results results)
	{
		this();
		inputFileProc = fileProcessor;
		outputResult = results;
	}

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
