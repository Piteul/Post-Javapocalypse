package Affichage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import Autre.Combat;
import Autre.Refuge;
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
	private boolean dansRefuge = true;
	Personnage perso = new Personnage(100);
	Refuge refuge = new Refuge(perso);
	Combat c;

	/**
	 * Constructeur
	 */
	public Jeu() {

		try {
			lectureFichier();
		} catch (IOException e) {
			e.printStackTrace();
		}
		refuge.affichePosition();
		menu();
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
		System.out.println("\nDéplacement" + "\n Haut -> z" + "\n Bas -> s" + "\n Gauche -> q" + "\n Droite -> d");
		System.out.println("1. Fouiller la zone");
		System.out.println("2. Inventaire");

		int nb = Outils.alea(0, 3);
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
	 * Gère l'affichage principal du jeu
	 */
	public void menu() {
		String choix;
		do {
			afficheGrille();
			System.out.println();
			System.out.println();
			afficheMenu();
			System.out.print("\nChoix : ");
			choix = scan.next();
			scan.nextLine();
			System.out.println();
			switch (choix) {
			case "z":
				System.out.println("Haut");
				break;
			case "1":
				break;
			case "2":
				System.out.println(choix);
				break;
			case "3":
				System.exit(0);
				break;

			}
		} while (choix != "3");

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
