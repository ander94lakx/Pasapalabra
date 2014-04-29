package packModelo;

import java.util.List;

public class DefinicionRosco extends Definicion {

    private Letra letra;
    private Estado estadoRespuesta;

    public DefinicionRosco(String pEnunciado, Letra pLetra) {
        super(pEnunciado);
        letra = pLetra;
        estadoRespuesta = Estado.SIN_CONTESTAR;
    }

    public Letra getLetra() {
        return letra;
    }
    
    public void setEstadoRespuesta(Estado pEstadoRespuesta){
        estadoRespuesta = pEstadoRespuesta;
    }

    public Estado getEstadoRespuesta() {
        return estadoRespuesta;
    }
    
    public boolean comprobarRespuesta(String pRespuesta){
        // TODO Hecho? Comprobar si la respuesta el la correcta
    	boolean estaLaRespuesta = false;
    	List<String> listaResp = getListaRespuestas();
    	for(String resp: listaResp){
    		if(resp.equals(pRespuesta)){
    			estaLaRespuesta = true;
    			break;
    		}
    	}
    	if(estaLaRespuesta){
    		estadoRespuesta = Estado.CORRECTA;
    		
    	}
    	else{
    		if(pRespuesta.equals("")){
    			estadoRespuesta = Estado.SIN_CONTESTAR;
    		}
    		else{
    			estadoRespuesta = Estado.FALLIDA;
    		}
    	}
    	return estaLaRespuesta;
    } // FIXME Modificar este metodo para que funcione!!
}
