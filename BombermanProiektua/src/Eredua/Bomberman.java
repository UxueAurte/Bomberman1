package Eredua;

public abstract class Bomberman {
	
	private int posY;
	private int posX;
	private int bombak = 10;
	
	public Bomberman() {
		posY = 0;
		posX = 0;;
		
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

}
