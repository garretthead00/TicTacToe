package tictactoe;

/** Class: Game_Board
 * This class will create a TicTacToe board for the game to be played on
 * This class will contain all the necessary methods to manipulate the game.
 *
 * @author Garrett
 */
public class Grid{
	
	private int grid[]= new int[9];
	private int playerID = 1;// Sets the initial move to Player2 or AI; 0=empty; 1=Player1; -1=Player2
	public int moveCount = 0; // keeps track of the number of turns the game has made 
	
	/** Constructor
	 * This default constructor will create an empty Grid will no parameters passed in
	 */
	public Grid()
	{
		for (int x =0;x<=8;x++)
			grid[x]=0;
    }
	
	/** Constructor:
	 * This will create a Grid from the board passed in
	 * @param intBoard
	 */
	public Grid(int[] intBoard)
	{
		for (int x =0;x<=8;x++)
			this.grid[x]= intBoard[x];
	}
	
	
	/**Method
	 * This method will copy a Grid to (this)Grid 
	 * @param board
	 */
	public void cloneBoard(Grid board)
	{
		this.moveCount=board.moveCount;
		this.playerID=board.playerID;		
		this.grid = board.grid.clone();
	}
	

	
	/** Method
	 * This method will set the symbol for the next move
	 * @param boxNo
	 */
	public Boolean performMove(int index)
	{
		if (grid[index]!= 0)
		{
			return false;
		}
		
		if (playerID == 1)
		{
			playerID = -1; // Switch Moves from P1 to P2
		}
		else
		{
			playerID = 1; // Switches move to P1
		}
		
		grid[index]=playerID;
		moveCount++;
		
		return true;
		
	} // End Set Move
	
	/**Method
	 * This method will check to see if there is a terminal state (WIN or DRAW) else it's none
	 * @return
	 */
	public int isTerminal() // Returns [1=Player1],[-1=Player2],[0=draw],[2 = !terminal]
	{
		if (grid[0]==grid[1] & grid[1]==grid[2] & grid[2]!=0)
			return grid[0];
		else if (grid[3]==grid[4] & grid[4]==grid[5] & grid[5]!=0)
			return grid[3];
		else if (grid[6]==grid[7] & grid[7]==grid[8] & grid[8]!=0)
			return grid[6];
		
		else if (grid[0]==grid[3] & grid[3]==grid[6] & grid[6]!=0)
			return grid[0];
		else if (grid[1]==grid[4] & grid[4]==grid[7] & grid[7]!=0)
			return grid[1];
		else if (grid[2]==grid[5] & grid[5]==grid[8] & grid[8]!=0)
			return grid[2];
		
		else if (grid[0]==grid[4] & grid[4]==grid[8] & grid[8]!=0)
			return grid[0];
		else if (grid[2]==grid[4] & grid[4]==grid[6] & grid[6]!=0)
			return grid[2];
		
		else if(moveCount == 9)
			return 0;
		
		return 2; // !terminal
	} // End Check
	
	/**Method
	 *This method will print the current state of the TicTacToe game
	 */
	public void Print()
	{
		System.out.print("\n_____GRID______");
	
		for(int x = 0; x < 9; x++)
		{
				if(x%3 == 0)
				{
					System.out.println("");
				}
				if(grid[x] !=0)
				{
					if(grid[x] == -1)
						System.out.print("|_"+ "O" + "_|");
					else
						System.out.print("|_"+ "X" + "_|");
				}
				else // blank square
				{
					System.out.print("|_"+ " " + "_|");
				}
				
		}
		System.out.println("");
	}
	
}

