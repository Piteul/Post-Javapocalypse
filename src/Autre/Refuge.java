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
	int capacite_par_emplacement = 99;
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
		int nb = (int) (Math.random() * 3);

		System.out.println();
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
				faireFeu();
				break;
			case 3:
				System.out.println("La fonction de gestion de coffre n'a pas entierement été implémantée...");
				// gestionCoffre();
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
		if (feu == true) {
			System.out.println("Vous tentez de vous reposer, tant bien que mal en vous approchant du feu.\n");
			p.setEndurance(50);
			p.setPointVie(p.getPointVie() + 20);
		} else {
			System.out.println("Vous avez eu du mal a vous reposer sans un bon feu ...\n");
			p.setEndurance(30);
		}
	}

	public void faireFeu() {
		if (feu == true) {
			System.out.println("Il y a déjà un feu d'allumé...");
		} else {
			if (p.chercheObjet("Allumette") != -1 && p.chercheObjet("Combustible") != -1 && feu == false) {
				System.out.println("Vous avez allumé un feu");
				p.retirerObjet(p.chercheObjet("Allumette"));
				p.retirerObjet(p.chercheObjet("Combustible"));
				feu = true;
			} else {
				System.out.println("Vous n'avez pas les ressources requises pour faire un feu...");
			}
		}
	}

	public void quitter() {
		feu = false; // à chaque départ, le feu est éteint
		p.setDansRefuge(false);
	}

	// Partie Coffre

	public void gestionCoffre() {
		System.out.println("Menu du coffre");
		System.out.println("1 - Deposer un objet");
		System.out.println("2 - Echanger un objet de votre inventaire");
		System.out.println("3 - Prendre un objet du coffre");
		System.out.println("4 - Jeter un objet du coffre");
		int choix = scan.nextInt();
		scan.nextLine();

		do {
			System.out.println("-1 pour quitter ce menu.");
			afficherCoffre();
			menuCoffre();

			switch (choix) {
			case 1:
				ajouterCoffre();
				break;
			case 2:
				echangerCoffre();
				break;
			case 3:
				retirerCoffre();
				break;
			case 4:
				supprimerCoffre();
				break;
			default:
				System.out.println("Cette action n'existe pas");
			}
		} while (choix != -1);
	}

	public void ajouterCoffre() {
		int deja_ajoute = 0;
		int action;
		int nombre;
		scan = new Scanner(System.in);
		p.voirInventaire();
		System.out.println("Quel objet voulez vous déposer dans le coffre ?\n-1 pour quitter.");
		action = scan.nextInt();
		// si le coffre n'est pas plein ou que l'inventaire du personnage n'est pas vide
		if (poidsCoffre() != coffre.length || p.poidsInventaire() != 0) {

			if (action == -1) {
				System.out.println("Vous avez quitté le menu d'echange d'objet.");
			} else {
				while (action < 0 || action > p.poidsInventaire()) {
					System.out.println("Selectionnez un objet valide. Quel objet voulez vous déposer dans le coffre ?");
					action = scan.nextInt();
				}
				// si l'objet qu'on veut mettre dans le coffre existe deja
				for (int i = 0; i < poidsCoffre(); i++) {
					if (coffre[i].getNom() == p.getInventaire()[action].getNom()) {
						System.out.println("Combien voulez vous en déposer ?");
						nombre = scan.nextInt();
						while (nombre < 0 || nombre > p.getInventaire()[action].getQuantite()) {
							System.out.println("Selectionnez un nombre valide. Combien voulez vous en déposer ?");
							action = scan.nextInt();
						}
						// on ajoute l'objet au coffre
						Objet tmp = p.getInventaire()[action];
						tmp.setQuantite(tmp.getQuantite() - nombre);
						coffre[i].setQuantite(coffre[i].getQuantite() + p.getInventaire()[action].getQuantite());
						p.retirerObjet(action);
						p.ajouterInventaire(tmp);
						deja_ajoute = 1;
						System.out.println(tmp.getQuantite() + " " + tmp.getNom() + " ajouté(e)(s) au coffre");
						if (coffre[i].getQuantite() > capacite_par_emplacement) {
							coffre[i].setQuantite(99);
						}
					}
				}
				// si l'objet n'existe pas encore dans le coffre
				if (deja_ajoute != 1) {
					System.out.println("xDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
					System.out.println("Combien voulez vous en déposer ?");
					nombre = scan.nextInt();
					while (nombre < 0 || nombre > p.getInventaire()[action].getQuantite()) {
						System.out.println("Selectionnez un nombre valide. Combien voulez vous en déposer ?");
						action = scan.nextInt();
					}
					int b = placeLibre();
					int tmp = p.getInventaire()[action].getQuantite();
					coffre[b] = p.getInventaire()[action];
					coffre[b].setQuantite(nombre);

					if (coffre[b].getQuantite() > capacite_par_emplacement) {
						coffre[b].setQuantite(99);
					}

					p.getInventaire()[action].setQuantite(tmp - nombre);

					System.out.println(nombre + " " + coffre[b].getNom() + " ajouté(e)(s) au coffre");
					System.out.println(p.getInventaire()[action]);
					afficherCoffre();
				}
			}
		} else {
			System.out.println("L'objet " + p.getInventaire()[action].getNom()
					+ " n'a pas été ajouté au coffre...\nL'inventaire est vide ou le coffre est plein !");
		}
	}

	public void echangerCoffre() {
		int action_coffre;
		int action_inventaire;
		if (p.poidsInventaire() == 0 && poidsCoffre() == 0) {
			System.out.println("Le coffre est vide ou votre inventaire est vide.");
		} else {
			scan = new Scanner(System.in);
			p.voirInventaire();
			System.out.println("Quel objet de l'inventaire voulez vous echanger ?");
			action_inventaire = scan.nextInt();
			if (action_inventaire == -1) {
				System.out.println("Vous avez quitté le menu d'echange d'objet.");
			} else {
				while (action_inventaire < 0 || action_inventaire > p.poidsInventaire()) {
					System.out
							.println("Selectionnez un objet valide. Quel objet de l'inventaire voulez vous echanger ?");
					action_inventaire = scan.nextInt();
				}
				System.out.println("\n");
				afficherCoffre();
				System.out.println("Quel objet du coffre voulez vous echanger ?");
				action_coffre = scan.nextInt();
				if (action_coffre == -1) {
					System.out.println("Vous avez quitté le menu d'echange d'objet.");
				} else {
					while (action_coffre < 0 || action_coffre > poidsCoffre()) {
						System.out.println("Selectionnez un objet valide. Quel objet du coffre voulez vous echanger ?");
						action_coffre = scan.nextInt();
					}
					Objet tmp = coffre[action_coffre];
					coffre[action_coffre] = p.getInventaire()[action_inventaire];
					p.retirerObjet(action_inventaire);
					p.ajouterInventaire(tmp);
				}
			}
		}
	}

	public void retirerCoffre() {
		int action;
		int nombre;
		if (poidsCoffre() == 0 || p.poidsInventaire() == p.getInventaire().length) {
			System.out.println("Le coffre est vide ou votre inventaire est plein.");
		} else {
			System.out.println("Selectionnez l'objet que voulez prendre ?\n-1 pour quitter ce menu.");
			scan = new Scanner(System.in);
			action = scan.nextInt();
			if (action == -1) {
				System.out.println("Vous avez quitté le menu pour jeter un objet");
			} else if (action < 0 || action > coffre.length) {
				System.out.println("Emplacement qui ne correspond pas à une place du coffre!");
			} else if (coffre[action] == null) {
				System.out.println("C'est un emplacement libre !");
			} else if (coffre[action].getQuantite() > 1) {
				System.out.println("Combien voulez vous en retirer ?");
				scan = new Scanner(System.in);
				nombre = scan.nextInt();
				while (nombre < 0 || nombre > coffre[action].getQuantite()) {
					System.out.println("Entrez une valeur correct");
					nombre = scan.nextInt();
					System.out.println("Vous avez retiré " + nombre + " " + coffre[action].getNom() + " !");
				}

				// manipulation qui permet d'ajouter le bon nombre d'objet dans l'inventaire
				// cela est du a l'implémentation de la classe objet
				int tmp = coffre[action].getQuantite();
				coffre[action].setQuantite(coffre[action].getQuantite() - (coffre[action].getQuantite() - nombre));
				p.ajouterInventaire(coffre[action]);
				System.out.println("Vous avez ajouté " + nombre + " " + coffre[action].getNom());
				coffre[action].setQuantite(tmp - nombre);

				if (coffre[action].getQuantite() == 0) {
					System.out.println(coffre[action].getNom() + " a été retiré !");
					coffre[action] = null;
					reorganiseCoffre(action);
				}
			} else {
				System.out.println(coffre[action].getNom() + " a été retiré !");
				p.ajouterInventaire(coffre[action]);
				coffre[action] = null;
				reorganiseCoffre(action);
			}
			afficherCoffre();
		}
	}

	// retire un element du coffre choisi par le joueur
	public void supprimerCoffre() {
		int action;
		int nombre;
		afficherCoffre();
		if (poidsCoffre() == 0) {
			System.out.println("L'inventaire est vide ...");
		} else {
			System.out.println("Selectionnez l'objet que voulez jeter ?\n-1 pour quitter ce menu.");
			scan = new Scanner(System.in);
			action = scan.nextInt();

			if (action == -1) {
				System.out.println("Vous avez quitté le menu pour jeter un objet");
			}
			// si l'indice de l'objet qu'on veut supprimé est incorrect
			else if (action < 0 || action > coffre.length) {
				System.out.println("Emplacement qui ne correspond pas à une place du coffre");
			} else if (coffre[action] == null) {
				System.out.println("C'est un emplacement libre !");

				// si l'objet qu'on veut supprimer est en quantité supérieur à 1, on demande de
				// combien il faut diminuer le nombre d'objet
			} else if (coffre[action].getQuantite() > 1) {
				System.out.println("Combien voulez vous en retirer ?");
				nombre = scan.nextInt();
				while (nombre < 0 || nombre > coffre[action].getQuantite()) {
					System.out.println("Entrez une valeur correct. Combien voulez vous en retirer ?");
					nombre = scan.nextInt();
					System.out.println("Vous avez retiré " + nombre + " " + coffre[action].getNom() + " !");
				}
				coffre[action].setQuantite(coffre[action].getQuantite() - nombre);
				if (coffre[action].getQuantite() == 0) {
					System.out.println(coffre[action].getNom() + " a été retiré !");
					coffre[action] = null;
					reorganiseCoffre(action);
				}
				// si l'objet n'existe qu'en un seul exemplaire, on le supprime simplement
			} else {
				System.out.println(coffre[action].getNom() + " a été retiré !");
				coffre[action] = null;
				reorganiseCoffre(action);
			}
			// affichage de l'inventaire apres la suppréssion
			afficherCoffre();
		}
	}

	// reourne le nombre d'emplacements utilisés dans le coffre
	public int poidsCoffre() {
		int compteur = 0;
		for (int i = 0; i < coffre.length; i++) {
			if (coffre[i] != null) {
				compteur++;
			}
		}
		return compteur;
	}

	public int placeLibre() {
		int i = 0;
		while (coffre[i] != null && i < coffre.length) {
			i++;
		}
		return i;
	}

	public void reorganiseCoffre(int a) {
		for (int i = a; i < coffre.length - 1; i++) {
			coffre[i] = coffre[i + 1];
		}
		coffre[coffre.length - 1] = null;
	}

	public void menuCoffre() {
		System.out.println("Que souhaitez-vous faire ?" + "\n1 - Ajouter un élément au coffre"
				+ "\n2 - Récupérer un élément du coffre" + "\n3 - Détruire un élément du coffre"
				+ "\n4 - Refermer le coffre");

	}

	public void afficherCoffre() {
		System.out.println("============================");
		System.out.println("= Contenu du coffre: ");
		for (int i = 0; i < coffre.length; i++) {
			if (coffre[i] != null) {
				System.out.println("= " + i + " - " + coffre[i]);
			}
		}
		System.out.println("============================\n");
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
