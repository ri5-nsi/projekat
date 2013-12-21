import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import difflib.*;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;


public class fileDiff {

	private static JFrame frame;

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
			@SuppressWarnings("deprecation")
			public void run() {
				try {
					fileDiff window = new fileDiff();
					window.frame.setVisible(true);
					// Helper method for get the file content
					
					
					JEditorPane jep = new JEditorPane();
					jep.setEditable(false);
					
					HTMLEditorKit kit = new HTMLEditorKit();
					jep.setEditorKit(kit);
					
					jep.setContentType("text/html");
					
					StyleSheet styleSheet = kit.getStyleSheet();
					styleSheet.addRule("body {color:#000; font-family:times; margin: 4px; }");
					styleSheet.addRule("h1 {color: blue;}");
					styleSheet.addRule("h2 {color: #ff0000;}");
					styleSheet.addRule("pre {font : 10px monaco; color : black; background-color : #fafafa; }");
					styleSheet.addRule(".insert { background-color: #6FFFA2}");
					styleSheet.addRule(".del { background-color: #f26a6a}");
					styleSheet.addRule(".mod { padding: 3px; background-color: #56a4ad;");
			        

	                List<String> original = fileToLines("file1.txt");
	                List<String> revised  = fileToLines("file2.txt");
	                
	                // Compute diff. Get the Patch object. Patch is the container for computed deltas.
	                Patch patch = DiffUtils.diff(original, revised);

	                for (Delta delta: patch.getDeltas()) {
	                        System.out.println(delta);
	                }
	                DiffRowGenerator.Builder builder = new DiffRowGenerator.Builder();   
	                boolean sideBySide = true;  //default -> inline
	                builder.showInlineDiffs(!sideBySide); 
	                builder.columnWidth(120);
	                DiffRowGenerator dfg = builder.build();      
	                final StringBuilder sb = new StringBuilder();
	                List<DiffRow> rows = dfg.generateDiffRows( original, revised);
	                for (final DiffRow diffRow : rows)
	                {
	                	if (diffRow.getTag().equals(DiffRow.Tag.INSERT)) // or use switch* 
	                    {
	                        sb.append("<div class='insert'>" + diffRow.getNewLine() + "</div>");
	                    }
	                    else if (diffRow.getTag().equals(DiffRow.Tag.DELETE))
	                    {
	                        sb.append("<div class='del'>" + diffRow.getOldLine() + "</div>");
	                    }
	                    else if (diffRow.getTag().equals(DiffRow.Tag.CHANGE))
	                    {
	                        sb.append("<div class='mod'>");
	                        sb.append("<div class='mc'>" + diffRow.getOldLine() + "</div>");
	                        sb.append("<div class='mc'>" + diffRow.getNewLine() + "</div>");
	                        sb.append("</div>");
	                    }
	                	/*
	                    if (diffRow.getTag().equals(DiffRow.Tag.INSERT))
	                    {
	                        sb.append("+" +diffRow.getNewLine()+"\n");
	                    }
	                    else if (diffRow.getTag().equals(DiffRow.Tag.DELETE))
	                    {
	                        sb.append("-"+diffRow.getOldLine()+"\n");
	                    }
	                    else if (diffRow.getTag().equals(DiffRow.Tag.CHANGE))
	                    {
	                        sb.append("!\n");
	                        sb.append("    -"+diffRow.getOldLine()+"\n");
	                        sb.append("    +"+diffRow.getNewLine()+"\n");
	                        sb.append("~\n");
	                    }
	                    */
	                }
	                jep.setText(sb.toString());
	                JScrollPane scrollPane = new JScrollPane(jep);
	                frame.getContentPane().add(scrollPane);
	                
					
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
