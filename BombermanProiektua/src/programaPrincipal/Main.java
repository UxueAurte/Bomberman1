package programaPrincipal;
import bmberman.src.packBista.*;
import bmberman.src.packModeloa.*;

import javax.swing.SwingUtilities;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(() -> {
			BloqueMapa bloqueMapa = new BloqueMapa();
            Jokoa frame = new Jokoa(bloqueMapa);
            frame.setVisible(true);
        });
	}

}
