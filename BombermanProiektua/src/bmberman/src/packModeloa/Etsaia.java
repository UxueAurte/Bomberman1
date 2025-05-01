
package bmberman.src.packModeloa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
public class Etsaia {
	private int x, y;
	private boolean bizirik;
	
	public Etsaia(int px, int py) {
		this.x = px;
		this.y = py;
		this.bizirik = true;
	}

	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}
	
	public boolean bizirikDago() {
        return bizirik;
    }
	
	public void egiaztatuSua() {
        if (bizirik && MapaFactory.getGF().getEgungoMapa().getMapa()[y][x]
        		.hasSua().equals("1")) {
            hilEtsaia();  // Si hay fuego en la posición actual, el enemigo muere
        }
	}
        
    public void hilEtsaia() {
    	if (bizirik) {
            this.bizirik = false;
            BlokeMapa mapa = MapaFactory.getGF().getEgungoMapa();
            
            // Eliminar de la celda actual
            mapa.getMapa()[y][x].removeEtsaia();
            
            // La eliminación de la lista global ahora se maneja en el movimiento
            System.out.println("💀 Etsaia hil da en (" + x + ", " + y + ")");
            mapa.getEtsaiak().remove(this);
            if(mapa.getEtsaiak().isEmpty()) {
            	mapa.jokoaAmaitu();
            }
            
            // Forzar actualización visual
            mapa.getMapa()[y][x].setGelaxkaMota();
        }
    }
    
    public void setPosizioa(int pX, int pY) {
        this.x = pX;
        this.y = pY;
        egiaztatuSua();  // Cada vez que se actualiza la posición, verifica si hay fuego
    }
}