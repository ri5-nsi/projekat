import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.ConcurrentRefUpdateException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.NoMessageException;
import org.eclipse.jgit.api.errors.UnmergedPathsException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.StoredConfig;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.RemoteConfig;

import com.sun.media.sound.InvalidFormatException;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JToolBarExtended extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final String PUSH = "Push";
	private static final String PULL = "Pull";
	private static final String ADD = "Add";
	private static final String DELETE = "Delete";
	private static final String COMMIT = "Commit";
    private JTree repositoryTree;
    private Repository Repozitorij;
    private Git git;
    private JProgressBar progressBar;
    private JLabel currentBranch;

    public JToolBarExtended(JTree tree) {
        super(new BorderLayout());

        this.repositoryTree = tree; 
        JToolBar toolBar = new JToolBar();
        addButtons(toolBar);
        toolBar.setFloatable(false);
        toolBar.setRollover(true);

        setPreferredSize(new Dimension(450, 130));
        add(toolBar, BorderLayout.PAGE_START);
    }
    
    public void setRepository(Repository repo) {
    	this.Repozitorij = repo;
    	this.git = new Git(repo);
    }
    
    public JLabel getCurrentBranch() {
    	return currentBranch;
    }

    protected void addButtons(JToolBar toolBar) {
    	currentBranch = new JLabel();
    	currentBranch.setFont(new Font(currentBranch.getFont().getFontName(), Font.BOLD, currentBranch.getFont().getSize()));
    	toolBar.add(currentBranch);
    	
        JButton button = null;

        toolBar.add(Box.createHorizontalGlue());
        
        button = makeNavigationButton("IconAdd", "Add", "Dodaj", "Dodaj");
        toolBar.add(button);
        
        button = makeNavigationButton("IconDelete", "Delete", "Obriši", "Obriši");
        toolBar.add(button);
        
        toolBar.addSeparator();
        
        button = makeNavigationButton("IconCommit", "Commit", "Snimi sve lokalne izmjene", "Commit");
        toolBar.add(button);
        
        button = makeNavigationButton("IconRefresh", "Reset", "Poništi sve necommitane promjene", "Reset");
        toolBar.add(button);
        
        toolBar.addSeparator();

        button = makeNavigationButton("IconPush", "Push", "Pošalji commit na centralni repozitorij", "Push");
        toolBar.add(button);
        
        button = makeNavigationButton("IconPull", "Pull", "Ažuriraj lokalni repozitorij sa novim podacima iz centralnog repozitorija", "Pull");
        toolBar.add(button);
    }

    protected JButton makeNavigationButton(String imageName,
                                           String actionCommand,
                                           String toolTipText,
                                           String altText) {
    	
        String imgLocation = "images/" + imageName + ".png";
        URL imageURL = JToolBarExtended.class.getResource(imgLocation);

        JButton button = new JButton();
        button.setActionCommand(actionCommand);
        button.setToolTipText(toolTipText);
        button.setPreferredSize(new Dimension(90,18));
        button.addActionListener(this);

        if (imageURL != null) {
            button.setIcon(new ImageIcon(imageURL, altText));
            button.setText(altText);
        } else {
            button.setText(altText);
        }

        return button;
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        new RepositoryOperations(cmd).execute();
    }
    
    public String GetSelectionPath(Boolean canonicPath, Boolean repositoryLocalPath) {
    	TreePath pathElements = repositoryTree.getSelectionPath();
    	if (pathElements == null)
    		return null;
    	
    	String path = canonicPath ? pathElements.getPathComponent(0).toString() : "";
    	
    	if (pathElements.getPathCount() == 1)
    		return path;
    	
    	for(int i=1; i<pathElements.getPathCount(); i++) {
    		if (i == 1 && repositoryLocalPath)
    			path = path + pathElements.getPathComponent(i).toString();
    		else
    			path = path + "/" + pathElements.getPathComponent(i).toString();
    	}
    	return path;
    }
    
    public void setProgressBar(JProgressBar bar) {
    	this.progressBar = bar;
    }
    
    private class RepositoryOperations extends SwingWorker<Void, Void> {
    	private String cmd;
    	
    	public RepositoryOperations(String s) {
    		super();
    		this.cmd = s;
    	}
    	
        @Override
        public Void doInBackground() {
        	if (PUSH.equals(cmd)) { 
                try {
                	String refspec = git.getRepository().getFullBranch();
                	git.push().setRefSpecs(new RefSpec(refspec)).setForce(true).call();
                	
				} catch (Exception e) {
					ShowError(e.getMessage());
				}
            } 
            else if (PULL.equals(cmd)) {
                
            }
            else if (ADD.equals(cmd)) { 
            	try {
            		System.out.println("test1");
            		File destination = new File(GetSelectionPath(true,false));
            		if (!destination.isDirectory()) {
            			throw new InvalidFormatException();
            		}
            		
            		JFileChooser chooser = new JFileChooser();
    				chooser.setCurrentDirectory(new File("."));
    				chooser.setDialogTitle("Dodaj");
    			    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    			    chooser.setMultiSelectionEnabled(true);
    			    chooser.setAcceptAllFileFilterUsed(false);
    			    int returnVal = chooser.showOpenDialog(null);
    			    if(returnVal == JFileChooser.APPROVE_OPTION) {
    			    	progressBar.setIndeterminate(true);
						progressBar.setStringPainted(true);
						progressBar.setString("Kopiranje i dodavanje izabranih datoteka u toku!");
    			    	File[] selectedFiles = chooser.getSelectedFiles();
    			    	for (File f: selectedFiles) {
    			    		if (f.isDirectory())
    			    			FileUtils.copyDirectory(f, destination);
    			    		else if (f.isFile())
    			    			FileUtils.copyFileToDirectory(f, destination);
    			    		git.add().addFilepattern(GetSelectionPath(false,true) + "/" + f.getName()).call();
    			    	}
    			    }
    			} catch (NoFilepatternException e1) {
    				ShowError("Izabrani fajl nema validnu putanju");
    			} catch (InvalidFormatException e1) {
    				ShowError("Morate izabrati direktorij u koji želite dodati novu datoteku");
    			} catch (GitAPIException e1) {
    				ShowError("Problem sa GIT bibliotekom. Provjerite PATH varijablu");
    			} catch (IOException e1) {
    				ShowError("Problem pri pristupu datotečnom sistemu");
    			} catch (Exception e1) {
    				ShowError("Morate označiti direktorij u koji želite dodati nove datoteke");
    			}
            }
            else if (DELETE.equals(cmd)) { 
            	try {
            		String selected = GetSelectionPath(false,true);
            		if (selected == null || selected.equals(""))
            			return null;
            		
            		Object[] options = {"Potvrdi", "Odustani"};
					int n = JOptionPane.showOptionDialog(null, "Da li ste sigurni da želite obrisati označenu datoteku?",
							"Delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if (n == JOptionPane.YES_OPTION) {
						progressBar.setIndeterminate(true);
						progressBar.setStringPainted(true);
						progressBar.setString("Brisanje izabranih datoteka u toku!");
						git.rm().addFilepattern(selected).call();
						
						if (!new File(GetSelectionPath(true,false)).delete())
							ShowError("Datoteka je izbrisana u reviziji, ali nije obrisana sa fizičke lokacije");
					}

    			} catch (NoFilepatternException e1) {
    				ShowError("Izabrani fajl nema validnu putanju!");
    			} catch (GitAPIException e1) {
    				ShowError("Problem sa GIT bibliotekom. Provjerite PATH varijablu!");
    			} catch (Exception e1) {
    				ShowError(e1.getMessage());
    			}
            }
            else if (COMMIT.equals(cmd)) {
            	try {
            		String selected = GetSelectionPath(false,true);
            		if (selected == null)
            			return null;
            		
            		String s = (String)JOptionPane.showInputDialog(null,
            		                    "Ova operacija će spasiti sve trenutne izmjene. Da li ste sigurni?",
            		                    "Commit",
            		                    JOptionPane.PLAIN_MESSAGE,
            		                    null,
            		                    null,
            		                    "Komentar...");

            		if ((s != null) && (s.length() > 0)) {
            			if (selected.equals("")) {
            				git.add().call();
            				git.commit().setAll(true).setMessage(s).call();
            				
            			} else  {
            				git.add().addFilepattern(selected).call();
            				git.commit().setOnly(selected).setMessage(s).call();
            			}	
            		}
					
				} catch (NoHeadException e1) {
					ShowError("Izabrani fajl nema validnu putanju");
				} catch (NoMessageException e1) {
					ShowError("Nije definiran komentar za commit");
				} catch (UnmergedPathsException e1) {
					ShowError("Postoje nerazriješeni konflikti");
				} catch (ConcurrentRefUpdateException e1) {
					ShowError("Problem pri konkurentskom commitu istih datoteka");
				} catch (WrongRepositoryStateException e1) {
					ShowError("Postoje nerazriješeni konflikti");
				} catch (GitAPIException e1) {
					ShowError("Problem sa GIT bibliotekom");
				} catch (Exception e1) {
					ShowError(e1.getMessage());
				}
            }
        	return null;
        } 
 
        @Override
        public void done() {
        	if (!progressBar.getString().contains("Greška")) {
        		progressBar.setIndeterminate(false);
            	progressBar.setStringPainted(false);
        	}
        }
    }
    
    public void ShowError(String message) {
    	progressBar.setIndeterminate(false);
		progressBar.setForeground(Color.RED);
		progressBar.setStringPainted(true);
		progressBar.setString("Greška: " + message);
	}
}