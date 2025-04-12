package bmberman.src.packModeloa;

import java.util.List;
import java.util.Observable;


public class hasieraPanelaEredua extends Observable  {
	private static hasieraPanelaEredua nHP = new hasieraPanelaEredua();
	private Bomberman nireBomberman = null;
	private String partidaMota = "";
	//private static final List<Bomberman> bomberman;
	
	
	public static hasieraPanelaEredua getHP() { 
		if (nHP == null) {
			nHP = new hasieraPanelaEredua();
			
		}
		return nHP;
	}
	
	public Bomberman getNireBomberman() { 
		return nireBomberman; 
		}
	
	

	

    public String getPartidaMota() { 
    	return partidaMota; 
    	}
    
	public void setNireBomberman(Bomberman bomberman) {
		this.nireBomberman = bomberman;
		setChanged();
        notifyObservers();
    }
	
	public void setPartidaMota(String mota) {
        this.partidaMota = mota;
        setChanged();
        notifyObservers();
    }
	

}
