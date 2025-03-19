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
	private int blokeKop = 0;
	
	private String nora;
	

	//private Etsaia etsaia;
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
	}

	public void mapaInizializatu() {
		sortuMapa();
		setChanged();
		notifyObservers(new Object[] {"hasieratu"});
	}
	public Bomberman getBomberman(){
		return bmberman;
	}
	
	public void bombermanHil(int x, int y) {
			 jolasaGelditu();
		     setChanged();
		     notifyObservers(new Object[]{"hil"});
		        
	}
	
	public void hildaBomberman() {
	    int j = bmberman.getX();
	    int i = bmberman.getY();

	    if (mapa[i][j].hasSua()) { 
	        System.out.println("Bomberman hil da sua ikutu baitu!");
	        System.out.println(mapa[i][j].hasSua());
	        bombermanHil(i, j);
	    }
	}
	
	public void jolasaGelditu() {
		jolasten = false;
	}
	public boolean isjolasten() {
        return jolasten;
    }
	
	private void sortuMapa() {
		Random random = new Random();
		this.mapa = new Kuadrikula[11][17];
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
						this.blokeKop++;
					}
					else {
						if(random.nextDouble() * 100 > 90 && etsaiak < 6) {
							etsaiak++;
						
						}	
					}
				}
			}
		}
		mapa[0][0].setBomber(bmberman);
		System.out.println(this.blokeKop);
		
		;
	}
	
	public Kuadrikula[][] getMapa() {
        return mapa;
    }
	
	public int getBlokeKop() {
		return this.blokeKop;
	}
	
	public void setBlokeKop() {
		this.blokeKop--;
	}
	
	public void bombaJ() {
		
			int posY = bmberman.getY();
			int posX = bmberman.getX();
			if (!mapa[posY][posX].hasBomba()&& bmberman.getBombak() > 0) {
	            bmberman.kenduBomba();
	            Bomba bomba = new Bomba(posX, posY); // BloqueMapa pasatzen diogu bonbari
	            mapa[posY][posX].setBomba(bomba);
	            bomba.startCountdown(); // Bonba aktibatu (3 segundoko tenporizadorea)
			}
			
			printMapa();
	}	
	
	
	public boolean barruanDago(int x, int y) {
	    return x >= 0 && x < zutabeak && y >= 0 && y < filak;
	}
	


	
	public boolean hasBlokeGogorra(int x, int y) {
	    return barruanDago(x, y) && mapa[y][x].hasBloke() && mapa[y][x].getBloke() instanceof BlokeG;
	}
			
	
	public String getNora() {
		return nora;
	}


	public void mugimendua(int dx, int dy, String mov) {
		
		bmberman.setNorabide(mov);
		
		if (!jolasten) return;
		
			int posYBerria = bmberman.getY() + dy;
			int posXBerria = bmberman.getX() + dx;
			
			if (posYBerria >= 0 && posYBerria < filak && posXBerria >=0 && posXBerria < zutabeak && !mapa[posYBerria][posXBerria].hasBloke()) {
		         //Bomba jarriz gero, ezin gara berriro bomba ipini dugun tokira bueltatu
				if (!mapa[posYBerria][posXBerria].hasBomba()) {
			            
					//Kuadrikula hori hutsitu
					mapa[bmberman.getY()][bmberman.getX()].removeBomberman();
					System.out.println("Moviendo Bomberman a posición: (" + posYBerria + ", " + posXBerria + ")");
					
					//Bombermanen koordenatuak eguneratu
					bmberman.mugitu(dx, dy);
					
					//bombermana leku berrira mugitu
					mapa[posYBerria][posXBerria].setBomber(bmberman);
					System.out.println("mugitu garen lekuan sua " +mapa[posYBerria][posXBerria].hasSua());
					
					hildaBomberman();
					printMapa();
			
			}
		}
	
	}
	
	public void printMapa() {
	    for (int i = 0; i < mapa.length; i++) {
	        for (int j = 0; j < mapa[0].length; j++) {
	            if (mapa[i][j].hasSua()) {
	                System.out.print("🔥 ");
	            } else if (mapa[i][j].hasBomberman()) {
	                System.out.print("👤 ");
	            } else {
	                System.out.print("⬜ ");
	            }
	        }
	        System.out.println();
	    }
	}

	public void jokoaAmaitu() {
		// TODO Auto-generated method stub
		jolasten = false;
		setChanged();
	    notifyObservers(new Object[] { "jokoaAmaitu" });
	    System.out.println("Jokoa amaitu da! Zorionak!");
	}
		
}



//EN BLOQUE MAPA CREAR VARIOS METODOS CON LO QUE PUEDA PASAR DURANTE EL JUEO Y EN UN NOTIFYoBSERVERS METER UN STRIG CON LO QUE PASA 


