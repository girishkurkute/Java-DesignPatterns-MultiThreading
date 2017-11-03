package wordTree.threadMgmt;

import wordTree.store.Results;
import wordTree.util.FileProcessor;
import wordTree.util.Node;
import wordTree.util.RedBlackTree;

/**
 * Class to populate the tree with the words read from the input file using threads.
 * @author hloya, girish
 *
 */
public class PopulateThread implements Runnable
{
	/**
	 * Data Members
	 */
	private FileProcessor inputFileProc;
	@SuppressWarnings("unused")
	private Results outputResult;

	/**
	 * Data member that holds the reference of the original tree
	 */
	private RedBlackTree localTree;

	/**
	 * Default Constructor
	 */
	public PopulateThread() 
	{
	}

	/**
	 * Parameterized Constructor to initialize the local data members.
	 * @param fileProcessor - instance of FileProcessor Class to read data from input file.
	 * @param results - instance of results class.
	 * @param tree - the tree containing words read from input file as nodes.
	 */
	public PopulateThread(FileProcessor fileProcessor, Results results, RedBlackTree tree) 
	{
		this();
		inputFileProc = fileProcessor;
		outputResult = results;
		localTree = tree;
	}

	@Override
	public void run() 
	{
		String line = "";
		String temp[];

		while((line = inputFileProc.readLine("")) != null)
		{
			//remove leading or trailing whitespaces if any
			line = line.trim();
			temp = line.split("\\s+");

			Node node_orig = null;
			for(int i = 0; i < temp.length; i++)
			{
				if(null != temp[i] && temp[i].compareTo(" ") != 0 && temp[i].compareTo("") != 0)
				{
					synchronized (localTree)
					{
						try
						{
							//check if node with that word string already exists
							node_orig = localTree.search(localTree.getRoot(), temp[i]);
							node_orig.setWordOccurances(node_orig.getWordOccurances() + 1);
						}
						catch(IndexOutOfBoundsException | NullPointerException e)
						{
							//else create a new node
							node_orig = new Node();
							node_orig.setWord(temp[i]);
							node_orig.setWordOccurances(1);
						}
						localTree.insert(node_orig);
					}
				}
			}
		}
	}
}
