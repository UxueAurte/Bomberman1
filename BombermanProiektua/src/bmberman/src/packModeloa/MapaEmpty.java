package bmberman.src.packModeloa;

import java.util.Random;

public class MapaEmpty extends BlokeMapa{
	 public MapaEmpty() {
		 super();
		 sortuMapa();
	    }
	 @Override
	 public void sortuMapa() {
		 Random random = new Random();
	 	    this.mapa = new Kuadrikula[11][17]; // Tamaño del mapa, ajustable si es necesario
	 	    for (int i = 0; i < filak; i++) {
	 	        for (int j = 0; j < zutabeak; j++) {
	 	            mapa[i][j] = new Kuadrikula();
	 	            if ((i == 0 && j == 0) || (i == 0 && j == 1) || (i == 1 && j == 0)) {
	 	                continue;
	 	            }else {
	 	            	if(random.nextInt(100) >= 95 && etsaikop < 10) {
	 	            		Etsaia etsaia = new Etsaia(j, i);
		                	etsaiak.add(etsaia);
		                	mapa[i][j].setEtsaia(etsaia);
		                    etsaikop++;
		                    System.out.println("Generando enemigos...");
		                    System.out.println("Etsaia añadido en posición (" + i + ", " + j + ")");
	 	            	}
	 	            }
	 	            mapa[i][j].setGelaxkaMota();
	 	        }
	 	    }
	 	    if (etsaikop < 10) {
	            for (int i = 0; i < filak; i++) {
	                for (int j = 0; j < zutabeak; j++) {
	                    if (etsaikop >= 10) break;

	                    // Saltar las posiciones reservadas y ocupadas
	                    if ((i == 0 && j == 0) || (i == 0 && j == 1) || (i == 1 && j == 0)) continue;
	                    if (mapa[i][j].getBloke() == null && mapa[i][j].getEtsaia() == null) {
	                        Etsaia etsaia = new Etsaia(j, i);
	                        etsaiak.add(etsaia);
	                        mapa[i][j].setEtsaia(etsaia);
	                        etsaikop++;
	                        System.out.println("Relleno forzado: etsaia en (" + i + ", " + j + ")");
	                    }
	                }
	            }
	 	    }
	 this.mapaInizializatu();
	 mapa[0][0].setBomberman(bmberman);
	 System.out.println("Mapa mota");
	 
	 }
}
	        