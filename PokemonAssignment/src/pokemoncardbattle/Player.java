package pokemoncardbattle;

/**
 * 
 * @MZT 17072075
 * This class is created to hold all information regarding the player
 * 
 */
public class Player{
	private String name;
	private int score;
	private PokemonCard[] cards; //create an array list which will hold in user's pokemon cards during the game
	private String[] cardTypes;
	
	public Player(String name, int score) {
		setName(name);
		setScore(score);
	}

	//generate getter and setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public PokemonCard[] getCards() {
		return cards;
	}

	public void setCards(PokemonCard[]cards) {
		this.cards = cards;
	}
	
	public String[] getCardTypes() {
		return cardTypes;
	}

	public void setCardTypes(String[] cardTypes) {
		this.cardTypes = cardTypes;
	}

	@Override
	public String toString() {
		return String.format("Player [name=%s, score=%s]", name, score);
	}


	
}
