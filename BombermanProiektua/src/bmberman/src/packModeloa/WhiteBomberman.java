package bmberman.src.packModeloa;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class WhiteBomberman extends Bomberman{
	private int bombak;
	private final int MAX_BOMBAS = 10;
	private String color = "white";
	
	public WhiteBomberman(int py, int px, BlokeMapa mapa){
		super(py, px, mapa, new BombaNormalStrategy());
		this.bombak = MAX_BOMBAS;
	}

	@Override
	protected boolean jarDezake() {
		// TODO Auto-generated method stub
		return bombak > 0;
	}
	
	public String getImage() {
		Random r = new Random();
		int randomNumber = r.nextInt(4)+1;
        return "/resources/white" + nora + randomNumber + ".png";
    }

	@Override
	protected void errekargatuBaldin() {
		new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                bombak++;
                System.out.println("BlackBomberman ha recargado su bomba.");
            }
        }, 3000); // Recarga la bomba automáticamente después de 3 segundos
    }
		
	
	
	public void bombaJarri() {
		if(jarDezake()) {
			super.bombaJarri();
			bombak--;
			if (bombak == 0) {
				errekargatuBaldin();
			}
		}
	}
	

}
