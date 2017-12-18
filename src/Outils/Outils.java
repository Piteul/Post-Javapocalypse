package Outils;
import java.util.Random;
/**
 * Classe qui fait office de boite à outils
 * @author Axel Tétart
 *
 */
public class Outils {

	static Random r = new Random();
	
	/**
	 * Constructeur
	 */

	public Outils() {

	}

	/**
	 * Génère un nombre aléatoire compris entre min (inclus) et max (exclu)
	 * @param min
	 * @param max
	 * @return
	 */ 
	public static int alea(int min, int max) {
		int low = min;
		int high = max;
		int result = r.nextInt(high - low) + low;
		return result;
	}

}
