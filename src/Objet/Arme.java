package Objet;
public class Arme extends Objet {

	private String nom;
	private int degat;
	private String type;

	
	public Arme(String nom) {
		super(nom, 1);			//nom et qté = 1		
		attribution(nom);
	}
	
	public void attribution(String nom) {
		switch (nom) {
		
		case "Pistolet":
			setDegat(10);
			setType("Arme à feu");
			break;
		case "Fusil à pompe":
			setDegat(30);
			setType("Arme à feu");
			break;
		case "Couteau":
			setDegat(5);
			setType("Arme blanche");
			break;
		case "Marteau":
			setDegat(10);
			setType("Arme blanche");
			break;
		case "Machette":
			setDegat(20);
			setType("Arme blanche");
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
