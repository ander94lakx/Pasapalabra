package packModelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Definicion {

    private String enunciado;
    private List<String> listaRespuestas;

    public Definicion(String pPreg) {
        enunciado = pPreg;
        listaRespuestas = new ArrayList<String>();
    }

    @Override
    public String toString() {
        String texto = String.format("Enunciado: %1$s\n", enunciado);
        for (String resp : listaRespuestas) {
            texto += String.format("\t\tRespuesta: %1$s\n", resp);
        }
        return texto;
    }

    public void addRespuesta(String pRes) {
        listaRespuestas.add(pRes);
    }

    public String getEnunciado() {
        return this.enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public List<String> getListaRespuestas() {
        return this.listaRespuestas;
    }
    
    public Iterator<String> getIteradorRespuestas(){
    	return listaRespuestas.iterator();
    }
}
