package Autre;

/**
 * Classe qui définit les différentes météos
 * @author Axel Tétart
 *
 */

public class Météo {
	private int cout_endurance;
	private int cout_point_vie;


	/**
	 * Constructeur
	 * @param nom
	 */
	
	public Météo (String nom) {
		attribution(nom);
	}
	
	
	/**
	 * Détermine les effet que la météo aura sur le personnage
	 * @param nom
	 */
	
	public void attribution(String nom) {
		switch (nom) {

		case "Vaste grisaille":
			setCout_endurance(0);
			setCout_point_vie(0);
			break;
		case "Pluie radioactive":
			setCout_endurance(0);
			setCout_point_vie(5);
			break;
		case "Grand froid":
			setCout_endurance(5);
			setCout_point_vie(0);
			break;
		case "Grêles tranchantes":
			setCout_endurance(2);
			setCout_point_vie(10);
			break;
		case "Soleil vert":
			setCout_endurance(2);
			setCout_point_vie(0);
			break;			
		default:
			System.out.println("Météo inexistante");
			break;
		}
	}
	
	/*
	 * Getters & Setters
	 */
	
	public int getCout_endurance() {
		return cout_endurance;
	}

	public void setCout_endurance(int cout_endurance) {
		this.cout_endurance = cout_endurance;
	}

	public int getCout_point_vie() {
		return cout_point_vie;
	}

	public void setCout_point_vie(int cout_point_vie) {
		this.cout_point_vie = cout_point_vie;
	}
	
}
