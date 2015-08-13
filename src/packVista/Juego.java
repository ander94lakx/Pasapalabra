package packVista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import packModelo.DefinicionRosco;
import packModelo.Estado;
import packModelo.Jugador;
import packModelo.Letra;
import packModelo.Pasapalabra;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;

import java.awt.Insets;

import javax.swing.border.SoftBevelBorder;
import javax.swing.JLayeredPane;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import com.github.sarxos.webcam.*;

public class Juego extends JFrame implements Observer {
	
	private static final long serialVersionUID = -7237743091044603390L;
	
	private static int tamIcono = 38;
	private static final boolean CON_ICONOS = true;
	public static boolean WEBCAM_ACTIVA = true;
	private static Pasapalabra pasapalabra = Pasapalabra.getPasapalabra();
	
	// Variables relativas a la GUI
	private JPanel contentPane;
	private JPanel panelInferior;
	private JTextField campoRespuesta;
	private JButton btnPasapalabra;
	private JButton btnResponder;
	private JPanel panelPuntuacion;
	private JLabel aciertos; 
	private JLabel fallos;
	private JLabel tiempoRestante;
	private JTextArea pregunta;
	private JPanel panelNombreJug;
	private JLabel lblJugador;
	private JPanel panelLetra;
	private JLabel lblLetra;
	private JPanel panelBotones;
	private LinkedList<JLabel> letrasRosco;
	private JLabel lblA;
	private JLabel lblB;
	private JLabel lblC;
	private JLabel lblD;
	private JLabel lblE;
	private JLabel lblF;
	private JLabel lblG;
	private JLabel lblH;
	private JLabel lblI;
	private JLabel lblJ;
	private JLabel lblK;
	private JLabel lblL;
	private JLabel lblM;
	private JLabel lblN;
	private JLabel lblO;
	private JLabel lblP;
	private JLabel lblQ;
	private JLabel lblR;
	private JLabel lblS;
	private JLabel lblT;
	private JLabel lblU;
	private JLabel lblV;
	private JLabel lblW;
	private JLabel lblX;
	private JLabel lblY;
	private JLabel lblZ;
	private JLabel label_1;
	private JPanel panelRespuesta;
	private JPanel panelPregunta;
	private JScrollPane scrollPane;
	private JLayeredPane panelRosco;
	
	// Variables relativas a la distribucion de las letras
	private int desplazamiento = 30;

	private static boolean externo = true; 
		// Esta variable sirve para que si se ejecuta el main de juego, que
		// esta creado para hacer pruebas, no se carguen los observables y asi
		// no de error
	
