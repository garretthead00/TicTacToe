package tictactoe;

import AI.AI;


/** This class will hold all of the Laws and variables for the TicTacToe game.
 * This class will connect the AI, Player, and Grid classes to pass data amongst one another to control the functions of the game.
 *  
 * @author Garrett
 */
public class Game {
	
	public Grid grid = new Grid();
	public Player p1; // 
	public Player AI;
	
	
	/** Constructor
	 * This will construct a game object for Single-Player mode [Player 1 vs AI]
	 * @param player1Name
	 */
	public Game(String player1Name)
	{
		grid = new Grid();
		this.p1 = new Player(player1Name);
		this.AI = new AI("AI");
	}
	
	
	/** Method
	 * This method will move the current gridState by first checking for a valid move then check the condition of the current gridState 
	 * and returning the value passed back by the checkCondition method
	 * @param gridState
	 * @return
	 */
	public int Move(int index)
	{	
		 if (grid.performMove(index)==false) // Use the set Move to check for a valid move and change the rotation if true
	            return -2;
		return grid.isTerminal(); // Scores the current grid State
	}
	
	/**Method
	 * This method will return the name of the player depending of who's turn it is
	 * @param playerNo
	 * @return
	 */
	public String PlayerName(boolean side)
	{
		if (side == true)
			return p1.playerName; 
		
		return AI.playerName;
	}
	
	
}