package bmberman.src.packModeloa;

public class MapaFactory {  
	
	private static MapaFactory nMF = null;
	private static BlokeMapa egungoMapa = null;
	private MapaFactory() {}
	
	public static MapaFactory getGF() {
		if (nMF == null) {
			nMF = new MapaFactory();
		}
		return nMF;
	}
	
	
    public static BlokeMapa createMapa(String mota) {
        BlokeMapa mapa = null;
        switch (mota) {
            case "c": 
                mapa = new MapaClassic(mota);
                break;
            case "s": 
                mapa = new MapaSoft(mota);
                break;
            case "e": 
                mapa = new MapaEmpty(mota);
                break;
            default: 
                throw new IllegalArgumentException("Tipo de mapa no válido");
        }
        MapaFactory.egungoMapa = mapa;
        return mapa;
    }
    
    public BlokeMapa getEgungoMapa() {
    	return egungoMapa;
    }
}
