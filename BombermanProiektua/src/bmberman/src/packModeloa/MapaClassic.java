package bmberman.src.packModeloa;

import java.util.Random;

public class MapaClassic extends BlokeMapa{
	public MapaClassic(String pMota) {
		super(pMota);
    }
	 @Override
	 public void sortuMapa() {
		 Random random = new Random();
		 this.mapa = new Kuadrikula[11][17];
	 	    for (int i = 0; i < filak; i++) {
	 	        for (int j = 0; j < zutabeak; j++) {
	 	            mapa[i][j] = new Kuadrikula();
	 	            if ((i == 0 && j == 0) || (i == 0 && j == 1) || (i == 1 && j == 0)) {
	 	                continue;
	 	            } else if (i % 2 == 0 && j % 2 == 0) {
	 	            	Bloke bloke = BlokeFactory.getBlokeFactory().createBloke("gogorra");
	 	                mapa[i][j].setBloke(bloke);
	 	            } else {
	 	            	if (random.nextInt(100) >= 40) {
	 	            		Bloke bloke = BlokeFactory.getBlokeFactory().createBloke("biguna");
	 		                mapa[i][j].setBloke(bloke);
	 	                    this.blokeKop++;
	 	            	} else if (random.nextInt(100) >= 90 && etsaikop<6) {
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
	        if (etsaikop < 6) {
	            for (int i = 0; i < filak; i++) {
	                for (int j = 0; j < zutabeak; j++) {
	                    if (etsaikop >= 6) break;

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
	 	}
	}

//aldaketak