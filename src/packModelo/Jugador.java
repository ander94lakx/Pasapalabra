package packModelo;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class Jugador extends Observable {

	public static int TIEMPO_INICIAL = 200;
	private int aciertos;
	private int fallos;
	private int tiempoRestante;
	private String nombre;
	private Rosco rosco;
	private Letra posicionRosco;
	private Timer timer = null;
	private TimerTask timerTask = null;

	public Jugador(String pNombre) {
		nombre = pNombre;
		aciertos = 0;
		fallos = 0;
		tiempoRestante = TIEMPO_INICIAL;
		rosco = new Rosco();
		posicionRosco = Letra.A;
	}

	public int getAciertos() {
		return this.aciertos;
	}

	public void setAciertos(int pAciertos) {
		aciertos = pAciertos;
		setChanged();
		notifyObservers();
	}

	public void incrementarAciertos() {
		aciertos++;
		setChanged();
		notifyObservers();
	}

	public int getFallos() {
		return this.fallos;
	}

	public void setFallos(int pFallos) {
		fallos = pFallos;
		setChanged();
		notifyObservers();
	}

	public void incrementarFallos() {
		fallos++;
		setChanged();
		notifyObservers();
	}

	public int getTiempoRestante() {
		return this.tiempoRestante;
	}

	public void setTiempoRestante(int tiempoRestante) {
		this.tiempoRestante = tiempoRestante;
	}

	public String getNombre() {
		return this.nombre;
	}

	public DefinicionRosco realizarPregunta() {
		DefinicionRosco def;
		do {
			def = rosco.obtenerDefinicionRosco(posicionRosco);
			if (def.getEstadoRespuesta() == Estado.SIN_CONTESTAR)
				break;
			else
				pasarSiguienteLetra();
		} while (def == null || def.getEstadoRespuesta() != Estado.SIN_CONTESTAR);
		reanudarReloj();
		return def;
	}

	public void gestionarRespuesta(String respuesta) {
		pausarReloj();
		getRosco().obtenerDefinicionRosco(posicionRosco).comprobarRespuesta(
				respuesta);
		if (getRosco().obtenerDefinicionRosco(posicionRosco)
				.getEstadoRespuesta() == Estado.CORRECTA)
			incrementarAciertos();
		if (getRosco().obtenerDefinicionRosco(posicionRosco)
				.getEstadoRespuesta() == Estado.FALLIDA)
			incrementarFallos();
		pasarSiguienteLetra();
	}

	public boolean haTerminado() {
		return rosco.estaCompletado() || tiempoRestante == 0;
	}

	public Rosco getRosco() {
		return this.rosco;
	}

	private void pasarSiguienteLetra() {
		if(posicionRosco == Letra.Z)
			posicionRosco = Letra.A;
		else
			posicionRosco = Letra.values()[posicionRosco.ordinal() + 1];
		setChanged();
		notifyObservers("rosco");
	}

	public Letra getPosicionRosco() {
		return posicionRosco;
	}

	public void reanudarReloj() {
		timerTask = new TimerTask() {
			@Override
			public void run() {
				if(tiempoRestante > 0)
					tiempoRestante--;
//				else {
//					tiempoRestante = 0;
//					Pasapalabra.getPasapalabra().jugar();
//				}
				setChanged();
				notifyObservers();
			}
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, 1000);
		timerTask.run();
	}

	public void pausarReloj() {
		if (timerTask != null) {
			timerTask.cancel();
		}
		timerTask = null;
	}

	public boolean haAcertadoLaAnterior() {
		Letra[] letras = Letra.values();
		Letra letraAnterior;
		if (posicionRosco == Letra.A)
			letraAnterior = Letra.Z;
		else
			letraAnterior = letras[posicionRosco.ordinal() - 1];
		return getRosco().obtenerDefinicionRosco(letraAnterior).getEstadoRespuesta() == Estado.CORRECTA;
	}
}