	private WebcamPanel panelWebcam = null;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					externo = false;
					//Pasapalabra.inicializar("Pedro","Juan", true);
					Juego frame = new Juego();
					frame.setVisible(true);
					//Pasapalabra.inicializarPartida();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public Juego() {
		initialize();
		if (externo) {
			if (pasapalabra.modoDosJugadores()) {
				pasapalabra.getJugador(0).addObserver(this);
				pasapalabra.getJugador(1).addObserver(this);
			} else {
				pasapalabra.getJugador(0).addObserver(this);
			}
			pasapalabra.addObserver(this);
		}
		letrasRosco = new LinkedList<JLabel>();
		letrasRosco.add(lblA);
		letrasRosco.add(lblB);
		letrasRosco.add(lblC);
		letrasRosco.add(lblD);
		letrasRosco.add(lblE);
		letrasRosco.add(lblF);
		letrasRosco.add(lblG);
		letrasRosco.add(lblH);
		letrasRosco.add(lblI);
		letrasRosco.add(lblJ);
		letrasRosco.add(lblK);
		letrasRosco.add(lblL);
		letrasRosco.add(lblM);
		letrasRosco.add(lblN);
		letrasRosco.add(lblO);
		letrasRosco.add(lblP);
		letrasRosco.add(lblQ);
		letrasRosco.add(lblR);
		letrasRosco.add(lblS);
		letrasRosco.add(lblT);
		letrasRosco.add(lblU);
		letrasRosco.add(lblV);
		letrasRosco.add(lblW);
		letrasRosco.add(lblX);
		letrasRosco.add(lblY);
		letrasRosco.add(lblZ);
		if (CON_ICONOS) {
			int i = 0;
			for (JLabel lbl : letrasRosco) {
				ImageIcon imgIco = new ImageIcon(
						Juego.class.getResource("/packRecursos/letra"
								+ Letra.values()[i] + "azul.png"));
				Icon icono = new ImageIcon(imgIco.getImage().getScaledInstance(
						tamIcono, tamIcono, Image.SCALE_DEFAULT));
				lbl.setIcon(icono);
				lbl.setSize(tamIcono, tamIcono);
				i++;
			}
		} else {
			int i = 0;
			for (JLabel lbl : letrasRosco) {
				lbl.setIcon(null);
				lbl.setFont(new Font("Tahoma", Font.BOLD, 20));
				lbl.setText(Letra.values()[i].name());
				i++;
			}
		}
		if(WEBCAM_ACTIVA)
			iniciarCamara();
		posicionarRosco();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 instanceof Jugador) {
			getLblJugador().setText(((Jugador) arg0).getNombre());
			getAciertos().setText(
					(String) Integer.toString(((Jugador) arg0).getAciertos()));
			getFallos().setText(
					(String) Integer.toString(((Jugador) arg0).getFallos()));
			getTiempoRestante().setText(
					(String) Integer.toString(((Jugador) arg0)
							.getTiempoRestante()));
			//actualizarRosco((Jugador)arg0);
			actualizarRosco();
			
		}
		if (pasapalabra.isTerminado()) {
			GameOver go = new GameOver();
			go.setVisible(true);
			this.setVisible(false);
			pasapalabra.setTerminado(false);
		}
		getPregunta().setText(pasapalabra.getDefinicionActual().getEnunciado());
		getLblLetra().setText(pasapalabra.getDefinicionActual().getLetra().name());
		actualizarRosco();
	}

	public void actualizarRosco() {
		Letra[] letras = Letra.values();
		Jugador j = pasapalabra.getJugadorActual();
		if (CON_ICONOS) {
			for (int i = 0; i < letras.length; i++) {
				DefinicionRosco def = j.getRosco().obtenerDefinicionRosco(
						Letra.values()[i]);
				if (def.getEstadoRespuesta() == Estado.CORRECTA) {
					ImageIcon imgIco = new ImageIcon(
							Juego.class.getResource("/packRecursos/letra" + letras[i] + "verde.png"));
					Icon icono = new ImageIcon(imgIco.getImage()
							.getScaledInstance(tamIcono, tamIcono, Image.SCALE_DEFAULT));
					letrasRosco.get(i).setIcon(icono);
				} else if (def.getEstadoRespuesta() == Estado.FALLIDA) {
					ImageIcon imgIco = new ImageIcon(
							Juego.class.getResource("/packRecursos/letra" + letras[i] + "rojo.png"));
					Icon icono = new ImageIcon(imgIco.getImage()
							.getScaledInstance(tamIcono, tamIcono, Image.SCALE_DEFAULT));
					letrasRosco.get(i).setIcon(icono);
				} else {
					ImageIcon imgIco = new ImageIcon(
							Juego.class.getResource("/packRecursos/letra" + letras[i] + "azul.png"));
					Icon icono = new ImageIcon(imgIco.getImage()
							.getScaledInstance(tamIcono, tamIcono, Image.SCALE_DEFAULT));
					letrasRosco.get(i).setIcon(icono);
				}
			}
		}
		else {
			for (int i = 0; i < letras.length; i++) {
				DefinicionRosco def = j.getRosco().obtenerDefinicionRosco(Letra.values()[i]);
				if (def.getEstadoRespuesta() == Estado.CORRECTA) {
					letrasRosco.get(i).setForeground(new Color(0, 200, 0)); // Es un verde pero no tan brillante como el basico
				} else if (def.getEstadoRespuesta() == Estado.FALLIDA) {
					letrasRosco.get(i).setForeground(Color.RED);
				} else {
					letrasRosco.get(i).setForeground(Color.BLUE);
				}
			}
		}
	}

	public void posicionarRosco(){
		int anchura = getPanelRosco().getWidth();
		int altura = getPanelRosco().getHeight();
		//System.out.println("Anchura: "+anchura+" Altura: "+altura);
		int puntoMedioX = anchura / 2;
		int puntoMedioY = altura / 2;
		int radio = Math.min(altura, anchura) / 2 - desplazamiento;
		//System.out.println("Radio: "+radio);
		for(int i = 0; i < letrasRosco.size(); i++){
			double grados =(360/25.98)*i; // Ligeramente menor a 26 para compensar los casting a enteros
			double rad = (grados*Math.PI)/180;
			double x = (puntoMedioX + radio * Math.sin(rad));
			double y = (puntoMedioY - radio * Math.cos(rad));
			if(CON_ICONOS)
				letrasRosco.get(i).setBounds((int)(x - tamIcono/2), (int)(y - tamIcono/2), tamIcono, tamIcono);
			else
				letrasRosco.get(i).setBounds((int)x, (int)y, 20, 20);
		}
	}

	public void iniciarCamara(){
		Webcam webcam = Webcam.getDefault();
		//webcam.setCustomViewSizes(new Dimension[] { new Dimension(320,240), new Dimension(640,480), new Dimension(960,700), new Dimension(1280,960) });
		webcam.setViewSize(new Dimension(640,480));
		panelWebcam = new WebcamPanel(webcam);
		panelWebcam.start();
		panelWebcam.setBounds(0, 0, getPanelRosco().getWidth(), getPanelRosco().getHeight());
		panelRosco.add(panelWebcam, 0);
		getPanelRosco().setLayer(panelWebcam, 0);
		panelWebcam.setFillArea(true);
	}
	
	public void reescalarCamara(){
		panelWebcam.setBounds(0, 0, getPanelRosco().getWidth(), getPanelRosco().getHeight());
		panelWebcam.setFillArea(true);
	}

	// Accion que se lleva a cabo al darle a responder o al hacer intro
	public void accionResponder() {
		// Cadena de caracteres original a sustituir.
		String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
		// Cadena de caracteres ASCII que reemplazarán los originales.
		String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
		String output = getCampoRespuesta().getText();
		if (output.equals("")) {
			accionPasapalabra();
			return;
		}
		for (int i = 0; i < original.length(); i++)
			// Reemplazamos los caracteres especiales.
			output = output.replace(original.charAt(i), ascii.charAt(i));
		pasapalabra.setRespuestaRecibida(output);
		getCampoRespuesta().setText("");
		pasapalabra.gestionarRespuesta(output);
	}

	// Accion que se lleva a cabo al hacer pasapalabra
	public void accionPasapalabra() {
		pasapalabra.setRespuestaRecibida("");
		getCampoRespuesta().setText("");
		pasapalabra.gestionarRespuesta("");
	}

	private void initialize() {
		setTitle("Pasapalabra");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(getPanelRosco(), GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(getPanelPuntuacion(), Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
								.addComponent(getPanelNombreJug(), GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)))
						.addComponent(getPanelInferior(), GroupLayout.PREFERRED_SIZE, 484, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(getPanelPuntuacion(), GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(getPanelNombreJug(), GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
						.addComponent(getPanelRosco(), GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(getPanelInferior(), GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
		);
		contentPane.setLayout(gl_contentPane);
		// TOCHOCODIGO para centrar la ventana
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension windowSize = this.getSize();
		if (windowSize.height > screenSize.height)
			windowSize.height = screenSize.height;
		if (windowSize.width > screenSize.width)
			windowSize.width = screenSize.width;
		setLocation((screenSize.width - windowSize.width) / 2,
				(screenSize.height - windowSize.height) / 2);
		// FIN TOCHOCODIGO
		this.setMinimumSize(new Dimension(550, 550)); // Estambece tamaño minimo
														// para la ventana
		
		setIconImage(new ImageIcon(MenuPrincipal.class.getResource("/packRecursos/icon.jpg")).getImage());
		
		//pack();
	}

	public JPanel getPanelInferior() {
		if (panelInferior == null) {
			panelInferior = new JPanel();
			panelInferior.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panelInferior.setLayout(new GridLayout(0, 1, 0, 0));
			panelInferior.add(getPanelPregunta());
			panelInferior.add(getPanelRespuesta());
		}
		return panelInferior;
	}

	public JTextField getCampoRespuesta() {
		if (campoRespuesta == null) {
			campoRespuesta = new JTextField();
			campoRespuesta.setHorizontalAlignment(SwingConstants.CENTER);
			campoRespuesta.setMinimumSize(new Dimension(6, 30));
			campoRespuesta.setFont(new Font("Tahoma", Font.PLAIN, 15));
			campoRespuesta.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
						accionResponder();
					}
				}
			});
			campoRespuesta.setColumns(10);
		}
		return campoRespuesta;
	}

	public JButton getBtnPasapalabra() {
		if (btnPasapalabra == null) {
			btnPasapalabra = new JButton("PASAPALABRA");
			btnPasapalabra.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					accionPasapalabra();
				}
			});
			btnPasapalabra.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return btnPasapalabra;
	}

	public JButton getBtnResponder() {
		if (btnResponder == null) {
			btnResponder = new JButton("RESPONDER");
			btnResponder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					accionResponder();
				}
			});
			btnResponder.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return btnResponder;
	}

	public JPanel getPanelPuntuacion() {
		if (panelPuntuacion == null) {
			panelPuntuacion = new JPanel();
			panelPuntuacion.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panelPuntuacion.setLayout(new GridLayout(3, 1, 0, 0));
			panelPuntuacion.add(getAciertos());
			panelPuntuacion.add(getFallos());
			panelPuntuacion.add(getTiempoRestante());
		}
		return panelPuntuacion;
	}

	public JLabel getAciertos() {
		if (aciertos == null) {
			aciertos = new JLabel("");
			aciertos.setToolTipText("Aciertos que lleva el jugador que esta jugando en este momento");
			aciertos.setBackground(Color.WHITE);
			aciertos.setForeground(Color.GREEN);
			aciertos.setHorizontalAlignment(SwingConstants.CENTER);
			aciertos.setFont(new Font("Tahoma", Font.PLAIN, 30));
		}
		return aciertos;
	}

	public JLabel getFallos() {
		if (fallos == null) {
			fallos = new JLabel("");
			fallos.setToolTipText("Fallos que lleva el jugador que esta jugando en este momento");
			fallos.setForeground(Color.RED);
			fallos.setFont(new Font("Tahoma", Font.PLAIN, 30));
			fallos.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return fallos;
	}

	public JLabel getTiempoRestante() {
		if (tiempoRestante == null) {
			tiempoRestante = new JLabel("");
			tiempoRestante
					.setToolTipText("Tiempo restante del jugador que esta jugando en este momento");
			tiempoRestante.setBackground(new Color(255, 255, 255));
			tiempoRestante.setForeground(Color.BLUE);
			tiempoRestante.setHorizontalAlignment(SwingConstants.CENTER);
			tiempoRestante.setFont(new Font("Tahoma", Font.PLAIN, 30));
		}
		return tiempoRestante;
	}

	public JTextArea getPregunta() {
		if (pregunta == null) {
			pregunta = new JTextArea();
			pregunta.setMargin(new Insets(2, 5, 2, 5));
			pregunta.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			pregunta.setWrapStyleWord(true);
			pregunta.setEditable(false);
			pregunta.setFont(new Font("Tahoma", Font.PLAIN, 14));
			pregunta.setForeground(Color.BLACK);
			pregunta.setBackground(Color.LIGHT_GRAY);
			pregunta.setLineWrap(true);
		}
		return pregunta;
	}

	public JPanel getPanelNombreJug() {
		if (panelNombreJug == null) {
			panelNombreJug = new JPanel();
			panelNombreJug.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panelNombreJug.setLayout(new GridLayout(1, 0, 0, 0));
			panelNombreJug.add(getLblJugador());
		}
		return panelNombreJug;
	}

	public JLabel getLblJugador() {
		if (lblJugador == null) {
			lblJugador = new JLabel("");
			lblJugador
					.setToolTipText("Nombre del jugador que esta jugando en este momento");
			lblJugador.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblJugador.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblJugador;
	}

	public JPanel getPanelLetra() {
		if (panelLetra == null) {
			panelLetra = new JPanel();
			panelLetra.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panelLetra.setLayout(new GridLayout(1, 1, 0, 0));
			panelLetra.add(getLblLetra());
		}
		return panelLetra;
	}

	public JLabel getLblLetra() {
		if (lblLetra == null) {
			lblLetra = new JLabel("");
			lblLetra.setBorder(new EmptyBorder(0, 15, 0, 15));
			lblLetra.setHorizontalAlignment(SwingConstants.CENTER);
			lblLetra.setFont(new Font("Tahoma", Font.PLAIN, 22));
		}
		return lblLetra;
	}

	public JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.setBorder(new EmptyBorder(2, 4, 2, 4));
			panelBotones.setLayout(new GridLayout(1, 2, 0, 0));
			panelBotones.add(getBtnResponder());
			panelBotones.add(getBtnPasapalabra());
		}
		return panelBotones;
	}

	public JLabel getLblA() {
		if (lblA == null) {
			lblA = new JLabel("");
			getPanelRosco().setLayer(lblA, 1);
			lblA.setBounds(0, 0, 79, 54);
			lblA.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblA.setForeground(Color.BLUE);
			lblA.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblA;
	}

	public JLabel getLblB() {
		if (lblB == null) {
			lblB = new JLabel("");
			getPanelRosco().setLayer(lblB, 1);
			lblB.setBounds(0, 0, 79, 54);
			lblB.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblB.setForeground(Color.BLUE);
			lblB.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblB;
	}

	public JLabel getLblC() {
		if (lblC == null) {
			lblC = new JLabel("");
			getPanelRosco().setLayer(lblC, 1);
			lblC.setBounds(0, 0, 79, 54);
			lblC.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblC.setForeground(Color.BLUE);
			lblC.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblC;
	}

	public JLabel getLblD() {
		if (lblD == null) {
			lblD = new JLabel("");
			getPanelRosco().setLayer(lblD, 1);
			lblD.setBounds(0, 0, 79, 54);
			lblD.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblD.setForeground(Color.BLUE);
			lblD.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblD;
	}

	public JLabel getLblE() {
		if (lblE == null) {
			lblE = new JLabel("");
			getPanelRosco().setLayer(lblE, 1);
			lblE.setBounds(0, 0, 79, 54);
			lblE.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblE.setForeground(Color.BLUE);
			lblE.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblE;
	}

	public JLabel getLblF() {
		if (lblF == null) {
			lblF = new JLabel("");
			getPanelRosco().setLayer(lblF, 1);
			lblF.setBounds(0, 0, 79, 54);
			lblF.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblF.setForeground(Color.BLUE);
			lblF.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblF;
	}

	public JLabel getLblG() {
		if (lblG == null) {
			lblG = new JLabel("");
			getPanelRosco().setLayer(lblG, 1);
			lblG.setBounds(0, 0, 79, 54);
			lblG.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblG.setForeground(Color.BLUE);
			lblG.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblG;
	}

	public JLabel getLblH() {
		if (lblH == null) {
			lblH = new JLabel("");
			getPanelRosco().setLayer(lblH, 1);
			lblH.setBounds(0, 0, 79, 54);
			lblH.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblH.setForeground(Color.BLUE);
			lblH.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblH;
	}

	public JLabel getLblI() {
		if (lblI == null) {
			lblI = new JLabel("");
			getPanelRosco().setLayer(lblI, 1);
			lblI.setBounds(0, 0, 79, 54);
			lblI.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblI.setForeground(Color.BLUE);
			lblI.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblI;
	}

	public JLabel getLblJ() {
		if (lblJ == null) {
			lblJ = new JLabel("");
			getPanelRosco().setLayer(lblJ, 1);
			lblJ.setBounds(0, 0, 79, 54);
			lblJ.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblJ.setForeground(Color.BLUE);
			lblJ.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblJ;
	}

	public JLabel getLblK() {
		if (lblK == null) {
			lblK = new JLabel("");
			getPanelRosco().setLayer(lblK, 1);
			lblK.setBounds(0, 0, 79, 54);
			lblK.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblK.setForeground(Color.BLUE);
			lblK.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblK;
	}

	public JLabel getLblL() {
		if (lblL == null) {
			lblL = new JLabel("");
			getPanelRosco().setLayer(lblL, 1);
			lblL.setBounds(0, 0, 79, 54);
			lblL.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblL.setForeground(Color.BLUE);
			lblL.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblL;
	}

	public JLabel getLblM() {
		if (lblM == null) {
			lblM = new JLabel("");
			lblM.setBounds(0, 0, 79, 54);
			lblM.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblM.setForeground(Color.BLUE);
			lblM.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblM;
	}

	public JLabel getLblN() {
		if (lblN == null) {
			lblN = new JLabel("");
			getPanelRosco().setLayer(lblN, 1);
			lblN.setBounds(0, 0, 79, 54);
			lblN.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblN.setForeground(Color.BLUE);
			lblN.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblN;
	}

	public JLabel getLblO() {
		if (lblO == null) {
			lblO = new JLabel("");
			getPanelRosco().setLayer(lblO, 1);
			lblO.setBounds(0, 0, 79, 54);
			lblO.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblO.setForeground(Color.BLUE);
			lblO.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblO;
	}

	public JLabel getLblP() {
		if (lblP == null) {
			lblP = new JLabel("");
			getPanelRosco().setLayer(lblP, 1);
			lblP.setBounds(0, 0, 79, 54);
			lblP.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblP.setForeground(Color.BLUE);
			lblP.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblP;
	}

	public JLabel getLblQ() {
		if (lblQ == null) {
			lblQ = new JLabel("");
			getPanelRosco().setLayer(lblQ, 1);
			lblQ.setBounds(0, 0, 79, 54);
			lblQ.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblQ.setForeground(Color.BLUE);
			lblQ.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblQ;
	}

	public JLabel getLblR() {
		if (lblR == null) {
			lblR = new JLabel("");
			getPanelRosco().setLayer(lblR, 1);
			lblR.setBounds(0, 0, 79, 54);
			lblR.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblR.setForeground(Color.BLUE);
			lblR.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblR;
	}

	public JLabel getLblS() {
		if (lblS == null) {
			lblS = new JLabel("");
			getPanelRosco().setLayer(lblS, 1);
			lblS.setBounds(0, 0, 79, 54);
			lblS.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblS.setForeground(Color.BLUE);
			lblS.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblS;
	}

	public JLabel getLblT() {
		if (lblT == null) {
			lblT = new JLabel("");
			getPanelRosco().setLayer(lblT, 1);
			lblT.setBounds(0, 0, 79, 54);
			lblT.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblT.setForeground(Color.BLUE);
			lblT.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblT;
	}

	public JLabel getLblU() {
		if (lblU == null) {
			lblU = new JLabel("");
			getPanelRosco().setLayer(lblU, 1);
			lblU.setBounds(0, 0, 79, 54);
			lblU.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblU.setForeground(Color.BLUE);
			lblU.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblU;
	}

	public JLabel getLblV() {
		if (lblV == null) {
			lblV = new JLabel("");
			getPanelRosco().setLayer(lblV, 1);
			lblV.setBounds(0, 0, 79, 54);
			lblV.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblV.setForeground(Color.BLUE);
			lblV.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblV;
	}

	public JLabel getLblW() {
		if (lblW == null) {
			lblW = new JLabel("");
			getPanelRosco().setLayer(lblW, 1);
			lblW.setBounds(0, 0, 79, 54);
			lblW.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblW.setForeground(Color.BLUE);
			lblW.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblW;
	}

	public JLabel getLblX() {
		if (lblX == null) {
			lblX = new JLabel("");
			getPanelRosco().setLayer(lblX, 1);
			lblX.setBounds(0, 0, 79, 54);
			lblX.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblX.setForeground(Color.BLUE);
			lblX.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblX;
	}

	public JLabel getLblY() {
		if (lblY == null) {
			lblY = new JLabel("");
			getPanelRosco().setLayer(lblY, 1);
			lblY.setBounds(0, 0, 79, 54);
			lblY.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblY.setForeground(Color.BLUE);
			lblY.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblY;
	}

	public JLabel getLblZ() {
		if (lblZ == null) {
			lblZ = new JLabel("");
			getPanelRosco().setLayer(lblZ, 1);
			lblZ.setBounds(0, 0, 79, 54);
			lblZ.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblZ.setForeground(Color.BLUE);
			lblZ.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblZ;
	}
	public JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("");
		}
		return label_1;
	}
	public JPanel getPanelRespuesta() {
		if (panelRespuesta == null) {
			panelRespuesta = new JPanel();
			panelRespuesta.setBorder(new EmptyBorder(6, 6, 6, 6));
			panelRespuesta.setLayout(new BoxLayout(panelRespuesta, BoxLayout.X_AXIS));
			panelRespuesta.add(getCampoRespuesta());
			panelRespuesta.add(getPanelBotones());
		}
		return panelRespuesta;
	}
	public JPanel getPanelPregunta() {
		if (panelPregunta == null) {
			panelPregunta = new JPanel();
			panelPregunta.setBorder(new EmptyBorder(4, 6, 4, 6));
			GroupLayout gl_panelPregunta = new GroupLayout(panelPregunta);
			gl_panelPregunta.setHorizontalGroup(
				gl_panelPregunta.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelPregunta.createSequentialGroup()
						.addComponent(getPanelLetra(), GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(getScrollPane(), GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
						.addGap(1))
			);
			gl_panelPregunta.setVerticalGroup(
				gl_panelPregunta.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelPregunta.createSequentialGroup()
						.addGroup(gl_panelPregunta.createParallelGroup(Alignment.LEADING)
							.addComponent(getPanelLetra(), GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addComponent(getScrollPane(), GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			panelPregunta.setLayout(gl_panelPregunta);
		}
		return panelPregunta;
	}
	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			scrollPane.setViewportView(getPregunta());
		}
		return scrollPane;
	}
	public JLayeredPane getPanelRosco() {
		if (panelRosco == null) {
			panelRosco = new JLayeredPane();
			panelRosco.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent arg0) {
					posicionarRosco();
					if(WEBCAM_ACTIVA)
						reescalarCamara();
				}
			});
			panelRosco.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panelRosco.setLayout(null);
			panelRosco.add(getLblA());
			panelRosco.add(getLblB());
			panelRosco.add(getLblC());
			panelRosco.add(getLblD());
			panelRosco.add(getLblE());
			panelRosco.add(getLblF());
			panelRosco.add(getLblG());
			panelRosco.add(getLblH());
			panelRosco.add(getLblI());
			panelRosco.add(getLblJ());
			panelRosco.add(getLblK());
			panelRosco.add(getLblL());
			panelRosco.add(getLblM());
			panelRosco.add(getLblN());
			panelRosco.add(getLblO());
			panelRosco.add(getLblP());
			panelRosco.add(getLblQ());
			panelRosco.add(getLblR());
			panelRosco.add(getLblS());
			panelRosco.add(getLblT());
			panelRosco.add(getLblU());
			panelRosco.add(getLblV());
			panelRosco.add(getLblW());
			panelRosco.add(getLblX());
			panelRosco.add(getLblY());
			panelRosco.add(getLblZ());
		}
		return panelRosco;
	}
}
