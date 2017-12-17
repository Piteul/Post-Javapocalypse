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
	static int defense = 10;
	String posture = "Offensif";
	double survie = 1.2;
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

	/**
	 * Permet de savoir si le personnage est en vie
	 */
	
	public boolean estVivant() {
		return super.estVivant();
	}
	
	/**
	 * Affiche l'état du personnage
	 */
	
	public void afficherEtat() {
		super.afficherEtat();
		System.out.println("Force : " + force);
		System.out.println("Defense : " + defense);
	}
	
	/**
	 * Le personnage s'équipe d'une arme
	 */
	
	public void equiperArme(Arme a) {
		this.arme = a;	
	}
	
	/**
	 * Le personnage attaque un adversaire
	 */
	
	public void attaquer(Monstre adversaire) {
		System.out.println("Vous attaquez !");
		adversaire.recevoirDegat(force + arme.getDegat());
	}

	/**
	 * Reçoit les dégats de l'adversaire
	 * Prend en compte la posture et la défense du personnage puis l'attaque du monstre.
	 */
	
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
		System.out.println(this.getNom() + " a reçu " + degat + " point(s) de degat !");
	}
	
	/**
	 * Permet au personnage de se mettre en posture défensive
	 * Ce qui aura pour effet de reduire considérablement les degats !
	 */
	
	public void seDefendre() {
		this.posture = "Defensif";
	}
	
	/**
	 * Permet de faire un jet de fuite avec un bonus issue de sa capacité 'Survie'
	 * retourne Vrai ou Faux
	 */
	public boolean fuir() {
		return Math.random()*this.survie > 0.5;
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
