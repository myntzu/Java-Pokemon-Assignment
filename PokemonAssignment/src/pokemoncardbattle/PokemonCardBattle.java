package pokemoncardbattle;

/** This is the driver class of the whole pokemon game.
 * It will prompt users to enter values in order to display what they have chosen based
 * on the values that was entered.
 */

import java.util.Random;
import java.util.Scanner;
public class PokemonCardBattle {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Game newgame = new Game(); //handler
		
		boolean start = false;	
		
		while (start == false) {
			System.out.print("Enter 1 to start game.\nEnter 2 to display leaderboards.\nWhat do you want to do?\n");
			int initiate_option = input.nextInt();
			
			switch(initiate_option) {
			case 1:
				newgame.setGameNum(1);
				
				//initiate players
				int p1Score = 0;
				int p2Score = 0;
				
				System.out.println("Enter Your Name (Player 1): ");
				String p1Name = input.next();
				System.out.println("Enter Your Name (Player 2): ");
				String p2Name = input.next();
				
				Player player1 = new Player(p1Name, p1Score);
				Player player2 = new Player(p2Name, p2Score);
				
				newgame.drawCard(player1, player2);
				
				while ((newgame.getWinner() != player1) || (newgame.getWinner() != player2)) {
					newgame.startGame(player1, player2);
					newgame.setGameNum(newgame.getGameNum() + 1);
					System.out.println(" ");
				}
				newgame.displayCards(player1, player2);
				break;
			case 2:
//				displayScore();
				break;
			default:
				System.out.println("This is not a valid choice. Please choose again.");
			}

		}	
				
	}
	
}
