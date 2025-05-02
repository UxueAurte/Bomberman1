package bmberman.src.packModeloa;

import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import bmberman.src.packBista.hasieraPanelaBista;
import bmberman.src.packModeloa.Bloke;
import bmberman.src.packModeloa.BlokeFactory;
import bmberman.src.packModeloa.Bomba;
import bmberman.src.packModeloa.Bomberman;
import bmberman.src.packModeloa.Etsaia;
import bmberman.src.packModeloa.Kuadrikula;
import bmberman.src.packBista.Jokoa;


import java.util.ArrayList;

public abstract class BlokeMapa extends Observable {
	
	private static BlokeMapa blokeMapa = null;
	private static Jokoa jokoa;

	//private ArrayList <Kuadrikula> arrayKuadrikula = new ArrayList <Kuadrikula>();
	protected boolean jolasten = true;
	protected int filak = 11;
	protected int zutabeak = 17;
	protected Kuadrikula[][] mapa;
	protected Bomberman bmberman;
	protected int bombak = 10;
	protected int blokeKop = 0;
	
	protected String nora;
	protected String tipoMapa;
	//private Etsaia etsaia;
	protected boolean isHandlingKeyPress = false;
	protected ArrayList<Etsaia> etsaiak= new ArrayList<>();
	protected int etsaikop = 0;
	
	public BlokeMapa(String pMota) {
		this.tipoMapa = pMota;
		this.mapa = new Kuadrikula[filak][zutabeak];
		this.bmberman = createBomberman(hasieraPanelaEredua.getHP().getNireBomberman());
		this.sortuMapa();
        etsaienMugimendua();
	}

	public Bomberman getBomberman(){
		return bmberman;
	}

	protected abstract void sortuMapa(); //Factory patroierako
	
	public Bomberman createBomberman(String type) {
		Bomberman nireBomberman = BombermanFactory.getBombermanFactory().createBomberman(type);
		return nireBomberman;
		
	}
	
	public void mapaInizializatu() {
		setChanged();
		notifyObservers(new Object[] { "hasieratu" });
		for (int i = 0; i < filak; i++) {
			for (int j = 0; j < zutabeak; j++) {
				mapa[i][j].setGelaxkaMota();
			}
		}
	}
	
	
	public void bombermanHil(int x, int y) {
		mapa[y][x].removeBomberman();
		jolasaGelditu();
		setChanged();
		notifyObservers(new Object[] { "hil" });
	}
	public void setBomberman(Bomberman b) {
		this.bmberman = b;
	}

	
	public void gehituBomba(int x, int y, Bomba b) {
	    mapa[x][y].setBomba(b);
	}


	public void hildaBomberman() {
		int j = bmberman.getX();
		int i = bmberman.getY();

		if (mapa[i][j].hasSua()== "1") {
			System.out.println("Bomberman hil da sua ikutu baitu!");
			System.out.println(mapa[i][j].hasSua());
			mapa[i][j].removeBomberman();
			bombermanHil(i, j);
		}
	}

	public void jolasaGelditu() {
		jolasten = false;
	}

