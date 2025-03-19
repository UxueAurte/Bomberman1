package bmberman.src.packBista;

import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.util.Random;

import bmberman.src.packModeloa.BlokeG;
import bmberman.src.packModeloa.BlokeS;
import bmberman.src.packModeloa.BloqueMapa;
import bmberman.src.packModeloa.Bomba;
import bmberman.src.packModeloa.Bomberman;
import bmberman.src.packModeloa.Kuadrikula;

public class kuadrikulaVista extends JLabel implements Observer {
	public kuadrikulaVista() {
	}

	private static final long serialVersionUID = 1L;
	

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */


	@Override
	public void update(Observable o, Object arg) {
		
		Object [] aux = (Object[]) arg;
		Random r = new Random();

		
		if (o instanceof Kuadrikula) {
			
			Kuadrikula kuad = (Kuadrikula) o;
			//if ((boolean[]) arg) {
	
			if(kuad.hasBomberman()) {
				this.setIcon(new ImageIcon(getClass().getResource("/resources/white"+kuad.getBomberman().getNorabide()+r.nextInt(1, 4)+".png")));
	        } else if (kuad.hasBomba()) {
	            this.setIcon(new ImageIcon(getClass().getResource("/resources/bomb1.png")));
	        } else if (kuad.hasBloke()) {
	        	if (kuad.getBloke() instanceof BlokeG) {
	                this.setIcon(new ImageIcon(getClass().getResource("/resources/hard1.png")));
	            } else if (kuad.getBloke() instanceof BlokeS) {
	                this.setIcon(new ImageIcon(getClass().getResource("/resources/soft1.png")));
	            }
	        } else if (kuad.hasSua()) { // Sua badago
	            this.setIcon(new ImageIcon(getClass().getResource("/resources/miniBlast1.gif")));
	            // Bloke bigunak suntsitu
	            if (kuad.getBloke() instanceof BlokeS) {
	                kuad.kenduBlokeBiguna(); // Blokea ezabatu datu-eredutik
	            }
	        } else {
	            this.setIcon(null); // Celda vacía
	        }
			
			
		}
	}
}
