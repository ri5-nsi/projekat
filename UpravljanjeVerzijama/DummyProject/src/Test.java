import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.activity.InvalidActivityException;
import javax.swing.JFrame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import org.eclipse.jgit.errors.NoWorkTreeException;
import org.eclipse.jgit.lib.RepositoryCache.FileKey;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.util.FS;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import de.javasoft.plaf.synthetica.SyntheticaBlackMoonLookAndFeel;
import difflib.Delta;
import difflib.DiffRow;
import difflib.DiffRowGenerator;
import difflib.DiffUtils;
import difflib.Patch;

import java.awt.Font;

public class Test {

	private static JFrame frmUpravljanjeRevizijama;
	private static JFrame frmKonfiguracija;
	private JSearchTextField txtDirektorij;
	private JButton btnPromijeni;
	private JButton btnRevisions;
	private String AdresaRepozitorija;
	private Repository Repozitorij;
	private JTree tree;
	private JTree treeStatus;
	private JTabbedPane centralTab;
	private JToolBarExtended toolBarRepositoryOperations;
	private FileSystemModel repositoryContents;
	private JTable commitHistoryTable;
	private JProgressBar progressBar;
	private LoadRepository loadRepository;
	private Git git;
	private Configuration conf = new Configuration();
	private static final String REMOTE_URL = "https://github.com/ri5-nsi/projekat.git";
	private static UsernamePasswordCredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider("ri5-nsi", "Rezonansa5");

	private JPasswordField passwordKonfiguracija;
	private JCheckBox useRemoteKonfiguracija;
	private JTextField usernameKonfiguracija;
	private JLabel korisnikKonfiguracija;
	private JLabel emailKonfiguracija;
	
	private JComboBox<RevCommitSpecial> commitList1;
	private JComboBox<RevCommitSpecial> commitList2;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
					Test.frmUpravljanjeRevizijama.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Fatal error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public Test() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	/**
	 * @throws Exception 
	 * 
	 */
	private void initialize() throws Exception {
		try {
			UIManager.setLookAndFeel(new SyntheticaBlackMoonLookAndFeel());
		} catch (Exception e) {
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | UnsupportedLookAndFeelException e1) {
				
				throw new Exception("Problem pri učitavanju konfiguracije korisničkog interfejsa!");
			}
		}
		// Provjeri da li ima spašene konfiguracije i ako ima učitaj
	    try {
	        XStream xstream = new XStream(new StaxDriver());
	        conf = (Configuration)xstream.fromXML(new FileReader("konfiguracija.xml"));
	    } catch (Exception e1) {
	    	System.out.println(e1.getMessage());
		}
		
		// Prozor za konfiguraciju
		frmKonfiguracija = new JFrame();
		frmKonfiguracija.setTitle("Konfiguracija");
		frmKonfiguracija.setBounds(100, 100, 400, 260);
		frmKonfiguracija.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmKonfiguracija.setLocationRelativeTo(null);
		if (getLocalIcon("IconConf", "Konfiguracija") != null)
			frmKonfiguracija.setIconImage(getLocalIcon("IconConf", "Konfiguracija").getImage());
		frmKonfiguracija.getContentPane().setLayout(new MigLayout("fillx, insets 15", "", ""));
		JTextField repoURL = new JTextField(REMOTE_URL);
		repoURL.setEditable(false);
		repoURL.setBackground(Color.LIGHT_GRAY);
		JLabel repoURLLabel = new JLabel("Centralni repozitorij: ");
		frmKonfiguracija.getContentPane().add(repoURLLabel);
		frmKonfiguracija.getContentPane().add(repoURL, "width 300, wrap");
		
		useRemoteKonfiguracija = new JCheckBox("Uspostavi konekciju sa centralnim repozitorijem");
		useRemoteKonfiguracija.setSelected(true);
		frmKonfiguracija.getContentPane().add(useRemoteKonfiguracija, "growx, span 2, wrap");
		
		usernameKonfiguracija = new JTextField(conf.getUser());
		JLabel usernameLabel = new JLabel("Korisničko ime: ");
		frmKonfiguracija.getContentPane().add(usernameLabel);
		frmKonfiguracija.getContentPane().add(usernameKonfiguracija, "width 300, wrap");
		
		passwordKonfiguracija = new JPasswordField(conf.getPassword());
		JLabel passwordLabel = new JLabel("Lozinka: ");
		frmKonfiguracija.getContentPane().add(passwordLabel);
		frmKonfiguracija.getContentPane().add(passwordKonfiguracija, "width 300, wrap");
		
