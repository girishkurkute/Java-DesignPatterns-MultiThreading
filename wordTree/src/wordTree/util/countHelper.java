package wordTree.util;

public class countHelper 
{
	/**
	 * Data Members
	 */
	private int wordCount;
	private int distinctWordCount;
	private int characterCount;
	
	/**
	 * Default Constructor
	 */
	public countHelper() 
	{
		MyLogger.writeMessage("countHelper class default constructor was called", MyLogger.DebugLevel.CONSTRUCTOR);
	}
	
	/**
	 * Method that fetches the counts - word count, distinct words count and character count of the input file.
	 * @param tree - tree with words read from the file.
	 * @return an array with word count, character count and distinct word count.
	 */
	public int[] getCounts(RedBlackTree tree)
	{
		wordCount = 0;
		distinctWordCount = 0;
		characterCount = 0;
		calculateCountsRecursively(tree.getRoot());
		int[] count = new int[3];
		count[0] = wordCount;
		count[1] = characterCount;
		count[2] = distinctWordCount;
		
		return count;
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
