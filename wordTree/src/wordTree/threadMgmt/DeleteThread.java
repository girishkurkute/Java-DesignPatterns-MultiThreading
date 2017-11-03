package wordTree.threadMgmt;

import wordTree.store.Results;
import wordTree.util.MyLogger;
import wordTree.util.Node;
import wordTree.util.RedBlackTree;

/**
 * Class to delete the words specified from the tree using threads.
 * @author hloya, girish
 *
 */
public class DeleteThread implements Runnable
{
	/**
	 * Data Members
	 */
	private Results outputResult;
	private String delWord;

	/**
	 * Data member that holds the reference of original tree
	 */
	private RedBlackTree localTree;

	/**
	 * Default Constructor
	 */
	public DeleteThread() 
	{
		MyLogger.writeMessage("DeleteThread class default constructor was called", MyLogger.DebugLevel.CONSTRUCTOR);
	}

	/**
	 * Parameterized Constructor to initialize the local data members.
	 * @param deleteWord - the word to be deleted.
	 * @param results - instance of results class.
	 * @param tree - the tree containing words read from input file as nodes.
	 */
	public DeleteThread(String deleteWord, Results results, RedBlackTree tree) 
	{
		delWord = deleteWord;
		outputResult = results;
		localTree = tree;
		outputResult.writeToScreen("DeleteThread class parameterized constructor was called", MyLogger.DebugLevel.CONSTRUCTOR);
	}

	@Override
	public void run() 
	{
		outputResult.writeToScreen("DeleteThread class run() method was called", MyLogger.DebugLevel.THREAD_RUN);
		Node node_orig = null;
		try
		{
			synchronized (localTree)
			{
				//check if node with that word string exists
				node_orig = localTree.search(localTree.getRoot(), delWord);
				
				//Skip Deleting the word if it already has a count of 0.
				if(node_orig.getWordOccurances() != 0)
				{
					node_orig.setWordOccurances(node_orig.getWordOccurances() - 1);
					localTree.insert(node_orig);
				}
			}
		}
		catch(IndexOutOfBoundsException | NullPointerException e)
		{
			//System.err.println("Word Not Found or has count of 0, Skipping and Continuing with Next Word (if any)");
		}
	}
}