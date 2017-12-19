package Autre;

import Personnage.Personnage;
import Personnage.Monstre;
import java.lang.Math;
import java.util.Scanner;

/**
 * Classe qui gére le combat
 * @author Axel Tétart
 *
 */
public class Combat {		
		
	private Scanner sc;

	/**
	 * Constructeur
	 * @param perso
	 * @param monstre
	 * La classe combat permet d'opposer notre personnage à divers monstres.
	 * Ce combat s'enclanche aléatoirement durant une exploration.
	 * Le systeme de combat est au tour par tour.
	 */
	public Combat(Personnage perso) {
		//partie génération du monstre de manière aléatoire
		Monstre monstre = null;
		//genere un nombre entre 0 et 1. Je multiplie par 10 pour gerer le taux d'apparition des monstres
		int rencontre = (int)(Math.random() * 10);
		switch (rencontre) {
		case 1: case 2: case 3:			//30% de chance d'apparaitre
			monstre = new Monstre("Colosse");
			break;
		case 4: case 5: case 6: case 7: //40% de chance d'apparaitre
			monstre = new Monstre("Goule");
			break;
		case 8: case 9:					//20% de chance d'apparaitre
			monstre = new Monstre("Carnigore");
			break;
		case 0:						//10% de chance d'apparaitre
			monstre = new Monstre("Sbire");
			break;

		default:
			System.out.println("Monstre inexistant");
		}
		
		
		//variable qui permet de savoir si on est en combat ou non
		int enCombat=1;
		//un entier qui stockera les actions de l'utilisateur
		int action;
		System.out.println("Début du combat !");
		System.out.println("Vous avez rencontré un(e) " + monstre.getNom());
		//tant que le combat est en cours
		while (enCombat !=0) {			
			//genere un nombre entre 0 et 1 pour determiner qui commence en premier
			//si tour = 1 c'est le tour du personnage 
			//si tour = 0 c'est le tour du monstre
			int tour = (int)(Math.random() * 2);
			if (tour != 0) {
				System.out.println("Vous avez l'initiative");
			}else {
				System.out.println(monstre.getNom() + " a l'initiative");
			}
			
			//affichage de l'etat des entités qui se battent avant le debut du combat
			perso.afficherEtat();
			monstre.afficherEtat();
			
			//tant que les entités qui combattent sont vivante, le combat continue
			while (enCombat == 1 && (perso.estVivant() && monstre.estVivant())) {
				System.out.println("\n");
				System.out.println("==========");
				System.out.println("=Tour : " + tour);
				System.out.println("==========");
				if (perso.getPointVie()<30) {
					System.out.println("Votre personnage est dans un sal etat !");
				}
				//tour du joueur
				if (tour%2 == 1) {
					perso.afficherEtat();
					//phase de choix de l'action par le joueur
					System.out.println("------------------------------");
					System.out.println("Choisissez une action !");
					System.out.println("1 - Attaquer");
					System.out.println("2 - Se defendre");
					System.out.println("3 - Fuir");
					
					sc = new Scanner(System.in);
					action = sc.nextInt();
					switch (action) {
					case 1:
						perso.attaquer(monstre);
						tour++;
						break;
					case 2:
						perso.seDefendre();
						tour++;
						break;
					case 3:
						if (perso.fuir()) {
							enCombat = 0;
							break;
						}else {
							System.out.println("Vous n'avez pas réussi à fuir !");
							tour++;
							break;
						}
					default :
						System.out.println("Commande inexistante");
					}
					
				//tour du monstre
				}else if (tour%2 == 0){
					monstre.afficherEtat();
					if (monstre.getBerserk() == 0 && monstre.getPointVie() <= monstre.getPalier()) {
						monstre.berserkMod();
						monstre.setBerserk(1);
					}
					monstre.attaquer(perso);
					tour++;
				}
			}
			enCombat = 0;
			if (monstre.getPointVie() <= 0) {
				System.out.println("Vous avez térassé " + monstre.getNom());
			}else {
				System.out.println("WASTED !");
			}
			System.out.println("Fin du combat !");
			
		}
	}
}

