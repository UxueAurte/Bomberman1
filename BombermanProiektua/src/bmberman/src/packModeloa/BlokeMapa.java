package bmberman.src.packModeloa;

import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
import java.util.Arrays;
import java.util.List;

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
	
	
	public BlokeMapa() {
		this.mapa = new Kuadrikula[filak][zutabeak];
		System.out.println(hasieraPanelaEredua.getHP().getNireBomberman());
		this.bmberman = createBomberman(hasieraPanelaEredua.getHP().getNireBomberman());
        etsaienMugimendua();
        
	}
	
	public static BlokeMapa getBloqueMapa() {
		if (blokeMapa == null) {
			String tipoMapa = hasieraPanelaEredua.getHP().getPartidaMota();
			blokeMapa = MapaFactory.createMapa(tipoMapa);
			System.out.println(tipoMapa);

		}
		return blokeMapa;
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
		System.out.println("Mapa inizializatzen");
		setChanged();
		notifyObservers(new Object[] {"hasieratu"});
		IntStream.range(0, filak).forEach(i ->
	    IntStream.range(0, zutabeak).forEach(j ->
	        mapa[i][j].setGelaxkaMota()
	    )
	);
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
		            
		            copiaEtsaiak.stream()
		            	.filter(Etsaia::bizirikDago)
		            	.forEach(e -> mugituEtsaia(e));
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
	    
	    int[][] norabideak = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

	    List<int[]> aukeraLegalak = Arrays.stream(norabideak)
	    	    .map(d -> new int[]{x + d[0], y + d[1]})
	    	    .filter(coord -> barruanDago(coord[0], coord[1]))
	    	    .filter(coord -> {
	    	        Kuadrikula celda = mapa[coord[1]][coord[0]];
	    	        return celda.hasBlokeGogorra().equals("0")
	    	            && celda.hasBlokeBiguna().equals("0")
	    	            && celda.hasEtsaia().equals("0")
	    	            && celda.hasBomba().equals("0");
	    	    })
	    	    .collect(Collectors.toList());


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
	    IntStream.range(0, mapa.length).forEach(i -> {
	        IntStream.range(0, mapa[0].length).forEach(j -> {
	            Kuadrikula k = mapa[i][j];
	            String celda = 
	                k.hasSua().equals("1") ? "🔥" :
	                k.hasBomberman().equals("1") ? "👤" :
	                k.hasBlokeGogorra().equals("1") ? "▣" :
	                k.hasBlokeBiguna().equals("1") ? "⊞" :
	                k.hasEtsaia().equals("1") ?
	                    (k.getEtsaia().bizirikDago() ? "👹" : "💀") :
	                "▢";
	            System.out.print(celda + " ");
	        });
	        System.out.println();
	    });
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