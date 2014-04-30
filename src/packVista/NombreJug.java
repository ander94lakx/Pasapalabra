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

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import packModelo.Pasapalabra;

public class NombreJug extends JDialog {

	private static final long serialVersionUID = -2170937685399162168L;
	private JPanel panel;
	private JLabel lblJugador;
	private JTextField textField;
	private JButton btnOk;
	private JPanel panel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NombreJug dialog = new NombreJug();
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
	public NombreJug() {

		initialize();
	}
	private void initialize() {
		setTitle("Introduce el nombre del jugador");
		setBounds(100, 100, 400, 200);
		getContentPane().setLayout(new GridLayout(2, 1, 0, 0));
		getContentPane().add(getPanel());
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
		getContentPane().add(getPanel_1());
	}

	public JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setVgap(-5);
			panel.setBorder(new EmptyBorder(50, 50, 50, 50));
			panel.add(getLblJugador());
			panel.add(getTextField());
		}
		return panel;
	}
	public JLabel getLblJugador() {
		if (lblJugador == null) {
			lblJugador = new JLabel("Jugador:");
			lblJugador.setHorizontalAlignment(SwingConstants.CENTER);
			lblJugador.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblJugador;
	}
	public JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if(arg0.getKeyCode() == KeyEvent.VK_ENTER && !textField.getText().equals("")) {
						accionJugar();
					}
				}
			});
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
			textField.setColumns(10);
		}
		return textField;
	}
	public JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("OK");
			btnOk.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if(arg0.getKeyCode() == KeyEvent.VK_ENTER && !textField.getText().equals(""))
						accionJugar();
				}
			});
			btnOk.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					accionJugar();
				}
			});
		}
		return btnOk;
	}
	
	public void accionJugar(){
		//Juego.main(null);
		Pasapalabra.main(new String[] {getTextField().getText()});
		setVisible(false);
	}
	public JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
			flowLayout.setVgap(25);
			panel_1.add(getBtnOk());
		}
		return panel_1;
	}
}
