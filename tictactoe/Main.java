package tictactoe;



import java.util.Scanner;
import AI.AI;


/** 
 *  This class will control the flow of the program using a while loop to iterate between each move from both players
 * until a win or draw is found.
 * This will connect the Grid, Player, and the AI classes together to create the game.
 * 
 * @author Garrett
 */

public class Main {

	
	
	

	public static void main(String[] args) 
	{
		int gridScore = 0;
		boolean side = false;
		Game game = new Game("Player1");
		System.out.println("Tic-Tac-Toe");
		System.out.println("Begin game....");
		
		Scanner in = new Scanner(System.in); // Opens up a scanner to receive user input
		
		while(true)
		{		
			//Switches the turn of each game; Beginning declaration is false giving player 1 the first move
			if (side == true) // If Human turn
			{
				side = false; // switch to AI
			}
			else // Is AI turn
			{
				side = true; // Switch to Human
			}
			
			// Print the results of each move
			game.grid.Print();
			System.out.println();
			System.out.print(game.PlayerName(side) + "'s Move: "); // Display who's turn it is currently
			
			// If single player and is AI's turn (Min) Lv
			if (side == false)
			{
				AI a = (AI) game.AI; // Declares the player2 in the single player mode as the AI
				int aiScore = a.NextBestMove(game.grid); // score the NextBestMove of the current grid state in the aiMove; calls the method to activate the A/B recursion
				System.out.print(aiScore); // Display the chosen move
				System.out.println(" found in " + AI.getBestCount() +  " Possible Best Moves");
				gridScore = game.Move(aiScore); // Pass in the best move into Move and store the grid Value into the gridScore
			}
			// If Single Player: Player 1 turn || If multi-player both players will use this block
			else
			{
				gridScore = game.Move(in.nextInt()); // make a move; Uses the GUI 
			}
			System.out.println();
			
			/** Checks for invalid index values given by the user **/
			if (gridScore == -2)
			{
				System.out.println("Invalid Move Selected; Try Again...");
				// Switch the turn back
				if (side == true) // If Human turn
				{
					side = false; // switch to AI
				}
				else // Is AI turn
				{
					side = true; // Switch to Human
				}

			}
			
			// IF Terminal State
			 else if (gridScore != 2) 
			{
				game.grid.Print(); // Display the grid
				System.out.println();
				System.out.println("\n____________GAME OVER_____________\n");
				if (gridScore == 1 || gridScore == -1) // Grid has a winner
				{
					System.out.println(game.PlayerName(side) + " Wins"); // Display winner's name
				}
				if (gridScore == 0) // grid is a draw
				{
					System.out.println(" Draw ");
				}
				break;
			}
			// Else if gridScore == 2, !terminal; continue looping
			
		}
	}// End Main
	
}
	