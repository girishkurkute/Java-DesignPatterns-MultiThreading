package wordTree.util;

import wordTree.util.RedBlackTree.RedBlackTreeConstants;

/**
 * This class creates Nodes
 * @author hloya
 *
 */
public class Node
{
	/**
	 * Data members
	 */
	private String word;
	private int wordOccurances;
	private Node left, right;
	private int color;

	/**
	 * Default Constructor used to initialize the data members to default values
	 */
	public Node()
	{
		MyLogger.writeMessage("Node class default constructor was called", MyLogger.DebugLevel.CONSTRUCTOR);
		word = "";
		wordOccurances = 0;
		left = right = null;
		color = RedBlackTreeConstants.RED.colorValue;
	}
	
	/**
	 * Parameterized Constructor used to initialize the data members to the parameter values
	 * @param node - Its values used to initialize data members
	 */
	public Node(Node node)
	{
		MyLogger.writeMessage("Node class paramterized constructor was called", MyLogger.DebugLevel.CONSTRUCTOR);
		word = node.getWord();
		wordOccurances = node.getWordOccurances();
		left = node.getLeft();
		right = node.getRight();
		color = node.getColor();
	}

	/**
	 * Accessor for wordOccurances
	 * @return wordOccurances of the Caller Node.
	 */
	public int getWordOccurances() {
		return wordOccurances;
	}

	/**
	 * Mutator for wordOccurances
	 * @param wordOccurances - contains the new value.
	 */
	public void setWordOccurances(int wordOccurances) {
		this.wordOccurances = wordOccurances;
	}
	
	/**
	 * Accessor for word
	 * @return the word that the node contains.
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Mutator for word
	 * @param word - new value of the word
	 */
	public void setWord(String word) {
		this.word = word;
	}
	
	/**
	 * Accessor for Left Child
	 * @return Left Child Node of the Caller Node
	 */
	public Node getLeft() 
	{
		return left;
	}

	/**
	 * Mutator for Left Child
	 * @param left - Left Child Node that will be set as Caller Node's Left Child
	 */
	public void setLeft(Node left) 
	{
		this.left = left;
	}

	/**
	 * Accessor for Right Child
	 * @return Right Child Node of the Caller Node
	 */
	public Node getRight() 
	{
		return right;
	}

	/**
	 * Mutator for Right Child
	 * @param right - Right Child Node that will be set as Caller Node's Right Child
	 */
	public void setRight(Node right) 
	{
		this.right = right;
	}

	/**
	 * Accessor for Node Color
	 * @return Color of the Caller Node
	 */
	public int getColor() {
		return color;
	}

	/**
	 * Mutator for Node Color
	 * @param color - The color value that will be set as Caller Node's Color
	 */
	public void setColor(int color) {
		this.color = color;
	}
	
	/**
	 * Custom implementation of toString method to print Word and wordOccurances of the Caller Node
	 */
	@Override
	public String toString() {
		return "Node : Word = " + word + ", Word Count = " + wordOccurances;
	}
}
