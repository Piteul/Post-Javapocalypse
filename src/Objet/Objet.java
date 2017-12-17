package Objet;

/**
 * Classe qui définit les objets
 * @author Axel Tétart
 *
 */
public abstract class Objet {
	private String nom;
	private int quantite;
	
	/**
	 * Constructeur
	 * @param nom
	 * @param quantite
	 */
	
	public Objet (String nom, int quantite) {
		setNom(nom);
		setQuantite(quantite);
	}
	

	/*
	 * Getters & Setters
	 */


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	public String toString() {
		return this.getNom()+", Quantité : "+this.getQuantite();
	}
}
