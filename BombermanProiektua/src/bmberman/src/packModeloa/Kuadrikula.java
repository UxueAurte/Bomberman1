package bmberman.src.packModeloa;

import java.util.Observable;

public class Kuadrikula extends Observable {

	private Bomberman bomberman;
	private Bloke bloke;
	private Bomba bomba;
	private Sua sutea;
	private boolean[] zerDauka = new boolean[10];
	private int kont;
	private Etsaiak etsaia;

	public Kuadrikula() {
		this.bomberman = null;
		this.bomba = null;
		this.bloke = null;
		this.sutea = null;
		this.kont = 0;
	}

	private void setGelaxkaMota() {
		boolean[] a = new boolean[10];
		a[0] = this.hasBomberman();
		a[1] = this.hasBomba();
		a[2] = this.hasBlokeBiguna();
		a[3] = this.hasBlokeGogorra();
		a[4] = this.hasSua();

	}

	public boolean hasBomberman() {
		return bomberman != null;
	}

	public boolean hasBomba() {
		return bomba != null;
	}

	public boolean hasBloke() {
		return bloke != null;
	}

	public boolean hasSua() {
		return sutea != null;
	}

	public boolean hasBlokeGogorra() {
		return bloke instanceof BlokeG; // BlokeGogorra bada true
	}

	public boolean hasBlokeBiguna() {
		return bloke instanceof BlokeS; // Bloke biguna bada, true
	}

	public void kenduBlokeBiguna() {
		if (hasBlokeBiguna()) {
			bloke = null; // Bloke biguna ezabatu
			BloqueMapa.getBloqueMapa().setBlokeKop();
			System.out.println(BloqueMapa.getBloqueMapa().getBlokeKop());
			if (BloqueMapa.getBloqueMapa().getBlokeKop() == 0) {
				BloqueMapa.getBloqueMapa().jokoaAmaitu();
			}
			setChanged();
			notifyObservers(new Object[] { this.zerDauka });
		}
	}

	public void setSua(Sua sua) {
		this.sutea = sua;
		this.kont = 1;  // reset
		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				removeSua();
			}
		}, 2000);
		setChanged();
		notifyObservers(new Object[] { this.zerDauka });
	}


	

	public void removeSua() {
		if (kont > 0) {
			kont--;
			if (kont == 0) {
				this.sutea = null;
			}
		}
		setChanged();
		notifyObservers(new Object[] { this.zerDauka });
	}

	public void setBomber(Bomberman bomberman) {
		// TODO Auto-generated method stub
		this.bomberman = bomberman;
		setChanged();
		notifyObservers(new Object[] { this.zerDauka });
	}

	public void removeBomberman() {
		this.bomberman = null;
		setChanged();
		notifyObservers(new Object[] { this.zerDauka });
	}

	public void setBloque(Bloke bloke) {
		this.bloke = bloke;
		setChanged();
		notifyObservers(new Object[] { this.zerDauka });
	}

	public void removeBloke() {
		this.bloke = null;
		setChanged();
		notifyObservers(new Object[] { this.zerDauka });
	}

	public void setBomba(Bomba bomba) {
		this.bomba = bomba;
		setChanged();
		notifyObservers(new Object[] { this.zerDauka });
	}

	public void removeBomba() {
		this.bomba = null;
		setChanged();
		notifyObservers(new Object[] { this.zerDauka });
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

	public void setNull() {

	}
	public Etsaiak getEtsaia() {
	    return this.etsaia;
	}

	public boolean hasEtsaia() {
		boolean emaitza=this.etsaia != null && this.etsaia.bizirikDago();
		return emaitza;
	}

	public void setEtsaia(Etsaiak e) {
	    etsaia = e;
	    setChanged();
	    notifyObservers();
	}

	public void removeEtsaia() {
	    this.etsaia = null;
	    setChanged();
	    notifyObservers();
	}
}