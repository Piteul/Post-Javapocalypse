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
	// variable qui definit si le monstre est en mode berserk
	int berserk;
	// palier en dessous duquel le monstre passe en mode berserk
	int palier;

	public Monstre(String p_nom) {
		super(p_nom);
		attribution(p_nom);
	}

	/**
	 * Permet d'attribuer les caractéristiques du monstre à partir de son nom
	 * 
	 * @param nom
	 */
	public void attribution(String nom) {
		switch (nom) {
		case "Colosse":
			setPointVie(70);
			setStatus("Ennemi");
			setForce(10);
			setDefense(5);
			setBerserk(0);
			setPalier(0);
			break;
		case "Goule":
			setPointVie(25);
			setStatus("Ennemi");
			setForce(5);
			setDefense(5);
			setBerserk(0);
			setPalier(10);
			break;
		case "Carnigore":
			setPointVie(40);
			setStatus("Ennemi");
			setForce(15);
			setDefense(5);
			setBerserk(0);
			setPalier(40);
			break;
		case "Sbire":
			setPointVie(70);
			setStatus("Ennemi");
			setForce(0);
			setDefense(0);
			setBerserk(0);
			setPalier(0);
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
		System.out.println(this.getNom() + " attaque !");
		if (Math.random() <= 0.10) {
			System.out.println("Coup critique !");
			adversaire.recevoirDegat(force * 2);
		} else {
			adversaire.recevoirDegat(force);
		}
		;
	}

	/**
	 * Boost les compétences du monstre
	 */
	public void berserkMod() {
		this.force += 5;
		this.defense += 5;
		System.out.println("Attention, le monstre gagne en puissance !");
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
		if (degat == 0) {
			System.out.println("Vous ne lui faite aucun degat. Il serait plus sage de fuir...");
		} else {
			System.out.println(this.getNom() + " a reçu " + degat + " point(s) de degat !");
		}
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

	/**
	 * Getters et Setters
	 * 
	 * @return
	 */

	public int getBerserk() {
		return berserk;
	}

	public void setBerserk(int berserk) {
		this.berserk = berserk;
	}

	public int getPalier() {
		return palier;
	}

	public void setPalier(int palier) {
		this.palier = palier;
	}

}
