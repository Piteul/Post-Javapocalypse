package Affichage;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import Autre.Combat;
import Autre.Météo;
import Autre.Refuge;
import Objet.Arme;
import Objet.ObjetDivers;
import Objet.ObjetVital;
import Outils.Outils;
import Personnage.Personnage;

/**
 * Classe qui gère le fonctionnement global du jeu et son affichage
 * 
 * @author Axel Tétart
 *
 */
public class Jeu {

	private int dimX;
	private int dimY;
	private Case grille[][];
	private Scanner scan = new Scanner(System.in);
	Outils outils = new Outils();
	Personnage perso;
	Refuge refuge;
	Combat combat;
	Météo meteo;

	/**
	 * Constructeur
	 */
	public Jeu() {
		perso = new Personnage(100);
		perso.ajouterInventaire(new ObjetDivers("Combustible", 2));
		perso.ajouterInventaire(new ObjetDivers("Allumette", 5));
		perso.ajouterInventaire(new Arme("Pistolet"));
		perso.ajouterInventaire(new ObjetVital("Eau usée",2));
		
		refuge = new Refuge(perso);
		meteo = new Météo(perso);
		demarrer();
	}

	public void demarrer() {
		try {
			lectureFichier();
		} catch (IOException e) {
			e.printStackTrace();
		}

		sortirRefuge();
		menuJeu();
	}

