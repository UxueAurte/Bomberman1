package bmberman.src.packModeloa;

import java.util.ArrayList;
import java.util.List;
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
	    List<int[]> aukeraLegalak = new ArrayList<>();

	    // 4 norabide posible: {dx, dy}
	    int[][] norabideak = {
	        {0, -1},  // gora
	        {0, 1},   // behera
	        {-1, 0},  // ezker
	        {1, 0}    // eskuin
	    };

	    for (int[] d : norabideak) {
	        int nx = x + d[0];
	        int ny = y + d[1];

	        if (mapa.barruanDago(nx, ny)) {
	            Kuadrikula hurrengoa = mapa.getMapa()[ny][nx];
	            
	            // Hurrengoa bidea libre bada, eta ez badago bloke, etsaia, edo bomba
	            if (!hurrengoa.hasBloke() && !hurrengoa.hasEtsaia() && !hurrengoa.hasBomba()) {
	                aukeraLegalak.add(new int[]{nx, ny});
	            }

	        }
	    }

	    if (!aukeraLegalak.isEmpty()) {
	        int[] hautatua = aukeraLegalak.get(rand.nextInt(aukeraLegalak.size()));

	        // Mugimendua egin
	        mapa.getMapa()[y][x].removeEtsaia();
	        x = hautatua[0];
	        y = hautatua[1];
	        mapa.getMapa()[y][x].setEtsaia(this);

	        // **Egiaztatu** suaren kuadrikula bada, hiltzeko logika gehitzen da
	        if (mapa.getMapa()[y][x].hasSua()) {
	            hilEtsaia();  // Sua ukituz gero hil
	        }
	        BloqueMapa.getBloqueMapa().hildaBomberman();
	    }
	    // Bestela, ez dago mugitzerik (ez dago aukerarik)
	}


	
	
	
	public void hilEtsaia() {
		this.bizirik=false;
		mapa.ezabatuEtsaia(x, y);
		System.out.println("💀 Etsaia hil da!");
	}
	
	// Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public boolean bizirikDago() { return bizirik; }
	
}
