package bmberman.src.packModeloa;

import java.util.Timer;
import java.util.TimerTask;

import bmberman.src.packModeloa.BlokeMapa;
import bmberman.src.packModeloa.Kuadrikula;
import bmberman.src.packModeloa.Sua;

public abstract class Bomba {
    protected int x, y;
    protected boolean active;
    private BombaStrategy bombaStrategy;

    protected Bomba(int x, int y,BombaStrategy bomba) {
        this.x = x;
        this.y = y;
        this.active = false;
        this.bombaStrategy=bomba;
        startCountdown();
        
    }
    
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

       
    public void bombaAldatu(BombaStrategy bomba) {
    	this.bombaStrategy=bomba;
    }

    public void startCountdown() {
        active = true;
        new Timer().schedule(new TimerTask() {
            public void run() {
                eztanda();
            }
        }, 3000);
    }
    
    public void eztanda() {
        System.out.println("💥 BOOM! Bonbak eztanda egin du en (" + x + ", " + y + ")!");
        BlokeMapa mapa = BlokeMapa.getBloqueMapa();
        mapa.getMapa()[y][x].setSua(new Sua()); // Centro de la explosión
        mapa.getMapa()[y][x].removeBomba();
        bombaStrategy.eztanda(x, y); // Usa la estrategia para expandir la explosión
    }
    
}


