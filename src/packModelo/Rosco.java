package packModelo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Rosco {

	private List<DefinicionRosco> listaDefiniciones;

	public Rosco() {
		// TODO Hecho? implementar Estr. de Datos correcta
		listaDefiniciones = new LinkedList<DefinicionRosco>();
		// utilizar una estructura de datos circular es mejor
	}

	public void inicializarRosco() {
		CatalogoDefiniciones diccionario = CatalogoDefiniciones
				.getCatalogoDefiniciones();
		Letra letras[] = Letra.values();
		for (Letra letra : letras) {
			ListaDefiniciones defsDeLetra = diccionario
					.obtenerDefiniciones(letra);
			Definicion def = defsDeLetra.obtenerDefinicionAlAzar();
			DefinicionRosco defRosco = new DefinicionRosco(def.getEnunciado(),
					letra);
			Iterator<String> it = def.getIteradorRespuestas();
			while (it.hasNext())
				defRosco.addRespuesta(it.next());
			listaDefiniciones.add(defRosco);
		}
	}

	public Iterator<DefinicionRosco> getIterador() {
		return listaDefiniciones.iterator();
	}

	public void addDefinicionRosco(DefinicionRosco pDefinicionRosco) {
		listaDefiniciones.add(pDefinicionRosco);
	}

	public DefinicionRosco obtenerDefinicionRosco(Letra letra) {
		/*
		 * TODO Hecho? obtener la siguiente definicion que tiene que mostrar la
		 * GUI
		 */
		return listaDefiniciones.get(letra.ordinal());
	}

	public boolean estaCompletado() {
		// TODO Hecho? comprobar si el rosco esta completado
		boolean faltaResponder = false;
		for (DefinicionRosco def : listaDefiniciones) {
			if (def.getEstadoRespuesta() == Estado.SIN_CONTESTAR) {
				faltaResponder = true;
				break;
			}
		}
		return !faltaResponder;
	}
}
