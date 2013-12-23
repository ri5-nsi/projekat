import java.awt.Component;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;


class StatusTreeRenderer extends DefaultTreeCellRenderer {
	private static final long serialVersionUID = 1L;

    public StatusTreeRenderer() {
    }

    public Component getTreeCellRendererComponent(
                        JTree tree,
                        Object value,
                        boolean sel,
                        boolean expanded,
                        boolean leaf,
                        int row,
                        boolean hasFocus) {

        super.getTreeCellRendererComponent(
                        tree, value, sel,
                        expanded, leaf, row,
                        hasFocus);
        if (value.toString().equals("Konflikti")) {
            setIcon(getLocalIcon("StatusConflicts", "Konflikti"));
            setToolTipText("Objekti koji su u konfliktu između lokalne i aktuelne verzije");
        } else if (value.toString().equals("Dodani")) {
            setIcon(getLocalIcon("StatusAdded", "Dodani"));
            setToolTipText("Objekti koji su dodani u lokalnu verziju");
        } else if (value.toString().equals("Modifikovani")) {
            setIcon(getLocalIcon("StatusChanged", "Modifikovani"));
            setToolTipText("Objekti koji su modificirani u lokalnoj verziji");
        } else if (value.toString().equals("Obrisani")) {
            setIcon(getLocalIcon("StatusRemoved", "Obrisani"));
            setToolTipText("Objekti koji su obrisani u lokalnoj verziji");
        } else if (value.toString().equals("Nedostajući")) {
            setIcon(getLocalIcon("StatusMissing", "Nedostajući"));
            setToolTipText("Objekti koji nedostaju u lokalnoj verziji, a postoje u aktuelnoj");
        } else if (value.toString().equals("Neindeksirani")) {
            setIcon(getLocalIcon("StatusUnindexed", "Neindeksirani"));
            setToolTipText("Objekti koji se nalaze u radnom direktoriju, a koji nisu indeksirani u repozitoriju");
        } else {
            setToolTipText(null);
        } 

        return this;
    }
    
    private ImageIcon getLocalIcon(String imageName, String altText) {
        String imgLocation = "images/"+ imageName + ".png";
        URL imageURL = StatusTreeRenderer.class.getResource(imgLocation);
        if (imageURL != null) {
            return new ImageIcon(imageURL, altText);
        } else {
            System.err.println("Slika nije pronađena: " + imgLocation);
            return null;
        }
	}
}