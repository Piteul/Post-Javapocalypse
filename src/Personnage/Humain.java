package Personnage;

/**
 * Classe qui définit l'entité humain
 * @author Axel Tétart
 *
 */
public class Humain extends EtreVivant{

	/**
	 * Constructeur
	 * @param p_nom
	 * @param p_pointVie
	 * @param p_status
	 * @param p_force
	 * @param p_defense
	 */
	public Humain(String p_nom, int p_pointVie, String p_status, int p_force, int p_defense) {
		super(p_nom, p_pointVie, p_status, p_force, p_defense);
	}

}