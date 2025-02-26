package bmberman.src.packBista;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Color;

import bmberman.src.packModeloa.MenuM;

public class Menu extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	private JPanel panelInicio;
	private JLabel lblFondo;
	private JPanel panelFondo;
	private JLabel lblTitulo;
	private JButton btnMapa;
	private JRadioButton classic;
	private JRadioButton soft;
	private JRadioButton empty;
	
	private Controler controler;
	
	private int mapa = 1;
	
	private ButtonGroup grupoMapa;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 400, 673, 400);
		panelInicio = new JPanel();
		panelInicio.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelInicio);
		panelInicio.setLayout(null);
		panelInicio.add(getLabel1());
		panelInicio.add(getPanelFondo());
		
		panelInicio.setComponentZOrder(getPanelFondo(), 0); // panelFondo encima
		panelInicio.setComponentZOrder(getLabel1(), 1);
		
	}

	private JLabel getLabel1() {
		if (lblFondo == null) {
			lblFondo = new JLabel();
			lblFondo.setBounds(0, 0, 673, 374);		
			lblFondo.setIcon(new ImageIcon(Menu.class.getResource("/resources/back.png")));
		}
		return lblFondo;
	}
	
	private JPanel getPanelFondo() {
		if (panelFondo == null) {
			panelFondo = new JPanel();
			panelFondo.setBounds(0, 0, 673, 363);
			panelFondo.setOpaque(false);
			panelFondo.setLayout(new BorderLayout(0, 0));
			panelFondo.add(getLblTitulo(), BorderLayout.NORTH);
			panelFondo.add(getBtnElegirMapa(), BorderLayout.SOUTH);
			
			

		}
		return panelFondo;
	}

	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel();
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setIcon(new ImageIcon(Menu.class.getResource("/resources/title.png")));
				
		}
		return lblTitulo;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	private JButton getBtnElegirMapa() {
		if (btnMapa == null) {
			btnMapa = new JButton("Aukeratu Mapa");
			btnMapa.setOpaque(true);
			btnMapa.setContentAreaFilled(false);
			btnMapa.setBorderPainted(false);
		}
		
		btnMapa.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				erakutsiAukerak();
				
			}
        });

        // Button action to open the dialog
        btnMapa.addActionListener(e -> erakutsiAukerak());
		return btnMapa;
	}
	
	private void erakutsiAukerak() {
		JFrame miniDialog = new JFrame("aukeratu mapa");
		miniDialog.setSize(200, 200);
		miniDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		miniDialog.setLocationRelativeTo(this);
		
		JLabel titleLabel = new JLabel("AUKERATU MAPA:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JRadioButton classic = new JRadioButton("classic");
        JRadioButton soft = new JRadioButton("soft");
        JRadioButton empty = new JRadioButton("empty");
        
        ButtonGroup grupoMapa = new ButtonGroup();
        grupoMapa.add(classic);
        grupoMapa.add(soft);
        grupoMapa.add(empty);
        
        classic.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mapa = 1;
        	}
        });
        
        soft.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mapa = 2;
        	}
        });
        
        empty.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mapa = 3;
        	}
        });
        
		JPanel panelMapa1 = new JPanel();
		panelMapa1.setLayout(new GridLayout(5, 1, 10, 10));
		panelMapa1.add(titleLabel);
		panelMapa1.add(classic);
		panelMapa1.add(soft);
		panelMapa1.add(empty);
		
		miniDialog.add(panelMapa1);
		
		miniDialog.setVisible(true);
		
		
	}
	
	private Controler getControler() {
		if (controler == null) {
			controler = new Controler();
		}
		return controler;
	}
	
	private class Controler extends KeyAdapter{
		
		public void keyPressed(KeyEvent e ) {
			if(e.getKeyCode()== KeyEvent.VK_ENTER) {
				bukatu();
				dispose();
			}
			if(e.getKeyCode()== KeyEvent.VK_ESCAPE) {
				bukatu();
				dispose();
			}
	}
	}
	
	private void bukatu() {
		MenuM.getMiMenu().bukatu();
	}
		
		
}