/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Adherents;

import Metier.Adherent;
import Metier.AdherentService;
import Metier.MetierServiceFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author drouinjonathan
 */
public class JTableModelAdherent extends AbstractTableModel {

    private List<Adherent> adherents = null;
    private AdherentService adherentMetierService = MetierServiceFactory.getAdherentService();
    
    private int perpage;
    private int page;
    
    private String[] entetes = {"Identifiant", "Nom", "Prenom"};

    public JTableModelAdherent(int limitDeb, int perpage, int page) throws Exception {
        super();
        this.perpage = perpage;
        this.page = page;
        adherents = adherentMetierService.getAll(limitDeb, perpage);
    }

    public JTableModelAdherent(List<Adherent> list, int perpage, int page) {
        super();
        this.adherents = list;
        this.perpage = perpage;
        this.page = page;
    }

    public JTableModelAdherent(Adherent adherent, int perpage, int page) {
        super();
        this.perpage = perpage;
        this.page = page;
        this.adherents = new ArrayList<>();
        this.adherents.add(adherent);
    }

    @Override
    public int getRowCount() {
        return this.adherents.size();
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
                return adherents.get(rowIndex).getId();
            case 1:
                return adherents.get(rowIndex).getNom();
            case 2:
                return adherents.get(rowIndex).getPrenom();
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
            case 0 : return false;
            default : return true;
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(aValue != null) {
            try {
                Adherent adherent = adherents.get(rowIndex);
                Adherent copy = adherent.clone();
                switch(columnIndex) {
                    case 1 :
                        copy.setNom((String)aValue);
                        this.updateAdherent(copy);
                        break;
                    case 2 :
                        copy.setPrenom((String)aValue);
                        this.updateAdherent(copy);
                        break;
                }
                adherents.set(rowIndex, copy);
            } catch (Exception ex) {
                Logger.getLogger(JTableModelAdherent.class.getName()).log(Level.SEVERE, null, ex);
                MetierServiceFactory.getBibliotheque().getTab().setStatut("<html><body><font color='red'>"+ex.getMessage()+"</font></body></html>", "adherent");
            }
        }
    }

    public void addAdherent(Adherent adherent) throws Exception {
        adherent.setNom(adherent.getNom().replace("\'", "\\'"));
        adherent.setPrenom(adherent.getPrenom().replace("\'", "\\'"));
        Adherent add = adherentMetierService.add(adherent);
        adherent.setNom(adherent.getNom().replace("\\'", "\'"));
        adherent.setPrenom(adherent.getPrenom().replace("\\'", "\'"));
        adherents.add(add);
        fireTableRowsInserted(adherents.size() - 1, adherents.size() - 1);
        if (adherents.size() > (perpage * page)) {
            adherents.remove(0);
            fireTableRowsDeleted(0, 0);
        }
    }

    public void updateAdherent(Adherent adherent) throws Exception {
        adherent.setNom(adherent.getNom().replace("\'", "\\'"));
        adherent.setPrenom(adherent.getPrenom().replace("\'", "\\'"));
        adherentMetierService.update(adherent);
        adherent.setNom(adherent.getNom().replace("\\'", "\'"));
        adherent.setPrenom(adherent.getPrenom().replace("\\'", "\'"));
        for (int i = 0; i < adherents.size(); i++) {
            if (adherents.get(i).getId() == adherent.getId()) {
                adherents.set(i, adherent);
                fireTableRowsUpdated(i, i);
                i = adherents.size();
            }
        }
        MetierServiceFactory.getBibliotheque().getTab().actualisation();
        MetierServiceFactory.getBibliotheque().getTab().setStatut("<html><body><font color='green'>L'adhérent a été modifié</font></body></html>", "adherent");
    }
    
    public void removeAdherent(Adherent adherent) throws Exception {
        adherentMetierService.remove(adherent);
        for (int i = 0; i < adherents.size(); i++) {
            if (adherents.get(i).getId() == adherent.getId()) {
                adherents.remove(i);
                fireTableRowsDeleted(i, i);
                i = adherents.size();
            }
        }
        MetierServiceFactory.getBibliotheque().getTab().actualisation();
        MetierServiceFactory.getBibliotheque().getTab().setStatut("<html><body><font color='green'>L'adhérent a été supprimé</font></body></html>", "adherent");
    }
    
}
