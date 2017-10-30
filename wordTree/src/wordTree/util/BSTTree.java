package wordTree.util;

import wordTree.store.Results;

/**
 * Implements a binary search tree where elements smaller than root are on left
 * and greater than root are on right.
 * <br>
 * This tree does not support nodes with duplicate values.
 * @author hloya
 *
 */
public class BSTTree 
{
	/**
	 * Data member which denotes the root of the tree
	 */
	private Node root;

	/**
	 * Default Constructor which initializes the root
	 */
	public BSTTree()
	{
		root = null;
	}

	/**
	 * Method to find a desired node
	 * @param recursionRoot - contains node starting with root and gets refined to a particular node that you are trying to find through recursive calls
	 * @param bNumber - is the bNumber whose node is to be found
	 * @return node that matches the bNumber passed as parameter
	 */
	public Node search(Node recursionRoot, String word)
	{
		if(null == recursionRoot || 0 == word.compareTo(recursionRoot.getWord()))
		{
			return new Node(recursionRoot);
		}
		
		//System.out.println("Test Word: " + word);
		//System.out.println("Test Root's Word: " + recursionRoot.getWord());
		
		if(word.compareTo(recursionRoot.getWord()) < 0)
		{
			return search(recursionRoot.getLeft(), word);
		}
		
		return search(recursionRoot.getRight(), word);
	}

	/**
	 * Inserts the node passed as parameter into the Caller Tree
	 * @param node - contains node that is to be added into the tree
	 */
	public void insert(Node node)
	{
		root = insertRecursive(root, node);
	}

	/**
	 * Inserts the node into the root through recursion
	 * @param rootRecursive - contains the root of the tree
	 * @param node - contains the node to be added into the tree
	 * @return the root of the Caller Tree with nodes added to it as children
	 */
	public Node insertRecursive(Node rootRecursive, Node node)
	{
		if(null == rootRecursive)
		{
			rootRecursive = node;
			return rootRecursive;
		}
		
		//System.out.println("Rec Word: " + node.getWord());
		//System.out.println("Rec Root's Word: " + rootRecursive.getWord());

		//If node's word is smaller than root's word, add the node as left child
		if(node.getWord().compareTo(rootRecursive.getWord()) < 0)
		{
			rootRecursive.setLeft(insertRecursive(rootRecursive.getLeft(), node));
		}
		//If node's word is greater than root's word, add the node as right child
		else if (node.getWord().compareTo(rootRecursive.getWord()) > 0)
		{
			rootRecursive.setRight(insertRecursive(rootRecursive.getRight(), node));
		}
		//If Node with that word already exists, just update the count of the existing node
		else if (0 == node.getWord().compareTo(rootRecursive.getWord()))
		{
			rootRecursive.setWordOccurances(node.getWordOccurances());
		}
		
		return rootRecursive;
	}

	/**
	 * Accessor for root of the tree
	 * @return a node that is the root of the tree
	 */
	public Node getRoot() 
	{
		return root;
	}
	
	/**
	 * Stores the bNumber and Courses of the nodes of the Caller Tree in ascending order of the bNumber in the data member of Results Class
	 * @param r - instance of Results class that stores the bNumber and Courses of the node
	 * @param root - contains the root node of the caller tree
	 */
	public void printNodes(Results r, Node root)
	{
		if(root != null)
		{
			printNodes(r, root.getLeft());
			r.storeNewResult(root.toString());
			printNodes(r, root.getRight());
		}
	}

}