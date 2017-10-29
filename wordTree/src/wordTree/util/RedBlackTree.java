/*
 * Referenced from http://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html
 */

package wordTree.util;

/**
 * Implementation of a balanced binary search tree - RED BLACK Tree
 * @author hloya
 *
 */
public class RedBlackTree extends BSTTree implements RedBlackTreeContantsIfc
{
	//Implement this using fixed int values as per RedBlackTreeContantsIfc and then remove RedBlackTreeContantsIfc
	//public static enum RedBlackTreeConstants {RED, BLACK};
	
	/**
	 * Default Constructor which calls the Default Constructor of parent Binary Search Tree
	 */
	public RedBlackTree()
	{
		super();
	}

	/**
	 * Inserts the node passed as parameter into the Caller Red Black Tree
	 */
	public void insert(Node node)
	{	
		super.insert(node);
		//sets the color of root as Black - Red Black Tree property
		getRoot().setColor(BLACK);
	}

	public Node insertRecursive(Node recursionRoot, Node node)
	{
		recursionRoot = super.insertRecursive(recursionRoot, node);

		//Rotations to maintain RedBlack Tree Property
		if (isRed(recursionRoot.getRight()) && !isRed(recursionRoot.getLeft()))
			recursionRoot = rotateLeft(recursionRoot);
		if (isRed(recursionRoot.getLeft())  &&  isRed(recursionRoot.getLeft().getLeft()))
			recursionRoot = rotateRight(recursionRoot);
		if (isRed(recursionRoot.getLeft())  &&  isRed(recursionRoot.getRight()))
			flipColors(recursionRoot);

		return recursionRoot;
	}

	/**
	 * Switch colors of the node and its two children
	 * <br>
	 * The parent should have opposite color than its children
	 * @param node - parent node whose color will be flipped with its children
	 */
	private void flipColors(Node node)
	{
		// checking that node must have opposite color of its two children than its own
		if ((!isRed(node) &&  isRed(node.getLeft()) &&  isRed(node.getRight()))  || (isRed(node)  && !isRed(node.getLeft()) && !isRed(node.getRight())))
		{
			if(isRed(node))
				node.setColor(BLACK);
			else
				node.setColor(RED);

			if(isRed(node.getLeft()) && isRed(node.getRight()))
			{
				node.getLeft().setColor(BLACK);
				node.getRight().setColor(BLACK);
			}
			else
			{
				node.getLeft().setColor(RED);
				node.getRight().setColor(RED);
			}
		}	
	}

	/**
	 * Method to rotate tree to make left leaning tree to lean right to balance the tree 
	 * @param node - the parent node of current tree being considered
	 * @return Balanced tree
	 */
	private Node rotateRight(Node node) 
	{
		Node rotatedNode = node.getLeft();
		node.setLeft(rotatedNode.getRight());
		rotatedNode.setRight(node);
		rotatedNode.setColor(rotatedNode.getRight().getColor());
		rotatedNode.getRight().setColor(RED);
		
		return rotatedNode;
	}

	/**
	 * Method to rotate tree to make right leaning tree to lean left to balance the tree
	 * @param node - the parent node of current tree being considered
	 * @return Balanced tree
	 */
	private Node rotateLeft(Node node) 
	{
		Node rotatedNode = node.getRight();
		node.setRight(rotatedNode.getLeft());
		rotatedNode.setLeft(node);
		rotatedNode.setColor(rotatedNode.getLeft().getColor());
		rotatedNode.getLeft().setColor(RED);
		
		return rotatedNode;
	}

	/**
	 * Method to Check if the Node's Color is RED
	 * <br>
	 * Negation of this method can be used to check if Node's Color is Black as the Node can be either RED or BLACK only
	 * @param nodeToCheck - contains the node whose color is to be checked
	 * @return true is node's color is RED
	 */
	private boolean isRed(Node nodeToCheck)
	{
		if (nodeToCheck != null && RED == nodeToCheck.getColor())
			return true;
		return false;
	}
}
