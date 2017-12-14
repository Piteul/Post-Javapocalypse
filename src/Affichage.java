
public class Affichage {
	
	private int dimX;
	private int dimY;
	private String grille[][];
	
	public Affichage(int nb1, int nb2){
		
		initGrille(nb1,nb2);
		afficheGrille();
		
	}
	
	public void initGrille(int nb1, int nb2) {
		int i, j;
		dimX = nb1;
		dimY = nb2;
		grille = new String[dimX][dimY];
	
		for(i=0; i<dimX; i++) {
			for(j=0; j<dimY; j++) {
				grille[i][j] = "*";
			}
		}

	}
	
	public void afficheGrille() {
		int i, j;
		System.out.println("Grille : ");
		for(i=0; i<dimX; i++) {
			System.out.println();
			for(j=0; j<dimY; j++) {
				System.out.print(grille[i][j]);
			}
		}
	}
	
}
