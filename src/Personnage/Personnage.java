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
		System.out.println("============================");
		System.out.println("= Contenu de l'inventaire : ");
		for (int i =0; i < inventaire.length; i++) {
			if (inventaire[i] != null) {
				System.out.println("= " + i + " - " + inventaire[i]);
			}
		}
		System.out.println("============================\n");
	}
	
	//renvoie le nombre d'emplacement utilisé dans l'inventaire
	public int poidsInventaire() {
		int compteur=0;
		for (int i = 0; i < inventaire.length; i++) {
			if (inventaire[i] != null) {
				compteur++;
			}
		}
		return compteur;
	}
	
	//fonction d'ajout d'un objet dans l'inventaire
	public void ajouterInventaire(Objet o) {
		int deja_ajoute=0;
		if (poidsInventaire() != inventaire.length) {
			for (int i = 0; i < poidsInventaire(); i++) {
				if (inventaire[i].getNom() == o.getNom()) {
					int a = o.getQuantite();
					inventaire[i] = o;
					inventaire[i].setQuantite(inventaire[i].getQuantite() + a);
					deja_ajoute = 1;
					System.out.println(o.getQuantite() + " " + o.getNom() + " ajouté(e)(s) à l'inventaire");
				}
			}
			if (deja_ajoute != 1) {
				int b = placeLibre();
				if (b != -1) {
					try {
						inventaire[b] = o;
						System.out.println(o.getQuantite() + " " + o.getNom() + " ajouté(e)(s) à l'inventaire");
					}catch (Exception e){
						System.out.println("L'inventaire est plein !");
					}
				}			
			}
		}else {
			System.out.println("L'inventaire est plein !!");
		}
	}
	
	//fonction pour retirer un objet de l'inventaire et liberer la place
	public void jeterObjet() {
		int action;
		int nombre;
		voirInventaire();
		System.out.println("Selectionnez l'objet que voulez jeter ?");
		sc = new Scanner(System.in);
		action = sc.nextInt();

		//si l'indice de l'objet qu'on veut supprimé est incorrect
		if (action < 0 || action > inventaire.length) {
			System.out.println("Emplacement qui ne correspond pas à une place de l'inventaire");
		//s'il existe un objet avec un quantité supérieur à 1, on demande, combien d'objet retirer
		}else if (inventaire[action] == null){
			System.out.println("C'est un emplacement libre !");
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
				System.out.println(inventaire[action].getNom() + " a été retiré !");
				inventaire[action]=null;
				reorganise(action);
		}
		//si l'objet n'existe qu'en un seul exemplaire, on le supprime simplement
		}else {
			System.out.println(inventaire[action].getNom() + " a été retiré !");
			inventaire[action]=null;
			reorganise(action);			
		}
		voirInventaire();
	}
	
	
	public void reorganise(int a) {
		for (int i=a; i< inventaire.length-1; i++) {
			inventaire[a]= inventaire[a+1];
		}
		inventaire[inventaire.length-1]=null;
	}
	
	//renvoie l'indice d'une place libre dans l'inventaire
	public int placeLibre() {
		int i = 0;
		while (inventaire[i] != null && i < inventaire.length) {
			i++;
		}
		return i;
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
