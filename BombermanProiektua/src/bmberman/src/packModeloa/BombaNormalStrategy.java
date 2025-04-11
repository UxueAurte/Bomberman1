package bmberman.src.packModeloa;

public class BombaNormalStrategy implements BombaStrategy {
    @Override
    public void bombaJ(int x, int y) {
        BlokeMapa.getBloqueMapa().getMapa()[y][x]
        		.setBomba(new BombaNormal(x, y));
    }
}