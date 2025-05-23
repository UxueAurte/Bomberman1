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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import bmberman.src.packModeloa.*;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
	private JLabel lblBlackBomberman;
	private JLabel lblWhiteBomberman;
		private JLabel fondoa;
	private boolean showJokoEgoerak = false;
	private	 hasieraPanelaKontroladorea kontroladorea = null;
	private JPanel panelPodium; // Podiuma erakusteko panela
	private JLabel lblPodiumIzenburua;

	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPane.setLayout(null);
		setContentPane(contentPane);
		panel = new JPanel(null);
		panel.setBounds(0, 0, 736, 453);

		fondoa = new JLabel(new ImageIcon(getClass().getResource("/resources/back.png")));
		fondoa.setBounds(0, 0, 736, 453);
		fondoa.setLayout(null);

		fondoa.add(getLblIzenburua());
		fondoa.add(getLblBlackBomberman());
		fondoa.add(getLblWhiteBomberman());
	//	panel.add(getBoss1());
	// panel.add(getBoss2());
		fondoa.add(getlblInfo());

		

		// Podium panela sortu eta kokatu
		lblPodiumIzenburua = new JLabel("Podiuma");
		lblPodiumIzenburua.setFont(new Font("Verdana", Font.BOLD, 28)); // Letra-tipo handiagoa
		lblPodiumIzenburua.setForeground(Color.WHITE); // Urre kolorea
		lblPodiumIzenburua.setBounds(50, 280, 150, 30);
		fondoa.add(lblPodiumIzenburua);
		panelPodium = new JPanel();
		panelPodium.setLayout(new GridLayout(5, 1)); // Adibidez, 5 denbora erakusteko
		panelPodium.setBounds(50, 310, 200, 150); // Kokapen eta tamaina egokitu
		panelPodium.setOpaque(false); // Atzeko planoa gardena izan dadin
		fondoa.add(panelPodium);

		panelaGehitu();
		kargatuDenborak(); // Denborak kargatu eta podiuma bistaratu
		hasieraPanelaEredua.getHP().addObserver(this);
		addKeyListener(kontroladorea);
		setVisible(true);
	}

	public hasieraPanelaBista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initialize();
	}


	private JLabel getLblIzenburua() {
		if (lblIzenburua == null) {
			lblIzenburua = new JLabel("");
			lblIzenburua.setFont(new Font("Tahoma", Font.PLAIN, 34));
			lblIzenburua.setHorizontalAlignment(SwingConstants.CENTER);
			lblIzenburua.setIcon(new ImageIcon(getClass().getResource("/resources/title.png")));
			lblIzenburua.setBounds(144, 28, 422, 97);
		}
		return lblIzenburua;
	}

	private JLabel getLblBlackBomberman() {
		if (lblBlackBomberman == null) {
			lblBlackBomberman = new JLabel();
			ImageIcon icon = new ImageIcon(getClass().getResource("/resources/bomber2.png"));
			lblBlackBomberman.setIcon(icon);
			int width = icon.getIconWidth();
			int height = icon.getIconHeight();
			lblBlackBomberman.setBounds(480, 177, width, height);
			lblBlackBomberman.addMouseListener(getKontroladorea());
		}
		return lblBlackBomberman;
	}


		private JLabel getLblWhiteBomberman() {
		if (lblWhiteBomberman == null) {
			lblWhiteBomberman = new JLabel();
			lblWhiteBomberman.setFont(new Font("Tahoma", Font.PLAIN, 61));
			lblWhiteBomberman.setIcon(new ImageIcon(getClass().getResource("/resources/bomber1.png")));
			lblWhiteBomberman.setBounds(150, 179, 422, 97);
			lblWhiteBomberman.addMouseListener(getKontroladorea());

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
				lblInfo = new JLabel("Jokalaria Aukeratu");
				lblInfo.setFont(new Font("Tahoma", Font.BOLD, 30));
				lblInfo.setForeground(new Color(0, 0, 0));
				lblInfo.setBounds(411, 358, 557, 40);
			}
			return lblInfo;
		}


	public void itxi() {
		hasieraPanelaEredua.getHP().deleteObserver(this);
		dispose();
	}

	private void panelaGehitu() {
		panel.add(fondoa);
		contentPane.add(panel);
		contentPane.revalidate();
		contentPane.repaint();
	}


	private void partidaMota() {
		JDialog dialog = new JDialog(this, "Aukeratu Mapa Mota", true);
		dialog.setSize(300, 200);
		dialog.setLocationRelativeTo(this);
		dialog.setLayout(new BorderLayout());

		JPanel centerPanel = new JPanel(new GridLayout(3, 1));
		JRadioButton classic = new JRadioButton("Classic");
		JRadioButton soft = new JRadioButton("Soft");
		JRadioButton empty = new JRadioButton("Empty");

		ButtonGroup group = new ButtonGroup();
		group.add(classic);
		group.add(soft);
		group.add(empty);

		centerPanel.add(classic);
		centerPanel.add(soft);
		centerPanel.add(empty);

		JButton startButton = new JButton("Hasi Jokoa");
		startButton.addActionListener(e -> {
			String tipoPartida = "c"; // lehenetsia
			if (soft.isSelected()) tipoPartida = "s";
			else if (empty.isSelected()) tipoPartida = "e";

			hasieraPanelaEredua.getHP().setPartidaMota(tipoPartida);


			dialog.dispose(); // itxi partida mota dialogoa
			new Jokoa();
			itxi();	 // metodoa zure hasieraPanelaBista-n dago
		});

		dialog.add(centerPanel, BorderLayout.CENTER);
		dialog.add(startButton, BorderLayout.SOUTH);
		dialog.setVisible(true);
	}

	private void kargatuDenborak() {
	    panelPodium.removeAll();
	    List<String> denborak = new ArrayList<>();
	    File denboraFitxategia = new File("denborak.txt");

	    if (denboraFitxategia.exists()) {
	        try (BufferedReader br = new BufferedReader(new FileReader(denboraFitxategia))) {
	            String lerroa;
	            while ((lerroa = br.readLine()) != null && denborak.size() < 5) {
	                if (!lerroa.equals("00:00:00")) { // Baldintza berria
	                    denborak.add(lerroa);
	                }
	            }
	        } catch (IOException e) {
	            System.err.println("Errorea denborak irakurtzean: " + e.getMessage());
	        }
	    } else {
	        System.out.println("Ez da aurkitu 'denborak.txt' fitxategia. Oraindik ez da jokorik amaitu?");
	    }

	    int rank = 1;
	    for (String denbora : denborak) {
	        JLabel lblDenbora = new JLabel(rank + ". " + denbora);
	        lblDenbora.setForeground(Color.WHITE);
	        panelPodium.add(lblDenbora);
	        rank++;
	    }
	    panelPodium.revalidate();
	    panelPodium.repaint();
	}


	@Override
	public void update(Observable o, Object arg) {
		// Ezer egin behar ez den kasuetan ere, inplementatu behar da.
	}

	private hasieraPanelaKontroladorea getKontroladorea() {
		if (kontroladorea == null) {
			kontroladorea = new hasieraPanelaKontroladorea();
		}
		return kontroladorea;
	}


	//---------------------------------------KONTROLADOREA---------------------------------------
	private class hasieraPanelaKontroladorea extends MouseAdapter implements ActionListener, KeyListener {


		public void actionPerformed(ActionEvent e) {}

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				hasieraPanelaEredua nHP = hasieraPanelaEredua.getHP();
				new Jokoa();
				itxi();
			}
		}


		public void mouseClicked(MouseEvent evt) {
			if (evt.getSource() == lblBlackBomberman) {
				hasieraPanelaEredua nHP = hasieraPanelaEredua.getHP();
				nHP.setNireBomberman("Black");
				lblWhiteBomberman.setIcon(new ImageIcon(getClass().getResource("/resources/bomber1 copia.png")));
				lblBlackBomberman.setIcon(new ImageIcon(getClass().getResource("/resources/bomber2.png")));

				partidaMota();
			} else if (evt.getSource() == lblWhiteBomberman) {
				hasieraPanelaEredua nHP = hasieraPanelaEredua.getHP();
				nHP.setNireBomberman("White");
				lblWhiteBomberman.setIcon(new ImageIcon(getClass().getResource("/resources/bomber1.png")));
				lblBlackBomberman.setIcon(new ImageIcon(getClass().getResource("/resources/bomber2bw.png")));


				partidaMota();
				}
			}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}

}