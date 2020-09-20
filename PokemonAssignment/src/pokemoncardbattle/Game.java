package pokemoncardbattle;
import pokemoncardbattle.Player;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**This Game class holds the information of the game that will be shown into the 
 * main program as the user begins playing the game.
 * @author MZT
 *
 */

public class Game {
	Random randomVal = new Random();
	Scanner input = new Scanner(System.in);
	
	PokemonCard deck = new PokemonCard();
	Player[] players = new Player[2];
	
	private Player winner;
	private Player[] playerlist;
	private int gameNum;
	private Player Offender;
	private Player Defender;
	private PokemonCard ChosenOffenderPokemon;
	private PokemonCard Target;
	
	//setters
	public void setWinner(Player winner) {
		this.winner = winner;
	}
	
	public void setPlayerlist(Player[] playerlist) {
		this.playerlist = playerlist;
	}
	
	public void setGameNum(int gameNum) {
		this.gameNum = gameNum;
	}

	public void setOffender(Player offender) {
		Offender = offender;
	}
	
	public void setDefender(Player defender) {
		Defender = defender;
	}
	
	public void setChosenOffenderPokemon(PokemonCard chosenOffenderPokemon) {
		ChosenOffenderPokemon = chosenOffenderPokemon;
	}
	
	public void setTarget(PokemonCard target) {
		Target = target;
	}
	
	//getters
	public Player getWinner() {
		return winner;
	}

	public Player[] getPlayerlist() {
		return playerlist;
	}
	
	public int getGameNum() {
		return gameNum;
	}
	
	public Player getOffender() {
		return Offender;
	}

	public Player getDefender() {
		return Defender;
	}
	
	public PokemonCard getChosenOffenderPokemon() {
		return ChosenOffenderPokemon;
	}
	
	public PokemonCard getTarget() {
		return Target;
	}

	//constructor
	public Game() {
		
	}
	
	public void drawCard(Player p1, Player p2) {
		PokemonCard[] p1Temp = new PokemonCard[6];
		PokemonCard[] p2Temp = new PokemonCard[6];
		String[] p1TypeTemp = new String[6];
		String[] p2TypeTemp = new String[6];
		
		//P1
		//draw up to 5 attacking types (leaving space for at least 1 defending type)
		int a = (2 + randomVal.nextInt(3));
		for (int i = 0; i < a; i ++) {
			p1Temp[i] = new Attacking();
			p1TypeTemp[i] = "Attacking";
		}
		//draw at least 1 defending type (or more depending on the empty slots left by the attacking types)
		int d = (1 + randomVal.nextInt(6 - a));
		for (int i = (a); i < (a+d); i ++) {
			p1Temp[i] = new Defending();
			p1TypeTemp[i] = "Defending";
		}
		//draw fairy types to fill in the empty slots left by both attacking and defending types
		int f = 6 - (a + d);
		for (int i = (a+d); i < 6; i ++) {
			p1Temp[i] = new Fairy();
			p1TypeTemp[i] = "  Fairy  ";
		}
		
		p1.setCards(p1Temp);
		p1.setCardTypes(p1TypeTemp);
		
		//P2
		//draw up to 5 attacking types (leaving space for at least 1 defending type)
		a = (2 + randomVal.nextInt(3));
		for (int i = 0; i < a; i ++) {
			p2Temp[i] = new Attacking();
			p2TypeTemp[i] = "Attacking";
		}
		//draw at least 1 defending type (or more depending on the empty slots left by the attacking types)
		d = (1 + randomVal.nextInt(6 - a));
		for (int i = (a); i < (a+d); i ++) {
			p2Temp[i] = new Defending();
			p2TypeTemp[i] = "Defending";
		}
		//draw fairy types to fill in the empty slots left by both attacking and defending types
		f = 6 - (a + d);
		for (int i = (a+d); i < 6; i ++) {
			p2Temp[i] = new Fairy();
			p2TypeTemp[i] = "  Fairy  ";
		}
		
		p2.setCards(p2Temp);
		p2.setCardTypes(p2TypeTemp);
	}
	
	/**
	 * Player instances are put in a list, their indexes determined by random;
	 * the player at index 0 is to be determined as the first player.
	 */
	public void whoStarts(Player p1, Player p2) {
		if (randomVal.nextInt(2) == 1) {
			players[0] = p1;
			players[1] = p2;
		}
		else {
			players[0] = p2;
			players[1] = p1;
		}
		setPlayerlist(players);	
		
		Player[] list = getPlayerlist();
		
		System.out.printf("%s will go first!\n", list[0].getName());
	}

	public void pickMove() {
		boolean chosen = false;
		
		while(chosen == false){
			System.out.print("Option 1: Attack\nOption 2: Recharge\nOption 3:Train\nChoose the option: ");
			int choice = input.nextInt();
			switch(choice){
			case 1: 
				int chOff = chooseOffenderPokemon();
				int choTar = chooseTarget();
				deck.attack(getChosenOffenderPokemon(), getTarget(), chOff, choTar);    //not sure if deck is right bc Cards was used to set the pokemons, but it can't be used bc it's a list type
				getOffender().setScore(getOffender().getScore() + 1);
				if (getTarget().getHitPt() <= 0) {
					setWinner(getOffender());
				} 
				chosen = true;
				break;
			case 2: 
				chooseOffenderPokemon();
				getChosenOffenderPokemon().recharge();
				chosen = true;
				break;
			case 3: 
				chooseOffenderPokemon();
				getChosenOffenderPokemon().train();
				chosen = true;
				break;
			default:
				System.out.println("This is not a valid choice. Please choose again.");
			}
		}
	}
	
