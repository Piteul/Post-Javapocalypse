package Personnage;

/**
 * Classe qui détermine l'entité Monstre
 * 
 * @author Axel Tétart
 *
 */
public class Monstre extends EtreVivant {

	/**
	 * Constructeur
	 * 
	 * @param p_nom
	 * @param p_pointVie
	 * @param p_status
	 * @param p_force
	 * @param p_defense
	 */

	public Monstre(String p_nom) {
		super(p_nom);
	}

	/**
	 * Permet d'attribuer les caractéristiques du monstre d'après son nom
	 * 
	 * @param nom
	 */
	public void attribution(String nom) {
		switch (nom) {
		case "Colosse":
			setPointVie(50);
			setStatus("Ennemi");
			setForce(10);
			setDefense(20);
			break;
		case "Goule":

			break;
		case "Carnigore":

			break;

		default:
			System.out.println("Monstre inexistant");
		}

	}

	/**
	 * Inflige des dégats à l'adversaire le monstre inflige des dégât au personnage
	 * d'un montant égal à sa force
	 * 
	 * @param adversaire
	 */
	public void attaquer(Personnage adversaire) {
		adversaire.recevoirDegat(force);
	}

	/**
	 * Boost les compétences du monstre
	 */
	public void berserkMod() {
		this.force += 5;
		this.defense += 5;
	}

	/**
	 * Reçoit les dégâts de l'adversaire
	 * 
	 * @param degat
	 */
	public void recevoirDegat(int degat) {
		degat -= defense;
		if (degat < 0) {
			degat = 0;
		}
		this.pointVie = pointVie - degat;
	}

	/**
	 * Permet de savoir si le monstre est vivant ou non
	 */
	public boolean estVivant() {
		return super.estVivant();
	}

	/**
	 * Affiche le status du monstre
	 */
	public void afficherEtat() {
		super.afficherEtat();
		System.out.println("Status : " + status);
		System.out.println("--------------------");
	}
}
