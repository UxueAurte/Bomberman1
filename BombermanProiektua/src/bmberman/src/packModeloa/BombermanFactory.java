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
	
	public Bomberman createBomberman(String type) {
		Bomberman bomberman = null;
		if (type.equals("White")) {
			bomberman = new WhiteBomberman(0,0);
		} else {
			bomberman = new BlackBomberman(0,0);
		}
		return bomberman;
	}
	
}

