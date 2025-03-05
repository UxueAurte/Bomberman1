package bmberman.src.packBista;

import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
		if (o instanceof Kuadrikula) {
			
			Kuadrikula kuad = (Kuadrikula) o;
			
			if(kuad.hasBomberman()) {
	            this.setIcon(new ImageIcon(getClass().getResource("/resources/whitefront1.png")));
	        } else if (kuad.hasBomba()) {
	            this.setIcon(new ImageIcon(getClass().getResource("/resources/bomb1.png")));
	        } else if (kuad.hasBloke()) {
	        	if (kuad.getBloke() instanceof BlokeG) {
	                this.setIcon(new ImageIcon(getClass().getResource("/resources/hard1.png")));
	            } else if (kuad.getBloke() instanceof BlokeS) {
	                this.setIcon(new ImageIcon(getClass().getResource("/resources/soft1.png")));
	            }
	        	
	        } else if (kuad.isSutea()) { // Sua badago
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
