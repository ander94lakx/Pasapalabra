package packModelo;

import java.util.Observable;

public class Pasapalabra extends Observable {

	// Singletons donde se almacenan informacion persistente
	private Ranking ranking = Ranking.getRanking();
	private CatalogoDefiniciones catalogo = CatalogoDefiniciones.getCatalogoDefiniciones();

	// Gestion de jugadores
	private Jugador listaJugadores[] = new Jugador[2];
	private Jugador jugadorActual;
	private boolean modo2Jugadores;

	private boolean terminado = false;
	private String respuestaRecibida;
	private DefinicionRosco definicionActual;

	/**
	 * Este metodo prepara todo el juego para que este listo para empezar la
	 * partida, carga las definiciones, crea los jugadores e inicializa sus
	 * roscos.
	 * 
	 * @param jug1
	 *            Nombre del primer jugador
	 * @param jug2
	 *            Nombre del segundo jugador
	 * @param modo2jug
	 *            indica si juega un jugador o dos
	 */
	public void inicializar(String jug1, String jug2, boolean modo2jug) {
		catalogo.loadData();
		ranking.cargarPuntuaciones();
		modo2Jugadores = modo2jug;

		if (modo2Jugadores) {
			listaJugadores[0] = new Jugador(jug1);
			listaJugadores[1] = new Jugador(jug2);
			for (Jugador jugador : listaJugadores) {
				Rosco rosco = jugador.getRosco();
				rosco.inicializarRosco();
			}
		} else {
			listaJugadores[0] = new Jugador(jug1);
			Rosco rosco = listaJugadores[0].getRosco();
			rosco.inicializarRosco();
		}
		jugadorActual = listaJugadores[0];
	}

	/**
	 * Este metodo es el que se encarga de jugar, en caso de que la partida
	 * todavia no haya terminado. En concreto juega un unico turno
	 */
	public void jugar() {
		if (modo2Jugadores) {
			if (!listaJugadores[0].haTerminado() || !listaJugadores[1].haTerminado()) {
				Jugador jugador = jugadorActual;
				setDefinicionActual(jugador.realizarPregunta());

			} else {
				for (Jugador jug : listaJugadores)
					ranking.insertarPuntuacionEnRanking(jug);
				ranking.guardarPuntuaciones();
				terminado = true;
				setChanged();
				notifyObservers();
			}
		} else {
			if (!listaJugadores[0].haTerminado()) {
				Jugador jugador = jugadorActual;
				setDefinicionActual(jugador.realizarPregunta());

			} else {
				ranking.insertarPuntuacionEnRanking(listaJugadores[0]);
				ranking.guardarPuntuaciones();
				terminado = true;
				setChanged();
				notifyObservers();
			}
		}
	}

	/**
	 * Este metodo es el que recibe la respuesta del jugador que esta jugando en
	 * ese momento y se encarga de getionarla, ademas de pasar el turno al otro
	 * jugador en caso de que este haya fallado o hecho pasapalabra
	 * 
	 * @param respuesta
	 */
	public void gestionarRespuesta(String respuesta) {
		if (modo2Jugadores) {
			jugadorActual.gestionarRespuesta(respuesta);
			if (!jugadorActual.haAcertadoLaAnterior())
				avanzarJugador();
		} else
			listaJugadores[0].gestionarRespuesta(respuesta);
		jugar();
	}

	public Jugador getJugadorActual() {
		return jugadorActual;
	}

	/**
	 * Este metodo cambia el turno de un jugador a otro siempre que el otro no
	 * haya terminado ya. En caso de jugar uno solo este se mantiene jugando
	 */
	public void avanzarJugador() {
		if (modo2Jugadores) {
			if (jugadorActual.equals(listaJugadores[0])) {
				if (!listaJugadores[1].haTerminado())
					jugadorActual = listaJugadores[1];
			} else if (jugadorActual.equals(listaJugadores[1])) {
				if (!listaJugadores[0].haTerminado())
					jugadorActual = listaJugadores[0];
			}
		} else
			jugadorActual = listaJugadores[0];

	}

	public boolean modoDosJugadores() {
		return modo2Jugadores;
	}

	public Jugador getJugador(int i) {
		if (i == 1 || i == 0)
			return listaJugadores[i];
		else
			return null;
	}

	public String getRespuestaRecibida() {
		return respuestaRecibida;
	}

	public void setRespuestaRecibida(String pRespuestaRecibida) {
		respuestaRecibida = pRespuestaRecibida;
	}

	public DefinicionRosco getDefinicionActual() {
		return definicionActual;
	}

	public void setDefinicionActual(DefinicionRosco pDefinicionActual) {
		definicionActual = pDefinicionActual;
		setChanged();
		notifyObservers();
	}

	public boolean isTerminado() {
		return terminado;
	}

	public void setTerminado(boolean pTerminado) {
		terminado = pTerminado;
	}

	// Parte del singleton
	private static Pasapalabra mPasapalabra;

	private Pasapalabra() {
		
	}

	public static Pasapalabra getPasapalabra() {
		if (mPasapalabra == null)
			mPasapalabra = new Pasapalabra();
		return mPasapalabra;
	}

}