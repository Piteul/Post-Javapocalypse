

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Affichage jeu = new Affichage(10,10);
		System.out.println();
		System.out.println("\033[31mRed\033[32m, Green\033[33m, Yellow\033[34m, Blue\033[0m");
		System.out.println("Hello \u001b[1;31mred\u001b[0m world!");
		System.out.println("\033[31mRed\033[32m, Green\033[33m, Yellow\033[34m, Blue\033[0m");
	    System.out.println("Hello \u001b[1;31mred\u001b[0m world!");
	}

}
