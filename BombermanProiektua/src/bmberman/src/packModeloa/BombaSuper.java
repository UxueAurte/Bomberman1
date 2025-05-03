package bmberman.src.packModeloa;

public class BombaSuper extends Bomba {
    public BombaSuper(int x, int y) {
        super(x, y, new BombaSuperStrategy());
    }
}