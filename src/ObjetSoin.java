
public class ObjetSoin {
	private String nom;
	private int pv_rendu;
	private int endurance_rendue;

	public ObjetSoin(String p_nom, int p_pv_rendu, int p_endurance_rendue) {
		setNom(p_nom);
		setEndurance_rendue(p_endurance_rendue);
		setPv_rendu(p_pv_rendu);
	}

	/*
	 * Getters & Setters
	 */

	public int getEndurance_rendue() {
		return endurance_rendue;
	}

	public void setEndurance_rendue(int endurance_rendue) {
		this.endurance_rendue = endurance_rendue;
	}

	public int getPv_rendu() {
		return pv_rendu;
	}

	public void setPv_rendu(int pv_rendu) {
		this.pv_rendu = pv_rendu;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
