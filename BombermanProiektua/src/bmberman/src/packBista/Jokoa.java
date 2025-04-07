package bmberman.src.packBista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import bmberman.src.packModeloa.BlokeG;
import bmberman.src.packModeloa.BlokeS;
import bmberman.src.packModeloa.BloqueMapa;
import bmberman.src.packModeloa.Bomba;
import bmberman.src.packModeloa.Bomberman;
import bmberman.src.packModeloa.Kuadrikula;
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

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Jokoa() {
		BloqueMapa.getBloqueMapa().addObserver(this);
		setTitle("BOMBERMAN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		fondoImg = new ImageIcon(getClass().getResource("/resources/stageBack1.png"));
		// keyListener
		addKeyListener(getControler()); // Llama a un método separado para manejar las teclas
		sortuMatrizea();
		setVisible(true);
	
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
			JOptionPane.showMessageDialog(Jokoa.this, "Bomberman hil da!", "", JOptionPane.INFORMATION_MESSAGE);
			jokoaAmaituDa = true;
			BloqueMapa.getBloqueMapa().jolasaGelditu();
			removeKeyListener(getControler());
		}
	}

	private void amaieraMezua() {
		EventQueue.invokeLater(() -> {
			JOptionPane.showMessageDialog(this, "Jokoa amaitu da! Zorionak!", "Game Over",
					JOptionPane.INFORMATION_MESSAGE);
		});
		removeKeyListener(getControler()); //
		jokoaAmaituDa = true;
	}

	private void sortuMatrizea() {
		JLabel fondoL = new JLabel(fondoImg);
		fondoL.setLayout(new GridLayout(Filak, Zutabeak));

		laberintoa = new KuadrikulaVista[Filak][Zutabeak];

		for (int i = 0; i < Filak; i++) {
			for (int j = 0; j < Zutabeak; j++) {
				Kuadrikula kuad = BloqueMapa.getBloqueMapa().getMapa()[i][j];
				KuadrikulaVista kv = new KuadrikulaVista();
				kuad.addObserver(kv);

				laberintoa[i][j] = kv;

				laberintoa[i][j].setPreferredSize(new Dimension(KuadrikulaTam, KuadrikulaTam));
				laberintoa[i][j].setOpaque(false); // Hacer transparentes los JLabel
				laberintoa[0][0].setIcon(new ImageIcon(Jokoa.class.getResource("/resources/whitefront1.png")));
				if (kuad.hasBomberman()) {
					laberintoa[i][j].setIcon(new ImageIcon(getClass().getResource("/resources/whitefront1.png")));
				} else if (kuad.hasBomba()) {
					laberintoa[i][j].setIcon(new ImageIcon(getClass().getResource("/resources/bomb1.png")));
				} else if (kuad.hasBloke()) {
					if (kuad.getBloke() instanceof BlokeG) {
						laberintoa[i][j].setIcon(new ImageIcon(getClass().getResource("/resources/hard1.png")));
					} else if (kuad.getBloke() instanceof BlokeS) {
						laberintoa[i][j].setIcon(new ImageIcon(getClass().getResource("/resources/soft1.png")));
					}
				}else if (kuad.hasEtsaia()) { 
				    laberintoa[i][j].setIcon(new ImageIcon(getClass().getResource("/resources/baloon1.png")));
				} else {
					laberintoa[i][j].setIcon(null); // Celda vacía
				}

				fondoL.add(laberintoa[i][j]);

			}
		}
		add(fondoL);
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
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				u = "up";
				BloqueMapa.getBloqueMapa().mugimendua(0, -1, u);
				break;
			case KeyEvent.VK_DOWN:
				u = "down";
				BloqueMapa.getBloqueMapa().mugimendua(0, 1, u);
				break;
			case KeyEvent.VK_LEFT:
				u = "left";
				BloqueMapa.getBloqueMapa().mugimendua(-1, 0, u);
				break;
			case KeyEvent.VK_RIGHT:
				u = "right";
				BloqueMapa.getBloqueMapa().mugimendua(1, 0, u);
				break;
			case KeyEvent.VK_ENTER:
				u = "bomba";
				BloqueMapa.getBloqueMapa().bombaJ();
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}

}