		JLabel prijava = new JLabel("Prijavljeni ste kao: ");
		frmKonfiguracija.getContentPane().add(prijava, "gaptop 10");
		korisnikKonfiguracija = new JLabel(conf.getKorisnik());
		korisnikKonfiguracija.setFont(new Font(korisnikKonfiguracija.getFont().getFontName(), Font.BOLD, korisnikKonfiguracija.getFont().getSize()));
		frmKonfiguracija.getContentPane().add(korisnikKonfiguracija, "align right, gaptop 10, wrap");
		
		JLabel email = new JLabel("Email adresa: ");
		frmKonfiguracija.getContentPane().add(email);
		emailKonfiguracija = new JLabel(conf.getEmail());
		emailKonfiguracija.setFont(new Font(emailKonfiguracija.getFont().getFontName(), Font.BOLD, emailKonfiguracija.getFont().getSize()));
		frmKonfiguracija.getContentPane().add(emailKonfiguracija, "align right, wrap");
		
		JButton loginKonfiguracija = new JButton("Prijavi se");
		loginKonfiguracija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conf.setUser(usernameKonfiguracija.getText());
				conf.setPassword(new String(passwordKonfiguracija.getPassword()));
				if (conf.Authentication())
				{
					korisnikKonfiguracija.setText(conf.getKorisnik());
					emailKonfiguracija.setText(conf.getEmail());
					conf.setClone(useRemoteKonfiguracija.isSelected());
				} else
					JOptionPane.showMessageDialog(frmKonfiguracija, "Prijava nije uspjela, provjerite pristupne podatke!", "Greška!", JOptionPane.ERROR_MESSAGE);
			}
		});
		frmKonfiguracija.getContentPane().add(new JLabel());
		frmKonfiguracija.getContentPane().add(loginKonfiguracija, "gaptop 5, align right, split 2");
		
		JButton spasiKonfiguracija = new JButton("Zapamti");
		spasiKonfiguracija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (conf.IsAuthenticated()) {
						XStream xstream = new XStream(new StaxDriver());
						BufferedWriter out = new BufferedWriter(new FileWriter("konfiguracija.xml"));
						conf.setPassword("");
						out.write(xstream.toXML(conf));
						out.close();
						frmKonfiguracija.setVisible(false);
					}
					else
						JOptionPane.showMessageDialog(frmKonfiguracija, "Prije snimanja konfiguracije se morate prijaviti!", "Greška!", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(frmKonfiguracija, "Problem pri snimanju konfiguracije!", "Greška!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		frmKonfiguracija.getContentPane().add(spasiKonfiguracija, "gaptop 5, align right");
		
		//kraj konfiguracijskog
		
		//početak glavnog prozora
		
		frmUpravljanjeRevizijama = new JFrame();
		frmUpravljanjeRevizijama.setTitle("Upravljanje verzijama");
		frmUpravljanjeRevizijama.setBounds(100, 100, 1000, 700);
		frmUpravljanjeRevizijama.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUpravljanjeRevizijama.setLocationRelativeTo(null);
		if (getLocalIcon("MainIcon", "Glavna ikona") != null)
			frmUpravljanjeRevizijama.setIconImage(getLocalIcon("MainIcon", "Glavna ikona").getImage());
		frmUpravljanjeRevizijama.getContentPane().setLayout(new MigLayout("fillx, insets 10", "[][grow]", "[]10[270][][][240]:push[]"));
		
		btnRevisions = new JButton(getLocalIcon("IconConf", "Korisnik"));
		btnRevisions.setText("Konfiguracija");
		btnRevisions.setFocusPainted(false);
		btnRevisions.setIconTextGap(10);
		btnRevisions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Test.frmKonfiguracija.setVisible(true);
			}
		});
		frmUpravljanjeRevizijama.getContentPane().add(btnRevisions, "cell 0 0, width 200");
		
		txtDirektorij = new JSearchTextField();
		txtDirektorij.setColumns(10);
		txtDirektorij.setTextWhenNotFocused(" Izaberite radni direktorij ");
		txtDirektorij.setIcon(this.getLocalIcon("IconFolder", "Direktorij"));
		frmUpravljanjeRevizijama.getContentPane().add(txtDirektorij, "cell 1 0, growx, height 30");
		
		btnPromijeni = new JButton("Promijeni");
		btnPromijeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("."));
				chooser.setDialogTitle("Lokacija repozitorija");
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    chooser.setAcceptAllFileFilterUsed(false);
			    int returnVal = chooser.showOpenDialog(frmUpravljanjeRevizijama);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	try {
						setAdresaRepozitorija(chooser.getSelectedFile().getCanonicalPath());
						loadRepository = new LoadRepository();
						loadRepository.execute();
					} catch (InvalidActivityException e1) {
						JOptionPane.showMessageDialog(frmUpravljanjeRevizijama, "Greška!", e1.getMessage(), JOptionPane.ERROR_MESSAGE);
					} 
			    	catch (IOException e1) {
			    		JOptionPane.showMessageDialog(frmUpravljanjeRevizijama, "Greška!", e1.getMessage(), JOptionPane.ERROR_MESSAGE);
					} 
			    }
			}
		});
		frmUpravljanjeRevizijama.getContentPane().add(btnPromijeni, "cell 1 0, width 50, height 28");

		DefaultTableModel modelBranches = new DefaultTableModel(); 
		modelBranches.addColumn("Branch:");
		JTable tableBranches = new JTable(modelBranches);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tableBranches.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		((DefaultTableCellRenderer)tableBranches.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		tableBranches.getTableHeader().setPreferredSize(new Dimension(200, 32));
		tableBranches.setRowHeight(25);
		modelBranches.addRow(new Object[] { "Branch1" });
		modelBranches.addRow(new Object[] { "Branch2" });
		modelBranches.addRow(new Object[] { "Branch3" });
		frmUpravljanjeRevizijama.getContentPane().add(new JScrollPane(tableBranches), "cell 0 1, width 200, height 270");
		
		centralTab = new JTabbedPane();
		tree = new JTree();
		tree.setModel(null);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
		    public void valueChanged(TreeSelectionEvent e) {
		    	if (git == null)
		    		return;

		    	try {
		    		String selectedPath = toolBarRepositoryOperations.GetSelectionPath(false, true);
					Iterable<RevCommit> logs = git.log().addPath(selectedPath).call();
					commitList1.removeAllItems();
					commitList2.removeAllItems();
					for (RevCommit rev : logs) {
						commitList1.addItem(new RevCommitSpecial(rev));
						commitList2.addItem(new RevCommitSpecial(rev));
					}
				} catch (NoHeadException e1) {
					e1.printStackTrace();
				} catch (GitAPIException e1) {
					e1.printStackTrace();
				}
		    }
		});
		
		commitHistoryTable = new JTable();
		commitHistoryTable.setFillsViewportHeight(true);
		commitHistoryTable.setAutoCreateRowSorter(true);
		commitHistoryTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		treeStatus = new JTree();
		treeStatus.putClientProperty("JTree.lineStyle", "None");
		treeStatus.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		treeStatus.setCellRenderer(new StatusTreeRenderer());
		treeStatus.setModel(null);
		ToolTipManager.sharedInstance().registerComponent(treeStatus);
		JScrollPane qPane1 = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JScrollPane qPane2 = new JScrollPane(commitHistoryTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JScrollPane qPane3 = new JScrollPane(treeStatus, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		centralTab.addTab("Repozitorij", qPane1);
		centralTab.addTab("Revizije", qPane2);
		centralTab.addTab("Status", qPane3);
		
		toolBarRepositoryOperations = new JToolBarExtended(tree);
		toolBarRepositoryOperations.add(centralTab);
		frmUpravljanjeRevizijama.getContentPane().add(toolBarRepositoryOperations, "cell 1 1, growx, height 270");
		
		commitList1 = new JComboBox<RevCommitSpecial>();
		commitList2 = new JComboBox<RevCommitSpecial>();
		frmUpravljanjeRevizijama.getContentPane().add(commitList1, "cell 1 2, split 2, growx");
		frmUpravljanjeRevizijama.getContentPane().add(commitList2, "cell 1 2, growx");
		
        URL revertURL = JToolBarExtended.class.getResource("images/IconRevert.png");
		JButton revertCommit = new JButton();
		revertCommit.setIcon(new ImageIcon(revertURL, "Revert"));
		revertCommit.setText("Revert");
		URL showURL = JToolBarExtended.class.getResource("images/IconShow.png");
		JButton showContent = new JButton();
		showContent.setIcon(new ImageIcon(showURL, "Prikaži"));
		showContent.setText("Prikaži");
		URL combinedURL = JToolBarExtended.class.getResource("images/IconCombined.png");
		JButton combinedView = new JButton();
		combinedView.setIcon(new ImageIcon(combinedURL, "Prikaz razlika"));
		combinedView.setText("Prikaz razlika");
		frmUpravljanjeRevizijama.getContentPane().add(revertCommit, "cell 1 3, width 140");
		frmUpravljanjeRevizijama.getContentPane().add(showContent, "cell 1 3, gapleft 5, width 140");
		frmUpravljanjeRevizijama.getContentPane().add(combinedView, "cell 1 3, gapleft push");
		
		// Editor
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

		File fileOld = new File("file1.txt");
		File fileNew = new File("file2.txt");
        
		fileDiff(fileOld, fileNew, jep);
        JScrollPane scrollPane = new JScrollPane(jep);
        frmUpravljanjeRevizijama.getContentPane().add(scrollPane, "cell 1 4, grow");

		progressBar = new JProgressBar();
		frmUpravljanjeRevizijama.getContentPane().add(progressBar, "cell 0 5, span 2 1, growx, height 25");
		toolBarRepositoryOperations.setProgressBar(progressBar);
	}

	private void setAdresaRepozitorija(String adresaRepozitorija) {
		AdresaRepozitorija = adresaRepozitorija;
	}
	
	private ImageIcon getLocalIcon(String imageName, String altText) {
        String imgLocation = "images/"+ imageName + ".png";
        URL imageURL = Test.class.getResource(imgLocation);
        if (imageURL != null) {
            return new ImageIcon(imageURL, altText);
        } else {
            System.err.println("Slika nije pronađena: " + imgLocation);
            return null;
        }
	}
	
	private class LoadRepository extends SwingWorker<Void, Void> {
        @Override
        public Void doInBackground() {
        	try {
        		centralTab.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	        	FileRepositoryBuilder builder = new FileRepositoryBuilder();
				
				if (!FileKey.isGitRepository(new File(AdresaRepozitorija, ".git"), FS.DETECTED)) {
					Object[] options = {"Kreiraj", "Odustani"};
					int n = JOptionPane.showOptionDialog(frmUpravljanjeRevizijama, "Izabrani direktorij ne sadrži verzionisane datoteke. Da li želite kreirati novi repozitorij?",
							"Novi repozitorij", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if (n == JOptionPane.YES_OPTION) {
						progressBar.setIndeterminate(true);
						progressBar.setStringPainted(true);
						if (conf.getClone()) {
							progressBar.setString("Kloniranje repozitorija: " + REMOTE_URL);
							Git.cloneRepository().setURI(REMOTE_URL).setCredentialsProvider(credentialsProvider).setDirectory(new File(AdresaRepozitorija)).call();
						}
						else {
							progressBar.setString("Instanciranje repozitorija: " + AdresaRepozitorija);
							FileRepositoryBuilder.create(new File(AdresaRepozitorija, ".git")).create();
						}
					}
					else
						return null;
				}
				
				progressBar.setIndeterminate(true);
				progressBar.setStringPainted(true);
				progressBar.setString("Učitavanje repozitorija");
				
				Repozitorij = builder.setGitDir(new File(AdresaRepozitorija, ".git")).readEnvironment().findGitDir().build();
				git = new Git(Repozitorij);
				
				toolBarRepositoryOperations.setRepository(Repozitorij);
				txtDirektorij.setText(Repozitorij.getWorkTree().getCanonicalPath());
				repositoryContents = new FileSystemModel(new File(Repozitorij.getWorkTree().getCanonicalPath()));
				tree.setModel(repositoryContents);
				
				
				Iterable<RevCommit> logs = git.log().all().call();
		        commitHistoryTable.setModel(new CommitHistoryTableModel(logs));
		        commitHistoryTable.getColumnModel().getColumn(0).setPreferredWidth(80);
		        commitHistoryTable.getColumnModel().getColumn(3).setPreferredWidth(220);
		        
		        Status status = git.status().call();
		        DefaultMutableTreeNode rootNodeStatus = new DefaultMutableTreeNode("status");
		        
		        DefaultMutableTreeNode statusConflictsNode = new DefaultMutableTreeNode("Konflikti");
		        for(String conflict : status.getConflicting())
		        	statusConflictsNode.add(new DefaultMutableTreeNode(conflict));
		        
		        DefaultMutableTreeNode statusAddedNode = new DefaultMutableTreeNode("Dodani");
		        for(String added : status.getAdded())
		        	statusAddedNode.add(new DefaultMutableTreeNode(added));
		        
		        DefaultMutableTreeNode statusChangedNode = new DefaultMutableTreeNode("Modifikovani");
		        for(String changed : status.getChanged())
		        	statusChangedNode.add(new DefaultMutableTreeNode(changed));
		        
		        DefaultMutableTreeNode statusDeletedNode = new DefaultMutableTreeNode("Obrisani");
		        for(String deleted : status.getRemoved())
		        	statusDeletedNode.add(new DefaultMutableTreeNode(deleted));
		        
		        DefaultMutableTreeNode statusMissingNode = new DefaultMutableTreeNode("Nedostajući");
		        for(String missing : status.getMissing())
		        	statusMissingNode.add(new DefaultMutableTreeNode(missing));
		        
		        DefaultMutableTreeNode statusUntrackedNode = new DefaultMutableTreeNode("Neindeksirani");
		        for(String Untracked : status.getUntracked())
		        	statusUntrackedNode.add(new DefaultMutableTreeNode(Untracked));
		        
		        rootNodeStatus.add(statusConflictsNode);
		        rootNodeStatus.add(statusAddedNode);
		        rootNodeStatus.add(statusChangedNode);
		        rootNodeStatus.add(statusDeletedNode);
		        rootNodeStatus.add(statusMissingNode);
		        rootNodeStatus.add(statusUntrackedNode);
		        treeStatus.setModel(new DefaultTreeModel(rootNodeStatus));
		        treeStatus.setRootVisible(false);
		        treeStatus.setShowsRootHandles(true);
	        }
	        catch (NoWorkTreeException nwte) {
				JOptionPane.showMessageDialog(frmUpravljanjeRevizijama, "Repozitorij ne sadrži radni direktorij!", "Upozorenje", JOptionPane.WARNING_MESSAGE);
			} catch (IllegalStateException ise) {
				JOptionPane.showMessageDialog(frmUpravljanjeRevizijama, "Repozitorij već postoji!", "Upozorenje", JOptionPane.WARNING_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(frmUpravljanjeRevizijama, "Problem pri pristupu izabranom direktoriju!", "Upozorenje", JOptionPane.WARNING_MESSAGE);
			} catch (InvalidRemoteException e) {
				JOptionPane.showMessageDialog(frmUpravljanjeRevizijama, "Problem pri pristupu udaljenom repozitoriju!", "Upozorenje", JOptionPane.WARNING_MESSAGE);
			} catch (TransportException e) {
				JOptionPane.showMessageDialog(frmUpravljanjeRevizijama, "Problem pri konekciji na udaljeni repozitorij!", "Upozorenje", JOptionPane.WARNING_MESSAGE);
			} catch (GitAPIException e) {
				JOptionPane.showMessageDialog(frmUpravljanjeRevizijama, "Problem sa GIT bibliotekom", "Upozorenje", JOptionPane.WARNING_MESSAGE);
			}
			return null;
        }
 
        @Override
        public void done() {
        	centralTab.setCursor(null);
        	progressBar.setIndeterminate(false);
        	progressBar.setStringPainted(false);
        }
    }
	
	public void ShowError(String message) {
		progressBar.setForeground(Color.RED);
		progressBar.setStringPainted(true);
		progressBar.setString("Greška: " + message);
	}
	
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
	
	private void fileDiff(File fileOld, File fileNew, JEditorPane jep) {
		List<String> original = fileToLines(fileOld.getAbsolutePath());
        List<String> revised  = fileToLines(fileNew.getAbsolutePath());
        
        // Compute diff. Get the Patch object. Patch is the container for computed deltas.
        Patch patch = DiffUtils.diff(original, revised);

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
                sb.append("<div class='insert'>" + "+" + diffRow.getNewLine() + "</div>");
            }
            else if (diffRow.getTag().equals(DiffRow.Tag.DELETE))
            {
                sb.append("<div class='del'>" + "-" + diffRow.getOldLine() + "</div>");
            }
            else if (diffRow.getTag().equals(DiffRow.Tag.CHANGE))
            {
                sb.append("<div class='mod'>");
                sb.append("<div class='mc'>" + "-" + diffRow.getOldLine() + "</div>");
                sb.append("<div class='mc'>" + "+" + diffRow.getNewLine() + "</div>");
                sb.append("</div>");
            }
        }
        jep.setText(sb.toString());
	}
}