package bmberman.src.packBista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import java.util.Random;

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

public class Jokoa extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static final int Filak = 11;
	private static final int Zutabeak = 17;
	private static final int KuadrikulaTam = 40;
	
	private boolean isHandlingKeyPress = false;
	
	private int etsaiak = 0;
	
	private BloqueMapa bloqueMapa = new BloqueMapa();
	
	//private int[][] mapa;
	private JLabel[][]kuadrikula;
	private JLabel lblFondo;
	
	private ImageIcon fondoImg;
	

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Jokoa() {
		bloqueMapa.addObserver(this);
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
	}

	
	
	private void sortuMatrizea() {
		JLabel fondoL = new JLabel (fondoImg);
		fondoL.setLayout(new GridLayout(Filak, Zutabeak));

		kuadrikula = new JLabel[Filak][Zutabeak];
		for (int i = 0; i < Filak; i++) {
            for (int j = 0; j < Zutabeak; j++) {
            	Kuadrikula kuad = bloqueMapa.getMapa()[i][j];
                kuadrikula[i][j] = new JLabel();
                kuadrikula[i][j].setPreferredSize(new Dimension(KuadrikulaTam, KuadrikulaTam));
                kuadrikula[i][j].setOpaque(false); // Hacer transparentes los JLabel
                kuadrikula[0][0].setIcon(new ImageIcon(Jokoa.class.getResource("/resources/whitefront1.png")));
                if (kuad.getObjetua()== null) {
                		kuadrikula[i][j].setIcon(null);
                		kuadrikula[i][j].setOpaque(false);
                     }else if(kuad.getObjetua() instanceof BlokeS){
                		kuadrikula[i][j].setIcon(new ImageIcon(Jokoa.class.getResource("/resources/soft1.png")));
                }else if(kuad.getObjetua() instanceof BlokeG) {
                		kuadrikula[i][j].setIcon(new ImageIcon(Jokoa.class.getResource("/resources/hard1.png")));
                		
                }else {
                		kuadrikula[i][j].setIcon(null);
                		
                }
                
                fondoL.add(kuadrikula[i][j]);
        		System.out.println("Posición (" + i + "," + j + "): " + kuadrikula[i][j].getClass());

            }
		}
		add(fondoL);
	}

	public void actualizarMapa(Kuadrikula[][] mapa) {
	    for (int i = 0; i < Filak; i++) {
	        for (int j = 0; j < Zutabeak; j++) {
	            // Actualizar la imagen del JLabel según el estado de cada celda
	            if (mapa[i][j].getObjetua() instanceof Bomberman) {
	                kuadrikula[i][j].setIcon(new ImageIcon(getClass().getResource("/resources/whitefront1.png")));
	            } else if (mapa[i][j].getObjetua() instanceof BlokeS) {
	                kuadrikula[i][j].setIcon(new ImageIcon(getClass().getResource("/resources/soft1.png")));
	            } else if (mapa[i][j].getObjetua() instanceof BlokeG) {
	                kuadrikula[i][j].setIcon(new ImageIcon(getClass().getResource("/resources/hard1.png")));
	                       
	            }else {
	                kuadrikula[i][j].setIcon(null);
	            }
	        }
	    }
	}
	
	
	

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("Aktualizatzen");
		Object [] aux = (Object[]) arg;
		String a = (String) aux[0];
		if(a == "hasieratu") {
			sortuMatrizea();
		}
		else if("mugimendua".equals(a)){
			System.out.println("Recibido evento de actualización del mapa.");
			actualizarMapa(bloqueMapa.getMapa());
		}else if("bomba".equals(a)) {
			System.out.println("Recibido evento de actualización del mapa.");	
			actualizarMapa(bloqueMapa.getMapa());

		}
		setVisible(true);
		
	}

	
	private void handleKeyPress(KeyEvent e) {
		String u = new String();
		switch (e.getKeyCode()) {
        	case KeyEvent.VK_UP:
        		u = "g";
        		bloqueMapa.mugimendua(-1, 0, u);
        		isHandlingKeyPress = false;
        		break;
        	case KeyEvent.VK_DOWN:
        		u = "b";
        		bloqueMapa.mugimendua(1, 0, u);
        		isHandlingKeyPress = false;
        		break;
        	case KeyEvent.VK_LEFT:
        		u = "ezk";
        		bloqueMapa.mugimendua(0, -1, u);
        		isHandlingKeyPress = false;
        		break;
        	case KeyEvent.VK_RIGHT:
        		u = "esk";
        		bloqueMapa.mugimendua(0, 1, u);
        		isHandlingKeyPress = false;
        		break;
        	case KeyEvent.VK_ENTER:
        		u = "bomba";
        		bloqueMapa.bombaJ();
        		isHandlingKeyPress = false;
        		break;
    }
		}
	}

	
	
