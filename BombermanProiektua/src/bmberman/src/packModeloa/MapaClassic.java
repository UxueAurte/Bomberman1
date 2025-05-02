package bmberman.src.packModeloa;

import java.util.Random;

public class MapaClassic extends BlokeMapa{
	public MapaClassic(String pMota) {
		super(pMota);
    }
	 @Override
	 public void sortuMapa() {
	        Random random = new Random();
	        for (int i = 0; i < filak; i++) {
	            for (int j = 0; j < zutabeak; j++) {
	                mapa[i][j] = new Kuadrikula();
	                if ((i == 0 && j == 0) || (i == 0 && j == 1) || (i == 1 && j == 0)) continue;
	                else if (i % 2 == 0 && j % 2 == 0) {
	                    Bloke bloke = BlokeFactory.getBlokeFactory().createBloke("gogorra");
	                    mapa[i][j].setBloke(bloke);
	                } else if (random.nextDouble() * 100 > 40) {
	                    mapa[i][j].setBloke(BlokeFactory.getBlokeFactory().createBloke("biguna"));
	                    this.blokeKop++;
	                } else if (random.nextDouble() * 100 > 90 && etsaikop < 6) {
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