package packModelo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Rosco {

	private List<DefinicionRosco> listaDefiniciones;

	public Rosco() {
		listaDefiniciones = new LinkedList<DefinicionRosco>();
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
			while (it.hasNext()) {
				// Cadena de caracteres original a sustituir.
			    String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
			    // Cadena de caracteres ASCII que reemplazarán los originales.
			    String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
				String respOriginal = it.next();
				for (int i=0; i<original.length(); i++) {
			        // Reemplazamos los caracteres especiales.
			        respOriginal = respOriginal.replace(original.charAt(i), ascii.charAt(i));
			    }
				defRosco.addRespuesta(respOriginal);
			}
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
		return listaDefiniciones.get(letra.ordinal());
	}

	public boolean estaCompletado() {
		for (DefinicionRosco def : listaDefiniciones)
			if (def.getEstadoRespuesta() == Estado.SIN_CONTESTAR)
				return false;
		return true;
	}
}
