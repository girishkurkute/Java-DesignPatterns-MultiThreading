package wordTree.threadMgmt;

import wordTree.store.Results;
import wordTree.util.FileProcessor;
import wordTree.util.RedBlackTree;

public class CreateWorkers
{
	private FileProcessor inputFileProc;
	private Results outputResult;
	private RedBlackTree commonTree;

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

	public void startDeleteWorkers(int numberOfThreads)
	{
		int i = 1;
		while(i <= numberOfThreads)
		{
			DeleteThread delT = new DeleteThread(inputFileProc, outputResult, commonTree);
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

}
