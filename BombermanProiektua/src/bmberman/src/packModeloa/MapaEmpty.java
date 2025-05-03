package bmberman.src.packModeloa;

import java.util.Random;

public class MapaEmpty extends BlokeMapa{
	 public MapaEmpty(String pMota) {
			super(pMota);
	    }
	 @Override
	 public void sortuMapa() {
	        Random random = new Random();
	        for (int i = 0; i < filak; i++) {
	            for (int j = 0; j < zutabeak; j++) {
	                mapa[i][j] = new Kuadrikula();
	            }
	        }  
	        mapa[0][0].setBomberman(bmberman);
	        etsaikop = 0;
	        for (int i = 0; i < filak; i++) {
	        	for (int j = 0; j < zutabeak; j++) {
	                if ((i == 0 && j == 0) || (i == 0 && j == 1) || (i == 1 && j == 0)) {
	                	continue;
	                }
	                
	                double probabilitatea = random.nextDouble() * 100;	                
	                if (probabilitatea > 95 && etsaikop <10) {
	                	Etsaia etsaia = new Etsaia(j, i);
	                	etsaiak.add(etsaia);
	                	mapa[i][j].setEtsaia(etsaia);
	                	etsaikop++;
	                }
	            }
	        }
	        etsaienMugimendua();
	    }
	}