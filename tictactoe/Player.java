package	tictactoe;


/**Class
 * This class will serve as the base class for the Players in the game
 * This will hold methods to detail each player individually.
 * @author Garrett
 *
 */
public class Player {
	public String playerName;
	public String symbol = "O";
	
	/**
	 * Constructor:
	 * This will create a player and set the players name
	 * @param playerName
	 */
	public Player(String playerName)
	{
		this.playerName=playerName;
	}
	
	/** Method
	 * This will return the Symbol for the player
	 * @return
	 */
	public String getSymbol()
	{
		return symbol;
	}
	
}
