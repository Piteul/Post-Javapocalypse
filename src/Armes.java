
public class Armes {

	private int degat;
	private String nom;

	public Armes(String p_nom, int p_degat) {
		setNom(p_nom);
		setDegat(p_degat);
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

	public int getDegat() {
		return degat;
	}

	public void setDegat(int degat) {
		this.degat = degat;
	}
}
