import java.awt.EventQueue;

import javax.swing.JFrame;
import java.io.File;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Test {

	private JFrame frmUpravljanjeRevizijama;
	private JTextField textField;
	private JButton btnPromijeni;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
					window.frmUpravljanjeRevizijama.setVisible(true);
					FileRepositoryBuilder builder = new FileRepositoryBuilder();
					Repository repository = builder.setGitDir(new File("/my/git/directory"))
							  .readEnvironment() // scan environment GIT_* variables
							  .findGitDir() // scan up the file system tree
							  .build();
					repository.create();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUpravljanjeRevizijama = new JFrame();
		frmUpravljanjeRevizijama.setTitle("Upravljanje revizijama");
		frmUpravljanjeRevizijama.setBounds(100, 100, 546, 460);
		frmUpravljanjeRevizijama.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUpravljanjeRevizijama.getContentPane().setLayout(new MigLayout("", "[][][grow,right]", "[][]"));
		
		JLabel lblRadniDirektorij = new JLabel("Radni direktorij:");
		frmUpravljanjeRevizijama.getContentPane().add(lblRadniDirektorij, "cell 0 0,alignx trailing");
		
		textField = new JTextField();
		frmUpravljanjeRevizijama.getContentPane().add(textField, "cell 2 0,growx");
		textField.setColumns(10);
		
		btnPromijeni = new JButton("Promijeni");
		frmUpravljanjeRevizijama.getContentPane().add(btnPromijeni, "cell 0 1");
	}
}
