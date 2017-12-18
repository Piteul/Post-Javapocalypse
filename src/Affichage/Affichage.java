package Affichage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Affichage {

	private int dimX;
	private int dimY;
	private Case grille[][];

	public Affichage() {

		try {
			lectureFichier();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		afficheGrille();
		
		if(grille[0][0].getObjet() == null) {
			System.out.println("Pas d'objet");
		}else {
			System.out.println(grille[0][0].getObjet().getNom());
		}

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

	public void afficheGrille() {
		int i, j;
		System.out.println("Grille : ");
		for (i = 0; i < dimX; i++) {
			System.out.println();
			for (j = 0; j < dimY; j++) {
				System.out.print(grille[i][j].getSymbole());
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
