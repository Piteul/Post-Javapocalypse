import Personnage.Personnage;
import Affichage.Jeu;
import Autre.Combat;
import Objet.Arme;
import Objet.ObjetVital;
import Outils.Outils;

//import Affichage.Affichage;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		//initialisation du personnage
		Personnage p = new Personnage(100);
		p.setPointVie(20);
		//simulation de combat
		//Combat C = new Combat (p);
		
		//test inventaire
		//p.ajouterInventaire(new ObjetVital("Eau usée", 100));
		//p.ajouterInventaire(new ObjetVital("Haricot en boite", 2));
		//p.voirInventaire();
		Jeu jeu = new Jeu();
	}

}
