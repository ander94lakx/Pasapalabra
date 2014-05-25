package packVista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = 8902413469389445787L;
	private JPanel contentPane;
	private JLayeredPane layeredPane;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JButton btnPuntuaciones;
	private JLabel lblAutores;
	private JButton btnInstrucciones;
	private JButton btnOpciones;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
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
	public MenuPrincipal() {
		initialize();
	}
	private void initialize() {
		setResizable(false);
		setTitle("PASAPALABRA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		// TOCHOCODIGO para centrar la ventana
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension windowSize = this.getSize();
		if (windowSize.height>screenSize.height) {
			windowSize.height= screenSize.height;
		}
		if (windowSize.width>screenSize.width){
			windowSize.width= screenSize.width;
		}
		setLocation((screenSize.width-windowSize.width)/2,
			(screenSize.height-windowSize.height)/2);
		// FIN TOCHOCODIGO
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getLayeredPane_1());
	}
	public JLayeredPane getLayeredPane_1() {
		if (layeredPane == null) {
			layeredPane = new JLayeredPane();
			layeredPane.add(getLblNewLabel());
			layeredPane.add(getButton_1());
			layeredPane.add(getButton_2());
			layeredPane.add(getLblAutores());
			layeredPane.add(getBtnInstrucciones());
			layeredPane.add(getBtnOpciones());
		}
		return layeredPane;
	}
	public JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("New label");
			getLayeredPane_1().setLayer(lblNewLabel, 0);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/packRecursos/pasapalabra1.jpg")));
			lblNewLabel.setBounds(0, 0, 794, 371);
		}
		return lblNewLabel;
	}
	public JButton getButton_1() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("JUGAR");
			btnNewButton.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
						NumJugadores.main(null);
						dispose();
					}
						
				}
			});
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					NumJugadores.main(null);
					dispose();
				}
			});
			getLayeredPane_1().setLayer(btnNewButton, 1);
			btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 24));
			btnNewButton.setBounds(565, 306, 219, 54);
		}
		return btnNewButton;
	}
	public JButton getButton_2() {
		if (btnPuntuaciones == null) {
			btnPuntuaciones = new JButton("Puntuaciones");
			btnPuntuaciones.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
						Puntuaciones.main(null);
				}
			});
			btnPuntuaciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Puntuaciones.main(null);
				}
			});
			getLayeredPane_1().setLayer(btnPuntuaciones, 1);
			btnPuntuaciones.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnPuntuaciones.setBounds(10, 329, 163, 31);
		}
		return btnPuntuaciones;
	}
	public JLabel getLblAutores() {
		if (lblAutores == null) {
			lblAutores = new JLabel("Creado por: Julen Aristimu\u00F1o y Ander Granado");
			lblAutores.setHorizontalAlignment(SwingConstants.CENTER);
			lblAutores.setForeground(Color.WHITE);
			getLayeredPane_1().setLayer(lblAutores, 1);
			lblAutores.setBounds(12, 11, 270, 14);
		}
		return lblAutores;
	}
	public JButton getBtnInstrucciones() {
		if (btnInstrucciones == null) {
			btnInstrucciones = new JButton("Instrucciones");
			btnInstrucciones.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
						Instrucciones.main(null);
				}
			});
			btnInstrucciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Instrucciones.main(null);
				}
			});
			btnInstrucciones.setFont(new Font("Tahoma", Font.PLAIN, 18));
			getLayeredPane_1().setLayer(btnInstrucciones, 1);
			btnInstrucciones.setBounds(183, 329, 163, 31);
		}
		return btnInstrucciones;
	}
	public JButton getBtnOpciones() {
		if (btnOpciones == null) {
			btnOpciones = new JButton("Opciones");
			btnOpciones.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
						Opciones.main(null);
				}
			});
			btnOpciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Opciones.main(null);
				}
			});
			btnOpciones.setFont(new Font("Tahoma", Font.PLAIN, 18));
			getLayeredPane_1().setLayer(btnOpciones, 1);
			btnOpciones.setBounds(356, 329, 147, 31);
		}
		return btnOpciones;
	}
}
