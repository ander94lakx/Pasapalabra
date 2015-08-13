package packVista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import packControlador.ModeloPuntuaciones;

public class Puntuaciones extends JDialog {

	private static final long serialVersionUID = -7792776533747016398L;
	private JPanel contentPane;
	private JScrollPane pnlCentral;
	private JTable tblDatos;
	private TableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Puntuaciones frame = new Puntuaciones();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Puntuaciones() {
		initialize();
	}

	private void initialize() {
		setTitle("Puntuaciones");
		setBounds(100, 100, 450, 232);
		// TOCHOCODIGO para centrar la ventana
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension windowSize = this.getSize();
		if (windowSize.height>screenSize.height) {
			windowSize.height= screenSize.height;
		}
		if (windowSize.width>screenSize.width){
			windowSize.width= screenSize.width;
		}
		setLocation((screenSize.width-windowSize.width)/2,
			(screenSize.height-windowSize.height)/2);
		// FIN TOCHOCODIGO
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnlCentral(), BorderLayout.CENTER);
		
		setIconImage(new ImageIcon(MenuPrincipal.class.getResource("/packRecursos/icon.jpg")).getImage());
	}

	private JScrollPane getPnlCentral() {
		if (pnlCentral == null) {
			pnlCentral = new JScrollPane();
			pnlCentral.setViewportView(getTblDatos());
		}
		return pnlCentral;
	}

	private JTable getTblDatos() {
		if (tblDatos == null) {
			tblDatos = new JTable(getModeloPuntuaciones());
			tblDatos.setFillsViewportHeight(true);
		}
		return tblDatos;
	}

	private TableModel getModeloPuntuaciones() {
		if (model == null) {
			model = new ModeloPuntuaciones();
		}
		return model;
	}
}
