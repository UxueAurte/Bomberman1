package bmberman.src.packModeloa;

import java.util.Observable;

public class Kuadrikula extends Observable{
	
	private Bomberman bomberman;
	private Bloke bloke;
	private Bomba bomba;
	private boolean sutea;

	
	
	public Kuadrikula() {
		this.bomberman = null;
		this.bomba = null;
        this.bloke = null;
        this.sutea = false;
	}
	
	/*public int getMota() {
		return mota;
	}
	
	public void setMota(int pMota) {
		mota = pMota;
	}
	*/
	

    public boolean hasBomberman() {
        return bomberman != null;
    }

    public boolean hasBomba() {
        return bomba != null;
    }

    public boolean hasBloke() {
        return bloke != null;
    }
    
    public boolean hasBlokeGogorra() {
    	return bloke instanceof BlokeG; //BlokeGogorra bada true
    }
    
    public boolean hasBlokeBiguna() {
        return bloke instanceof BlokeS; // Bloke biguna bada, true
    }
    
    public void kenduBlokeBiguna() {
        if (hasBlokeBiguna()) {
            bloke = null; // Bloke biguna ezabatu
            setChanged();
            notifyObservers(null);
        }
    }

	public void setBomber(Bomberman bomberman) {
		// TODO Auto-generated method stub
		this.bomberman = bomberman;
		setChanged();
		notifyObservers(bomberman);
	}
	
	public void removeBomberman() {
        this.bomberman = null;
        setChanged();
		notifyObservers(bomberman);
    }
	
	public void setBloque(Bloke bloke) {
		this.bloke = bloke;
		setChanged();
		notifyObservers(bloke);
	}
	public void removeBloke() {
        this.bloke = null;
        setChanged();
		notifyObservers(bloke);
    }
	
	public void setBomba(Bomba bomba) {
		this.bomba = bomba;
		setChanged();
		notifyObservers(bomba);
	}
	
	public void removeBomba() {
        this.bomba = null;
        setChanged();
		notifyObservers(bomba);
    }
	
	public Bomberman getBomberman() {
        return bomberman;
    }

    public Bomba getBomba() {
        return bomba;
    }

    public Bloke getBloke() {
        return bloke;
    }

    public void setSutea(boolean sutea) {
        this.sutea = sutea;
        setChanged();
        notifyObservers(sutea); // Sua aktibatuta edo ez dagoen jakinarazi
    }

    public boolean isSutea() {
        return sutea;
    }

	}
