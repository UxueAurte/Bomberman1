package bmberman.src.packModeloa;

public class BombaNormalStrategy implements BombaStrategy {
    @Override
    public void bombaJ(int x, int y) {
    	MapaFactory.getGF().getEgungoMapa().getMapa()[y][x]
        		.setBomba(new BombaNormal(x, y));
    }
}