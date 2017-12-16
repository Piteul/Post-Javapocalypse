import Personnage.Personnage;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		System.out.println("\033[31mRed\033[32m, Green\033[33m, Yellow\033[34m, Blue\033[0m");
		System.out.println("Hello \u001b[1;31mred\u001b[0m world!");
		System.out.println();
		
		Personnage p = new Personnage();
		p.setPointVie(56);
		System.out.println(p.getPointVie());
		Refuge r = new Refuge(p);
		System.out.println(p.getPointVie());
		p.setPointVie(32);
		System.out.println(p.getPointVie());
		
	}

}
