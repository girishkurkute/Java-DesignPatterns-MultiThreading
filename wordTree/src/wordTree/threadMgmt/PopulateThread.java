package wordTree.threadMgmt;

import wordTree.store.Results;
import wordTree.util.FileProcessor;
import wordTree.util.Node;
import wordTree.util.RedBlackTree;

public class PopulateThread implements Runnable
{
	private FileProcessor inputFileProc;
	private Results outputResult;
	private int threadNumber;
	
	/**
	 * Data member that holds the original tree
	 */
	private RedBlackTree tree_orig;
	
	public PopulateThread() 
	{
		
	}
	
	public PopulateThread(FileProcessor fileProcessor, Results results, int no) 
	{
		threadNumber = no;
		inputFileProc = fileProcessor;
		outputResult = results;	
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
			temp = line.split(" ");
			
			Node node_orig = null;
			for(int i = 0; i < temp.length; i++)
			{
				if(null != temp[i] && temp[i].compareTo(" ") != 0 && temp[i].compareTo("") != 0)
				{
					try
					{
						//check if node with that word string already exists
						node_orig = tree_orig.search(tree_orig.getRoot(), temp[i]);
						node_orig.setWordOccurances(node_orig.getWordOccurances() + 1);
					}
					/*catch (ArrayIndexOutOfBoundsException e) 
					{
						continue;
					}*/
					catch(IndexOutOfBoundsException | NullPointerException e)
					{
						//else create a new node
						node_orig = new Node();
						node_orig.setWord(temp[i]);
						node_orig.setWordOccurances(1);
					}
					
					tree_orig.insert(node_orig);
				}
			}
		}
		
		//close the open file in the end of reading
		inputFileProc.closeFile();
	}
}
