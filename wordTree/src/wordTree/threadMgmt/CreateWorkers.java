package wordTree.threadMgmt;

import wordTree.store.Results;
import wordTree.util.FileProcessor;

public class CreateWorkers
{
	private FileProcessor inputFileProc;
	private Results outputResult;
	
	public CreateWorkers()
	{
		
	}
	
	public CreateWorkers(FileProcessor fileProcessor, Results results)
	{
		inputFileProc = fileProcessor;
		outputResult = results;
	}
	
	public void startPopulateWorkers(int numberOfThreads)
	{
		PopulateThread pop1 = new PopulateThread(inputFileProc, outputResult, 1);
		Thread worker1 = new Thread(pop1, "worker1");
		worker1.start();
		
		PopulateThread pop2 = new PopulateThread(inputFileProc, outputResult, 2);
		Thread worker2 = new Thread(pop2, "worker2");
		worker2.start();
		
		PopulateThread pop3 = new PopulateThread(inputFileProc, outputResult, 3);
		Thread worker3 = new Thread(pop3, "worker3");
		worker3.start();
	}
	
	public void startDeleteWorkers(int numberOfThreads)
	{
		
	}

}
