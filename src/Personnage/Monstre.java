package Personnage;
public class Monstre extends EtreVivant{

	public Monstre(String p_nom, int p_pointVie, String p_status, int p_force, int p_defense) {
		super(p_nom, p_pointVie, p_status, p_force, p_defense);
	}
	//le monstre inflige des degat au personnage d'un montant egal à sa force
	public void attaquer(Personnage adversaire) {
		adversaire.recevoirDegat(force);
	}
	
	public void berserkMod() {
		this.force += 5;
		this.defense += 5;
	}

	public void recevoirDegat(int degat) {
		degat -= defense;
		if (degat < 0) {
			degat = 0;
		}
		this.pointVie = pointVie - degat;
	}

	public boolean estVivant() {
		return super.estVivant();
	}
	public void afficherEtat() {
		super.afficherEtat();
		System.out.println("Status : " + status);
		System.out.println("--------------------");
	}
}
