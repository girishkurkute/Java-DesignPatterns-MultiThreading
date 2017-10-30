package wordTree.threadMgmt;

import wordTree.store.Results;
import wordTree.util.FileProcessor;
import wordTree.util.RedBlackTree;

public class DeleteThread implements Runnable
{
	private FileProcessor inputFileProc;
	private Results outputResult;
	
	/**
	 * Data member that holds the original tree
	 */
	private RedBlackTree localTree;
	
	public DeleteThread() 
	{
	}
	
	public DeleteThread(FileProcessor fileProcessor, Results results, RedBlackTree tree) 
	{
		this();
		inputFileProc = fileProcessor;
		outputResult = results;
		localTree = tree;
	}

	@Override
	public void run() 
	{
		
	}

}
