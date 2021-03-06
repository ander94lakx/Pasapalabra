package packVista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class NumJugadores extends JDialog {

	private static final long serialVersionUID = 6578834991388096246L;
	private JPanel panel;
	private JLabel lblNumJugadores;
	private JPanel panel_1;
	private JButton btnUnJug;
	private JButton btnDosJug;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NumJugadores dialog = new NumJugadores();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NumJugadores() {
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 400, 300);
		getContentPane().add(getPanel(), BorderLayout.NORTH);
		getContentPane().add(getPanel_1(), BorderLayout.CENTER);

		// Centrar la ventana
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension windowSize = this.getSize();
		if (windowSize.height > screenSize.height)
			windowSize.height = screenSize.height;
		if (windowSize.width > screenSize.width)
			windowSize.width = screenSize.width;
		setLocation((screenSize.width - windowSize.width) / 2, (screenSize.height - windowSize.height) / 2);
		
		setIconImage(new ImageIcon(MenuPrincipal.class.getResource("/packRecursos/icon.jpg")).getImage());
	}

	public JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new EmptyBorder(30, 0, 30, 0));
			panel.add(getLblNumJugadores());
		}
		return panel;
	}

	public JLabel getLblNumJugadores() {
		if (lblNumJugadores == null) {
			lblNumJugadores = new JLabel("Numero de jugadores:");
			lblNumJugadores.setFont(new Font("Tahoma", Font.PLAIN, 24));
		}
		return lblNumJugadores;
	}

	public JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBorder(new EmptyBorder(50, 50, 50, 50));
			panel_1.setLayout(new GridLayout(0, 2, 100, 200));
			panel_1.add(getBtnUnJug());
			panel_1.add(getBtnDosJug());
		}
		return panel_1;
	}

	public JButton getBtnUnJug() {
		if (btnUnJug == null) {
			btnUnJug = new JButton("1");
			btnUnJug.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
						dispose();
						NombreJug.main(null);
					}
				}
			});
			btnUnJug.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
					NombreJug.main(null);
				}
			});
			btnUnJug.setFont(new Font("Dialog", Font.PLAIN, 24));
		}
		return btnUnJug;
	}

	public JButton getBtnDosJug() {
		if (btnDosJug == null) {
			btnDosJug = new JButton("2");
			btnDosJug.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
						NombresJug.main(null);
						dispose();
					}
				}
			});
			btnDosJug.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					NombresJug.main(null);
					dispose();
				}
			});
			btnDosJug.setFont(new Font("Dialog", Font.PLAIN, 24));
		}
		return btnDosJug;
	}
}
