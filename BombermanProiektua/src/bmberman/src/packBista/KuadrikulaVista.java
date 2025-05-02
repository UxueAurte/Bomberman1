package bmberman.src.packBista;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.util.Random;

import bmberman.src.packModeloa.BlokeGogorra;
import bmberman.src.packModeloa.BlokeBiguna;
import bmberman.src.packModeloa.BlokeMapa;
import bmberman.src.packModeloa.Bomba;
import bmberman.src.packModeloa.Bomberman;
import bmberman.src.packModeloa.Kuadrikula;

public class KuadrikulaVista extends JLabel implements Observer {
	
	private static final List<String> blokeGogorraImagenes = List.of(
	        "/resources/hard1.png",
	        "/resources/hard2.png",
	        "/resources/hard3.png",
	        "/resources/hard4.png"
	    );
	
	private static final List<String> etsaiImagenes = List.of(
			"/resources/baloon1.png",
	        "/resources/baloon2.png",
			"/resources/doria1.png",
	        "/resources/doria2.png",
	        "/resources/pass1.png",
	        "/resources/pass2.png"
			
	);
	
	private static final List<String> blokeBigunaImagenes = List.of(
	        "/resources/soft1.png",
	        "/resources/soft2.png",
	        "/resources/soft3.png",
	        "/resources/soft4.png",
	        "/resources/soft41.png",
	        "/resources/soft42.png",
	        "/resources/soft43.png",
	        "/resources/soft44.png",
	        "/resources/soft45.png",
	        "/resources/soft46.png"	        
	    );
	
	private static String blokeGogorraImgSeleccionada = null;
    private static String blokeBigunaImgSeleccionada = null;
    private static String etsaiImgSeleccionada = null;
    
    
	public KuadrikulaVista() {
		
		this.setPreferredSize(new Dimension(40, 40));
		this.setOpaque(false);
		if (blokeGogorraImgSeleccionada == null) {
            blokeGogorraImgSeleccionada = seleccionarImagenAleatoria(blokeGogorraImagenes);
        }
        if (blokeBigunaImgSeleccionada == null) {
            blokeBigunaImgSeleccionada = seleccionarImagenAleatoria(blokeBigunaImagenes);
        }
        if (etsaiImgSeleccionada == null) {
        	etsaiImgSeleccionada = seleccionarImagenAleatoria(etsaiImagenes);
        }
		
	}
	

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	
	private String seleccionarImagenAleatoria(List<String> imagenes) {
		return imagenes.stream()
		        .skip(new Random().nextInt(imagenes.size()))
		        .findFirst()
		        .orElse("/resources/fallback.png");
    }
	//EDITAR PARA MVC
	
	/* [0] = bomberman
	 * [1] = bomba
	 * [2] = blokeB
	 * [3] = blokeG
	 * [4] = sua
	 * [5] = etsaia
	 */
	@Override
	public void update(Observable o, Object arg) {
		Random r = new Random();

		if (arg instanceof String[]) {
	        String[] zerDauka = (String[]) arg;
	        Kuadrikula kuadrikula = (Kuadrikula) o;
	        if ("1".equals(zerDauka[6])) { // etsaia
	            this.setIcon(new ImageIcon(getClass().getResource(etsaiImgSeleccionada)));
	        } else if ("1".equals(zerDauka[0])) {
	        	if (zerDauka[5] != null) {
	        	    URL resource = getClass().getResource(zerDauka[5]);
	        	    if (resource != null) {
	        	        this.setIcon(new ImageIcon(resource));
	        	    } else {
	        	        System.err.println("Recurso no encontrado: " + zerDauka[5]);
	        	        this.setIcon(null); // O usar una imagen por defecto
	        	    }
	        	}
	        } else if ("1".equals(zerDauka[1])) { // Bomba
	            this.setIcon(new ImageIcon(getClass().getResource("/resources/bomb1.png")));
	        } else if ("1".equals(zerDauka[2])) { // Bloke Biguna
	            this.setIcon(new ImageIcon(getClass().getResource(blokeBigunaImgSeleccionada)));
	        } else if ("1".equals(zerDauka[3])) { // Bloke Gogorra
	            this.setIcon(new ImageIcon(getClass().getResource(blokeGogorraImgSeleccionada)));
	        } else if ("1".equals(zerDauka[4])) { // Sua
	            this.setIcon(new ImageIcon(getClass().getResource("/resources/miniBlast1.gif")));
	        } else {
	            this.setIcon(null); // Celda vacía
	        }
	    }
	}
}
