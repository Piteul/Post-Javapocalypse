package Personnage;

public abstract class EtreVivant {

	private String nom;
	private int pointVie;
	private int status;
	private int force;
	private int defense;

	public EtreVivant(String p_nom, int p_pointVie, int p_status, int p_force, int p_defense) {
		setNom(p_nom);
		setPointVie(p_pointVie);
		setStatus(p_status);
		setForce(p_force);
		setDefense(p_defense);
	}

	
	/*
	 * Getters & Setters
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getForce() {
		return force;
	}

	public void setForce(int force) {
		this.force = force;
	}
}