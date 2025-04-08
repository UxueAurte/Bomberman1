package bmberman.src.packModeloa;

public class Sua {
    
    // Etsaia erre egiten da suak ukitzen badu
    public void etsaiakErre(int x, int y) {
        BloqueMapa mapa = BloqueMapa.getBloqueMapa();
        Kuadrikula kuad = mapa.getMapa()[y][x];

        if (kuad.hasEtsaia()) {  
            System.out.println("🔥 Etsaia erre da (" + x + ", " + y + ")!");
            mapa.ezabatuEtsaia(x, y);
            Etsaiak etsaia = kuad.getEtsaia();
            etsaia.hilEtsaia();
            eraginEtsaia(etsaia); // Sua ikusten duen momentuan etsaia hiltzea
            kuad.removeEtsaia();  // Etsaia ezabatzen dugu mapatik
        }
    }

    // Etsaia hiltzeko logika
    public void eraginEtsaia(Etsaiak etsaia) {
        if (etsaia != null) {
            etsaia.hilEtsaia(); // Etsaia hil egiten da suak ukituz gero
            
            System.out.println("🔥 Etsaia hil egin da suaren eraginez!");
        }
    }
}
