package bmberman.src.packModeloa;

public class BombaSuper extends Bomba {
    public BombaSuper(int x, int y) {
        super(x, y, new BombaSuperStrategy());
    }
    
    protected void expandEztanda() {
    	expandDirection(x, y, -1, 0, 10); // Izquierda
        expandDirection(x, y, 1, 0, 10);  // Derecha
        expandDirection(x, y, 0, -1, 10); // Arriba
        expandDirection(x, y, 0, 1, 10);  // Abajo
    }
}