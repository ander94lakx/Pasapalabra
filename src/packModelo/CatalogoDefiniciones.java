package packModelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatalogoDefiniciones {

	private static CatalogoDefiniciones mCatalogoDefiniciones = new CatalogoDefiniciones();
	private Map<Character, List<Definicion>> listaDefiniciones;

	private CatalogoDefiniciones() {
		listaDefiniciones = new HashMap<Character, List<Definicion>>();
	}

	public static CatalogoDefiniciones getCatalogoDefiniciones() {
		return mCatalogoDefiniciones;
	}

	public void loadData() {
		try {
			XMLParser.getPDF2XMLParser().parseXmlFile("DB.xml");
		} catch (XmlParsingException e) {
			e.printStackTrace();
		}
	}

	public void addDefinicion(Character pLetra, Definicion pDefinicion) {
		if (!listaDefiniciones.containsKey(pLetra)) {
			listaDefiniciones.put(pLetra, new ArrayList<Definicion>());
		}
		listaDefiniciones.get(pLetra).add(pDefinicion);
	}

	@Override
	public String toString() {
		return listaDefiniciones.toString();
	}

	public ListaDefiniciones obtenerDefiniciones(Letra pLetra) {
		Character letra = pLetra.name().charAt(0);
		ListaDefiniciones listaDef = new ListaDefiniciones();
		List<Definicion> listaDefTemp = listaDefiniciones.get(letra);
		for (Definicion def : listaDefTemp)
			listaDef.addDefinicion(def);
		return listaDef;
	}
}