	public int chooseOffenderPokemon() {
		PokemonCard[] DefenderCards =  getDefender().getCards();
		int choice = 0;
		
		boolean chosen = false;
		while(chosen == false){
			PokemonCard[] OffenderCards =  getOffender().getCards();
			System.out.print("Choose Pokemon: ");
			choice = input.nextInt();
			
			switch(choice) {
			case 1: 
				setChosenOffenderPokemon(OffenderCards[0]);
				chosen = true;
				break;
			case 2: 
				setChosenOffenderPokemon(OffenderCards[1]);
				chosen = true;
				break;
			case 3: 
				setChosenOffenderPokemon(OffenderCards[2]);
				chosen = true;
				break;
			case 4: 
				setChosenOffenderPokemon(OffenderCards[3]);
				chosen = true;
				break;
			case 5: 
				setChosenOffenderPokemon(OffenderCards[4]);
				chosen = true;
				break;
			case 6: 
				setChosenOffenderPokemon(OffenderCards[5]);
				chosen = true;
				break;
			default:
				System.out.println("This is not a valid choice. Please choose again.");
			}
		}	
		return choice;
	}
	
	public int chooseTarget() {
		PokemonCard[] DefenderCards =  getDefender().getCards();
		int choice = 0;
		
		boolean chosen = false;
		while(chosen == false){
			System.out.print("Target Pokemon: ");
			choice = input.nextInt();
			
			switch(choice) {
			case 1: 
				setTarget(DefenderCards[0]);
				chosen = true;
				break;
			case 2: 
				setTarget(DefenderCards[1]);
				chosen = true;
				break;
			case 3: 
				setTarget(DefenderCards[2]);
				chosen = true;
				break;
			case 4: 
				setTarget(DefenderCards[3]);
				chosen = true;
				break;
			case 5: 
				setTarget(DefenderCards[4]);
				chosen = true;
				break;
			case 6: 
				setTarget(DefenderCards[5]);
				chosen = true;
				break;
			default:
				System.out.println("This is not a valid choice. Please choose again.");
			}
		}
	
		return choice;
		
	}
	
	public void startGame(Player p1, Player p2) {
		//display the cards
		displayCards(p1, p2);
		
		//determine who starts (only if it's game(round) 1
		if (getGameNum() == 1) {//this is not the newgame instance; might want to check
			whoStarts(p1, p2);
		}
				
		//set the Offender and Defender roles according to number of games(rounds) played
		Player[] list = getPlayerlist();
		
		if (getGameNum() % 2 != 0) {
			setOffender(list[0]);
			setDefender(list[1]);
			System.out.println(list[0].getName() + "'s turn: ");
		}
		else {
			setOffender(list[1]);
			setDefender(list[0]);
			System.out.println(list[1].getName() + "'s turn: ");
		}
		
		//prompt player to pick a move
		pickMove();
		System.out.print(" ");
		getOffender().setScore(getOffender().getScore() + 1);
	}
	
	
	
	/**
	 * @AmritaMenon 17029596
	 * 
	 */
	public void displayCards(Player player1, Player player2){
		PokemonCard[] player1Cards =  player1.getCards();
		PokemonCard[] player2Cards =  player2.getCards();
		String[] player1CardTypes = player1.getCardTypes();
		String[] player2CardTypes = player2.getCardTypes();
		
		System.out.println("\n" + player1.getName() + "'s Cards:");
		System.out.println("|   No.   |       Type       |     Stage     |     EXP     |     HP     |     Energy     |     EColour     |   Attack Pt.   |   Resistance Pt.   |     Status     |\n"
				+ "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"
				+ "|    1    |\t" + player1CardTypes[0] + "\t" + player1Cards[0].toString() + "\n"
				+ "|    2    |\t" + player1CardTypes[1] + "\t" + player1Cards[1].toString() + "\n"
				+ "|    3    |\t" + player1CardTypes[2] + "\t" + player1Cards[2].toString() + "\n"
				+ "|    4    |\t" + player1CardTypes[3] + "\t" + player1Cards[3].toString() + "\n"
				+ "|    5    |\t" + player1CardTypes[4] + "\t" + player1Cards[4].toString() + "\n"
				+ "|    6    |\t" + player1CardTypes[5] + "\t" + player1Cards[5].toString());
		System.out.println("");
		
		System.out.println("\n" + player2.getName() + "'s Cards:");
		System.out.println("|   No.   |       Type       |     Stage     |     EXP     |     HP     |     Energy     |     EColour     |   Attack Pt.   |   Resistance Pt.   |     Status     |\n"
				+ "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"
				+ "|    1    |\t" + player2CardTypes[0] + "\t" + player2Cards[0].toString() + "\n"
				+ "|    2    |\t" + player2CardTypes[1] + "\t" + player2Cards[1].toString() + "\n"
				+ "|    3    |\t" + player2CardTypes[2] + "\t" + player2Cards[2].toString() + "\n"
				+ "|    4    |\t" + player2CardTypes[3] + "\t" + player2Cards[3].toString() + "\n"
				+ "|    5    |\t" + player2CardTypes[4] + "\t" + player2Cards[4].toString() + "\n"
				+ "|    6    |\t" + player2CardTypes[5] + "\t" + player2Cards[5].toString());
		System.out.println("");
	}

	@Override
	public String toString() {
		return String.format(
				"Game [randomVal=%s, input=%s, deck=%s, players=%s, winner=%s, playerlist=%s, gameNum=%s, Offender=%s, Defender=%s, ChosenOffenderPokemon=%s, Target=%s]",
				randomVal, input, deck, Arrays.toString(players), winner, Arrays.toString(playerlist), gameNum,
				Offender, Defender, ChosenOffenderPokemon, Target);
	}



	
	

	
}
