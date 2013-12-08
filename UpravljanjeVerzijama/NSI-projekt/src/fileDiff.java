import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import difflib.*;

import javax.swing.JFrame;


public class fileDiff {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			private List<String> fileToLines(String filename) {
                List<String> lines = new LinkedList<String>();
                String line = "";
                try {
                        BufferedReader in = new BufferedReader(new FileReader(filename));
                        while ((line = in.readLine()) != null) {
                                lines.add(line);
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return lines;
			}	
			public void run() {
				try {
					System.out.println("blaaa");
					fileDiff window = new fileDiff();
					window.frame.setVisible(true);
					// Helper method for get the file content
			        

	                List<String> original = fileToLines("file1.txt");
	                List<String> revised  = fileToLines("file2.txt");
	                
	                // Compute diff. Get the Patch object. Patch is the container for computed deltas.
	                Patch patch = DiffUtils.diff(original, revised);

	                for (Delta delta: patch.getDeltas()) {
	                        System.out.println(delta);
	                }
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public fileDiff() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
