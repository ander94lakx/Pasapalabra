package packVista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class OtraPartida extends JDialog {

	private static final long serialVersionUID = -7619654827690558668L;
	private JPanel panel;
	private JLabel lblotraPartida;
	private JPanel panel_1;
	private JButton btnSi;
	private JButton btnNo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OtraPartida dialog = new OtraPartida();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public OtraPartida() {

		initialize();
	}
	private void initialize() {
		setBounds(100, 100, 400, 300);
		getContentPane().add(getPanel(), BorderLayout.NORTH);
		getContentPane().add(getPanel_1(), BorderLayout.CENTER);
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
	}
	public JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new EmptyBorder(30, 0, 30, 0));
			panel.add(getLblotraPartida());
		}
		return panel;
	}
	public JLabel getLblotraPartida() {
		if (lblotraPartida == null) {
			lblotraPartida = new JLabel("\u00BFOtra partida?");
			lblotraPartida.setFont(new Font("Tahoma", Font.PLAIN, 24));
		}
		return lblotraPartida;
	}
	public JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBorder(new EmptyBorder(50, 50, 50, 50));
			panel_1.setLayout(new GridLayout(0, 2, 100, 200));
			panel_1.add(getBtnSi());
			panel_1.add(getBtnNo());
		}
		return panel_1;
	}
	public JButton getBtnSi() {
		if (btnSi == null) {
			btnSi = new JButton("SI");
			btnSi.setFont(new Font("Dialog", Font.PLAIN, 24));
		}
		return btnSi;
	}
	public JButton getBtnNo() {
		if (btnNo == null) {
			btnNo = new JButton("NO");
			btnNo.setFont(new Font("Dialog", Font.PLAIN, 24));
		}
		return btnNo;
	}
}
