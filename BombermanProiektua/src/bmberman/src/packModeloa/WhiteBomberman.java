package bmberman.src.packModeloa;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class WhiteBomberman extends Bomberman{
	private int bombak;
	private final int MAX_BOMBAS = 1000;
	private String color = "white";
	private boolean kargatzen = false;
	
	public WhiteBomberman(int py, int px){
		super(py, px);
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
		if (!kargatzen && bombak == 0) { // Solo iniciar recarga si no hay bombas disponibles
			kargatzen = true;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    bombak++; // Añade una bomba después de 3 segundos
                    kargatzen = false; // Permite futuras recargas si es necesario
                    System.out.println("WhiteBomberman ha recargado una bomba. Bombas disponibles: " + bombak);
                }
            }, 3000); // Tiempo de recarga
        }
    }
	
	
	public void bombaJarri() {
		if (bombak > 0) {
			Bomba b = new BombaNormal(this.getX(), this.getY());
        	colocarBomba(b);
            bombak--;
            if (bombak == 0) {
                errekargatuBaldin();
            }
        }
    }
	

}
