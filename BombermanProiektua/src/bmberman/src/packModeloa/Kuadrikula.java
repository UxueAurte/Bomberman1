package bmberman.src.packModeloa;

import java.util.Observable;

public class Kuadrikula extends Observable{
	
	private Object[] kontenido;
	private Bomberman bomberman;
	private Bloke bloke;
	private Bomba bomba;

	
	
	public Kuadrikula() {
		this.kontenido = null;
	}
	
	/*public int getMota() {
		return mota;
	}
	
	public void setMota(int pMota) {
		mota = pMota;
	}
	*/

	public void setBomber(Bomberman bomberman) {
		// TODO Auto-generated method stub
		this.kontenido = bomberman;
	}
	
	public void setBloque(Bloke bloke) {
		this.kontenido = bloke;
	}
	
	public void setBomba(Bomba bomba) {
		this.kontenido = bomba;
	}
	
	public void setNull() {
		this.kontenido = null;
	}
	
	public Object getObjetua() {
		return this.kontenido;
	}
	}
