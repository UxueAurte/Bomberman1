package bmberman.src.packModeloa;

import java.util.Random;

public class MapaSoft extends BlokeMapa{
	public MapaSoft(String pMota, Bomberman bomberman) {
		super(pMota, bomberman);
    }
	 @Override
	 public void sortuMapa() {
	        Random random = new Random();
	        for (int i = 0; i < filak; i++) {
	            for (int j = 0; j < zutabeak; j++) {
	                mapa[i][j] = new Kuadrikula();
	                if ((i == 0 && j == 0) || (i == 0 && j == 1) || (i == 1 && j == 0)) continue;
	                else if (random.nextDouble() * 100 > 40) {
	                    mapa[i][j].setBloke(BlokeFactory.getBlokeFactory().createBloke("biguna"));
	                    this.blokeKop++;
	                } else if (random.nextDouble() * 100 > 90 && etsaikop < 8) {
	                    Etsaia etsaia = new Etsaia(j, i);
	                    etsaiak.add(etsaia);
	                    mapa[i][j].setEtsaia(etsaia);
	                    etsaikop++;
	                }
	            }
	        }
	        mapa[0][0].setBomberman(bmberman);
	        etsaienMugimendua();
	    }
	}