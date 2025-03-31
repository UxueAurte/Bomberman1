package bmberman.src.packModeloa;

public class Sua {
	public void etsaiakErre(int x, int y) {
        BloqueMapa mapa = BloqueMapa.getBloqueMapa();
        Kuadrikula kuad = mapa.getMapa()[y][x];

        if (kuad.hasEtsaia()) {  
            System.out.println("🔥 Etsaia erre da (" + x + ", " + y + ")!");
            kuad.removeEtsaia(); // Etsaia ezabatzen dugu
        }
    }
}
