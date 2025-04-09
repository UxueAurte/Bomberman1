package bmberman.src.programaPrincipal;
import javax.swing.JFrame;

import bmberman.src.packBista.*;
import bmberman.src.packModeloa.*;
import bmberman.src.packBista.*;
import bmberman.src.packModeloa.*;


public class Main extends JFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BlokeMapa mapa = BlokeMapa.getBloqueMapa();
		Jokoa j = new Jokoa();

		mapa.konfiguratuMapa("c");;

		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setSize(800, 500);
		j.setVisible(true);
	}
}




//Bomberman Factory
//Laberinto mota Factory
//	--> Bombak Strategy
// Etsaiak 