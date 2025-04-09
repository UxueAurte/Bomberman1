package bmberman.src.packModeloa;

public class BombermanFactory {
	private static BombermanFactory nBombermanFactory;
	
	private BombermanFactory() {}
	public static BombermanFactory getBombermanFactory() {
		if(nBombermanFactory==null) {
			nBombermanFactory = new BombermanFactory();
		}
		return nBombermanFactory;
	}
	
	public static Bomberman createBomberman(String type) {
		Bomberman bomberman = null;
		if (type.equals("white")) {
			bomberman = new WhiteBomberman(0,0,BlokeMapa.getBloqueMapa());
		} else {
			bomberman = new BlackBomberman(0,0,BlokeMapa.getBloqueMapa());
		}
		return bomberman;
	}
}

