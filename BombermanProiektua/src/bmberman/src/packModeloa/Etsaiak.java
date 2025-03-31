package bmberman.src.packModeloa;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
public class Etsaiak {
	private int x, y;
	private boolean bizirik;
	private BloqueMapa mapa;
	private static final Random rand= new Random();
	
	
	public Etsaiak (int pX, int pY, BloqueMapa mapa) {
		this.x= pX;
		this.y= pY;
		this.bizirik= true;
		this.mapa=mapa;
		hasiMugitzen();
	}
	
	private void hasiMugitzen() {
		Timer timer= new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				mugimendua();
			}
		},1000,1000);
	}
	
	private void mugimendua() {
		int dx= 0;
		int dy= 0;
		int norabidea= rand.nextInt(4);
		
		switch(norabidea) {
		case 0: dy= -1; break; //GORA
		case 1: dy= 1; break; //BEHERA
		case 2: dx= -1; break; //EZKERRA
		case 3: dx= 1; break; //ESKUMA
		}
		int X = x+ dx;
		int Y = y+ dy;
		
		//Bidea libre badago mugitu
		
		if (mapa.barruanDago(X, Y)&& !mapa.getMapa()[Y][X].hasBloke()) {
			mapa.getMapa()[y][x].removeEtsaia();
			x= X;
			y= Y;
			mapa.getMapa()[y][x].setEtsaia(this);
		}
	}
	
	
	
	public void hilEtsaia() {
		this.bizirik=false;
	}
	
	// Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public boolean bizirikDago() { return bizirik; }
	
}
