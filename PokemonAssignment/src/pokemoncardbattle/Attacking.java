package pokemoncardbattle;


/**
 * This subclass inherits methods and attributes from the superclass PokemonCard
 *
 */

import java.util.Random;
public class Attacking extends PokemonCard {
	
	//in this class, there is an attack point attribute which assigns attack points to the attacking card
	private int attackPt;

	//a constructor
	public Attacking() {
		super();
		setAttackPt(2 + randomVal.nextInt(3));
	}
	
	//setters and getters are generated here
	public int getAttackPt() {
		return attackPt;
	}

	public void setAttackPt(int attackPt) {
		this.attackPt = attackPt;
	}
	
	//Damage count for the attacking is done based on different conditions
	public double extraDamageCount(Attacking offender, PokemonCard defender, String coinFlip) {
		double damageBonus;
		double extraDamage;
		
		/*based on the result of the coin flip, the damage bonus values are granted differently
		* according to the defender's status
		*
		*/
		if (coinFlip == "head") {
			System.out.println(" Pokemon's attack point is " + offender.getAttackPt() + ".");
			damageBonus = (0.5 * offender.getAttackPt());
		}
		else {
			System.out.println(" Pokemon's attack point is 0.");
			damageBonus = 0;
		}
		
		if (defender.getStatus() == "Active" && checkWeakness(offender, defender) == false) {
			extraDamage = 1 * damageBonus;
		}
		else {
			if (defender.getStatus() == "Active" && checkWeakness(offender, defender) == true){
				extraDamage = 2 * damageBonus;
			}
			else {
				if (defender.getStatus() != "Active" && checkWeakness(offender, defender) == false) {
					extraDamage = 2 * damageBonus;
				}
				else {
					extraDamage = 4 * damageBonus;
				}
			}
		}
		return extraDamage;
	}
	
	@Override
	public void promote() {
		super.promote();
		if (getExperience() == 20) {
			setAttackPt(2 * getAttackPt());
		}
	}

	@Override
	public String toString() {
		return String.format("%s\t|\t%s\t|\t-\t|", super.toString(), attackPt);
	}
	


}
