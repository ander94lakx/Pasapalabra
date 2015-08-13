package packVista;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import packModelo.Pasapalabra;

public class NombresJug extends JDialog {

	private static final long serialVersionUID = -1792086598276614047L;
	private JPanel panel;
	private JLabel lblNumJugadores;
	private JPanel panel_1;
	private JTextField textField;
	private JLabel lblJugador;
	private JTextField textField_1;
	private JButton btnOk_1;
	private JPanel panel_2;
	private static Pasapalabra pasapalabra = Pasapalabra.getPasapalabra();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NombresJug dialog = new NombresJug();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public NombresJug() {

		initialize();
	}

	private void initialize() {
		setTitle("Introduce los nombres de los jugadores");
		setBounds(100, 100, 400, 300);
		getContentPane().setLayout(new GridLayout(3, 1, 0, 0));
		getContentPane().add(getPanel());
		getContentPane().add(getPanel_1());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension windowSize = this.getSize();
		if (windowSize.height > screenSize.height) {
			windowSize.height = screenSize.height;
		}
		if (windowSize.width > screenSize.width) {
			windowSize.width = screenSize.width;
		}
		setLocation((screenSize.width - windowSize.width) / 2,
				(screenSize.height - windowSize.height) / 2);
		getContentPane().add(getPanel_2());
		setIconImage(new ImageIcon(MenuPrincipal.class.getResource("/packRecursos/icon.jpg")).getImage());
	}

	public JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new EmptyBorder(50, 50, 50, 50));
			panel.add(getLblNumJugadores());
			panel.add(getTextField());
		}
		return panel;
	}

	public JLabel getLblNumJugadores() {
		if (lblNumJugadores == null) {
			lblNumJugadores = new JLabel("Jugador 1:");
			lblNumJugadores.setHorizontalAlignment(SwingConstants.LEFT);
			lblNumJugadores.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblNumJugadores;
	}

	public JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBorder(new EmptyBorder(50, 50, 50, 50));
			panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
			panel_1.add(getLblJugador());
			panel_1.add(getTextField_1());
		}
		return panel_1;
	}

	public JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if (arg0.getKeyCode() == KeyEvent.VK_ENTER && !textField.getText().equals("") && !textField_1.getText().equals("")) {
						pasapalabra.inicializar(textField.getText(), textField_1.getText(), true);
						pasapalabra.jugar();
						Juego juego = new Juego();
						juego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						juego.setVisible(true);
						dispose();
					}
				}
			});
			textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
			textField.setToolTipText("");
			textField.setHorizontalAlignment(SwingConstants.RIGHT);
			textField.setColumns(10);
		}
		return textField;
	}

	public JLabel getLblJugador() {
		if (lblJugador == null) {
			lblJugador = new JLabel("Jugador 2:");
			lblJugador.setHorizontalAlignment(SwingConstants.LEFT);
			lblJugador.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblJugador;
	}

	public JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if (arg0.getKeyCode() == KeyEvent.VK_ENTER && !textField.getText().equals("") && !textField_1.getText().equals("")) {
						pasapalabra.inicializar(textField.getText(), textField_1.getText(), true);
						pasapalabra.jugar();
						Juego juego = new Juego();
						juego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						juego.setVisible(true);
						dispose();
					}
				}
			});
			textField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
			textField_1.setHorizontalAlignment(SwingConstants.RIGHT);
			textField_1.setColumns(10);
		}
		return textField_1;
	}

	public JButton getBtnOk_1() {
		if (btnOk_1 == null) {
			btnOk_1 = new JButton("OK");
			btnOk_1.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if (arg0.getKeyCode() == KeyEvent.VK_ENTER && !textField.getText().equals("") && !textField_1.getText().equals("")) {
						pasapalabra.inicializar(textField.getText(), textField_1.getText(), true);
						pasapalabra.jugar();
						Juego juego = new Juego();
						juego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						juego.setVisible(true);
						dispose();
					}
				}
			});
			btnOk_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnOk_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					pasapalabra.inicializar(textField.getText(), textField_1.getText(), true);
					pasapalabra.jugar();
					Juego juego = new Juego();
					juego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					juego.setVisible(true);
					dispose();
				}
			});
		}
		return btnOk_1;
	}

	public JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
			flowLayout.setVgap(25);
			panel_2.add(getBtnOk_1());
		}
		return panel_2;
	}
}
