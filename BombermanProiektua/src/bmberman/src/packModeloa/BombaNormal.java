package bmberman.src.packModeloa;

import bmberman.src.packModeloa.BlokeMapa;

public class BombaNormal extends Bomba {
    public BombaNormal(int x, int y) {
        super(x, y, new BombaNormalStrategy());
    }
    
    protected void expandEztanda() {
    	boolean bombermanHil = false;
    	boolean ezker = expandDirection(x, y, -1, 0, 1); // Izquierda
    	boolean eskubi = expandDirection(x, y, 1, 0, 1);  // Derecha
    	boolean gorantz = expandDirection(x, y, 0, -1, 1); // Arriba
    	boolean beherantz = expandDirection(x, y, 0, 1, 1);  // Abajo
    	
		BlokeMapa.getBloqueMapa().printMapa();
		
		bombermanHil = gorantz || beherantz || ezker || eskubi;
		System.out.println(bombermanHil);
		
		if (bombermanHil) {
			BlokeMapa.getBloqueMapa().bombermanHil(x, y);
		}

       }
}
