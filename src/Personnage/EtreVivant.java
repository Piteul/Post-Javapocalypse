package Personnage;

/**
 * Classe qui définit l'entité Etre Vivant
 *
 */
public abstract class EtreVivant {

	protected String nom;
	protected int pointVie;
	protected String status;
	protected int force;
	protected int defense;

	/**
	 * Constructeur
	 * @param p_nom
	 */
	public EtreVivant(String p_nom) {
		setNom(p_nom);
	}
	public EtreVivant(String p_nom, int p_pointVie, String p_status, int p_force, int p_defense) {
		setNom(p_nom);
		setPointVie(p_pointVie);
		setStatus(p_status);
		setForce(p_force);
		setDefense(p_defense);
	}
	
	/**
	 * Permet de savoir si vivant ou non
	 * @return
	 */
	public boolean estVivant() {
		if (pointVie > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Affiche le Nom et les Points de Vie
	 */
	public void afficherEtat() {
		System.out.println("--------------------");
		System.out.println("Nom : " + nom);
		System.out.println("Santé : " + pointVie + " PV");
	}
	
	/**
	 * Getters et Setters
	 * @return
	 */

	public int getDefense() {
		return defense;
	}


	public void setDefense(int defense) {
		this.defense = defense;
	}


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getPointVie() {
		return pointVie;
	}

	public void setPointVie(int pointVie) {
		this.pointVie = pointVie;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getForce() {
		return force;
	}

	public void setForce(int force) {
		this.force = force;
	}
}
