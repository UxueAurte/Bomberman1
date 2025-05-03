package bmberman.src.packModeloa;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class BlackBomberman extends Bomberman {
	private int bombak;
	private final int MAX_BOMBAS = 1;
	private String color = "black";
	private boolean kargatzen = false;
	
	public BlackBomberman(int py, int px){
		super(py, px);
		this.bombak = MAX_BOMBAS;
	}

	@Override
	protected boolean jarDezake() {
		// TODO Auto-generated method stub
		return bombak > 0;
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
		if (bombak > 0) {
			Bomba b = new BombaSuper(this.getX(), this.getY());
        	colocarBomba(b);
            bombak--;
            if (bombak == 0) {
                errekargatuBaldin();
            }
        }
    }

	@Override
	public String getImage() {
			Random r = new Random();
			int randomNumber = r.nextInt(4)+1;
	        return "/resources/black" + nora + randomNumber + ".png";
	    }
	
}
