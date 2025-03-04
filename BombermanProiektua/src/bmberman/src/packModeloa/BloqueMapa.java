package bmberman.src.packModeloa;

import java.util.Observable;
import java.util.Random;
import java.util.ArrayList;

public class BloqueMapa extends Observable {
	
	private static BloqueMapa blokeMapa = null;
	//private ArrayList <Kuadrikula> arrayKuadrikula = new ArrayList <Kuadrikula>();
	private boolean jolasten = true;
	private int filak = 11;
	private int zutabeak = 17;
	private Kuadrikula[][] mapa;
	private Bomberman bmberman;
	private Bloke bloke;
	private Bomba bomba; 
	//private Etsaia etsaia;
	private boolean isHandlingKeyPress = false;
	
	
	private int etsaiak = 0;
	
	
	
	public static BloqueMapa getBloqueMapa() {
		if(blokeMapa == null) {
			blokeMapa = new BloqueMapa();
		}
		return blokeMapa;
			
	}
	
	
	public BloqueMapa() {
		this.mapa = new Kuadrikula[filak][zutabeak];
		this.bmberman = new Bomberman(0, 0);
		sortuMapa();
		
	}
	
	
	
	public Bomberman getBomberman()
	{
		return bmberman;
	}
	private void sortuMapa() {
		Random random = new Random();
		String hasieratu = new String();
		for (int i=0; i < filak; i++) {
			for (int j=0; j < zutabeak; j++) {
				mapa[i][j] = new Kuadrikula();
				if((i == 0 && j == 0) || (i == 0 && j == 1) || (i == 1 && j == 0)){
					mapa[i][j].setBloque(null);
					mapa[i][j].setBomber(null);
	                mapa[i][j].setBomba(null);
									}
				else if(i % 2 == 0 && j % 2 == 0) {
					mapa[i][j].setBloque(new BlokeG());
				}
				else {
					if(random .nextDouble()*100 > 40) {
						mapa[i][j].setBloque(new BlokeS());
					}
					else {
						if(random.nextDouble() * 100 > 90 && etsaiak < 6) {
							mapa[i][j].setBloque(null);
							mapa[i][j].setBomber(null);
			                mapa[i][j].setBomba(null);
							etsaiak++;
						}
						else {
							mapa[i][j].setBloque(null);
							mapa[i][j].setBomber(null);
			                mapa[i][j].setBomba(null);
						}	
					}
				}
			}
		}
		mapa[0][0].setBomber(bmberman);
		
		setChanged();
		notifyObservers(new Object[] {hasieratu});
		;
	}
	
	public Kuadrikula[][] getMapa() {
        return mapa;
    }
	
	public void bombaJ() {
		if (isHandlingKeyPress) return;
		isHandlingKeyPress = true;
		try {
			int posX = bmberman.getX();
			int posY = bmberman.getY();
			if (!mapa[posY][posX].hasBomba()&& bmberman.getBombak() > 0) {
	            bmberman.kenduBomba();
	            Bomba bomba = new Bomba(this, posX, posY); // BloqueMapa pasatzen diogu bonbari
	            mapa[posY][posX].setBomba(bomba);
	            bomba.startCountdown(); // Bonba aktibatu (3 segundoko tenporizadorea)
		        }
			//if(bmberman instance of WhiteBomberman) {
			
			
				//mapa[bmberman.getY()][bmberman.getX()].setBombaZ();
			//}else {
				//mapa[bmberman.getY()][bmberman.getX()].setNull();
				//mapa[bmberman.getY()][bmberman.getX()].setBombaB();
			setChanged();
			System.out.println("Notificando...");
			//System.out.println(mapa[posX][posY].getObjetua());
			System.out.println("BOMBAK: " +bmberman.getBombak());

			notifyObservers(new Object [] {"bomba", posY, posX});
		
			
		}
		finally {
			isHandlingKeyPress = false;
		}
		}
//	}
	
	public boolean barruanDago(int x, int y) {
	    return x >= 0 && x < zutabeak && y >= 0 && y < filak;
	}

	public void garbituSua() {
	    for (int i = 0; i < filak; i++) {
	        for (int j = 0; j < zutabeak; j++) {
	            mapa[i][j].setSutea(false);
	            notifyObservers(new Object [] {"sua", i, j});
	        }
	
	    }
	}
	
	public void setSutea(int x, int y) {
	    if (barruanDago(x, y)) { // Koordenatuak baliozkoak direla egiaztatu
	        mapa[y][x].setSutea(true); // Kuadrikulan sua jarri
	        notifyObservers(new Object [] {"sua", x, y});
	    }
	}
	
	public boolean hasBlokeGogorra(int x, int y) {
	    return barruanDago(x, y) && mapa[y][x].hasBloke() && mapa[y][x].getBloke() instanceof BlokeG;
	}

	public void mugimendua(int dy, int dx, String mov) {
		if (isHandlingKeyPress) return;
		isHandlingKeyPress = true;
		int i = 1;
		try {
			int posYBerria = bmberman.getY() + dy;
			int posXBerria = bmberman.getX() + dx;
			
			
			if (posXBerria >=0 && posXBerria < 18 && posYBerria >=0 && posYBerria < 12 && !mapa[posYBerria][posXBerria].hasBloke()) {
			
			//Kuadrikula hori hutsitu
				mapa[bmberman.getY()][bmberman.getX()].removeBomberman();
				
				System.out.println("Moviendo Bomberman a posición: (" + posXBerria + ", " + posYBerria + ")");

			
			//Bombermanen koordenatuak eguneratu
				bmberman.mugitu(dx, dy);
			
			//bombermana leku berrira mugitu
				mapa[posYBerria][posXBerria].setBomber(bmberman);
				//System.out.println(mapa[posYBerria][posXBerria].getObjetua());

			
			//notifikatu observer
				setChanged();
				System.out.println("Notificando...");
				notifyObservers(new Object [] {"mugimendua", posYBerria, posXBerria});	
		}
		} finally {
	        isHandlingKeyPress = false; // Liberar el control
	    }
}
}
	

//EN BLOQUE MAPA CREAR VARIOS METODOS CON LO QUE PUEDA PASAR DURANTE EL JUEO Y EN UN NOTIFYoBSERVERS METER UN STRIG CON LO QUE PASA 


