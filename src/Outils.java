import java.util.Random;

public class Outils {

	Random r = new Random();

	public Outils() {

	}

	/**
	 * Génère un nombre aléatoire compris entre min (inclus) et max (exclu)
	 * @param min
	 * @param max
	 * @return
	 */ 
	public int alea(int min, int max) {
		int low = 10;
		int high = 100;
		int result = r.nextInt(high - low) + low;
		return result;
	}

}
