/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Livres;

import java.awt.Component;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Gameone
 */
public class BooleanCellRenderer extends DefaultTableCellRenderer {

    private Icon dispo;
    private Icon erreur;

    public BooleanCellRenderer() {
        super();
        dispo = new ImageIcon(getClass().getResource("/Pictures/Livres/disponible.png"));
        erreur = new ImageIcon(getClass().getResource("/Pictures/Livres/erreur.png"));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, null, isSelected, hasFocus, row, column);
        Boolean disponible = (Boolean) value;
        if (disponible) {
            setIcon(dispo);
        } else {
            setIcon(erreur);
        }
        return this;
    }
}
