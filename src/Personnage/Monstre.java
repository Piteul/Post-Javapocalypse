package Personnage;
public class Monstre extends EtreVivant{

	public Monstre(String p_nom, int p_pointVie, int p_status, int p_force, int p_defense) {
		super(p_nom, p_pointVie, p_status, p_force, p_defense);
	}
	//le monstre inflige des degat au personnage d'un montant egal à sa force
	public void attaquer(Personnage adversaire) {
		adversaire.recevoirDegat(force);
	}
	
	public void berserkMod() {
		this.pointVie += 20;
		this.force += 20;
		
	}
	public void recevoirDegat(int degat) {
		this.pointVie = pointVie - degat + defense;
	}
}
