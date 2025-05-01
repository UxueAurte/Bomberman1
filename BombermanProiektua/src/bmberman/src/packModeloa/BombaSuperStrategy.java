package bmberman.src.packModeloa;

public class BombaSuperStrategy implements BombaStrategy {
	@Override
    public void bombaJ(int x, int y) {
		MapaFactory.getGF().getEgungoMapa().getMapa()[y][x].setBomba(new BombaSuper(x,y));
    }
}
