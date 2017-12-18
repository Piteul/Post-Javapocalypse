package Personnage;

import java.util.Scanner;

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
	static int defense = 5;
	String posture = "Offensif";
	double survie = 1.2;
	int endurance = 50;
	Objet inventaire[] = new Objet[10];
	Arme arme = new Arme("Couteau");
	private Scanner sc;
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
	
	//visualisation de l'inventaire
	public void voirInventaire() {
		System.out.println("Contenu de l'inventaire");
		for (int i =0; i < inventaire.length; i++) {
			if (inventaire[i] != null) {
				System.out.println(i + " - " + inventaire[i]);
			}
		}
	}
	//fonction d'ajout d'un objet dans l'inventaire
	public void ajouterInventaire(Objet o) {
		int i = placeLibre();
		if (i != -1) {
			inventaire[i] = o;
		}
	}
	
	//fonction pour retirer un objet de l'inventaire et liberer la place
	public void jeterObjet() {
		int action;
		int nombre;
		System.out.println("Quel objet voulez vous jetez ?");
		sc = new Scanner(System.in);
		action = sc.nextInt();
		//si la quantité de l'objet qu'on veut supprimé est incorrect on redemande
		if (action < 0 || action > inventaire.length) {
			System.out.println("Emplacement inaccesible de l'inventaire");
			jeterObjet();
		//s'il existe un objet avec un quantité supérieur à 1, on demande, combien d'objet retirer
		}else if(inventaire[action].getQuantite() > 1){
			System.out.println("Combien voulez vous en retirer ?");
			nombre = sc.nextInt();
			while (nombre < 0 || nombre > inventaire[action].getQuantite()) {
				System.out.println("Entrez une valeur correct");
				nombre = sc.nextInt();
				System.out.println("Vous avez retiré " + nombre + " " + inventaire[action].getNom() + " !");
			}
			inventaire[action].setQuantite(inventaire[action].getQuantite() - nombre);
			if (inventaire[action].getQuantite()==0) {
				System.out.println(inventaire[action] + " a été retiré !");
				inventaire[action]=null;
			}
		//si l'objet n'existe qu'en un seul exemplaire, on le supprime simplement
		}else{
			System.out.println(inventaire[action] + " a été retiré !");
			inventaire[action]=null;
			
		}
	}
	
	//renvoie l'indice d'une place libre dans l'inventaire
	public int placeLibre() {
		int i = 0;
		int indice = -1;
		while (inventaire[i] != null && i < inventaire.length) {
			i++;
		}
		indice = i;
		if (indice == -1) {
			System.out.println("L'inventaire est plein !");
		}
		return indice;
	}


	// a specifier
	public void utiliserObjet() {

	}

	public void seSoigner(ObjetVital p_objetVital) {
		pointVie += p_objetVital.getPv_rendu();
		endurance += p_objetVital.getEndurance_rendue();
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
		if (Math.random()*this.survie <=0.5) {
			System.out.println("Coup critique !");
			adversaire.recevoirDegat((force + arme.getDegat()) * 2);
		}else {
			adversaire.recevoirDegat(force + arme.getDegat());
		}
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
