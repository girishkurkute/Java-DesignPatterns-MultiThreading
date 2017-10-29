package wordTree.util;

/**
 * This class creates Nodes that can act as Subject as well as Observer
 * @author hloya
 *
 */
public class Node implements RedBlackTreeContantsIfc
{
	/**
	 * Data members
	 */
	private String word;
	private int wordOccurances;
	private Node left, right;
	private int color;
	//private ArrayList<Node> observers;

	/**
	 * Default Constructor used to initialize the data members to default values
	 */
	public Node()
	{
		word = "";
		wordOccurances = 0;
		left = right = null;
		color = RED;
		//observers = new ArrayList<Node>();
	}
	
	/**
	 * Parameterized Constructor used to initialize the data members to the parameter values
	 * @param node - Its values used to initialize data members
	 */
	public Node(Node node)
	{
		word = node.getWord();
		wordOccurances = node.getWordOccurances();
		left = node.getLeft();
		right = node.getRight();
		color = node.getColor();
		//observers = node.getObservers();
	}

	/**
	 * Accessor for wordOccurances
	 * @return wordOccurances of the Caller Node
	 */
	public int getWordOccurances() {
		return wordOccurances;
	}

	public void setWordOccurances(int wordOccurances) {
		this.wordOccurances = wordOccurances;
	}
	
	

	public String getWord() {
		return word;
	}

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
	 * Accessor for ArrayList of Observers
	 * @return ArrayList of Observers of the Caller Node
	 */
	/*public ArrayList<Node> getObservers() {
		return observers;
	}*/
	
	
	/**
	 * Custom implementation of Clone to perform deep copying
	 */
	@Override
	public Node clone() throws CloneNotSupportedException
	{
		Node clone =  new Node();
		clone.word = new String(word);
		clone.wordOccurances = new Integer(wordOccurances);
		if(null != left)
		{
			clone.left = new Node(left);
		}
		else
		{
			clone.left = null;
		}
		
		if(null != right)
		{
			clone.right = new Node(right);
		}
		else
		{
			clone.right = null;
		}
		
		clone.color = new Integer(color);
		//clone.observers = new ArrayList<Node>(observers);
		return clone;
	}

	/**
	 * Custom implementation of toString method to print Word and wordOccurances of the Caller Node
	 */
	@Override
	public String toString() {
		return "Node : Word = " + word + ", Word Count = " + wordOccurances;
	}
	
	
}
