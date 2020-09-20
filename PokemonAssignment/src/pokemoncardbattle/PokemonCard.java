package pokemoncardbattle;

/**@author Group16
 * This is the superclass which contains majority of the attributes and methods
 * which will be inherited by its subclasses: Attacking, Defending and Fairy.
 */
import java.util.Random; 
public class PokemonCard {
	private int Stage;
	private int Experience;
	private double HitPt;
	private int Energy;
	private String EColour;
	private String Status;
	static private String[] colour = {"Red","Blue","Yellow","Colourless"};
	static private String[] cardcolour = {"Red","Blue","Yellow"};
	static private String[] state = {"Active", "Poisoned","Paralysed"};
	
	//values for the Hit point, Energy and EColour will be generated randomly using the util.Random library
	Random randomVal = new Random();
	public PokemonCard() {
		//values by default
		setStage(0);
		setExperience(0);
		setHitPt(50 + randomVal.nextInt(30));
		setEnergy(20 + randomVal.nextInt(30));
		setEColour(colour[randomVal.nextInt(3)]);
		setStatus(state[0]);
	}

	/* getters and setters are generated for each method to ensure that the program
	 * will run more efficiently
	 */
	public int getStage() {
		return Stage;
	}
	
	public void setStage(int stage) {
		Stage = stage;
	}
	
	public int getExperience() {
		return Experience;
	}
	
	public void setExperience(int exp) {
		Experience = exp;
	}
	
	public double getHitPt() {
		return HitPt;
	}


	public void setHitPt(double hitPt) {
		HitPt = hitPt;
	}


	public int getEnergy() {
		return Energy;
	}


	public void setEnergy(int energy) {
		Energy = energy;
	}


	public String getEColour() {
		return EColour;
	}


	public void setEColour(String EColour) {
		this.EColour = EColour;
	}


	public String getStatus() {
		return Status;
	}


	public void setStatus(String status) {
		Status = status;
	}
	
	public void attack(PokemonCard offender, PokemonCard defender, int offenderPokemon, int targetPokemon) {
		if (offender.getStatus() != "Active") {
			System.out.println("Pokemon " + offenderPokemon + " is" + offender.getStatus() + ", please choose another Pokemon.");
		}
		else {
			//setting extra damage/resistance/paralyze/poison
			double extraDamage = 0;
			double resistance = 0;
			
			String coinFlip = flipCoin();
			
			if (offender instanceof Attacking) {
				Attacking temp = (Attacking) offender;
				temp.extraDamageCount(temp, defender, coinFlip);
			}
			
			if (offender instanceof Fairy) {
				Fairy temp = (Fairy) offender;
				if (coinFlip == "head") {
					temp.poison(defender, targetPokemon);
				}
				else {
					temp.paralyze(defender, targetPokemon);
				}
			}
			
			if (defender instanceof Defending) {
				Defending temp = (Defending) defender;
				temp.resistCount(offender, temp, coinFlip);
			}
			
			//actual attack
			double damage = 0;
			int energyDrain = 0;
			
			boolean weakness = checkWeakness(offender, defender);
			
			if (defender.getStatus() == "Active" && weakness == false) {
				damage = 1 + (extraDamage - resistance);
				energyDrain = 1;
				defender.setHitPt(defender.getHitPt() - damage);
				offender.setEnergy(offender.getEnergy() - energyDrain);
			}
			else {
				if (defender.getStatus() == "Active" && weakness == true){
					damage = 2 + (extraDamage - resistance);
					energyDrain = 2;
					defender.setHitPt(defender.getHitPt() - damage);
					offender.setEnergy(offender.getEnergy() - energyDrain);
				}
				else {
					if (defender.getStatus() != "Active" && weakness == false) {
						damage = 2 + (extraDamage - resistance);
						energyDrain = 1;
						defender.setHitPt(defender.getHitPt() - damage);
						offender.setEnergy(offender.getEnergy() - energyDrain);
					}
					else {
						damage = 4 + (extraDamage - resistance);
						energyDrain = 1;
						defender.setHitPt(defender.getHitPt() - damage);
						offender.setEnergy(offender.getEnergy() - energyDrain);
					}
				}
			}
			offender.setExperience(offender.getExperience() + 1);
			System.out.printf("Pokemon %d's Hit Point is damaged by %.2f.\n", targetPokemon, damage);
			System.out.printf("Pokemon %d's Energy is reduced by %.2f.\n", offenderPokemon, damage);
		}
	}		
	//recharges energy
	public void recharge() {
		System.out.println("Draw Card...");
		System.out.printf("Colour drawn: %s\n", drawEnergyCard() );
		if (getEColour() == "Colourless") {
			System.out.println("Colourless Pokemon takes all! Energy increased by 5.");
			setEnergy(getEnergy() +5);
		}
		else {
			if (drawEnergyCard() == getEColour()) {
				System.out.println("Matched! Energy increased by 5.");
				setEnergy(getEnergy() +5);
			}
			else {
				System.out.println("Not matched. Energy level remains the same.");
			}
		}
	}
	//to train player's cards
	public void train() {
		System.out.println("Experience increased by 5");
		System.out.println("Energy reduced by 5");
		setExperience(getExperience() + 5);
		setEnergy(getEnergy() - 5);
	}
	//flip coin method
	public String flipCoin() {
		int coin = (int)(Math.random() * 2);
		if (coin == 0) {
			System.out.print("[Flip a Coin: Head]");
			return "head";
		}
		else {
			System.out.print("[Flip a Coin: Tail]");
			return "tail";
		}
	}
	//checks weakness of both cards then decides on attack/resistance values
	public boolean checkWeakness(PokemonCard x, PokemonCard y) {
		if (x.getClass() == y.getClass()) {
			System.out.println("Weakness: On, double attack point");
			return true;
		}
		else {
			System.out.println("Weakness: Off");
			return false;
		}
	}	
	
	private String drawEnergyCard() {
		int rand = (int)(Math.random() * 3);
		String energycard = cardcolour[rand];
		return energycard;
	}
	//promotes player cards
	public void promote() {
		if (getExperience() == 20) {
			setStage(getStage()+1);
			setExperience(0);
			setHitPt(2 * getHitPt());
			setEnergy(2 * getEnergy());
		}
	}
	
	@Override
	public String toString() {
		return String.format("|\t%d\t|\t%d\t|\t%.2f\t|\t%d\t|\t%s\t|\t%s",
				 Stage,Experience, HitPt, Energy, EColour, Status);
	}

	
}

