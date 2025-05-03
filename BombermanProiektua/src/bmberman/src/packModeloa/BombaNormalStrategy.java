package bmberman.src.packModeloa;

public class BombaNormalStrategy implements BombaStrategy {
    @Override
    public void eztanda(int x, int y) {
        expandDirection(x, y, -1, 0, 1); // Izquierda
        expandDirection(x, y, 1, 0, 1);  // Derecha
        expandDirection(x, y, 0, -1, 1); // Arriba
        expandDirection(x, y, 0, 1, 1);  // Abajo
    }

    private boolean expandDirection(int x, int y, int dx, int dy, int alcance) {
    	boolean continuar = true;
        for (int i = 1; i <= alcance && continuar; i++) {
            int nx = x + dx * i;
            int ny = y + dy * i;

            if (!BlokeMapa.getBloqueMapa().barruanDago(nx, ny)) {
                break;
            }

            Kuadrikula celda = BlokeMapa.getBloqueMapa().getMapa()[ny][nx];
            
            // Primero aplicar fuego en todas las celdas
            celda.setSua(new Sua());
            
            if (celda.hasEtsaia().equals("1")) {
                celda.getEtsaia().hilEtsaia();
            
            // Luego verificar obstáculos
            } else if (celda.hasBlokeGogorra().equals("1")) {
                continuar = false;
            } 
            else if (celda.hasBlokeBiguna().equals("1")) {
                celda.removeBloke();
            }else if(celda.hasBomberman().equals("1")){
            	BlokeMapa.getBloqueMapa().bombermanHil(nx, ny);
            }
            	          
            BlokeMapa.getBloqueMapa().printMapa();
        }
        return false;
    }
}
