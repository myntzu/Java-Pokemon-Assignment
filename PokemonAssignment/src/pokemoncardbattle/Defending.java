package pokemoncardbattle;


import java.util.Random;
public class Defending extends PokemonCard {

	private int resistancePt;

	public Defending() {
		super();
		setResistancePt(1 + randomVal.nextInt(2));
	}
	
	public int getResistancePt() {
		return resistancePt;
	}

	public void setResistancePt(int resistancePt) {
		this.resistancePt = resistancePt;
	}
	
	public double resistCount(PokemonCard offender, Defending defender, String coinFlip) {
		double resistBonus;
		double resistance;
		
		if (coinFlip == "head") {
			System.out.println(" Target's resistance point is " + defender.getResistancePt());
			resistBonus = (0.5 * defender.getResistancePt());
		}
		else {
			System.out.println(" Target's resistance point is 0");
			resistBonus = 0;
		}
		
		if (defender.getStatus() == "Active" && checkWeakness(offender, defender) == false) {
			resistance = 1 * resistBonus;
		}
		else {
			if (defender.getStatus() == "Active" && checkWeakness(offender, defender) == true){
				resistance = 2 * resistBonus;
			}
			else {
				if (defender.getStatus() != "Active" && checkWeakness(offender, defender) == false) {
					resistance = 2 * resistBonus;
				}
				else {
					resistance = 4 * resistBonus;
				}
			}
		}
		return resistance;
	}

	@Override
	public void promote() {
		super.promote();
		if (getExperience() == 20) {
			setResistancePt(2 * getResistancePt());
		}
	}

	@Override
	public String toString() {
		return String.format("%s\t|\t-\t|\t%s\t|", super.toString(), resistancePt);
	}	
}
