package bmberman.src.packModeloa;

public class BombaSuperStrategy implements BombaStrategy {
	@Override
    public void bombaJ(int x, int y) {
        BlokeMapa.getBloqueMapa().getMapa()[y][x].setBomba(new BombaSuper(x,y));
    }
}
