package packModelo;

import java.util.Observable;

public class Pasapalabra extends Observable {

	// Singletons donde se almacenan informacion persistente
	private Ranking ranking = Ranking.getRanking();
	private CatalogoDefiniciones catalogo = CatalogoDefiniciones.getCatalogoDefiniciones();

	// Gestion de jugadores
	private Jugador listaJugadores[] = new Jugador[2];
	private int siguienteJugador = -2;
		// Indica al jugador al que le tocaria jugar el siguiente turno
		// Siempre es el que no esta jugando en ese momento
			// Modos posibles: 0 -> el Siguiente jugador teorico es el Jug 1
			// Modos posibles: 1 -> el Siguiente jugador teorico es el Jug 2
			// Modos posibles: -1 -> el Siguiente jugador teorico es el Jug 2 (Todavia no ha jugado el Jug 2)
			// Modos posibles: -2 -> el Siguiente jugador teorico es el Jug 1 (Todavia no ha jugado ninguno)

	private boolean modo2Jugadores;

	private String respuestaRecibida;
	private DefinicionRosco definicionActual;

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
	}
	
	public void jugar() {
		if (modo2Jugadores) {
			if (!listaJugadores[0].haTerminado() || !listaJugadores[1].haTerminado()) {
				Jugador jugador = getSiguienteJugador();
				setDefinicionActual(jugador.realizarPregunta());
				setChanged();
				notifyObservers();
			}
			else {
				setChanged();
				notifyObservers("fin");
				for (Jugador jug : listaJugadores)
					ranking.insertarPuntuacionEnRanking(jug);
				ranking.guardarPuntuaciones();
			}
		} else {
			if (!listaJugadores[0].haTerminado()) {
				Jugador jugador = getSiguienteJugador();
				setDefinicionActual(jugador.realizarPregunta());
				setChanged();
				notifyObservers();
			}
			else {
				setChanged();
				notifyObservers("fin");
				for (Jugador jug : listaJugadores)
					ranking.insertarPuntuacionEnRanking(jug);
				ranking.guardarPuntuaciones();
			}
		}
		
	}
	
	public void gestionarRespuesta(String respuesta){
		if (modo2Jugadores) {
			if (siguienteJugador == -1 || siguienteJugador == 1) {
				listaJugadores[0].gestionarRespuesta(respuesta);
			} else if (siguienteJugador == -2 || siguienteJugador == 0) {
				listaJugadores[1].gestionarRespuesta(respuesta);
			}
		}
		else
			listaJugadores[0].gestionarRespuesta(respuesta);
		jugar();
	}

	/**
	 * Este metodo devuelve el jugador que itene que jugar a continuacion
	 * teniendo en cuenta las condiciones necesarias, que son: 
	 * 		Si alguno de los jugadores ha terminado 
	 * 		Si el jugador puede seguir jugando ya que ha acertado la pregunta anterior 
	 * 		Si todavia no ha empezado a jugar alguno 
	 * 		Si solo juega uno porque esta en modo Un Jugador
	 * 
	 * @return Jugador que tiene que jugar
	 */
	public Jugador getSiguienteJugador() {
		if (modo2Jugadores) 
			if (siguienteJugador == 1)
				if (listaJugadores[siguienteJugador].haTerminado())
					return listaJugadores[0];
				else if (listaJugadores[siguienteJugador - 1].haAcertadoLaAnterior())
					return listaJugadores[siguienteJugador - 1];
				else 
					return listaJugadores[siguienteJugador--];
			else if (siguienteJugador == 0)
				if (listaJugadores[siguienteJugador].haTerminado())
					return listaJugadores[1];
				else if (listaJugadores[siguienteJugador + 1].haAcertadoLaAnterior())
					return listaJugadores[siguienteJugador + 1];
				else
					return listaJugadores[siguienteJugador++];
			else if (siguienteJugador == -1) {
				siguienteJugador = 0;
				return listaJugadores[1];
			} 
			else {
				siguienteJugador = -1;
				return listaJugadores[0];
			}
		else
			return listaJugadores[0];
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
	
	public void setDefinicionActual(DefinicionRosco pDefinicionActual){
		definicionActual = pDefinicionActual;
		setChanged();
		notifyObservers();
	}
	
	//Parte del singleton
	private static Pasapalabra mPasapalabra;
	private Pasapalabra(){}
	public static Pasapalabra getPasapalabra(){
		if(mPasapalabra == null)
			mPasapalabra = new Pasapalabra();
		return mPasapalabra;
	}
	
}