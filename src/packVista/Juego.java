package packVista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.LabelUI;

import packModelo.DefinicionRosco;
import packModelo.Estado;
import packModelo.Jugador;
import packModelo.Letra;
import packModelo.Pasapalabra;
import java.awt.FlowLayout;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

//TODO Hacer que sea reescalable por completo tras a�adir el campo de la letra
public class Juego extends JFrame implements Observer {
	private static final long serialVersionUID = -7237743091044603390L;

	private JPanel contentPane;
	private JPanel panelInferior;
	private JPanel panelRosco;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Juego frame = new Juego();
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
	public Juego() {
		initialize();
		if (Pasapalabra.modoDosJugadores()) {
			Pasapalabra.getJugador(0).addObserver(this);
			Pasapalabra.getJugador(1).addObserver(this);
		} else {
			Pasapalabra.getJugador(0).addObserver(this);
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
		for (JLabel lbl : letrasRosco)
			lbl.setFont(new Font("Tahoma", Font.BOLD, 16));
	}

	private void initialize() {
		setTitle("Pasapalabra");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																Alignment.TRAILING,
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				getPanelRosco(),
																				GroupLayout.DEFAULT_SIZE,
																				399,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING,
																								false)
																						.addComponent(
																								getPanelNombreJug(),
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								getPanelPuntuacion(),
																								GroupLayout.DEFAULT_SIZE,
																								79,
																								Short.MAX_VALUE)))
														.addComponent(
																getPanelInferior(),
																GroupLayout.PREFERRED_SIZE,
																484,
																Short.MAX_VALUE))
										.addContainerGap()));
		gl_contentPane
				.setVerticalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				getPanelPuntuacion(),
																				GroupLayout.DEFAULT_SIZE,
																				253,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				getPanelNombreJug(),
																				GroupLayout.DEFAULT_SIZE,
																				81,
																				Short.MAX_VALUE))
														.addComponent(
																getPanelRosco(),
																GroupLayout.DEFAULT_SIZE,
																340,
																Short.MAX_VALUE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(getPanelInferior(),
												GroupLayout.PREFERRED_SIZE,
												105, GroupLayout.PREFERRED_SIZE)));
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
		this.setMinimumSize(new Dimension(510, 500)); // Estambece tama�o minimo
														// para la ventana
	}

	public JPanel getPanelInferior() {
		if (panelInferior == null) {
			panelInferior = new JPanel();
			panelInferior.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			GroupLayout gl_panelInferior = new GroupLayout(panelInferior);
			gl_panelInferior
					.setHorizontalGroup(gl_panelInferior
							.createParallelGroup(Alignment.LEADING)
							.addGroup(
									gl_panelInferior
											.createSequentialGroup()
											.addContainerGap()
											.addGroup(
													gl_panelInferior
															.createParallelGroup(
																	Alignment.TRAILING)
															.addGroup(
																	gl_panelInferior
																			.createSequentialGroup()
																			.addComponent(
																					getPanelLetra(),
																					GroupLayout.PREFERRED_SIZE,
																					44,
																					GroupLayout.PREFERRED_SIZE)
																			.addPreferredGap(
																					ComponentPlacement.RELATED)
																			.addComponent(
																					getPregunta(),
																					GroupLayout.DEFAULT_SIZE,
																					412,
																					Short.MAX_VALUE))
															.addGroup(
																	gl_panelInferior
																			.createSequentialGroup()
																			.addComponent(
																					getCampoRespuesta(),
																					GroupLayout.DEFAULT_SIZE,
																					209,
																					Short.MAX_VALUE)
																			.addPreferredGap(
																					ComponentPlacement.RELATED)
																			.addComponent(
																					getPanelBotones(),
																					GroupLayout.PREFERRED_SIZE,
																					247,
																					GroupLayout.PREFERRED_SIZE)))
											.addGap(10)));
			gl_panelInferior
					.setVerticalGroup(gl_panelInferior
							.createParallelGroup(Alignment.LEADING)
							.addGroup(
									gl_panelInferior
											.createSequentialGroup()
											.addGap(8)
											.addGroup(
													gl_panelInferior
															.createParallelGroup(
																	Alignment.LEADING)
															.addComponent(
																	getPregunta(),
																	GroupLayout.DEFAULT_SIZE,
																	49,
																	Short.MAX_VALUE)
															.addComponent(
																	getPanelLetra(),
																	GroupLayout.DEFAULT_SIZE,
																	49,
																	Short.MAX_VALUE))
											.addPreferredGap(
													ComponentPlacement.RELATED)
											.addGroup(
													gl_panelInferior
															.createParallelGroup(
																	Alignment.LEADING)
															.addComponent(
																	getPanelBotones(),
																	GroupLayout.PREFERRED_SIZE,
																	29,
																	Short.MAX_VALUE)
															.addComponent(
																	getCampoRespuesta(),
																	GroupLayout.DEFAULT_SIZE,
																	29,
																	Short.MAX_VALUE))
											.addContainerGap()));
			panelInferior.setLayout(gl_panelInferior);
		}
		return panelInferior;
	}

	public JPanel getPanelRosco() {
		if (panelRosco == null) {
			panelRosco = new JPanel();
			panelRosco.setBackground(Color.LIGHT_GRAY);
			panelRosco.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panelRosco.setLayout(new GridLayout(6, 5, 0, 0));
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

	public JTextField getCampoRespuesta() {
		if (campoRespuesta == null) {
			campoRespuesta = new JTextField();
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
			panelPuntuacion.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panelPuntuacion.setLayout(new GridLayout(3, 1, 0, 0));
			panelPuntuacion.add(getAciertos());
			panelPuntuacion.add(getFallos());
			panelPuntuacion.add(getTiempoRestante());
		}
		return panelPuntuacion;
	}

	public JLabel getAciertos() {
		if (aciertos == null) {
			aciertos = new JLabel("A");
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
			fallos = new JLabel("F");
			fallos.setToolTipText("Fallos que lleva el jugador que esta jugando en este momento");
			fallos.setForeground(Color.RED);
			fallos.setFont(new Font("Tahoma", Font.PLAIN, 30));
			fallos.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return fallos;
	}

	public JLabel getTiempoRestante() {
		if (tiempoRestante == null) {
			tiempoRestante = new JLabel("T");
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
			pregunta.setEditable(false);
			pregunta.setFont(new Font("Tahoma", Font.PLAIN, 15));
			pregunta.setForeground(Color.BLACK);
			pregunta.setBackground(Color.LIGHT_GRAY);
			pregunta.setLineWrap(true);
			pregunta.setText("Campo donde van apareciendo las preguntas");
		}
		return pregunta;
	}

	public JPanel getPanelNombreJug() {
		if (panelNombreJug == null) {
			panelNombreJug = new JPanel();
			panelNombreJug.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panelNombreJug.setLayout(new GridLayout(1, 0, 0, 0));
			panelNombreJug.add(getLblJugador());
		}
		return panelNombreJug;
	}

	public JLabel getLblJugador() {
		if (lblJugador == null) {
			lblJugador = new JLabel("Jugador");
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
			lblLetra = new JLabel("L");
			lblLetra.setHorizontalAlignment(SwingConstants.CENTER);
			lblLetra.setFont(new Font("Tahoma", Font.PLAIN, 22));
		}
		return lblLetra;
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
			actualizarRosco();
		}
		getPregunta().setText(Pasapalabra.getDefinicionActual().getEnunciado());
		getLblLetra().setText(
				Pasapalabra.getDefinicionActual().getLetra().name());
		actualizarRosco();

	}

	public void actualizarRosco() {
		Jugador j = Pasapalabra.getSiguienteJugador();
		for (int i = 0; i < Letra.values().length; i++) {
			DefinicionRosco def = j.getRosco()
					.obtenerDefinicionRosco(Letra.values()[i]);
			if (def.getEstadoRespuesta() == Estado.CORRECTA) {
				letrasRosco.get(i).setForeground(Color.GREEN);
			} else if (def.getEstadoRespuesta() == Estado.FALLIDA) {
				letrasRosco.get(i).setForeground(Color.RED);
			} else {
				letrasRosco.get(i).setForeground(Color.BLUE);
			}
		}
	}

	// Accion que se lleva a cabo al darle a responder o al hacer intro
	public void accionResponder() {
		// Cadena de caracteres original a sustituir.
		String original = "��������������u�������������������";
		// Cadena de caracteres ASCII que reemplazar�n los originales.
		String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
		String output = getCampoRespuesta().getText();
		if (output.equals("")) {
			accionPasapalabra();
			return;
		}
		for (int i = 0; i < original.length(); i++)
			// Reemplazamos los caracteres especiales.
			output = output.replace(original.charAt(i), ascii.charAt(i));
		Pasapalabra.setRespuestaRecibida(output);
		getCampoRespuesta().setText("");
		synchronized (Pasapalabra.lock) {
			Pasapalabra.setSePuedeSeguir(true);
			Pasapalabra.lock.notify();
		}
	}

	// Accion que se lleva a cabo al hacer pasapalabra
	public void accionPasapalabra() {
		Pasapalabra.setRespuestaRecibida("");
		getCampoRespuesta().setText("");
		synchronized (Pasapalabra.lock) {
			Pasapalabra.setSePuedeSeguir(true);
			Pasapalabra.lock.notify();
		}
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
			lblA = new JLabel("A");
			lblA.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblA.setForeground(Color.BLUE);
			lblA.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblA;
	}

	public JLabel getLblB() {
		if (lblB == null) {
			lblB = new JLabel("B");
			lblB.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblB.setForeground(Color.BLUE);
			lblB.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblB;
	}

	public JLabel getLblC() {
		if (lblC == null) {
			lblC = new JLabel("C");
			lblC.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblC.setForeground(Color.BLUE);
			lblC.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblC;
	}

	public JLabel getLblD() {
		if (lblD == null) {
			lblD = new JLabel("D");
			lblD.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblD.setForeground(Color.BLUE);
			lblD.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblD;
	}

	public JLabel getLblE() {
		if (lblE == null) {
			lblE = new JLabel("E");
			lblE.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblE.setForeground(Color.BLUE);
			lblE.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblE;
	}

	public JLabel getLblF() {
		if (lblF == null) {
			lblF = new JLabel("F");
			lblF.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblF.setForeground(Color.BLUE);
			lblF.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblF;
	}

	public JLabel getLblG() {
		if (lblG == null) {
			lblG = new JLabel("G");
			lblG.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblG.setForeground(Color.BLUE);
			lblG.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblG;
	}

	public JLabel getLblH() {
		if (lblH == null) {
			lblH = new JLabel("H");
			lblH.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblH.setForeground(Color.BLUE);
			lblH.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblH;
	}

	public JLabel getLblI() {
		if (lblI == null) {
			lblI = new JLabel("I");
			lblI.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblI.setForeground(Color.BLUE);
			lblI.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblI;
	}

	public JLabel getLblJ() {
		if (lblJ == null) {
			lblJ = new JLabel("J");
			lblJ.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblJ.setForeground(Color.BLUE);
			lblJ.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblJ;
	}

	public JLabel getLblK() {
		if (lblK == null) {
			lblK = new JLabel("K");
			lblK.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblK.setForeground(Color.BLUE);
			lblK.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblK;
	}

	public JLabel getLblL() {
		if (lblL == null) {
			lblL = new JLabel("L");
			lblL.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblL.setForeground(Color.BLUE);
			lblL.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblL;
	}

	public JLabel getLblM() {
		if (lblM == null) {
			lblM = new JLabel("M");
			lblM.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblM.setForeground(Color.BLUE);
			lblM.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblM;
	}

	public JLabel getLblN() {
		if (lblN == null) {
			lblN = new JLabel("N");
			lblN.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblN.setForeground(Color.BLUE);
			lblN.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblN;
	}

	public JLabel getLblO() {
		if (lblO == null) {
			lblO = new JLabel("O");
			lblO.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblO.setForeground(Color.BLUE);
			lblO.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblO;
	}

	public JLabel getLblP() {
		if (lblP == null) {
			lblP = new JLabel("P");
			lblP.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblP.setForeground(Color.BLUE);
			lblP.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblP;
	}

	public JLabel getLblQ() {
		if (lblQ == null) {
			lblQ = new JLabel("Q");
			lblQ.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblQ.setForeground(Color.BLUE);
			lblQ.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblQ;
	}

	public JLabel getLblR() {
		if (lblR == null) {
			lblR = new JLabel("R");
			lblR.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblR.setForeground(Color.BLUE);
			lblR.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblR;
	}

	public JLabel getLblS() {
		if (lblS == null) {
			lblS = new JLabel("S");
			lblS.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblS.setForeground(Color.BLUE);
			lblS.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblS;
	}

	public JLabel getLblT() {
		if (lblT == null) {
			lblT = new JLabel("T");
			lblT.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblT.setForeground(Color.BLUE);
			lblT.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblT;
	}

	public JLabel getLblU() {
		if (lblU == null) {
			lblU = new JLabel("U");
			lblU.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblU.setForeground(Color.BLUE);
			lblU.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblU;
	}

	public JLabel getLblV() {
		if (lblV == null) {
			lblV = new JLabel("V");
			lblV.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblV.setForeground(Color.BLUE);
			lblV.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblV;
	}

	public JLabel getLblW() {
		if (lblW == null) {
			lblW = new JLabel("W");
			lblW.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblW.setForeground(Color.BLUE);
			lblW.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblW;
	}

	public JLabel getLblX() {
		if (lblX == null) {
			lblX = new JLabel("X");
			lblX.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblX.setForeground(Color.BLUE);
			lblX.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblX;
	}

	public JLabel getLblY() {
		if (lblY == null) {
			lblY = new JLabel("Y");
			lblY.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblY.setForeground(Color.BLUE);
			lblY.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblY;
	}

	public JLabel getLblZ() {
		if (lblZ == null) {
			lblZ = new JLabel("Z");
			lblZ.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblZ.setForeground(Color.BLUE);
			lblZ.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblZ;
	}
}
