package programaPrincipal;
import javax.swing.SwingUtilities;

import Bista.*;
import Eredua.*;


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
