import Personnage.Personnage;
import Personnage.Monstre;
import Autre.Combat;
import java.lang.Math;

//import Affichage.Affichage;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		//initialisation du personnage
		Personnage p = new Personnage(100);
		//simulation de combat
		Combat C = new Combat (p);
		
	
		//Affichage aff = new Affichage();
	}

}
