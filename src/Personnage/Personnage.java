package Personnage;

import Objet.Arme;
import Objet.Objet;
import Objet.ObjetVital;
import Objet.ObjetDivers;

public class Personnage extends Humain {

	// CARACTERISTIQUES

	static String nom = "Stalker";
	static int pointVie = 100;
	static int status = 0;
	static int force = 15;
	static int defense = 20;
	double survie = 1.2; // à changer
	int endurance = 100;
	Objet inventaire[] = new Objet[10];
	Arme p_arme;
	/**
	 * Constructeur
	 */

	public Personnage() {
		super(nom, pointVie, status, force, defense);		
	}

	/**
	 * Partie exploration
	 */

	// sur la carte
	public void seDeplacer() {

	}

	public void fouiller() {

	}

	public void voirInventaire() {

	}

	// dans l'inventaire
	
	//a specifier
	public void utiliserObjet() {
		
	}
	
	public void seSoigner(ObjetVital p_objetVital) {
		pointVie += p_objetVital.getPv_rendu();
		endurance += p_objetVital.getEndurance_rendue();
	}

	public void jeterObjet() {

	}

	/**
	 * Partie Combat
	 */

	public void attaquer(Monstre adversaire) {
		adversaire.recevoirDegat(force+p_arme.getDegat());
	}
	
	public void recevoirDegat(int degat) {
		pointVie = pointVie - degat + defense;
		defense = defense/2;
	}

	public void seDefendre() {
		defense = defense *2;
	}

	public void fuir() {

	}

	/**
	 * Getters et Setters
	 */
	public double getSurvie() {
		return survie;
	}

	public void setSurvie(double survie) {
		this.survie = survie;
	}

	public int getEndurance() {
		return endurance;
	}

	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}

	public Objet[] getInventaire() {
		return inventaire;
	}

	public void setInventaire(Objet[] inventaire) {
		this.inventaire = inventaire;
	}

}
