package bmberman.src.packBista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import java.util.Random;
import java.util.Timer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bmberman.src.packModeloa.Bloke;
import bmberman.src.packBista.Jokoa;
import bmberman.src.packModeloa.BlokeGogorra;
import bmberman.src.packModeloa.BlokeBiguna;
import bmberman.src.packModeloa.BlokeMapa;
import bmberman.src.packModeloa.Bomba;
import bmberman.src.packModeloa.Bomberman;
import bmberman.src.packModeloa.BombermanFactory;
import bmberman.src.packModeloa.DenboraEredua;
import bmberman.src.packModeloa.Kuadrikula;
import bmberman.src.packModeloa.MapaFactory;
import bmberman.src.packModeloa.hasieraPanelaEredua;

import java.util.TimerTask;

public class Jokoa extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static final int Filak = 11;
	private static final int Zutabeak = 17;
	private static final int KuadrikulaTam = 40;
	private boolean jokoaAmaituDa = false;


	private int etsaiak = 0;

	private Controler controler = null;

	// private int[][] mapa;
	private KuadrikulaVista[][] laberintoa;
	private JLabel lblFondo;

	private ImageIcon fondoImg;

	private Timer timer;
	private boolean irabaziDa = false;
	
	private DenboraEredua denboraEredua;
	
	private static final List<String> fondoImagenes = List.of(
	        "/resources/stageBack1.png",
	        "/resources/stageBack2.png",
	        "/resources/stageBack3.png"
	);
	
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Jokoa() {
		BlokeMapa BM = BlokeMapa.getBloqueMapa();
		System.out.println("Mapa sortu da");
		BM.addObserver(this);
		System.out.println(BM);
		
		

	
		setTitle("BOMBERMAN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		fondoImg = new ImageIcon(getClass().getResource(seleccionarFondoAleatorio()));
      	
        addKeyListener(getControler()); // Kontroladorea
        
		String mota = hasieraPanelaEredua.getHP().getPartidaMota();
        String bomberman = hasieraPanelaEredua.getHP().getNireBomberman();
        BM.mapaInizializatu();
        
        denboraEredua = DenboraEredua.getDenboraEredua(); // Lortu DenboraEredua instantzia
        denboraEredua.hasieratu(); // Jokoa hasten denean timerra hasieratu
        
		setVisible(true);
		
	}
	
	private String seleccionarFondoAleatorio() {
		return fondoImagenes.stream()
		        .skip(new Random().nextInt(fondoImagenes.size()))
		        .findFirst()
		        .orElse("/resources/default.png");
    }
	

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("Aktualizatzen");
		Object[] aux = (Object[]) arg;
		String a = (String) aux[0];
		if (a == "hasieratu") {
			sortuMatrizea();
		} else if ("hil".equals(a)) {
			hildaBomberman();
		} else if ("jokoaAmaitu".equals(a)) {
			irabaziDa = true;
			amaieraMezua();
		}

		setVisible(true);

	}

	private Controler getControler() {
		if (controler == null) {
			controler = new Controler();
		}
		return controler;
	}

	public void hildaBomberman() {
		if (!jokoaAmaituDa) {
            String denbora = denboraEredua.getIraupenaFormatuan();
            JOptionPane.showMessageDialog(Jokoa.this, "Bomberman hil da! Denbora: " + denbora, "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
            jokoaAmaituDa = true;
            MapaFactory.getGF().getEgungoMapa().jolasaGelditu();
            denboraEredua.gelditu(); // Timerra gelditu jokoa amaitzen denean
            removeKeyListener(getControler());
		}
	}

	private void amaieraMezua() {
		EventQueue.invokeLater(() -> {
            String denbora = denboraEredua.getIraupenaFormatuan();
            JOptionPane.showMessageDialog(this, "Jokoa amaitu da! Zorionak! Denbora: " + denbora, "WIN",
                    JOptionPane.INFORMATION_MESSAGE);
        });
        denboraEredua.gelditu(); // Timerra gelditu jokoa amaitzen denean
        removeKeyListener(getControler()); //
        jokoaAmaituDa = true;
        if (irabaziDa) {
            gordeDenbora(denboraEredua.getIraupenaFormatuan()); // Denbora gorde soilik irabaziz gero
        }
	}
	
/*	private void jokoaBerrabiarazi() {
		dispose();
		jokoaAmaituDa=false;
		new Jokoa();
	}
*/
	private void sortuMatrizea() {
		System.out.println("Mapa sortzen");
		contentPane.removeAll();
		JLabel fondoL = new JLabel(fondoImg);
		fondoL.setLayout(new GridLayout(Filak, Zutabeak));
		laberintoa = new KuadrikulaVista[Filak][Zutabeak];

		for (int i = 0; i < Filak; i++) {
			for (int j = 0; j < Zutabeak; j++) {
				Kuadrikula kuad = BlokeMapa.getBloqueMapa().getMapa()[i][j];
				KuadrikulaVista kv = new KuadrikulaVista();
				kuad.addObserver(kv);
				laberintoa[i][j] = kv;
				fondoL.add(kv);

			}
		}
		add(fondoL);
		
		
	}
	
	private void gordeDenbora(String denbora) {
		try {
	        
			// Definir la ruta del archivo en un directorio específico
	        String directorio = System.getProperty("user.dir"); // Obtiene el directorio donde se ejecuta el JAR
	        String archivoPath = directorio + File.separator + "denborak.txt";
			
	        ArrayList<String> denborak = new ArrayList<>();

	        // Leer tiempos existentes
	        try (BufferedReader br = new BufferedReader(new FileReader("denborak.txt"))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                denborak.add(line);
	            }
	        }

	        // Insertar nuevo tiempo en orden alfabético
	        int index = 0;
	        while (index < denborak.size() && denborak.get(index).compareTo(denbora) <= 0) {
	            index++;
	        }
	        denborak.add(index, denbora);

	        // Reescribir archivo con los tiempos ordenadosw
	        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("denborak.txt")))) {
	            for (String d : denborak) {
	                out.println(d);
	            }
	        }

	        System.out.println("Denbora gordeta fitxategian (ordenatuta): " + denbora);
	    } catch (IOException e) {
	        System.err.println("Errorea denbora gordetzean: " + e.getMessage());
	    }
	
	}

//---------------------------------------------KONTROLADOREA-------------------------------------------------
	private class Controler implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			String u = new String();
			Bomberman bomberman = MapaFactory.getGF().getEgungoMapa().getBomberman();
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				u = "up";
				MapaFactory.getGF().getEgungoMapa().mugimendua(0, -1, u);
				break;
			case KeyEvent.VK_DOWN:
				u = "down";
				MapaFactory.getGF().getEgungoMapa().mugimendua(0, 1, u);
				break;
			case KeyEvent.VK_LEFT:
				u = "left";
				MapaFactory.getGF().getEgungoMapa().mugimendua(-1, 0, u);
				break;
			case KeyEvent.VK_RIGHT:
				u = "right";
				MapaFactory.getGF().getEgungoMapa().mugimendua(1, 0, u);
				break;
			case KeyEvent.VK_ENTER:
				u = "bomba";
				bomberman.bombaJarri();
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}

}