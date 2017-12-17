package Objet;

/**
 * Classe qui définit les objets vitaux
 * @author Axel Tétart
 *
 */
public class ObjetVital extends Objet {
	private int pv_rendu;
	private int endurance_rendue;
	private String type;

	
	/**
	 * Constructeur
	 * @param nom
	 * @param quantite
	 */
	public ObjetVital(String nom, int quantite) {
		super(nom, quantite);
		attribution(nom);
	}

	/**
	 * Détermine les caractéristiques des objets
	 * @param nom
	 */
	public void attribution(String nom) {

		switch (nom) {

		case "Haricot en boite":
			setPv_rendu(20);
			setEndurance_rendue(2);
			setType("Nourriture");
			break;
		case "Eau usée":
			setPv_rendu(10);
			setEndurance_rendue(5);
			setType("Nourriture");
			break;
		case "Eau propre":
			setPv_rendu(30);
			setEndurance_rendue(15);
			setType("Nourriture");
			break;
		case "Soda":
			setPv_rendu(15);
			setEndurance_rendue(10);
			setType("Nourriture");
			break;
		case "Viande cru":
			setPv_rendu(10);
			setEndurance_rendue(5);
			setType("Nourriture");
			break;
		case "Viande cuite":
			setPv_rendu(40);
			setEndurance_rendue(20);
			setType("Nourriture");
			break;
		case "Sirop pour la toux":
			setPv_rendu(5);
			setEndurance_rendue(2);
			setType("Produit Pharmaceutique");
			break;
		case "Bandage":
			setPv_rendu(25);
			setEndurance_rendue(0);
			setType("Produit Pharmaceutique");
			break;
		case "Morphine":
			setPv_rendu(30);
			setEndurance_rendue(5);
			setType("Produit Pharmaceutique");
			break;
		case "Vitamine C":
			setPv_rendu(0);
			setEndurance_rendue(25);
			setType("Produit Pharmaceutique");
			break;
		default:
			System.out.println("Objet de soin inexistant");
			break;
		}

	}

	/*
	 * Getters & Setters
	 */
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}


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

}
