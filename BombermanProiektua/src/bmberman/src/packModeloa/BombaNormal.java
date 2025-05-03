package bmberman.src.packModeloa;

import bmberman.src.packModeloa.BlokeMapa;

public class BombaNormal extends Bomba {
    public BombaNormal(int x, int y) {
        super(x, y, new BombaNormalStrategy());
    }
}
