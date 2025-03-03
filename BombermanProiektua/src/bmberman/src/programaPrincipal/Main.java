package programaPrincipal;
import bmberman.src.packBista.*;
import bmberman.src.packModeloa.*;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BloqueMapa mapa = new BloqueMapa();
		Jokoa j = new Jokoa();
		
		
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setSize(800, 500);
        j.setVisible(true);
	}

}
