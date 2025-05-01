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
        BlokeMapa mapa = MapaFactory.getGF().getEgungoMapa();
        mapa.getMapa()[y][x].setSua(new Sua()); // Centro de la explosión
        mapa.getMapa()[y][x].removeBomba();
        expandEztanda();
                
    }

    protected abstract void expandEztanda(); // Diferentes estrategias de expansión

    protected boolean expandDirection(int x, int y, int dx, int dy, int alcance) {
        boolean continuar = true;
        for (int i = 1; i <= alcance && continuar; i++) {
            int nx = x + dx * i;
            int ny = y + dy * i;

            if (!MapaFactory.getGF().getEgungoMapa().barruanDago(nx, ny)) {
                break;
            }

            Kuadrikula celda = MapaFactory.getGF().getEgungoMapa().getMapa()[ny][nx];
            
            // Primero aplicar fuego en todas las celdas
            celda.setSua(new Sua());
            
            if (celda.hasEtsaia().equals("1")) {
                celda.getEtsaia().hilEtsaia();
            
            // Luego verificar obstáculos
            } else if (celda.hasBlokeGogorra().equals("1")) {
                continuar = false;
            } 
            else if (celda.hasBlokeBiguna().equals("1")) {
                celda.removeBloke();
            }else if(celda.hasBomberman().equals("1")){
            	MapaFactory.getGF().getEgungoMapa().bombermanHil(nx, ny);
            }
            	          
            MapaFactory.getGF().getEgungoMapa().printMapa();
        }
        return false;
    }
    
    
}


