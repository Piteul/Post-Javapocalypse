
public class Personnage extends Humain{

	
	//CARACTERISTIQUES
	int survie;
	int endurance;
	int inventaire;
	
	protected Personnage(String p_nom, int p_pointVie, int p_status, int p_force, int p_defense, int p_endurance, int p_survie, int p_inventaire) {
		super(p_nom, p_pointVie, p_status, p_force, p_defense);
		this.endurance=p_endurance;
		this.survie=p_survie;
		this.inventaire=p_inventaire;
	}
	
	
	//ACTION
	
	//sur la carte
	public void seDeplacer() {
		
	}
	
	public void fouiller() {
		
	}
	
	public void voirInventaire() {
		
	}

	//dans l'inventaire
	public void utiliserObjet() {
		
	}
	
	public void jeterObjet() {
		
	}
	
	//en combat
	public void attaquer() {
		
	}
	
	public void seDefendre() {
		
	}
	public void fuir() {
		
	}
}
