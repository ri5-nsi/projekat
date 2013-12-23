import javax.swing.Box;
import javax.swing.JFileChooser;
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
import org.eclipse.jgit.api.errors.ConcurrentRefUpdateException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.NoMessageException;
import org.eclipse.jgit.api.errors.UnmergedPathsException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.eclipse.jgit.lib.Repository;

import com.sun.media.sound.InvalidFormatException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

    protected void addButtons(JToolBar toolBar) {
        JButton button = null;
        
        button = makeNavigationButton("IconRefresh", "Refresh", "Osvježi prikaz", "Refresh");
        toolBar.add(button);
        
        toolBar.add(Box.createHorizontalGlue());
        
        button = makeNavigationButton("IconAdd", "Add", "Dodaj", "Dodaj");
        toolBar.add(button);
        
        button = makeNavigationButton("IconDelete", "Delete", "Obriši", "Obriši");
        toolBar.add(button);
        
        toolBar.addSeparator();
        
        button = makeNavigationButton("IconCommit", "Commit", "Snimi sve lokalne izmjene", "Commit");
        toolBar.add(button);

        button = makeNavigationButton("IconPush", "Push", "Pošalji commit na centralni repozitorij", "Push");
        toolBar.add(button);
        
        toolBar.addSeparator();
        
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
        		
            } 
            else if (PULL.equals(cmd)) {
                
            }
            else if (ADD.equals(cmd)) { 
            	try {
            		File destination = new File(GetSelectionPath(true,false));
            		if (!destination.isDirectory())
            			throw new InvalidFormatException("Morate označiti direktorij u koji želite dodati nove datoteke!");
            		
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
    				ShowError("Izabrani fajl nema validnu putanju!");
    			} catch (InvalidFormatException e1) {
    				ShowError("Pogrešan format datoteke!");
    			} catch (GitAPIException e1) {
    				ShowError("Problem sa GIT bibliotekom. Provjerite PATH varijablu!");
    			} catch (IOException e1) {
    				ShowError("Problem pri pristupu datotečnom sistemu!");
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
					}

    			} catch (NoFilepatternException e1) {
    				ShowError("Izabrani fajl nema validnu putanju!");
    			} catch (GitAPIException e1) {
    				ShowError("Problem sa GIT bibliotekom. Provjerite PATH varijablu!");
    			} catch (Exception e1) {
    				
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
            			if (selected.equals(""))
            				git.commit().setAll(true).setMessage(s).call();
            			else
            				git.commit().setOnly(selected).setMessage(s).call();
            		}
					
				} catch (NoHeadException e1) {
					
				} catch (NoMessageException e1) {
					
				} catch (UnmergedPathsException e1) {
					
				} catch (ConcurrentRefUpdateException e1) {
					
				} catch (WrongRepositoryStateException e1) {
					
				} catch (GitAPIException e1) {
					
				}
            }
        	return null;
        } 
 
        @Override
        public void done() {
        	if (progressBar.isIndeterminate()) {
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