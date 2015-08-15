package packVista;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JTextPane;
import java.awt.Font;

public class Instrucciones extends JDialog {

	private static final long serialVersionUID = -7107191430116927606L;
	private String instrucciones = 
			  "En el panel de arriba aparecerá un rosco con cada letra del abecedario"
			+ " en el que varia el color según este respondida, bien o mal, o no la pregunta correspondiente a cada letra.\n\n"
			+ "Abajo iran apareciendo la preguntas que toca responder y a un lado el jugador que tiene que responderla. "
			+ "Para responderla simbremente escribir en el cuadro de texto la respuesta y pulsar Intro o darle al boton "
			+ "RESPONDER, o si se desea hacer pasapalabra, pulsar el boton PASAPALABRA o pulsar Intro con el campo de texto vacío.\n\n"
			+ "En la parte superior derecha aparcecerán en verde los aciertos, fallos y el tiempo que le queda al jugador que se indica "
			+ "justo debajo de estos indicadores (que es es jugador que esta jugando en ese momento).\n";
	private JTextPane textPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Instrucciones dialog = new Instrucciones();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Instrucciones() {
		initialize();
	}

	private void initialize() {
		setResizable(false);
		setTitle("Instrucciones de juego");
		setBounds(100, 100, 450, 300);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(getTextPane(),
				GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(getTextPane(),
				GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE));
		getContentPane().setLayout(groupLayout);

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

	public JTextPane getTextPane() {
		if (textPane == null) {
			textPane = new JTextPane();
			textPane.setFont(new Font("Tahoma", Font.PLAIN, 13));
			textPane.setEditable(false);
			textPane.setText(instrucciones);
			textPane.setFocusable(false);
			Insets insets = new Insets(20, 20, 0, 20); // Crea un objeto que define los bordes de un elemento
			textPane.setMargin(insets); // Configura el margen con el objeto anterior
		}
		return textPane;
	}
}
