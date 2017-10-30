package wordTree.threadMgmt;

import wordTree.store.Results;
import wordTree.util.Node;
import wordTree.util.RedBlackTree;

public class DeleteThread implements Runnable
{
	@SuppressWarnings("unused")
	private Results outputResult;
	private String delWord;

	/**
	 * Data member that holds the original tree
	 */
	private RedBlackTree localTree;

	public DeleteThread() 
	{
	}

	public DeleteThread(String deleteWord, Results results, RedBlackTree tree) 
	{
		this();
		delWord = deleteWord;
		outputResult = results;
		localTree = tree;


	}

	@Override
	public void run() 
	{
		Node node_orig = null;
		try
		{
			synchronized (localTree)
			{
				//check if node with that word string exists
				node_orig = localTree.search(localTree.getRoot(), delWord);
				if(node_orig.getWordOccurances() != 0)
				{
					node_orig.setWordOccurances(node_orig.getWordOccurances() - 1);
					localTree.insert(node_orig);
				}
			}
		}
		catch(IndexOutOfBoundsException | NullPointerException e)
		{
			System.err.println("PHATA");
		}

	}

}
