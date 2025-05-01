package bmberman.src.packModeloa;

public class MapaFactory {  
	
	private static MapaFactory nMF = null;
	private BlokeMapa egungoMapa = null;
	private MapaFactory() {}
	
	public static MapaFactory getGF() {
		if (nMF == null) {
			nMF = new MapaFactory();
		}
		return nMF;
	}
	
	
    public static BlokeMapa createMapa(String mota, Bomberman bomberman) {
        BlokeMapa mapa = null;
        switch (mota) {
            case "c": 
                mapa = new MapaClassic(mota,bomberman);
                break;
            case "s": 
                mapa = new MapaSoft(mota,bomberman);
                break;
            case "e": 
                mapa = new MapaEmpty(mota,bomberman);
                break;
            default: 
                throw new IllegalArgumentException("Tipo de mapa no válido");
        }
        return mapa;
    }
    
    public BlokeMapa getEgungoMapa() {
    	return egungoMapa;
    }
}
