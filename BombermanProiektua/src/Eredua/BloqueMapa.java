package Eredua;

import java.util.Observable;
import java.util.Random;
import java.util.ArrayList;

public class BloqueMapa extends Observable {
	
	private static BloqueMapa blokeMapa = new BloqueMapa();
	//private ArrayList <Kuadrikula> arrayKuadrikula = new ArrayList <Kuadrikula>();
	private boolean jolasten = true;
	private int filak = 11;
	private int zutabeak = 17;
	private Kuadrikula[][] mapa;
	
	private static Bomberman bmbrman; 
	
	private int etsaiak = 0;
	
	
	
	public static BloqueMapa getBloqueMapa() {
		if(blokeMapa == null)
			blokeMapa = new BloqueMapa();
		return blokeMapa;
			
	}
	
	
	public BloqueMapa() {
		this.mapa = new Kuadrikula[filak][zutabeak];
		sortuMapa();
	}
	
	private void sortuMapa() {
		Random random = new Random();

		for (int i=0; i < filak; i++) {
			for (int j=0; j < zutabeak; j++) {
				
				if((i == 0 && j == 0) || (i == 0 && j == 1) || (i == 1 && j == 0)){
					mapa[i][j] = new Kuadrikula();
					mapa[i][j].setMota(0);
					
				}
				else if(i % 2 == 0 && j % 2 == 0) {
					mapa[i][j] = new Kuadrikula();
					mapa[i][j].setMota(2);
				}
				else {
					if(random .nextDouble()*100 > 40) {
						mapa[i][j] = new Kuadrikula();
						mapa[i][j].setMota(1);
					}
					else {
						if(random.nextDouble() * 100 > 90 && etsaiak < 6) {
							mapa[i][j] = new Kuadrikula();
							mapa[i][j].setMota(3);
							etsaiak++;
						}
						else {
							mapa[i][j] = new Kuadrikula();
							mapa[i][j].setMota(0);
						}	
					}
				}
			}
		}
	}
	
	public Kuadrikula[][] getMapa() {
        return mapa;
    }
	
	public void mugimendua(int dx, int dy) {
		Bomberman bmb = bmbrman;
		int posYBerria = bmbrman.getY() + dy;
		int posXBerria = bmbrman.getX() + dx;
		
		if (posXBerria >=0 && posXBerria < 18 && posYBerria >=0 && posYBerria < 11) {
			bmbrman.mugitu(dx, dy);
			notifyObservers();
			
		}
	}
}
	