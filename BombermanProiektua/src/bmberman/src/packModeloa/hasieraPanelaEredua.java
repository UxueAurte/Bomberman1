package bmberman.src.packModeloa;

import java.util.List;
import java.util.Observable;


public class hasieraPanelaEredua extends Observable  {
	private static hasieraPanelaEredua nHP = new hasieraPanelaEredua();
	private String nireBomberman = "White";
	private String partidaMota= "c";
	//private static final List<Bomberman> bomberman;
	
	
	public static hasieraPanelaEredua getHP() { 
		if (nHP == null) {
			nHP = new hasieraPanelaEredua();
		}
		return nHP;
	}
	
	public String getNireBomberman() { 
		return nireBomberman; 
	}
	
    public String getPartidaMota() { 
    	return partidaMota; 
    }
    
	public void setNireBomberman(String bomberman) {
		this.nireBomberman = bomberman;
		System.out.println(this.nireBomberman);
		
		setChanged();
        notifyObservers();
    }
	
	public void setPartidaMota(String mota) {
	    if (mota == null || (!mota.equals("c") && !mota.equals("s") && !mota.equals("e"))) {
	        throw new IllegalArgumentException("Tipo de partida inválido: " + mota);
	    }
	    this.partidaMota = mota;
	    setChanged();
	    notifyObservers("partidaMota");
	}
	

}
