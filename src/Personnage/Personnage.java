package Personnage;

import Objet.Arme;
import Objet.Objet;
import Objet.ObjetVital;

/**
 * Classe de l'entité personnage, qu'incarne le joueur
 * @author Axel Tétart
 *
 */
public class Personnage extends Humain {

	// CARACTERISTIQUES

	static String nom = "Stalker";
	static String status = "Allié";
	
	static int force = 15;
	static int defense = 20;
	String posture = "Offensif";
	double survie = 1.2; // à changer
	int endurance = 50;
	Objet inventaire[] = new Objet[10];
	Arme arme = new Arme("Couteau");

	/**
	 * Constructeur
	 */

	public Personnage(int p_pointVie) {
		super(nom, p_pointVie, status, force, defense);
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

	// a specifier
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

	public boolean estVivant() {
		return super.estVivant();
	}
	//exemple de polymorphisme
	public void afficherEtat() {
		super.afficherEtat();
		System.out.println("Force : " + force);
		System.out.println("Defense : " + defense);
	}
	
	public void equiperArme(Arme a) {
		this.arme = a;
		
	}
	public void attaquer(Monstre adversaire) {
		adversaire.recevoirDegat(force + arme.getDegat());
	}

	public void recevoirDegat(int degat) {
		if (posture == "Defensif") {
			degat-=5;
		}
		degat -= defense;
		if (degat < 0) {
			degat = 0;
		}
		this.pointVie = pointVie - degat;
		this.posture= "Offensif";
	}

	public void seDefendre() {
		this.posture = "Defensif";
	}

	public boolean fuir() {
		return Math.random() > 0.5;
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
	
	public Arme getArme() {
		return arme;
	}
	
	public void setArme(Arme arme) {
		this.arme = arme;
	}
	
	public String getPosture() {
		return posture;
	}
	
	public void setPosture(String posture) {
		this.posture = posture;
	}

}
