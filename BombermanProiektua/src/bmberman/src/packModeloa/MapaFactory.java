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
                mapa = new MapaClassic();
                mapa.mapaInizializatu();

                break;
            case "s": 
                mapa = new MapaSoft();
                mapa.mapaInizializatu();

                break;
            case "e": 
                mapa = new MapaEmpty();
                mapa.mapaInizializatu();

                break;
            default: 
                throw new IllegalArgumentException("Tipo de mapa no válido");
        }
        MapaFactory.egungoMapa = mapa;
        mapa.mapaInizializatu();

        System.out.println(mapa);
        return mapa;
    }
    
    public BlokeMapa getEgungoMapa() {
    	return egungoMapa;
    }
}