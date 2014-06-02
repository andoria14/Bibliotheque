/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Livres;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Gameone
 */
public class DisponibiliteCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private boolean dispo;
    private JButton button;
    
    public DisponibiliteCellEditor() {
        super();
        
        button = new JButton();
        button.addActionListener(this);
        button.setBorderPainted(false);
    }
    
    @Override
    public Object getCellEditorValue() {
        return dispo;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        dispo = (Boolean)value;
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispo ^= true;
        fireEditingStopped();
    }
}
