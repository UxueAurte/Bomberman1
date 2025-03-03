package bmberman.src.packModeloa;

public class Kuadrikula {
	
	private Object kontenido;
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
