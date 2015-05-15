package AI;

import tictactoe.Grid;
import tictactoe.Player;

/**
 * Class
 * This class will serve as an extended player for Single-Player matches
 * This class will be the AI opponent for the game and will inherits all methods from the Player class and additional methods
 * to find the best counter move
 * @author Garrett
 *
 */
public class AI extends Player {
	
	private static int bestCount; // Node count in tree; number of possible moves traversed
	public String symbol = "X";
	
	/**
	 * Constructor:
	 * This will create an AI and assigns the name of the AI
	 * @param PlayerName
	 */
	public AI(String PlayerName)
	{
		super(PlayerName); // uses super to pass the PlayerName into the Player Constructor to create an AI_Player
	}
	
	/**
	 * Method
	 * This is the method that separates the AI from the HUMAN. This will find the next move for the AI to select
	 * @param state
	 * @return move
	 */
	public int NextBestMove(Grid state)
	{
		
		BestMove root = new BestMove(state);
		
		int bestScore = -2; // Sets a default score of every grid so that the AI is force to move; terminals will change the score
		BestMove move = new BestMove();
		bestCount=0; // resets the tree traversal count to 0 each call
		for (int x = 0; x <= 8; x++)
		{
			BestMove temp = new BestMove();
			temp.cloneBestMove(root); // copies the gridState from the root of this Tree
			
			if (temp.gridState.performMove(x) == true) // If valid move
			{
				root.AddChild(temp); // Add this Best to the children list of the Root
				temp.gridIndex = x; // sets the gridIndex to x to test the move
				int counterScore = minimaxAB(temp, true, -2, 2); // Initial minimax call; Alpha = -2; Beta = 2;
				// Once minimaxAB returns with a move
				if (counterScore >= bestScore) // If its score is better than the default
				{
					bestScore = counterScore; // Updates the bestScore to the counterScore
					move = temp; // Reassigns the Best move to the temp Best
				}
			}
		}
		// Return the selected Best index to move to
		return move.gridIndex;
	}
	
	
	/** 
	 * Method: RECURSIVE
	 * This method will create a Decision Tree for the AI to choose the best move to counter the Player
	 * This method will use recursion to perform a POSTORDER traversal and score each Best in the tree;
	 * pruning, and updating the Best possible move;
	 * This method uses an Alpha-Beta pruning method to prune away unnecessary sub-tree traversals and save Run-Time.
	 * @param node
	 * @param side
	 * @param alpha
	 * @param beta
	 * @return score
	 */
	private int minimaxAB(BestMove best, boolean side, int alpha, int beta)
	{
		if (best.gridState.isTerminal() != 2) // If the gridState is termin al; [Draw | Win]
		{
			best.score = best.gridState.isTerminal(); // score this node
			
			return best.score; // return the new score
		}
		
		if(side == false) // If AI turn; MIN Level
		{
			best.score = alpha; // set score to alpha
		}
		else // MAX Level; Player1 turn
		{
			best.score = beta; // set score to beta
		}
		bestCount++;
		// For each legal move in the grid; 8 possible worst case
		for (int x = 0; x <= 8; x++)
		{			
			BestMove nextBest = new BestMove();
			nextBest.cloneBestMove(best); // copies the Best best passed into minimax; into the nextBest
			if (nextBest.gridState.performMove(x) == true)
			{
				best.AddChild(nextBest); // Add nextBest to the children list
				nextBest.gridIndex = x; // set the index value of the nextBest
				
				//Recursive minimaxA/B call
				int nextScore = minimaxAB(nextBest, !side, alpha, beta); // switches sides each call with !side; returns the best score and stores into the nextScore
				
				// Once the minimaxAB returns
				if (side == true && nextScore < beta) // If Player 1 turn & the nextBest score is less than beta
				{
					beta = nextScore; // Reassign beta
					nextBest.parent.score = nextScore; // Reassign the parents score to this
				}
				else if (side == false && nextScore > alpha) // Else AI's turn & it's score is greater than alpha
				{
					alpha = nextScore;
					nextBest.parent.score = nextScore;
				}
			}
			// If and whenever alpha > beta; no further searching is necessary because there cannot be a better move than the current one
			if(alpha > beta) // Prune the tree
				return alpha; // return the alpha
		}
		// Else return the current score
		return best.score;
	}
	

	/**Method
	 * returns the number of BestMove the tree created each turn.
	 * @return
	 */
	public static int getBestCount()
	{
		return bestCount;
	}
	
}