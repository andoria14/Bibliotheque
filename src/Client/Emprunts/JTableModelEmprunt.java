/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Emprunts;

import Metier.Emprunt;
import Metier.EmpruntService;
import Metier.MetierServiceFactory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Gameone
 */
public class JTableModelEmprunt extends AbstractTableModel {

    private List<Emprunt> emprunts = null;
    private EmpruntService empruntMetierService = MetierServiceFactory.getEmpruntService();
    
    private int perpage;
    private int page;
    
    private String[] entetes = {"Identifiant", "Date", "Livre", "Adhérent"};
    
    public JTableModelEmprunt(int limitdeb, int perpage, int page) throws Exception {
        super();
        this.perpage = perpage;
        this.page = page;
        emprunts = empruntMetierService.getAll(limitdeb, perpage);
    }
    
    public JTableModelEmprunt(List<Emprunt> list, int perpage, int page) {
        super();
        this.emprunts = list;
        this.perpage = perpage;
        this.page = page;
    }
    
    public JTableModelEmprunt(Emprunt emprunt, int perpage, int page) {
        super();
        this.perpage = perpage;
        this.page = page;
        this.emprunts = new ArrayList<>();
        this.emprunts.add(emprunt);
    }
    
    @Override
    public int getRowCount() {
        return this.emprunts.size();
    }

    @Override
    public int getColumnCount() {
        return this.entetes.length;
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return emprunts.get(rowIndex).getId();
            case 1:
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                return sdf.format(emprunts.get(rowIndex).getDateEmprunt());
            case 2:
                return emprunts.get(rowIndex).getLivre();
            case 3:
                return emprunts.get(rowIndex).getAdherent();
            default:
                return null;
        }
    }
    
    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            default:
                return Object.class;
        }
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 1 : return true;
            default : return false;
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(aValue != null) {
            try {
                Emprunt emprunt = emprunts.get(rowIndex);
                Emprunt copy = emprunt.clone();
                switch(columnIndex) {
                    case 1 :
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        copy.setDateEmprunt(sdf.parse((String)aValue));
                        this.updateEmprunt(copy);
                        break;
                }
                emprunts.set(rowIndex, copy);
            } catch (Exception ex) {
                Logger.getLogger(JTableModelEmprunt.class.getName()).log(Level.SEVERE, null, ex);
                MetierServiceFactory.getBibliotheque().getTab().setStatut("<html><body><font color='red'>"+ex.getMessage()+"</font></body></html>", "emprunt");
            }
        }
    }
    
    public void addEmprunt(Emprunt emprunt) throws Exception {
        emprunt.getLivre().setTitre(emprunt.getLivre().getTitre().replace("\'", "\\'"));
        emprunt.getLivre().setAuteur(emprunt.getLivre().getAuteur().replace("\'", "\\'"));
        Emprunt add = MetierServiceFactory.getBibliotheque().emprunter(emprunt.getLivre(), emprunt.getAdherent());
        emprunt.getLivre().setTitre(emprunt.getLivre().getTitre().replace("\\'", "\'"));
        emprunt.getLivre().setAuteur(emprunt.getLivre().getAuteur().replace("\\'", "\'"));
        emprunts.add(add);
        fireTableRowsInserted(emprunts.size() - 1, emprunts.size() - 1);
        if (emprunts.size() > (perpage * page)) {
            emprunts.remove(0);
            fireTableRowsDeleted(0, 0);
        }
    }

    public void updateEmprunt(Emprunt emprunt) throws Exception {
        emprunt.getLivre().setTitre(emprunt.getLivre().getTitre().replace("\'", "\\'"));
        emprunt.getLivre().setAuteur(emprunt.getLivre().getAuteur().replace("\'", "\\'"));
        empruntMetierService.update(emprunt);
        emprunt.getLivre().setTitre(emprunt.getLivre().getTitre().replace("\\'", "\'"));
        emprunt.getLivre().setAuteur(emprunt.getLivre().getAuteur().replace("\\'", "\'"));
        for (int i = 0; i < emprunts.size(); i++) {
            if (emprunts.get(i).getId() == emprunt.getId()) {
                emprunts.set(i, emprunt);
                fireTableRowsUpdated(i, i);
                i = emprunts.size();
            }
        }
        MetierServiceFactory.getBibliotheque().getTab().actualisation();
        MetierServiceFactory.getBibliotheque().getTab().setStatut("<html><body><font color='green'>L'emprunt a été modifié</font></body></html>", "emprunt");
    }
    
    public void removeEmprunt(Emprunt emprunt) throws Exception {
        MetierServiceFactory.getBibliotheque().rendre(emprunt);
        for (int i = 0; i < emprunts.size(); i++) {
            if (emprunts.get(i).getId() == emprunt.getId()) {
                emprunts.remove(i);
                fireTableRowsDeleted(i, i);
                i = emprunts.size();
            }
        }
        MetierServiceFactory.getBibliotheque().getTab().actualisation();
        MetierServiceFactory.getBibliotheque().getTab().setStatut("<html><body><font color='green'>L'emprunt a été supprimé</font></body></html>", "emprunt");
    }
    
}
