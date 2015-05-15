package AI;

import java.util.ArrayList;
import tictactoe.Grid;

/**
 * Class
 * This class will create the BestMove ADT to hold data of the moves for the AI_Tree.
 * @author Garrett
 *
 */
public class BestMove {
	
	public int gridIndex; // holds value of the index that the user/AI selects for the move
	public int score; // holds value of the BestMove
	public Grid gridState; // holds the gridState for the BestMove
	public BestMove parent; // Parent link
	public ArrayList<BestMove> children = new ArrayList<BestMove>(); // list of children links
	
	/**
	 * Constructor:
	 * This constructor will create a BestMove by default call
	 */
	public BestMove()
	{
		this.gridState = new Grid(); // creates a new Game_Board for this BestMove
	}
	
	/**
	 * Constructor:
	 * This constructor will create a BestMove and assigning its board to the board variable of this BestMove
	 */
	public BestMove(Grid gridState)
	{
		this.gridState = gridState;
	}
	
	/**
	 * Method
	 * This method will add a child to the BestMove ADT by passing in the child
	 * @param child
	 */
	public void AddChild(BestMove child)
	{
		children.add(child);
		child.setParent(this); // Sets parent link to thisBestMove
	}
	
	/**
	 * Method
	 * This method will assign the parent of the BestMove by passing in the parent BestMove
	 * @param parent
	 */
	private void setParent(BestMove parent)
	{
		this.parent = parent;
	}
	
	/**
	 * Method
	 * This method will copy the attributes of the BestMove passed in
	 * @param score
	 */
	public void cloneBestMove(BestMove node)
	{
		this.score= node.score;
		this.gridState.cloneBoard(node.gridState);
	}
	
	/**
	 * Method
	 * This method will print the data for the BestMove
	 */
	public void Print()
	{
		
		System.out.println("\nPoint " + this.score); // prints score
		System.out.println("moveBox " + this.gridIndex); // prints the score of the box to move
		this.gridState.Print(); // prints the grid array of the current gridState data
		System.out.println();
	}
}
