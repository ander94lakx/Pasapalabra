package packModelo;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class Jugador extends Observable {

	private int TIEMPO_INICIAL = 120;
	private int aciertos;
	private int fallos;
	private int tiempoRestante;
	private int turnosPasados;
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
		turnosPasados = 0;
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
		setChanged();
		notifyObservers();
	}

	public String getNombre() {
		return this.nombre;
	}

	public DefinicionRosco realizarPregunta() {
		// TODO Hecho? realizar la siguiente pregunta
		DefinicionRosco def;
		do {
			def = rosco.obtenerDefinicionRosco(posicionRosco);
			pasarSiguienteLetra();
			if (def.getEstadoRespuesta() != Estado.SIN_CONTESTAR)
				break;
			else
				pasarSiguienteLetra();
		} while (def == null || def.getEstadoRespuesta() == Estado.SIN_CONTESTAR);
		turnosPasados++;
		reanudarReloj();
		return def;
	}

	public void gestionarRespuesta(String respuesta) {
		// TODO Hecho? gestionar la respuesta recibida
		pausarReloj();
		getRosco().obtenerDefinicionRosco(posicionRosco).comprobarRespuesta(
				respuesta);
		if (getRosco().obtenerDefinicionRosco(posicionRosco)
				.getEstadoRespuesta() == Estado.CORRECTA)
			incrementarAciertos();
		if (getRosco().obtenerDefinicionRosco(posicionRosco)
				.getEstadoRespuesta() == Estado.FALLIDA)
			incrementarFallos();
	}

	public boolean haTerminado() {
		return rosco.estaCompletado() || tiempoRestante == 0;
	}

	public Rosco getRosco() {
		return this.rosco;
	}

	// TODO Comprobar la nueva implementacion del metodo
	private void pasarSiguienteLetra() {
		switch (posicionRosco) {
		case Z:
			posicionRosco = Letra.A;
			break;
		default:
			Letra[] letras = Letra.values();
			posicionRosco = letras[posicionRosco.ordinal() + 1];
			break;
		}
		// switch (posicionRosco){
		// case A:
		// posicionRosco = Letra.B;
		// break;
		// case B:
		// posicionRosco = Letra.C;
		// break;
		// case C:
		// posicionRosco = Letra.D;
		// break;
		// case D:
		// posicionRosco = Letra.E;
		// break;
		// case E:
		// posicionRosco = Letra.F;
		// break;
		// case F:
		// posicionRosco = Letra.G;
		// break;
		// case G:
		// posicionRosco = Letra.H;
		// break;
		// case H:
		// posicionRosco = Letra.I;
		// break;
		// case I:
		// posicionRosco = Letra.J;
		// break;
		// case J:
		// posicionRosco = Letra.K;
		// break;
		// case K:
		// posicionRosco = Letra.L;
		// break;
		// case L:
		// posicionRosco = Letra.M;
		// break;
		// case M:
		// posicionRosco = Letra.N;
		// break;
		// case N:
		// posicionRosco = Letra.O;
		// break;
		// case O:
		// posicionRosco = Letra.P;
		// break;
		// case P:
		// posicionRosco = Letra.Q;
		// break;
		// case Q:
		// posicionRosco = Letra.R;
		// break;
		// case R:
		// posicionRosco = Letra.S;
		// break;
		// case S:
		// posicionRosco = Letra.T;
		// break;
		// case T:
		// posicionRosco = Letra.U;
		// break;
		// case U:
		// posicionRosco = Letra.V;
		// break;
		// case V:
		// posicionRosco = Letra.W;
		// break;
		// case W:
		// posicionRosco = Letra.X;
		// break;
		// case X:
		// posicionRosco = Letra.Y;
		// break;
		// case Y:
		// posicionRosco = Letra.Z;
		// break;
		// case Z:
		// posicionRosco = Letra.A;
		// break;
		// default:
		// throw new AssertionError(posicionRosco.name());
		// }
		setChanged();
		notifyObservers();
	}

	public Letra getPosicionRosco() {
		return posicionRosco;
	}

	// TODO Comprobar implementacion del tiempo
	public void reanudarReloj() {
		timerTask = new TimerTask() {
			@Override
			public void run() {
				decrementarTiempo();
			}
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, 1000);
		timerTask.run();
	}

	public void decrementarTiempo() {
		tiempoRestante--;
		setChanged();
		notifyObservers();
	}

	public void pausarReloj() {
		timerTask.cancel();
		timerTask = null;
	}

	public boolean haAcertadoLaAnterior() {
		Letra[] letras = Letra.values();
		Letra letraAnterior;
		if (posicionRosco == Letra.A)
			letraAnterior = Letra.Z;
		else
			letraAnterior = letras[posicionRosco.ordinal() - 1];
		if(turnosPasados <= 1 && letraAnterior == Letra.Z)
			return true;
		else	
		 return getRosco().obtenerDefinicionRosco(letraAnterior).getEstadoRespuesta() == Estado.CORRECTA;
	}
	
//	public boolean haEmpezado(){
//		return aciertos == 0 && fallos == 0;
//	}
}
