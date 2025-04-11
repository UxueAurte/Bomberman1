package bmberman.src.packModeloa;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Bomberman {

	private int posY;
	private int posX;
	private BombaStrategy pBomba;
	private boolean martxan = false;
	protected String nora;

	protected Bomberman(int y, int x, BlokeMapa mapa, BombaStrategy pB) {
		posY = y;
		posX = x;
		nora = "up";
		pBomba = pB; 

	}
	
	public void bombaJarri() {
		if(jarDezake()){
			pBomba.bombaJ(posX, posY);
			errekargatuBaldin();
		}
	}
	
	public String getNorabide() {
        return nora;
    }

	public int getMota() {
		return 1;
	}

	public int getX() {
		return this.posX;
	}

	public int getY() {
		return this.posY;
	}

	public void mugitu(int dx, int dy) {
		posY += dy;
		posX += dx;
	}

	public void setX(int x) {
		this.posX = x;
	}

	public void setY(int y) {
		this.posY = y;
	}

	public void setNorabide(String a) {
		this.nora = a;
		}

	protected abstract boolean jarDezake(); // Lógica en subclases
    protected abstract void errekargatuBaldin(); // Lógica en subclases
    
	protected abstract Object getImage();
}
