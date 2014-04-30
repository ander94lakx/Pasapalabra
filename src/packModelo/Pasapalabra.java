package packModelo;

import java.util.Observable;

public class Pasapalabra extends Observable{

	// Singletons donde se almacenan informacion persistente
	static Ranking ranking = Ranking.getRanking();
	static CatalogoDefiniciones catalogo = CatalogoDefiniciones
			.getCatalogoDefiniciones();

	// Gestion de jugadores
	public static Jugador listaJugadores[] = new Jugador[2];
	static int siguienteJugador = -2; 
			// Indica al jugador al que le tocaria jugar el siguiente turno
			// Siempre es el que no esta jugando en ese momento
			// Modos posibles: 0 -> el Siguiente jugador teorico es el Jug 1
			// Modos posibles: 1 -> el Siguiente jugador teorico es el Jug 2
			// Modos posibles: -1 -> el Siguiente jugador teorico es el Jug 2 (Todavia no ha jugado el Jug 2)
			// Modos posibles: -2 -> el Siguiente jugador teorico es el Jug 1 (Todavia no ha jugado ninguno)

	static boolean modo2Jugadores;
	// FIXME Arreglar la gestion de la respuesta
	private static String respuestaRecibida;

	public static String getRespuestaRecibida() {
		return respuestaRecibida;
	}

	public static void setRespuestaRecibida(String respuestaRecibida) {
		Pasapalabra.respuestaRecibida = respuestaRecibida;
	}
	
	private static DefinicionRosco definicionActual;
	
	public static DefinicionRosco getDefinicionActual() {
		return definicionActual;
	}
	public void setDefinicionActual(DefinicionRosco pDefinicionActual) {
		definicionActual = pDefinicionActual;
		setChanged();
		notifyObservers();
	}

	/**
	 * El metodo main gestiona la carga del XML y la carga inicial de las
	 * puntuaciones
	 * 
	 * @param args
	 *            Recibe los nombres de los Jugadores o del jugador
	 */
	public static void main(String[] args) {
		CatalogoDefiniciones catalogo = CatalogoDefiniciones
				.getCatalogoDefiniciones();
		catalogo.loadData();

		ranking.cargarPuntuaciones();

		if (args.length == 1) {
			modo2Jugadores = false;
			inicializar(args[0], null);
		} else if (args.length == 2) {
			modo2Jugadores = true;
			inicializar(args[0], args[1]);
		}

	}

	/**
	 * Este metodo se encarga de inicializar la parte del juego que no utiliza
	 * archivos, diferenciando entre los modos 1J y 2J
	 * 
	 * @param jug1
	 *            Nombre del primer jugador obtenido del main
	 * @param jug2
	 *            Nombre del segundo jugador obtenido del main
	 */
	public static void inicializar(String jug1, String jug2) {
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
		inicializarPartida();
	}

	/**
	 * Este metodo se encarga de gestionar el bucle principal del juego,
	 * cambiando los jugadores y gestionando tambien el final del juego
	 * 
	 * @param jug1
	 *            Nombre del primer jugador obtenido del main
	 * @param jug2
	 *            Nombre del segundo jugador obtenido del main
	 */
	public static void inicializarPartida() {
		/*
		 * FIXME Corregir el bucle principal para que funcione junto a la GUI y
		 * gestione la respuesta de manera correcta
		 */
		if (modo2Jugadores) {
			while (!listaJugadores[0].haTerminado()
					|| !listaJugadores[1].haTerminado()) {
				Jugador jugador = getSiguienteJugador();
				// Realizar pregunta
				definicionActual = jugador.realizarPregunta();
				//Esperar hasta que la GUI notifique que puede seguir
				try {
					Thread.currentThread().wait();
				} catch (InterruptedException e) { e.printStackTrace(); }
				// gestionar respuesta
				jugador.gestionarRespuesta(respuestaRecibida);
			}
			for (Jugador jug : listaJugadores)
				ranking.insertarPuntuacionEnRanking(jug);
			siguienteJugador = -1;
		} else {
			while (!listaJugadores[0].haTerminado()) {
				Jugador jugador = getSiguienteJugador();
				// Realizar pregunta
				definicionActual = jugador.realizarPregunta();
				//Esperar hasta que la GUI notifique que puede seguir
				try {
					Thread.currentThread().wait();
				} catch (InterruptedException e) { e.printStackTrace(); }
				// gestionar respuesta
				jugador.gestionarRespuesta(respuestaRecibida);
			}
			for (Jugador jug : listaJugadores)
				ranking.insertarPuntuacionEnRanking(jug);
			siguienteJugador = -1;
		}
		ranking.guardarPuntuaciones();
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
	public static Jugador getSiguienteJugador() {
		// Extendido y con comentarios para que se entienda mejor
		if (modo2Jugadores) 
		{
			if (siguienteJugador == 1) // Si el siquente en teoria es el Jug 2
			{
				if (listaJugadores[siguienteJugador].haTerminado()) // Si el que esta por jugar (Jug 2) ya ha terminado
				{
					return listaJugadores[0];
				}
				else if(listaJugadores[siguienteJugador - 1].haAcertadoLaAnterior()) 
				{
					return listaJugadores[siguienteJugador - 1]; // Si el que esta jugando ha acertado la anterior sigue jugando
				}
				else 
				{
					return listaJugadores[siguienteJugador--]; // Si no juega el siguiente jugador y se pone al otro jugador como siguiente
				}
			} 
			else if (siguienteJugador == 0) // Si el siguiente en teoria es el Jug 1
			{
				if (listaJugadores[siguienteJugador].haTerminado()) // Si el que esta por jugar (Jug 1) ya ha terminado
				{
					return listaJugadores[1];
				}
				else if(listaJugadores[siguienteJugador + 1].haAcertadoLaAnterior()) 
				{
					return listaJugadores[siguienteJugador + 1]; // Si el que esta jugando ha acertado la anterior sigue jugando
				}
				else 
				{
					return listaJugadores[siguienteJugador++]; // Si no juega el siguiente jugador y se pone al otro jugador como siguiente
				}
			} 
			else if(siguienteJugador == -1) // Si es -1 es que el Jug 2 todavia no ha comenzado a jugar
			{
				siguienteJugador = 1; // Se pone al jug 2 como siguiente
				return listaJugadores[0]; // Y se pone a Jug 2 como actual
			}
			else // Si no es ni 0 ni 1 ni -1 es que no se ha epezado a jugar (Ninguno de los 2)
			{
				siguienteJugador = -1; // Se pone a -1 para que inicie el Jug 2 posteriormete sin comprobar si ha acertado la anterior
				return listaJugadores[0]; // Y se pone a Jug 1 como actual
			}
		} 
		else 
		{
			return listaJugadores[0]; // Si solo juega uno el siguiente siempre va a ser el
		}
	}
	
	public static boolean modoDosJugadores(){
		return modo2Jugadores;
	}
}
