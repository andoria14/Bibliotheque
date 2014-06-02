/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Thread;

import javax.swing.JLabel;
import javax.swing.JTable;

/**
 *
 * @author drouinjonathan
 */
public class Selected extends Thread {
    
    private JTable jTable;
    private JLabel jLabel;
    
    public Selected(JTable jTable, JLabel jLabel) {
        this.jTable = jTable;
        this.jLabel = jLabel;
    }
    
    @Override
    public void run() {
        if(!jTable.hasFocus()) {
            jLabel.setText("Aucun");
        }
    }
    
}
