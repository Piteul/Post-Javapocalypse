package Autre;

import java.util.Scanner;

import Objet.Objet;
import Outils.Outils;
import Personnage.Personnage;

/**
 * Classe qui définit le refuge du joueur
 * 
 * @author Axel Tétart
 *
 */
public class Refuge {
	private static Scanner scan = new Scanner(System.in);
	Objet coffre[] = new Objet[30];
	Personnage p;
	Outils outils = new Outils();
	private int posX, posY;
	private boolean feu = false;

	/**
	 * Constructeur
	 */
	public Refuge(Personnage perso) {
		p = perso;
	}

	/**
	 * Affiche menu de commande du refuge
	 */

	public void afficheMenu() {
		int nb = outils.alea(0, 3);

		switch (nb) {
		case 0:
			System.out.println("Vous voilà enfin rentré dans votre refuge après une perilleuse exploration.");
			break;
		case 1:
			System.out.println(
					"Vous jetez vos affaires dans un coin de votre modeste abri, vous voilà enfin en sécurité");
			break;

		case 2:
			System.out.println(
					"Comme toujours vous vous demandez pourquoi il ne serait pas mieux d'en finir, puis vous fouillez votre fourbi à la recherche d'une bouteille, que vous ne trouverez jamais ou presque.");
			break;
		default:
			System.out.println("Bienvenue au refuge");
		}
		
		System.out.println(p.toString());
		System.out.println();
		System.out.println("Que souhaitez-vous faire :");
		System.out.println("1. Se reposer");
		System.out.println("2. Faire un feu");
		System.out.println("3. Ouvrir votre coffre");
		System.out.println("4. Quitter votre refuge");

	}

	/**
	 * Menu d'affichage du refuge
	 * 
	 * @return
	 */
	public void menuRefuge() {
		int choix;
		do {
			afficheMenu();
			System.out.print("\nChoix : ");
			choix = scan.nextInt();
			scan.nextLine();
			System.out.println();
			switch (choix) {
			case 1:
				seReposer();
				break;
			case 2:
				break;

			case 3:
				break;
			case 4:
				quitter();
				break;
			default:
				System.out.println("Vous procrastinez, comme l'a fait l'humanité toute entière pour en arriver là");
			}
		} while (choix != 4);

	}

	/**
	 * Méthodes
	 */
	public void seReposer() {
		System.out.println("Vous tentez de vous reposer, tant bien que mal.\n");
		this.p.setEndurance(30);
	}

	public void faireFeu() {
		if (feu == true) {
			System.out.println("Il y a déjà un feu d'allumé...");
		} else {
			for (int i = 0; i < p.poidsInventaire(); i++) {
				if (p.chercheObjet("Alumette") != -1 && p.chercheObjet("Combustible") != -1) {
					feu = true;
					System.out.println("Vous avez alumé un feu");
					p.getInventaire()[p.chercheObjet("Alumette")]
							.setQuantite(p.getInventaire()[p.chercheObjet("Alumette")].getQuantite() - 1);
					p.getInventaire()[p.chercheObjet("Combustible")]
							.setQuantite(p.getInventaire()[p.chercheObjet("Combustible")].getQuantite() - 1);
				} else {
					System.out.println("Vous n'avez pas les ressources requises pour faire un feu...");
				}
			}
		}
	}

	public void quitter() {
		feu = false; // à chaque départ, le feu est éteint
		p.setDansRefuge(false);
	}

	// Partie Coffre

	public void gestionCoffre() {

		int choix = scan.nextInt();
		scan.nextLine();

		do {
			afficherCoffre();
			menuCoffre();

			switch (choix) {
			case 1:
				// ajouterCoffre();
				break;
			case 2:
				// recupererCoffre();
				break;
			case 3:
				// detruireCoffre();
				break;
			default:
				System.out.println("Cette action n'existe pas");
			}

		} while (choix != 4);
	}

	public void menuCoffre() {
		System.out.println("Que souhaitez-vous faire ?" + "\n1 - Ajouter un élément au coffre"
				+ "\n2 - Récupérer un élément du coffre" + "\n3 - Détruire un élément du coffre"
				+ "\n4 - Refermer le coffre");

	}

	public void afficherCoffre() {
		int nb = 1;
		System.out.println("Contenu du coffre :");

		for (Objet obj : coffre) {
			System.out.println("\t" + nb + ". " + obj.toString());
		}
	}

	public void affichePosition() {
		System.out.println("Pos x : " + getPosX() + ", " + "Pos y : " + getPosY());
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

}
