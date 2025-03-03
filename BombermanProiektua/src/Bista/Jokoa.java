package Bista;
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

import Eredua.BloqueMapa;
public class Jokoa extends JFrame implements Observer{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static final int Filak = 11;
	private static final int Zutabeak = 17;
	private static final int KuadrikulaTam = 40;
	
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
	public Jokoa(Observable pBloqueMapa) {
		pBloqueMapa.addObserver(this);
		setTitle("BOMBERMAN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		fondoImg = new ImageIcon(getClass().getResource("/resources/StageBack1.png"));
		
		//mapaHasi();
		//sortuMapa();
		
		sortuMatrizea();
		
		setVisible(true);
	}

	
	
	private void sortuMatrizea() {
		JLabel fondoL = new JLabel (fondoImg);
		fondoL.setLayout(new GridLayout(Filak, Zutabeak));

		kuadrikula = new JLabel[Filak][Zutabeak];
		
		for (int i = 0; i < Filak; i++) {
            for (int j = 0; j < Zutabeak; j++) {
                kuadrikula[i][j] = new JLabel();
                kuadrikula[i][j].setPreferredSize(new Dimension(KuadrikulaTam, KuadrikulaTam));
                kuadrikula[i][j].setOpaque(false); // Hacer transparentes los JLabel
                
                switch (bloqueMapa.getMapa()[i][j].getMota()) {
                	case 0:
                		kuadrikula[i][j].setIcon(null);
                		kuadrikula[i][j].setOpaque(false);
                		break;
                	case 1:
                		kuadrikula[i][j].setIcon(new ImageIcon(Menu.class.getResource("/resources/soft1.png")));
                		break;
                	case 2:
                		kuadrikula[i][j].setIcon(new ImageIcon(Menu.class.getResource("/resources/hard1.png")));
                		break;
                	case 3:
                		kuadrikula[i][j].setIcon(null);
                		break;
                }
                
                fondoL.add(kuadrikula[i][j]);
            }
		}
		add(fondoL);
	}
	
	/*private void mapaHasi() {
		mapa = new int[Filak][Zutabeak];
		Random random = new Random();
		
		for (int i = 0; i < Filak; i++) {
			for (int j = 0; j < Zutabeak; j++) {
				if((i == 0 && j == 0) || (i == 0 && j == 1) || (i == 1 && j == 0)) {
					mapa [i][j] = 0;
				}
				else if (i % 2 == 0 && j % 2 == 0) {
					mapa[i][j] = 2;			
				}
				else {
					if(random .nextDouble()*100 > 40) {
						mapa[i][j] = 1;						
					} else {
						if(random.nextDouble() * 100 > 90 && etsaiak < 6) {
							mapa[i][j] = 3;
							etsaiak++;
						}
						else {
							mapa[i][j] = 0;
						}
					}
				} 
			}	
		}
	}
	
	
	
	
	private void sortuMapa() {
		JLabel panelMapa = new JLabel(fondoImg);
		panelMapa.setLayout(new GridLayout(Filak, Zutabeak));
		kuadrikula = new JLabel[Filak][Zutabeak];
		
		for (int i = 0; i < Filak; i++) {
			for (int j = 0; j < Zutabeak; j++) {
				kuadrikula[i][j] = new JLabel();
				kuadrikula[i][j].setOpaque(true);
				
				switch (mapa[i][j]) {
					case 0:
						kuadrikula[i][j].setOpaque(false);
						break;
					case 1:
						kuadrikula[i][j].setIcon(new ImageIcon(Menu.class.getResource("/resources/soft1.png")));
						break;
					case 2:
						kuadrikula[i][j].setIcon(new ImageIcon(Menu.class.getResource("/resources/hard1.png")));
						break;
					case 3:
						kuadrikula[i][j].setIcon(new ImageIcon(Menu.class.getResource("/resources/baloon1.png")));
						kuadrikula[i][j].setOpaque(false);
						break;
				}
				
				panelMapa.add(kuadrikula[i][j]);
			}
		}
		
		add(panelMapa);
	}
	*/

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		Object [] aux = (Object[]) arg;
		
		setVisible(true);
		
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
            bloqueMapa.mugimendua(0, -1);
            break;
        case KeyEvent.VK_DOWN: 
            bloqueMapa.mugimendua(0, 2);
            break;
        case KeyEvent.VK_LEFT:
            bloqueMapa.mugimendua(-1, 0);
            break;
        case KeyEvent.VK_RIGHT:
            bloqueMapa.mugimendua(1, 0);
            break;
    }
	}
}
