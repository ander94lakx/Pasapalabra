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

import packModelo.Jugador;
import packModelo.Pasapalabra;

//TODO Hacer que sea reescalable por completo tras añadir el campo de la letra
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
		this.setMinimumSize(new Dimension(510, 500)); // Estambece tamaño minimo
														// para la ventana
	}

	public JPanel getPanelInferior() {
		if (panelInferior == null) {
			panelInferior = new JPanel();
			panelInferior.setBorder(new MatteBorder(1, 1, 1, 1,
					(Color) new Color(0, 0, 0)));
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
			panelRosco.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(
					0, 0, 0)));
			GroupLayout gl_panelRosco = new GroupLayout(panelRosco);
			gl_panelRosco.setHorizontalGroup(gl_panelRosco.createParallelGroup(
					Alignment.LEADING).addGap(0, 397, Short.MAX_VALUE));
			gl_panelRosco.setVerticalGroup(gl_panelRosco.createParallelGroup(
					Alignment.LEADING).addGap(0, 338, Short.MAX_VALUE));

			panelRosco.setLayout(gl_panelRosco);
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
					accionResponder();
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
			panelPuntuacion.setBorder(new MatteBorder(1, 1, 1, 1,
					(Color) new Color(0, 0, 0)));
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
			panelNombreJug.setBorder(new MatteBorder(1, 1, 1, 1,
					(Color) new Color(0, 0, 0)));
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
			panelLetra.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(
					0, 0, 0)));
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
		if(arg0 instanceof Jugador) {
			getLblJugador().setText(((Jugador) arg0).getNombre());
			getAciertos().setText(
					(String) Integer.toString(((Jugador) arg0).getAciertos()));
			getFallos().setText(
					(String) Integer.toString(((Jugador) arg0).getFallos()));
			getTiempoRestante()
					.setText(
							(String) Integer.toString(((Jugador) arg0)
									.getTiempoRestante()));
			getPregunta().setText(
					((Jugador) arg0)
							.getRosco()
							.obtenerDefinicionRosco(
									Pasapalabra.getSiguienteJugador()
											.getPosicionRosco()).getEnunciado());
			getLblLetra().setText(((Jugador) arg0).getPosicionRosco().name());
		}
		else if(arg0 instanceof Pasapalabra) {
			getPregunta().setText(((Pasapalabra) arg0).getDefinicionActual().getEnunciado());
			getLblLetra().setText(((Pasapalabra) arg0).getDefinicionActual().getLetra().name());
		}

	}

	// Accion que se lleva a cabo al darle a responder o al hacer intro
	public void accionResponder() {
		Pasapalabra.setRespuestaRecibida(getCampoRespuesta().getText());
		notifyAll();
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
}
