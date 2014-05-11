package packVista;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameOver extends JDialog {
	private JLabel lblGameOver;
	private JPanel panel;
	private JButton btnMenuPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameOver dialog = new GameOver();
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
	public GameOver() {

		initialize();
	}
	private void initialize() {
		setBounds(100, 100, 450, 250);
		getContentPane().setLayout(new GridLayout(2, 1, 50, 0));
		getContentPane().add(getLblGameOver());
		getContentPane().add(getPanel());
	}
	public JLabel getLblGameOver() {
		if (lblGameOver == null) {
			lblGameOver = new JLabel("Game Over");
			lblGameOver.setFont(new Font("Tahoma", Font.BOLD, 48));
			lblGameOver.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblGameOver;
	}
	public JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setVgap(40);
			panel.add(getBtnMenuPrincipal());
		}
		return panel;
	}
	public JButton getBtnMenuPrincipal() {
		if (btnMenuPrincipal == null) {
			btnMenuPrincipal = new JButton("Menu Principal");
			btnMenuPrincipal.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					MenuPrincipal.main(null);
				}
			});
			btnMenuPrincipal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MenuPrincipal.main(null);
				}
			});
			btnMenuPrincipal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return btnMenuPrincipal;
	}
}
