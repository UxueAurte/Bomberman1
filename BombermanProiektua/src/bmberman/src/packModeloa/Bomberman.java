package bmberman.src.packModeloa;

public class Bomberman {
	
	private int posY;
	private int posX;
	private Bomba bomba;
	private int bombak = 10;
	
	public Bomberman(int y, int x) {
		posY = y;
		posX = x;;
		
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
	
	public Bomba getBomba() {
		return bomba;
	}
	
	public int getBombak() {
		return bombak;
	}
	
	public void kenduBomba() {
		bombak --;
	}
}
