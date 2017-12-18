import Personnage.Personnage;
import Affichage.Affichage;
import Autre.Combat;
import Objet.Arme;
import Objet.ObjetVital;

//import Affichage.Affichage;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		//initialisation du personnage
		Personnage p = new Personnage(100);
		p.setPointVie(20);
		//simulation de combat
		//Combat C = new Combat (p);
		
		p.ajouterInventaire(new ObjetVital("Eau usée", 2));
		p.ajouterInventaire(new ObjetVital("Haricot en boite", 2));
		p.ajouterInventaire(new ObjetVital("Eau propre", 2));
		p.ajouterInventaire(new ObjetVital("Soda", 2));
		p.ajouterInventaire(new ObjetVital("Viande cru", 2));
		p.ajouterInventaire(new ObjetVital("Vitamine C", 2));
		p.jeterObjet();
	}

}
