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

	private Scanner sc;
	// CARACTERISTIQUES

	static String nom = "Stalker";
	static String status = "Allié";
	
	static int force = 15;
	static int defense = 5;
	String posture = "Offensif";
	double survie = 1.2;
	int endurance = 30;
	Objet inventaire[] = new Objet[10];
	int capacite_par_emplacement=99;


	Arme arme = new Arme("Couteau");
	private int posX, posY; //position du joueur dans la grille;
	private boolean dansRefuge = false;


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
	
	/**
	 * visualisation de l'inventaire
	 * 
	 * 
	 */
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
	
	/**
	 * renvoie le nombre d'emplacement utilisé dans l'inventaire
	 * 
	 * 
	 */
	public int poidsInventaire() {
		int compteur=0;
		for (int i = 0; i < inventaire.length; i++) {
			if (inventaire[i] != null) {
				compteur++;
			}
		}
		return compteur;
	}
	
	/**
	 * fonction d'ajout d'un objet dans l'inventaire
	 * 
	 * @param o 
	 * 
	 */
	//
	public void ajouterInventaire(Objet o) {
		int deja_ajoute=0;
		//si l'inventaire n'est pas plein, on ajoute l'objet
		if (poidsInventaire() != inventaire.length) {
			for (int i = 0; i < poidsInventaire(); i++) {
				//s'il s'agit d'un objet qui existe deja dans l'inventaire, on rajoute juste les quantité
				if (inventaire[i].getNom() == o.getNom()) {
					int a = o.getQuantite();
					inventaire[i] = o;
					inventaire[i].setQuantite(inventaire[i].getQuantite() + a);
					deja_ajoute = 1;
					System.out.println(o.getQuantite() + " " + o.getNom() + " ajouté(e)(s) à l'inventaire");
					if (inventaire[i].getQuantite()>capacite_par_emplacement) {
						inventaire[i].setQuantite(99);
					}
				}
			}
			//si l'objet n'a pas encore été ajouté à l'inventaire apres le test précédant, on ajoute l'objet
			if (deja_ajoute != 1) {
				int b = placeLibre();
				inventaire[b] = o;
				if (inventaire[b].getQuantite()>capacite_par_emplacement) {
					inventaire[b].setQuantite(99);
				}
				System.out.println(o.getQuantite() + " " + o.getNom() + " ajouté(e)(s) à l'inventaire");
			}
		}else {
			System.out.println("L'objet " + o.getNom() + " n'a pas été ajouté...\nL'inventaire est plein !");
		}
	}
	
	/**
	 * fonction pour retirer un objet de l'inventaire et liberer la place
	 * 
	 * 
	 */
	public void jeterObjet() {
		int action;
		int nombre;
		voirInventaire();
		if (poidsInventaire()==0) {
			System.out.println("L'inventaire est vide ...");
		}else {
			System.out.println("Selectionnez l'objet que voulez jeter ?\n-1 pour quitter ce menu.");
			sc = new Scanner(System.in);
			action = sc.nextInt();
			
			if (action == -1) {
				System.out.println("Vous avez quitté le menu pour jeter un objet");
			}
			//si l'indice de l'objet qu'on veut supprimé est incorrect
			else if (action < 0 || action > inventaire.length) {
				System.out.println("Emplacement qui ne correspond pas à une place de l'inventaire");
			}else if (inventaire[action] == null){
				System.out.println("C'est un emplacement libre !");
				
			//si l'objet qu'on veut supprimer est en quantité supérieur à 1, on demande de combien il faut diminuer le nombre d'objet
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
			//affichage de l'inventaire apres la suppréssion
			voirInventaire();
		}
	}
	
	
	//retire un exemplaire d'un objet
	//utile pour les objet a usage unique ou objet que l'on doit utiliser qu'une fois pour certaines actions
	public void retirerObjet(int place) {
		inventaire[place].setQuantite(inventaire[place].getQuantite() -1 );
		if (inventaire[place].getQuantite() == 0) {
			System.out.println(1 + " " + inventaire[place].getNom() + " a été retiré de l'inventaire. Il ne vous en reste plus...");
			inventaire[place]=null;
			reorganise(place);
		}else {
			System.out.println(1 + " " + inventaire[place].getNom() + " a été retiré de l'inventaire. Il vous en reste " + inventaire[place].getQuantite());
		}
	}
	
	/**
	 * Réorganise l'inventaire
	 * @param a
	 * a est un indice a partir duquel on commence à réorganiser
	 * si l'inventaire est remplit et qu'il y a un emplacement vide entre les emplacement utilisé
	 * on decale les objets de la meme maniere qu'une liste
	 */
	public void reorganise(int a) {
		for (int i=a; i< inventaire.length-1; i++) {
			inventaire[i]= inventaire[i+1];
		}
		inventaire[inventaire.length-1]=null;
	}
	
	/**
	 * Cherche une place libre dans l'inventaire
	 * Renvoie l'indice d'une place libre dans l'inventaire
	 */
	public int placeLibre() {
		int i = 0;
		while (inventaire[i] != null && i < inventaire.length) {
			i++;
		}
		return i;
	}
	/**
	*retourne l'indice d'un objet qu'on cherche
	*
	*/
	public int chercheObjet(String nom) {
		for (int i = 0; i < poidsInventaire(); i++) {
			if (inventaire[i].getNom() == nom) {
				return i;
			}
		}
		return -1;
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
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public void setPosition(int x, int y) {
		this.posX = x;
		this.posY = y;
	}

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

	public int getCapacite_par_emplacement() {
		return capacite_par_emplacement;
	}

	public void setCapacite_par_emplacement(int capacite_par_emplacement) {
		this.capacite_par_emplacement = capacite_par_emplacement;
	}

	public boolean isDansRefuge() {
		return dansRefuge;
	}

	public void setDansRefuge(boolean dansRefuge) {
		this.dansRefuge = dansRefuge;
	}
}
