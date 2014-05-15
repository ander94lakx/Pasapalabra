package packModelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Ranking {

    private static Ranking mRanking = new Ranking();
    private LinkedList<Jugador> lPuntuaciones = new LinkedList<Jugador>();
    private final int NUM_PUNTUACIONES = 10;
    private Ranking() {
    }

    public static Ranking getRanking() {
        return mRanking;
    }

    public Iterator<Jugador> getIterador() {
        return lPuntuaciones.iterator();
    }

    public void cargarPuntuaciones() {
    	try {	
    		File archivo = new File("puntuaciones.txt");
    		if(!archivo.exists())
    			archivo.createNewFile();
			FileReader entrada = new FileReader(archivo);
			Scanner sc = new Scanner(entrada);
			int i = 0;
			do{
	            String nombre = sc.next();
	            int aciertos = sc.nextInt();
	            int fallos = sc.nextInt();
	            int tiempoRestante = sc.nextInt();
	            Jugador jugadorTemp = new Jugador(nombre);
	            jugadorTemp.setAciertos(aciertos);
	            jugadorTemp.setFallos(fallos);
	            jugadorTemp.setTiempoRestante(tiempoRestante);
	            lPuntuaciones.add(jugadorTemp);
	            i++;
	            if(i >= NUM_PUNTUACIONES)
	            	break;
			} while(sc.hasNext());
			sc.close();
			entrada.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }

    public void guardarPuntuaciones() {
		try {
			File archivo = new File("puntuaciones.txt");
			FileWriter fw = new FileWriter(archivo);
			PrintWriter pw = new PrintWriter(fw);
			// Va de 1 hasta 10 para almacenar solo las 10 mejores
	    	for(int i = 0; i < NUM_PUNTUACIONES; i++){ 
	    		Jugador jug = lPuntuaciones.get(i);
	    		String nombre = jug.getNombre();
	    		int aciertos = jug.getAciertos();
	    		int fallos = jug.getFallos();
	    		int tiempoRestante = jug.getTiempoRestante();
	    		pw.println(nombre +" "+ aciertos +" "+ fallos +" "+ tiempoRestante);
	    	}
	    	pw.close();
	    	fw.close();
	    	
		} catch (IOException e) {
			e.printStackTrace();
    	}
    }

    public void insertarPuntuacionEnRanking(Jugador pJugador) {
    	for(int i = 0; i < NUM_PUNTUACIONES; i++){
    		Jugador jug = lPuntuaciones.get(i);
    		if(pJugador.getAciertos() > jug.getAciertos()) {
    			lPuntuaciones.add(i, pJugador);
    			break;
    		}
    		else if(pJugador.getAciertos() == jug.getAciertos()) {
    			if(pJugador.getFallos() < jug.getFallos()) {
    				lPuntuaciones.add(i, pJugador);
    				break;
    			}
    			else if(pJugador.getFallos() == jug.getFallos()) {
    				if(pJugador.getTiempoRestante() >= jug.getTiempoRestante()) {
    					lPuntuaciones.add(i, pJugador);
    					break;
    				}
    			}
    		}
    	}
    } 
}
