package bmberman.src.packModeloa;

import java.util.Timer;
import java.util.TimerTask;

public class Bomba {
	
	private int y;
	private int x;
	private boolean active;
	private boolean bombermanHil = false;
	//private BloqueMapa mapa;
	
	public Bomba(int x, int y) {
		//this.mapa=pMapa;
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
		System.out.println("💥 BOOM! Bonbak eztanda egin du!");
		
		BloqueMapa mapa = BloqueMapa.getBloqueMapa();
		
		mapa.getMapa()[y][x].removeBomba();
		
	    mapa.getMapa()[y][x].setSua(new Sua());
	    
		expandEstanda(x,y);
		
		if (mapa.getMapa()[y][x].hasBomberman()) {
		        mapa.bombermanHil(x, y);
		  }
		
	}
	
	private void expandEstanda(int x, int y) {
		boolean bombermanHil = false;

	    // Verifica cada dirección para la expansión del fuego
	    boolean ezker = expandDirection(x, y, -1, 0);
	    System.out.println("Ezker: " + ezker+ (x-1));
	    
	    boolean eskubi = expandDirection(x, y, 1, 0);
	    System.out.println("Eskubi: " + eskubi+ (x+1));
	    
	    boolean gorantz = expandDirection(x, y, 0, -1);
	    System.out.println("Gorantz: " + gorantz + (y-1));
	    
	    boolean beherantz = expandDirection(x, y, 0, 1);
	    System.out.println("Beherantz: " + beherantz+ (y+1));

	    System.out.println("Gorantz: " + gorantz);
	    System.out.println("Beherantz: " + beherantz);
	    System.out.println("Ezker: " + ezker);
	    System.out.println("Eskubi: " + eskubi);

	    // Actualiza el estado de Bomberman
	    
	    BloqueMapa.getBloqueMapa().printMapa();
	    
	    bombermanHil = gorantz || beherantz || ezker || eskubi;
	    System.out.println(bombermanHil);
	    // Si Bomberman fue alcanzado, maneja el evento de muerte
	    if (bombermanHil) {
	        BloqueMapa.getBloqueMapa().bombermanHil(x, y);
	    }
	}

	private boolean expandDirection(int x, int y, int dx, int dy) {
		 for (int i = 1; i <= 1; i++) { // Default: 1 posizio zabaltzen du
	            int nx = x + (dx * i);
	            int ny = y + (dy * i);
		

	    // Verifica si la posición está dentro de los límites del mapa
	    if (nx >= 0 && nx < 17 && ny >= 0 && ny < 11) {
	        Kuadrikula celda = BloqueMapa.getBloqueMapa().getMapa()[ny][nx];

	        // Verifica si Bomberman está en la celda
	        if (celda.hasBomberman()) {
	        	
	            System.out.println("Bomberman hil da (" + nx + ", " + ny + ")");
	            celda.removeBomberman();
	            celda.setSua(new Sua());
	            return true; // Indica que Bomberman ha muerto
	        }

	        // Si no hay bloque duro, coloca el fuego
	        if (!celda.hasBlokeGogorra()) {
	            celda.setSua(new Sua());

	            // Si hay un bloque blando, destrúyelo y detén la expansión en esa dirección
	            if (celda.hasBlokeBiguna()) {
	                celda.kenduBlokeBiguna();
	            }
	        }
	    }
	    }
	    return false; // Si no hay Bomberman en esta dirección
	} 
		 
}
