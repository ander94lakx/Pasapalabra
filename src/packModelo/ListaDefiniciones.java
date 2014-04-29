package packModelo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ListaDefiniciones {

    private List<Definicion> lDefiniciones;

    public ListaDefiniciones() {
        lDefiniciones = new LinkedList<Definicion>();
    }

    public void addDefinicion(Definicion pDefinicion) {
        lDefiniciones.add(pDefinicion);
    }

    public Iterator<Definicion> getIterador() {
        return lDefiniciones.iterator();
    }
    
    public Definicion obtenerDefinicionAlAzar(){
        Random random = new Random();
        return lDefiniciones.get(random.nextInt(lDefiniciones.size()));
    }
}
