
public class Personnage extends Humain{
	
	 //CARACTERISTIQUES
	
	static String nom = "Stalker";
	static int vie = 100;
	static int status = 0;
	static int force = 15;
	static int defense = 20;
	double survie = 1.2; //à changer  
	int endurance = 100;
	int inventaire = 5; //à revoir
	
	/**
	 * Constructeur
	 */
	protected Personnage() {
		super(nom, vie, status, force, defense);
		this.endurance=this.endurance;
		this.survie=this.survie;
		this.inventaire=this.inventaire;
	}
	
	
	/**
	 * Partie exploration
	 */
	
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
	
	/**
	 * Partie Combat
	 */
	
	
	public void attaquer() {
		
	}
	
	public void seDefendre() {
		
	}
	public void fuir() {
		
	}
}
