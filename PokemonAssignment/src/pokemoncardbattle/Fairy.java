package pokemoncardbattle;


public class Fairy extends PokemonCard {

	public Fairy() {
		super();
	}
	
	public void poison(PokemonCard target, int targetPokemon) {
		target.setStatus("Poisoned");
		System.out.println(" Pokemon " + targetPokemon + " is poisoned."); 
	}
	

	public void paralyze(PokemonCard target, int targetPokemon) {
		target.setStatus("Paralyzed");
		System.out.println(" Pokemon " + targetPokemon + " is paralyzed.");
	}
	
	@Override
	public String toString() {
		return String.format("%s\t|\t-\t|\t-\t|", super.toString());
	}
	
}