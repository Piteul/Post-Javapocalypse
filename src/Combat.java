
import Personnage.Personnage;
import Personnage.Monstre;
import java.lang.Math;
import java.util.Scanner;
import java.io.*;
public class Combat {		
		
	public Combat(Personnage perso, Monstre monstre) {
		int enCombat=1;
		int action;
		int berserk = 0;
		System.out.println("Début du combat !");
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
			
			
			perso.afficherEtat();
			monstre.afficherEtat();
			
			//tant que les entités qui combattent sont vivante, le combat continue
			while (enCombat == 1 && (perso.estVivant() && monstre.estVivant())) {
				System.out.println("==========");
				System.out.println("=Tour : " + tour);
				System.out.println("==========");
				if (tour%2 == 1) {
					perso.afficherEtat();
					System.out.println("------------------------------");
					System.out.println("Choisissez une action !");
					System.out.println("1 - Attaquer");
					System.out.println("2 - Se defendre");
					System.out.println("3 - Fuir");
					
					Scanner sc = new Scanner(System.in);
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
					}
				}else if (tour%2 == 0){
					//tour du monstre
					monstre.afficherEtat();
					monstre.attaquer(perso);
					tour++;
					if (berserk == 0 && monstre.getPointVie() <= 30) {
						System.out.println("Attention, le monstre gagne en puissance !");
						monstre.berserkMod();
						berserk = 1;
					}
				}
			}
			enCombat = 0;
			System.out.println("Fin du combat !");
			
		}
	}
}

