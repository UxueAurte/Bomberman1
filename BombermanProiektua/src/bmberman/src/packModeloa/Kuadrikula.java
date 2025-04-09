package bmberman.src.packModeloa;

import java.util.Arrays;
import java.util.Observable;

import bmberman.src.packModeloa.Bloke;
import bmberman.src.packModeloa.BlokeGogorra;
import bmberman.src.packModeloa.BlokeBiguna;
import bmberman.src.packModeloa.BlokeMapa;
import bmberman.src.packModeloa.Bomba;
import bmberman.src.packModeloa.Bomberman;
import bmberman.src.packModeloa.Sua;

public class Kuadrikula extends Observable {

	private Bomberman bomberman;
	private Bloke bloke;
	private Bomba bomba;
	private Sua sutea;
	private Etsaia etsaia;
	//private Etsaia etsaia;
	private String[] zerDauka = new String[10];
	private int kont;

	public Kuadrikula() {
		this.bomberman = null;
		this.bomba = null;
		this.bloke = null;
		this.sutea = null;
		this.etsaia = null;
		this.kont = 0;
		
		
	}

	public void setGelaxkaMota() {
        zerDauka[0] = this.hasBomberman();
        zerDauka[1] = this.hasBomba();
        zerDauka[2] = this.hasBlokeBiguna();
        zerDauka[3] = this.hasBlokeGogorra();
        zerDauka[4] = this.hasSua();
        if (this.bomberman != null) {
            zerDauka[5] = (String) this.bomberman.getImage();
        } else {
            // Si bomberman es null, asigna un valor por defecto o maneja el caso.
            zerDauka[5] = "/resources/defaultImage.png";
        // Actualizar a los observadores
        }
        zerDauka[6] = this.hasEtsaia();
        setChanged();
        notifyObservers(zerDauka);
        
    }

	public String hasEtsaia() {
        if(etsaia != null) {
        	return "1";
        }else {
        	return "0";
        }
        
    }

	public void setBomberman(Bomberman bomber) {
        this.bomberman = bomber;
        setGelaxkaMota();
        
        
        setChanged();
        notifyObservers(zerDauka);
    }

    public void setBomba(Bomba bomba) {
        this.bomba = bomba;
        setGelaxkaMota();
        setChanged();
        notifyObservers(zerDauka);
    }

    public void setBloke(Bloke bloke) {
        this.bloke = bloke;
        setGelaxkaMota();
        setChanged();
        notifyObservers(zerDauka);
    }

    public void setSua(Sua sua) {
    	if (this.sutea != null) {
            removeSua();
        }
    	this.sutea = sua;
		this.kont=1;
		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				removeSua();
			}
		}, 2000);
        setGelaxkaMota();
        setChanged();
        notifyObservers(zerDauka);
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

    public Sua getSua() {
        return sutea;
    }
    
    public Etsaia getEtsaia() {
        return etsaia;
    }

    public String hasBomberman() {
        if( bomberman != null) {
        	return "1";
        }else {
        	return "0";
        }
    }
    

    public String hasBomba() {
        if(bomba != null) {
        	return "1";
        }else {
        	return "0";
        }
        
    }

    public String hasBlokeBiguna() {
        if( bloke != null && bloke instanceof BlokeBiguna) {
        	return "1";
        }else {
        	return "0";
        }
    }

    public String hasBlokeGogorra() {
    	if( bloke != null && bloke instanceof BlokeGogorra) {
        	return "1";
        }else {
        	return "0";
        }
    }

    public String hasSua() {
    	 if(sutea != null) {
         	return "1";
         }else {
         	return "0";
         }
    }

    public void removeBomberman() {
        this.bomberman = null;
        setGelaxkaMota();
        setChanged();
        notifyObservers(zerDauka);
    }
    
    public void removeEtsaia() {
        this.etsaia = null;
        setGelaxkaMota();
        setChanged();
        notifyObservers(zerDauka);
    }


    // Eliminar Bomba de la celda
    public void removeBomba() {
        this.bomba = null;
        setGelaxkaMota();
        setChanged();
        notifyObservers(zerDauka);
    }

    // Eliminar Bloke (genérico)
    public void removeBloke() {
        if (hasBlokeBiguna().equals("1")) {
			bloke = null; // Bloke biguna ezabatu
			BlokeMapa.getBloqueMapa().setBlokeKop();
			System.out.println(BlokeMapa.getBloqueMapa().getBlokeKop());
			if (BlokeMapa.getBloqueMapa().getBlokeKop() == 0) {
				BlokeMapa.getBloqueMapa().jokoaAmaitu();
			}
        }
        setGelaxkaMota();
        setChanged();
        notifyObservers(zerDauka);
    }

    // Eliminar fuego (Sua) de la celda
    public void removeSua() {
    	if (kont > 0) {
			kont--;
			if (kont == 0) {
				this.sutea = null;
			}
		}
        setGelaxkaMota();
        setChanged();
        notifyObservers(zerDauka);
    }

	public void setEtsaia(Etsaia etsaia1) {
        this.etsaia = etsaia1;
        setGelaxkaMota();
        setChanged();
        notifyObservers(zerDauka);
		
	}
}
