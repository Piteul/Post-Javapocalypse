import Personnage.Personnage;
import Personnage.Monstre;
import Autre.Combat;
import java.lang.Math;

//import Affichage.Affichage;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Personnage p = new Personnage(100);
		Monstre m = null;
		//genere un nombre entre 0 et 1 (je multiplie par 4 et je le cast en int pour avoir : 0,1,2 ou 3)
		//je rajoute 1 parceque j'aime pas quand ca commence à 0
		int rencontre = (int)(Math.random() * 4)+1;
		switch (rencontre) {
		case 1:
			m = new Monstre("Colosse");
			break;
		case 2:
			m = new Monstre("Goule");
			break;
		case 3:
			m = new Monstre("Carnigore");
			break;
		case 4:
			m = new Monstre("Sbire");
			break;

		default:
			System.out.println("Monstre inexistant");
		}
		
		Combat C = new Combat (p,m);
		
	
		//Affichage aff = new Affichage();
	}

}