	/**
	 * Permet de lire le fichier carte.txt et générer la carte qui sera définit
	 * dedans La première ligne correspond au nombre de ligne qu'aura la grille
	 * (hauteur) La deuxième ligne correspond à la longueur de chaque ligne
	 * (longueur) Le reste est la composition de la grille et qui doit respecter les
	 * dimensions données.
	 * 
	 * @throws IOException
	 */
	public void lectureFichier() throws IOException {
		int i = 0, j;
		int nbligne = 0;
		char[] tab;
		File file = new File("carte.txt");
		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				if (nbligne == 0) { // première ligne correspond à la dimension X - nb de ligne de la grille
					setDimX(Integer.parseInt(line));
				} else if (nbligne == 1) { // deuxième ligne correspond à la dimension Y - longueur de chaque ligne
					setDimY(Integer.parseInt(line));
					grille = new Case[dimX][dimY];
				} else {
					tab = line.toCharArray();

					for (j = 0; j < dimY; j++) {
						grille[i][j] = new Case(tab[j]);

						if (grille[i][j].getSymbole() == '@') { // on récupère la position du refuge
							refuge.setPosX(i);
							refuge.setPosY(j);
						}
					}
					i++;
				}
				nbligne++;
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Initialise la grille
	 * 
	 * @param nb1
	 * @param nb2
	 */
	public void initGrille(int nb1, int nb2) {
		int i, j;
		dimX = nb1;
		dimY = nb2;
		grille = new Case[dimX][dimY];

		for (i = 0; i < dimX; i++) {
			for (j = 0; j < dimY; j++) {
				grille[i][j] = new Case('A');
			}
		}

	}

	/**
	 * Affiche la grille du jeu
	 */
	public void afficheGrille() {
		int i, j;
		System.out.println("\nCarte des Terres Dévastées : ");
		for (i = 0; i < dimX; i++) {
			System.out.println();
			for (j = 0; j < dimY; j++) {
				System.out.print(grille[i][j].getSymbole());
			}
		}
	}

	/**
	 * Affiche le menu de commande
	 */
	public void afficheMenu() {
		System.out.println("Que souhaitez-vous faire :");
		System.out.println("\nDéplacement");
		System.out.println("--------------------------------------------------");
		System.out.println("z. Haut" + "		s. Bas" + "		q. Gauche" + "		d. Droite");
		System.out.println("\nAction");
		System.out.println("--------------------------------------------------");
		System.out.println("1. Fouiller la zone 		2. Ouvrir le sac à dos");
		

		int nb = outils.alea(0, 3);
		switch (nb) {
		case 0:
			System.out.println("3. Mettre la table pour les asticots");
			break;
		case 1:
			System.out.println("3. Exhaler son âme");
			break;
		case 2:
			System.out.println("3. Aérer ses tripes");
			break;

		}
	}

	/**
	 * Regroupe les multiples fonctions d'affichages
	 */
	public void affichageGlobal() {
		afficheGrille();
		System.out.println();
		System.out.println();
		meteo.afficherMeteo();
		System.out.println("Vous êtes en " + grille[perso.getPosX()][perso.getPosY()].getDescription() + ".");
		System.out.println(perso.toString());
		System.out.println();
		afficheMenu();
		System.out.print("\nChoix : ");
	}

	/**
	 * Gère l'affichage principal du jeu
	 */
	public void menuJeu() {
		String choix;
		do {

			// si le joueur est sur la case refuge
			if (perso.getPosX() == refuge.getPosX() && perso.getPosY() == refuge.getPosY()) {
				perso.setDansRefuge(true);
				do {
					refuge.menuRefuge();
				} while (perso.isDansRefuge());
				grille[refuge.getPosX()][refuge.getPosY()].symboleOriginal();
				sortirRefuge();
				meteo = new Météo(perso);
			}

			affichageGlobal();
			choix = scan.next();
			scan.nextLine();
			System.out.println();

			// condition de fin
			if (!(perso.estVivant())) {
				System.out.println("Vous êtes mort !");
				choix = "3";
			}
			switch (choix) {
			case "z":
			case "s":
			case "q":
			case "d":
				if (perso.getEndurance() > 0) { // se déplacer nécessite de l'endurance
					deplacement(choix);
				} else {
					System.out.println("Plus assez d'endurance pour se déplacer.");
				}
				break;
			case "1":
				fouiller();
				break;
			case "2":
				gererInventaire();
				break;
			case "3":
				System.out.println("GAME OVER");
				System.exit(0);
				break;
			default:
				System.out.println("Commande inexistante");
			}
		} while (choix != "3");

	}

	/**
	 * Gère l'affichage et gestion des méthodes de l'inventaire
	 */
	public void gererInventaire() {
		String choix;
		int nb;
		do {
			perso.voirInventaire();
			System.out.println();
			System.out.println("1 - Utiliser objet");
			System.out.println("2 - Jeter un objet de l'inventaire ");
			System.out.println("3 - Refermer le sac à dos ");
			choix = scan.next();
			scan.nextLine();
			System.out.println();

			switch (choix) {
			case "1":
				System.out.println("Emplacement : ");
				nb = scan.nextInt();
				scan.nextLine();
				System.out.println();
				perso.utiliserObjet(perso.getInventaire()[nb], nb);
				break;
			case "2":
				perso.jeterObjet();
				break;
			default :
				System.out.println("Commande inexistante");

			}
		} while (choix.compareTo("3") != 0);
	}

	/**
	 * Fonctionne qui détermine de quel côté sortira le héros du refuge
	 */
	public void sortirRefuge() {
		int x = refuge.getPosX();
		int y = refuge.getPosY();

		if (grille[x][y + 1] == null) { // si refuge côté droit, perso sort du côté gauche
			grille[x][y - 1].placerPerso();
			perso.setPosition(x, y - 1);
		} else { // sinon sort par défaut du côté droit
			grille[x][y + 1].placerPerso();
			perso.setPosition(x, y + 1);
		}

	}

	/**
	 * Gère visuellement le déplacement du personnage
	 * 
	 * @param d
	 */
	public void deplacement(String d) {
		int x = perso.getPosX();
		int y = perso.getPosY();

		switch (d) {
		case "z": // haut
			if (x - 1 < 0) {
				System.out.println("Impossible d'aller plus haut");
			} else {
				grille[x][y].symboleOriginal(); // on remet l'ancien symbole
				grille[x - 1][y].placerPerso(); // la nouvelle case obtient le symbole du personnage
				perso.setPosition(x - 1, y); // on met à jour la position du personnage
				meteo.action();
				generationCombat();
			}
			break;
		case "s": // bas
			if (x + 1 >= getDimX()) {
				System.out.println("Impossible d'aller plus bas");
			} else {
				grille[x][y].symboleOriginal();
				grille[x + 1][y].placerPerso();
				perso.setPosition(x + 1, y);
				meteo.action();
				generationCombat();

			}
			break;
		case "q": // gauche
			if (y - 1 < 0) {
				System.out.println("Impossible d'aller à gauche");
			} else {
				grille[x][y].symboleOriginal();
				grille[x][y - 1].placerPerso();
				perso.setPosition(x, y - 1);
				meteo.action();
				generationCombat();

			}
			break;
		case "d": // droite
			if (y + 1 >= getDimY()) {
				System.out.println("Impossible d'aller à droite");
			} else {
				grille[x][y].symboleOriginal();
				grille[x][y + 1].placerPerso();
				perso.setPosition(x, y + 1);
				meteo.action();
				generationCombat();

			}
			break;
		default:
			System.out.println("La pirouette c'est qu'il n'y en a pas.");
		}
	}

	/**
	 * Gère la récupération d'objet dans la case
	 */
	public void fouiller() {
		int x = perso.getPosX();
		int y = perso.getPosY();

		if (perso.getEndurance() > 0) {

			if (grille[x][y].getObjet() == null) {
				System.out.println("Vous ne trouvez rien.");
			} else {
				System.out.println("Vous avez trouvé : " + grille[x][y].getObjet().getNom());
				perso.ajouterInventaire(grille[x][y].getObjet());
				grille[x][y].setObjet(null);
			}
			perso.setEndurance(perso.getEndurance() - 1);

		} else {
			System.out.println("Vous n'avez plus assez d'endurance pour fouiller la zone.");
		}
	}

	/**
	 * Génère aléatoirement un combat
	 */
	public void generationCombat() {
		double nb = Math.random();
		if (perso.estVivant() && grille[perso.getPosX()][perso.getPosY()].getDescription() == "Zone hostile") {
			if (nb < 0.15) {
				combat = new Combat(perso);
			}
		}
	}

	/**
	 * Getters et Setters
	 */
	public int getDimX() {
		return dimX;
	}

	public void setDimX(int dimX) {
		this.dimX = dimX;
	}

	public int getDimY() {
		return dimY;
	}

	public void setDimY(int dimY) {
		this.dimY = dimY;
	}
}
