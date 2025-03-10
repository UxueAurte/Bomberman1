package bmberman.src.packModeloa;

import java.util.Timer;
import java.util.TimerTask;

public class Bomba {
	
	private int y;
	private int x;
	private boolean active;
	private BloqueMapa mapa;
	
	public Bomba(BloqueMapa pMapa, int x, int y) {
		this.mapa=pMapa;
		this.active=false;
		this.x=x;
		this.y=y;
	}
	
	public void startCountdown() {
        active = true;

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                estanda();
            }
        }, 3000); // 3 segundo eta gero eztanda
    }
	
	public void estanda() {
		if (!active) return;
		System.out.println("💥 BOOM! Bonba eztanda egin du!");
		BloqueMapa mapa = BloqueMapa.getBloqueMapa();
		mapa.getMapa()[y][x].removeBomba();
	    mapa.setSutea(x, y);
		expandEstanda(x,y);
		
		//TIMER-a 2 segundo sua mantendu
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				clearEstanda();
			}
		},2000);
	}
	
	private void expandEstanda( int x, int y) {
		if (mapa == null) {  
	        System.err.println("Errorea: BloqueMapa ez dago hasieratuta!"); 
	        return;
	    }

		mapa.setSutea(x,y);
		expandDirection(x, y, -1, 0); // Gorantz
        expandDirection(x, y, 1, 0);  // Beherantz
        expandDirection(x, y, 0, -1); // Ezker
        expandDirection(x, y, 0, 1);  // Eskuina
        if (mapa.barruanDago(x, y) && !mapa.hasBlokeGogorra(x, y)) {
            mapa.setSutea(x, y);
        }
        
	}
	
	private void expandDirection(int x, int y, int dx, int dy) {
		 for (int i = 1; i <= 1; i++) { // Default: 1 posizio zabaltzen du
	            int nx = x + (dx * i);
	            int ny = y + (dy * i);

	            if (!mapa.barruanDago(nx, ny)) break; // Mapa barruan dagoen egiaztatu
	            if (mapa.getMapa()[ny][nx].hasBlokeGogorra()) break; // Bloke gogorra bada, eztanda ez da pasako
	            	mapa.setSutea(nx, ny); // Sua jarri
	            if (mapa.getMapa()[ny][nx].hasBlokeBiguna()) {
	                mapa.getMapa()[ny][nx].kenduBlokeBiguna(); // Bloke biguna suntsitu
	                break;
	            }
	            if (mapa.getMapa()[ny][nx].hasBomberman()) {
	            	Bomberman bomberman = mapa.getMapa()[ny][nx].getBomberman();
	                System.out.println("Bomberman ha muerto por el fuego en (" + nx + ", " + ny + ")");
	                BloqueMapa.getBloqueMapa().bombermanHil(nx, ny);  // Bomberman badago, hil
	            }
	        }
	    }
	
	private void clearEstanda() {
        System.out.println("🔥 Sua desagertu da");
        mapa.garbituSua(); // Mapatik sua kendu
        active = false;
    }
}
