package bmberman.src.packBista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import java.util.Random;
import java.util.Timer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bmberman.src.packModeloa.Bloke;
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
	
	private boolean isHandlingKeyPress = false;
	
	private int etsaiak = 0;
	

	//private int[][] mapa;
	private kuadrikulaVista[][] laberintoa;
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
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		fondoImg = new ImageIcon(getClass().getResource("/resources/StageBack1.png"));

		sortuMatrizea();
		
		//keyListener
		addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
	            handleKeyPress(e); // Llama a un método separado para manejar las teclas
	        
		}
		});
			  
		setVisible(true);  
		timer=new Timer();
		hasiEguneratzea();
	}

	
	
	
	
	private void sortuMatrizea() {
		JLabel fondoL = new JLabel (fondoImg);
		fondoL.setLayout(new GridLayout(Filak, Zutabeak));
		
		laberintoa = new kuadrikulaVista[Filak][Zutabeak];

		for (int i = 0; i < Filak; i++) {
            for (int j = 0; j < Zutabeak; j++) {
            	Kuadrikula kuad = BloqueMapa.getBloqueMapa().getMapa()[i][j];
            	kuad.addObserver(new kuadrikulaVista());
            	
            	laberintoa[i][j] = new kuadrikulaVista();
            	
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
                  } else {
                	  laberintoa[i][j].setIcon(null); // Celda vacía
                  }
                
                fondoL.add(laberintoa[i][j]);
        		System.out.println("Posición (" + i + "," + j + "): " + laberintoa[i][j].getClass());

            }
		}
		add(fondoL);
	}

	public void actualizarMapa(Kuadrikula[][] mapa) {
	    for (int i = 0; i < Filak; i++) {
	        for (int j = 0; j < Zutabeak; j++) {
	            // Actualizar la imagen del JLabel según el estado de cada celda
	        	
	        	Kuadrikula[][] kuad = BloqueMapa.getBloqueMapa().getMapa();
	        	kuadrikulaVista vista = laberintoa[i][j];
	        	
	        	vista.update(kuad[i][j], null);
	        	
	        	
	            
	            }
	        }
	    }
	
	
	private void hasiEguneratzea() {
		timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Maparen eguneratzea
                actualizarMapa(BloqueMapa.getBloqueMapa().getMapa());
            }
        }, 0, 1000); // 1 segunduro eguneratu
    }
	

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("Aktualizatzen");
		Object [] aux = (Object[]) arg;
		String a = (String) aux[0];
		if(a == "hasieratu") {
			sortuMatrizea();
		} else if ("mugimendua".equals(a)) {
	        System.out.println("Mapa eguneratzen...");
	        actualizarMapa(BloqueMapa.getBloqueMapa().getMapa());
	    } else if ("bomba".equals(a)) {
	    	System.out.println("Mapa eguneratzen...");
	        actualizarMapa(BloqueMapa.getBloqueMapa().getMapa());
	    }else if ( "sua".equals(a)) {
	    	System.out.println("Mapa eguneratzen...");
	        actualizarMapa(BloqueMapa.getBloqueMapa().getMapa());
	    }
		setVisible(true);
		
	}

	
	private void handleKeyPress(KeyEvent e) {
		String u = new String();
		switch (e.getKeyCode()) {
        	case KeyEvent.VK_UP:
        		u = "g";
        		BloqueMapa.getBloqueMapa().mugimendua(-1, 0, u);
        		isHandlingKeyPress = false;
        		break;
        	case KeyEvent.VK_DOWN:
        		u = "b";
        		BloqueMapa.getBloqueMapa().mugimendua(1, 0, u);
        		isHandlingKeyPress = false;
        		break;
        	case KeyEvent.VK_LEFT:
        		u = "ezk";
        		BloqueMapa.getBloqueMapa().mugimendua(0, -1, u);
        		isHandlingKeyPress = false;
        		break;
        	case KeyEvent.VK_RIGHT:
        		u = "esk";
        		BloqueMapa.getBloqueMapa().mugimendua(0, 1, u);
        		isHandlingKeyPress = false;
        		break;
        	case KeyEvent.VK_ENTER:
        		u = "bomba";
        		BloqueMapa.getBloqueMapa().bombaJ();
        		isHandlingKeyPress = false;
        		break;
    }
		}
	}

	
	
