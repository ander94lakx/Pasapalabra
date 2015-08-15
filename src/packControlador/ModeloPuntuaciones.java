package packControlador;

import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import packModelo.*;

public class ModeloPuntuaciones extends AbstractTableModel {

	private static final long serialVersionUID = -1650068466977391948L;

	private String[] nombresColumnas = null;
	private Object[][] datos = null;
	private int numPuntuaciones;

	public ModeloPuntuaciones() {
		nombresColumnas = new String[] { "Nombre", "Aciertos", "Fallos", "Tiempo restante" };
		if (!Ranking.getRanking().getIterador().hasNext())
			Ranking.getRanking().cargarPuntuaciones();
		LinkedList<Jugador> punt = new LinkedList<Jugador>();
		Iterator<Jugador> it = Ranking.getRanking().getIterador();
		numPuntuaciones = 0;
		while (it.hasNext()) {
			Jugador sig = it.next();
			Jugador temp = new Jugador(sig.getNombre());
			temp.setAciertos(sig.getAciertos());
			temp.setFallos(sig.getFallos());
			temp.setTiempoRestante(sig.getTiempoRestante());
			punt.add(temp);
			numPuntuaciones++;
		}
		datos = new Object[numPuntuaciones][nombresColumnas.length];
		for (int i = 0; i < numPuntuaciones; i++) {
			datos[i][0] = punt.get(i).getNombre();
			datos[i][1] = punt.get(i).getAciertos();
			datos[i][2] = punt.get(i).getFallos();
			datos[i][3] = punt.get(i).getTiempoRestante();
		}
	}

	@Override
	public int getRowCount() {
		return datos.length;
	}

	@Override
	public int getColumnCount() {
		return nombresColumnas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return datos[rowIndex][columnIndex];
	}

	@Override
	public String getColumnName(int column) {
		return nombresColumnas[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return String.class;
		case 1:
		case 2:
		case 3:
			return Integer.class;
		default:
			return String.class;
		}
	}

}
