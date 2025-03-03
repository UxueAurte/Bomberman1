package bmberman.src.packModeloa;

import java.util.Observable;

public class MenuM extends Observable{
	private static MenuM miMenuM;


	public static MenuM getMiMenu() {
		if(miMenuM == null) {
			miMenuM = new MenuM();
		}
		return miMenuM;
	}
	
	public void bukatu() {
		miMenuM = null;
	}
}

	