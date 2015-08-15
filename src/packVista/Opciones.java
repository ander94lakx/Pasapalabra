package packVista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JCheckBox;

import packModelo.Jugador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Opciones extends JDialog {

	private static final long serialVersionUID = -3843087081676431850L;

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	JCheckBox chckbxNewCheckBox;

	public static void main(String[] args) {
		try {
			Opciones dialog = new Opciones();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Opciones() {
		initialize();
	}

	private void initialize() {
		setTitle("Opciones");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(2, 1, 0, 0));
		{
			JPanel panel1 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel1.getLayout();
			flowLayout.setVgap(50);
			contentPanel.add(panel1);
			{
				JLabel lblTiempo = new JLabel("Tiempo de juego: ");
				lblTiempo.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblTiempo.setHorizontalAlignment(SwingConstants.CENTER);
				panel1.add(lblTiempo);
			}
			{
				textField = new JTextField();
				textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
				panel1.add(textField);
				textField.setColumns(10);
			}
		}
		{
			JPanel panel2 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel2.getLayout();
			flowLayout.setVgap(50);
			contentPanel.add(panel2);
			{
				JLabel lblActivarWebcam = new JLabel("Activar Webcam:");
				lblActivarWebcam.setFont(new Font("Tahoma", Font.PLAIN, 18));
				panel2.add(lblActivarWebcam);
			}
			{
				chckbxNewCheckBox = new JCheckBox("");
				chckbxNewCheckBox.setSelected(Juego.WEBCAM_ACTIVA);
				panel2.add(chckbxNewCheckBox);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent arg0) {
						if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
							int tiempo = Integer.parseInt(textField.getText());
							if (tiempo >= 10 && tiempo <= 1000) {
								Juego.WEBCAM_ACTIVA = chckbxNewCheckBox.isSelected();
								Jugador.TIEMPO_INICIAL = tiempo;
							} else {
								Juego.WEBCAM_ACTIVA = chckbxNewCheckBox.isSelected();
								Jugador.TIEMPO_INICIAL = 200; // un tiempo por
																// defecto
							}
							Opciones.this.setVisible(false);
						}
					}
				});
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int tiempo;
						try {
							tiempo = Integer.parseInt(textField.getText());
						} catch (NumberFormatException e) {
							tiempo = 200;
						}
						if (tiempo >= 10 && tiempo <= 1000) {
							Juego.WEBCAM_ACTIVA = chckbxNewCheckBox.isSelected();
							Jugador.TIEMPO_INICIAL = tiempo;
						} else {
							Juego.WEBCAM_ACTIVA = chckbxNewCheckBox.isSelected();
							Jugador.TIEMPO_INICIAL = 200; // un tiempo por
															// defecto
						}
						Opciones.this.setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent arg0) {
						if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
							Opciones.this.setVisible(false);
					}
				});
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Opciones.this.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		// Centrar la ventana
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension windowSize = this.getSize();
		if (windowSize.height > screenSize.height) {
			windowSize.height = screenSize.height;
		}
		if (windowSize.width > screenSize.width) {
			windowSize.width = screenSize.width;
		}
		setLocation((screenSize.width - windowSize.width) / 2, (screenSize.height - windowSize.height) / 2);

		setIconImage(new ImageIcon(MenuPrincipal.class.getResource("/packRecursos/icon.jpg")).getImage());
	}

}