	public boolean isjolasten() {
		return jolasten;
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

	public boolean barruanDago(int x, int y) {
	    return x >= 0 && x < mapa[0].length && y >= 0 && y < mapa.length;
	}

	public boolean hasBlokeGogorra(int x, int y) {
		return barruanDago(x, y) && mapa[y][x].hasBlokeGogorra()== "1";
	}

	public String getNora() {
		return nora;
	}

	public void mugimendua(int dx, int dy, String mov) {
	    bmberman.setNorabide(mov);

	    if (!jolasten) return;

	    int posYBerria = bmberman.getY() + dy;
	    int posXBerria = bmberman.getX() + dx;

	    if (posYBerria >= 0 && posYBerria < filak && posXBerria >= 0 && posXBerria < zutabeak
	            && mapa[posYBerria][posXBerria].hasBlokeGogorra().equals("0")
	            && mapa[posYBerria][posXBerria].hasBlokeBiguna().equals("0")) {
	        
	        // Verificar si hay un enemigo en la nueva posición
	        if (mapa[posYBerria][posXBerria].hasEtsaia().equals("1")) {
	            bombermanHil(posXBerria, posYBerria);
	            return;
	        }
	        
	        if (mapa[posYBerria][posXBerria].hasBomba().equals("0")) {
	            mapa[bmberman.getY()][bmberman.getX()].removeBomberman();
	            bmberman.mugitu(dx, dy);
	            mapa[posYBerria][posXBerria].setBomberman(bmberman);
	            
	            hildaBomberman();
	            printMapa();
	        }
	    }
	}
	
	public void etsaienMugimendua() {
		Timer etsaienTimer = new Timer();
	    etsaienTimer.scheduleAtFixedRate(new TimerTask() {
	        @Override
	        public void run() {
	        	if(jolasten) {
		            if (etsaiak.isEmpty()) {
		                System.out.println("No hay enemigos para mover.");
		                
		                return;
		            }
		            ArrayList<Etsaia> copiaEtsaiak = new ArrayList<>(etsaiak);
		            
		            for (Etsaia etsaia : copiaEtsaiak) {
		                if (etsaia.bizirikDago()) {
		                    mugituEtsaia(etsaia);
		                }
		            }
	        	}
		        }
	    }, 0, 1000); // Cada 1 segundo
	
	}
	
	private void mugituEtsaia(Etsaia etsaia) {
	    if (!etsaia.bizirikDago()) {
	        etsaiak.remove(etsaia);
	        return;
	    }

	    int x = etsaia.getX();
	    int y = etsaia.getY();
	    
	    // Verificar si el enemigo está junto a Bomberman
	    if (estaJuntoABomberman(x, y)) {
	        int bombermanX = bmberman.getX();
	        int bombermanY = bmberman.getY();
	        bombermanHil(bombermanX, bombermanY);
	        return;
	    }

	    ArrayList<int[]> aukeraLegalak = new ArrayList<>();
	    int[][] norabideak = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

	    for (int[] d : norabideak) {
	        int nx = x + d[0];
	        int ny = y + d[1];

	        if (barruanDago(nx, ny)) {
	            Kuadrikula celda = mapa[ny][nx];
	            
	            // Verificar si la celda destino tiene a Bomberman
	            if (celda.hasBomberman().equals("1")) {
	                bombermanHil(nx, ny);
	                return;
	            }
	            
	            if (celda.hasBlokeGogorra().equals("0") &&
	                celda.hasBlokeBiguna().equals("0") &&
	                celda.hasEtsaia().equals("0") &&
	                celda.hasBomba().equals("0")) {
	                aukeraLegalak.add(new int[]{nx, ny});
	            }
	        }
	    }

	    if (!aukeraLegalak.isEmpty()) {
	        int[] hautatua = aukeraLegalak.get(new Random().nextInt(aukeraLegalak.size()));
	        int nuevoX = hautatua[0];
	        int nuevoY = hautatua[1];
	        
	        mapa[y][x].removeEtsaia();
	        etsaia.setPosizioa(nuevoX, nuevoY);
	        
	        if (mapa[nuevoY][nuevoX].hasSua().equals("1")) {
	            etsaia.hilEtsaia();
	            etsaiak.remove(etsaia);
	        } else {
	            mapa[nuevoY][nuevoX].setEtsaia(etsaia);
	        }

	        mapa[y][x].setGelaxkaMota();
	        mapa[nuevoY][nuevoX].setGelaxkaMota();
	    }
	    
	    if (!etsaia.bizirikDago()) {
	        etsaiak.remove(etsaia);
	    }
	}

	private boolean estaJuntoABomberman(int x, int y) {
	    int[][] direcciones = {{0,1}, {1,0}, {0,-1}, {-1,0}};
	    for (int[] dir : direcciones) {
	        int nx = x + dir[0];
	        int ny = y + dir[1];
	        if (barruanDago(nx, ny) && mapa[ny][nx].hasBomberman().equals("1")) {
	            return true;
	        }
	    }
	    return false;
	}
	    


	public void printMapa() {
	    for (int i = 0; i < mapa.length; i++) {
	        for (int j = 0; j < mapa[0].length; j++) {
	            String celda = "▢";
	            if (mapa[i][j].hasSua().equals("1")) celda = "🔥";
	            else if (mapa[i][j].hasBomberman().equals("1")) celda = "👤";
	            else if (mapa[i][j].hasBlokeGogorra().equals("1")) celda = "▣";
	            else if (mapa[i][j].hasBlokeBiguna().equals("1")) celda = "⊞";
	            else if (mapa[i][j].hasEtsaia().equals("1")) {
	                Etsaia e = mapa[i][j].getEtsaia();
	                celda = e.bizirikDago() ? "👹" : "💀";
	            }
	            System.out.print(celda + " ");
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

	public ArrayList<Etsaia> getEtsaiak() {
		// TODO Auto-generated method stub
		return this.etsaiak;
	}	

}