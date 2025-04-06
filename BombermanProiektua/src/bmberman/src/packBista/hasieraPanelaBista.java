package bmberman.src.packBista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;
import bmberman.src.packModeloa.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


public class hasieraPanelaBista extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblIzenburua;
//	private JLabel lblBoss1;
//	private JLabel lblBoss2;
	private JLabel lblInfo;
	private JLabel lblInfo2;
    private JLabel lblBlackBomberman;
    private JLabel lblWhiteBomberman;
    private boolean showJokoEgoerak = false;
	private  hasieraPanelaKontroladorea kontroladorea = null;

	
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane.setLayout(null);
		setContentPane(contentPane);		
		panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 450, 300);
        
        
        contentPane.add(panel);
		panel.add(getLblIzenburua());
		panel.add(getLblBlackBomberman()); 
		panel.add(getLblWhiteBomberman());
	//	panel.add(getBoss1());
    //  panel.add(getBoss2());
        panel.add(getlblInfo());
        panel.add(getlblInfo2());
		hasieraPanelaEredua.getHP().addObserver(this);
		addKeyListener(kontroladorea);
		System.out.println("La ventana debería aparecer ahora");
		setVisible(true);		
	}
	
	public hasieraPanelaBista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initialize();
	}
	
	private JLabel getLblIzenburua() {
		if (lblIzenburua == null) {
			lblIzenburua = new JLabel("");
			lblIzenburua.setHorizontalAlignment(SwingConstants.CENTER);
			lblIzenburua.setIcon(new ImageIcon("/resources/title.png"));
			lblIzenburua.setBounds(100, 10, 250, 50);
		}
		return lblIzenburua;
	}
	
	 private JLabel getLblBlackBomberman() {
	        if (lblBlackBomberman == null) {
	            lblBlackBomberman = new JLabel();
	            lblBlackBomberman.setIcon(new ImageIcon("/resources/bomber2.png"));
	            lblBlackBomberman.setBounds(100, 80, 50, 50);
	            lblBlackBomberman.addKeyListener(getKontroladorea());
	            lblBlackBomberman.setBounds(100, 80, 50, 50);
	        }
	        return lblBlackBomberman;
	    }

	    private JLabel getLblWhiteBomberman() {
	        if (lblWhiteBomberman == null) {
	            lblWhiteBomberman = new JLabel();
	            lblWhiteBomberman.setIcon(new ImageIcon("/resources/bomber1.png"));
	            lblWhiteBomberman.setBounds(200, 80, 50, 50);
	            lblWhiteBomberman.addKeyListener(getKontroladorea());
	            lblWhiteBomberman.setBounds(200, 80, 50, 50);

	        }
	        return lblWhiteBomberman;
	    }
	
	
	
	/*private JLabel getBoss1() {
		if (lblBoss1 == null) {
			lblBoss1 = new JLabel("");
			lblBoss1.setIcon(new ImageIcon(hasieraPanelaBista.class.getResource("/resources/malo1.png")));
			lblBoss1.setBounds(50, 150, 50, 50);
		}		
		
		return lblBoss1;
	}	
	
	private JLabel getBoss2() {
		if (lblBoss2 == null) {
			lblBoss2 = new JLabel("");
			lblBoss2.setIcon(new ImageIcon(hasieraPanelaBista.class.getResource("/resources/malo2.png")));
			lblBoss2.setBounds(150, 150, 50, 50);
		}
	return lblBoss2;
	}

	*/
	private JLabel getlblInfo() {
		if (lblInfo == null) {
			lblInfo = new JLabel("<Choose your player>");
					//+ "<space> to start, <m>usic & <esc>to exit");
			lblInfo.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblInfo.setForeground(new Color(0, 0, 0));
            lblInfo.setBounds(225, 200, 350, 30);

		}
		return lblInfo;
	}
	
	private JLabel getlblInfo2() {
		if (lblInfo2 == null) {
			lblInfo2 = new JLabel("<space> to start, <m>usic & <esc>to exit");
			lblInfo2.setFont(new Font("Tahoma", Font.BOLD, 10));
			lblInfo2.setForeground(new Color(0, 0, 0));
		    lblInfo2.setBounds(189, 231, 350, 30);

		}
		return lblInfo2;
	}
	
	public void itxi() {
		hasieraPanelaEredua.getHP().deleteObserver(this);
		dispose();
	}
	
/*	private void showGameModeSelection() {
	    JFrame gameModeSelectionWindow = new JFrame("Select Game Mode");
	    gameModeSelectionWindow.setSize(300, 200);
	    gameModeSelectionWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(3, 1));

	    JLabel classicLabel = new JLabel("Classic");
	    JLabel emptyLabel = new JLabel("Empty");
	    JLabel softLabel = new JLabel("Soft");

	    // Asignamos el controlador para gestionar los clics
	    classicLabel.addMouseListener(getKontroladorea());
	    emptyLabel.addMouseListener(getKontroladorea());
	    softLabel.addMouseListener(getKontroladorea());

	    panel.add(classicLabel);
	    panel.add(emptyLabel);
	    panel.add(softLabel);

	    gameModeSelectionWindow.add(panel);
	    gameModeSelectionWindow.setVisible(true);
	}
*/
		

	@Override
	public void update(Observable o, Object arg) {
		hasieraPanelaEredua nHP = hasieraPanelaEredua.getHP();		
	}
	
	private hasieraPanelaKontroladorea getKontroladorea() {
		if (kontroladorea == null) {
			kontroladorea = new hasieraPanelaKontroladorea();
		}
		return kontroladorea;
	}

		
	//---------------------------------------KONTROLADOREA---------------------------------------
	private class hasieraPanelaKontroladorea implements ActionListener, KeyListener {
		
		public void actionPerformed(ActionEvent e) {}
		
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				hasieraPanelaEredua nHP = hasieraPanelaEredua.getHP();
				new Jokoa();
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyReleased(KeyEvent e) {}


		
	}

}
