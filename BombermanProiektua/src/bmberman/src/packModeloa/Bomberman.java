package bmberman.src.packModeloa;

import java.util.Timer;
import java.util.TimerTask;

public class Bomberman {

	private int posY;
	private int posX;
	private Bomba bomba;
	private int bombak = 10;
	private boolean martxan = false;
	private String nora;

	public Bomberman(int y, int x) {
		posY = y;
		posX = x;
		nora = "up";

	}

	public int getMota() {
		return 1;
	}

	public int getX() {
		return this.posX;
	}

	public int getY() {
		return this.posY;
	}

	public void mugitu(int dx, int dy) {
		posY += dy;
		posX += dx;
	}

	public void setX(int x) {
		this.posX = x;
	}

	public void setY(int y) {
		this.posY = y;
	}

	public Bomba getBomba() {
		return bomba;
	}

	public int getBombak() {
		return bombak;
	}

	public void kenduBomba() {
		bombak--;

		if (bombak == 0) {
			rekargatu();
		}
	}

	public String getNorabide() {
		return nora;
	}

	public void setNorabide(String a) {
		this.nora = a;
	}

	private void rekargatu() {
		if (martxan)
			return;
		martxan = true;

		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				gehituBomba();
				martxan = false;
			}
		}, 3000);
	}

	private void gehituBomba() {
		this.bombak++;
	}

}
