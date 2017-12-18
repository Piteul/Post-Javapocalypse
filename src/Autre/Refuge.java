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
	private int feu=0;

	/**
	 * Constructeur
	 */
	public Refuge(Personnage perso) {
		p = perso;
		outils = new Outils();
		//seReposer();
		faireFeu();
	}

	/**
	 * Menu d'affichage du refuge
	 */
	public void menu() {
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

		System.out.println();
		System.out.println("Que souhaitez-vous faire ?");

		nb = scan.nextInt();
		scan.nextLine();

		switch (nb) {
		case 1:
			System.out.println("");
			break;
		case 2:
			break;

		case 3:
			break;
		default:
			System.out.println("Vous procrastinez, comme l'a fait l'humanité toute entière pour en arriver là");
		}

	}

	/**
	 * Méthodes
	 */
	public void seReposer() {
		this.p.setPointVie(100);
	}

	public void faireFeu() {
		if (feu == 1) {
			System.out.println("Il y a deja un feu...");
		}else {
			for (int i =0; i < p.poidsInventaire(); i++) {
				if (p.chercheObjet("Alumette") != -1 && p.chercheObjet("Combustible") != -1) {
					feu = 1;
					System.out.println("Vous avez alumé un feu");
					p.getInventaire()[p.chercheObjet("Alumette")].setQuantite(p.getInventaire()[p.chercheObjet("Alumette")].getQuantite()-1);
					p.getInventaire()[p.chercheObjet("Combustible")].setQuantite(p.getInventaire()[p.chercheObjet("Combustible")].getQuantite()-1);
				}else {
					System.out.println("Vous n'avez pas les ressources requises pour faire un feu...");
				}
			}
		}
	}
	
	public Personnage quitter() {
		return p;
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
