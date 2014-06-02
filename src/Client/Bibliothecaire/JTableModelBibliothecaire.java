/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Bibliothecaire;

import Metier.AdherentService;
import Metier.Bibliothecaire;
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
public class JTableModelBibliothecaire extends AbstractTableModel {
    
    private List<Bibliothecaire> bibliothecaires = null;
    private AdherentService adherentMetierService = MetierServiceFactory.getAdherentService();
    
    private int perpage;
    private int page;
    
    private String[] entetes = {"Identifiant", "Nom", "Prenom", "Login"};

    public JTableModelBibliothecaire(int limitDeb, int perpage, int page) throws Exception {
        super();
        this.perpage = perpage;
        this.page = page;
        bibliothecaires = adherentMetierService.getAllBiblio(limitDeb, perpage);
    }

    public JTableModelBibliothecaire(List<Bibliothecaire> list, int perpage, int page) {
        super();
        this.bibliothecaires = list;
        this.perpage = perpage;
        this.page = page;
    }

    public JTableModelBibliothecaire(Bibliothecaire bibliothecaire, int perpage, int page) {
        super();
        this.perpage = perpage;
        this.page = page;
        this.bibliothecaires = new ArrayList<>();
        this.bibliothecaires.add(bibliothecaire);
    }
    
    @Override
    public int getRowCount() {
        return bibliothecaires.size();
    }

    @Override
    public int getColumnCount() {
        return entetes.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return bibliothecaires.get(rowIndex).getId();
            case 1:
                return bibliothecaires.get(rowIndex).getNom();
            case 2:
                return bibliothecaires.get(rowIndex).getPrenom();
            case 3:
                return bibliothecaires.get(rowIndex).getLogin();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
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
                Bibliothecaire bibliothecaire = bibliothecaires.get(rowIndex);
                Bibliothecaire copy = bibliothecaire.clone();
                switch(columnIndex) {
                    case 1 :
                        copy.setNom((String)aValue);
                        this.updateBiblio(copy);
                        break;
                    case 2 :
                        copy.setPrenom((String)aValue);
                        this.updateBiblio(copy);
                        break;
                    case 3 :
                        copy.setLogin((String)aValue);
                        this.updateBiblio(copy);
                }
                bibliothecaires.set(rowIndex, copy);
            } catch (Exception ex) {
                Logger.getLogger(JTableModelBibliothecaire.class.getName()).log(Level.SEVERE, null, ex);
                MetierServiceFactory.getBibliotheque().getTab().setStatut("<html><body><font color='red'>"+ex.getMessage()+"</font></body></html>", "admin");
            }
        }
    }
    
    public void addBiblio(Bibliothecaire bibliothecaire) throws Exception{
        bibliothecaire.setNom(bibliothecaire.getNom().replace("\'", "\\'"));
        bibliothecaire.setPrenom(bibliothecaire.getPrenom().replace("\'", "\\'"));
        Bibliothecaire add = adherentMetierService.addBiblio(bibliothecaire);
        bibliothecaire.setNom(bibliothecaire.getNom().replace("\\'", "\'"));
        bibliothecaire.setPrenom(bibliothecaire.getPrenom().replace("\\'", "\'"));
        bibliothecaires.add(add);
        fireTableRowsInserted(bibliothecaires.size() - 1, bibliothecaires.size() - 1);
        if (bibliothecaires.size() > (perpage * page)) {
            bibliothecaires.remove(0);
            fireTableRowsDeleted(0, 0);
        }
    }

    public void updateBiblio(Bibliothecaire bibliothecaire) throws Exception {
        bibliothecaire.setNom(bibliothecaire.getNom().replace("\'", "\\'"));
        bibliothecaire.setPrenom(bibliothecaire.getPrenom().replace("\'", "\\'"));
        adherentMetierService.updateBiblio(bibliothecaire);
        bibliothecaire.setNom(bibliothecaire.getNom().replace("\\'", "\'"));
        bibliothecaire.setPrenom(bibliothecaire.getPrenom().replace("\\'", "\'"));
        for (int i = 0; i < bibliothecaires.size(); i++) {
            if (bibliothecaires.get(i).getId() == bibliothecaire.getId()) {
                bibliothecaires.set(i, bibliothecaire);
                fireTableRowsUpdated(i, i);
                i = bibliothecaires.size();
            }
        }
        MetierServiceFactory.getBibliotheque().getTab().actualisation();
        MetierServiceFactory.getBibliotheque().getTab().setStatut("<html><body><font color='green'>Le bibliothécaire a été modifié</font></body></html>", "admin");
    }
    
    public void removeBiblio(Bibliothecaire bibliothecaire) throws Exception {
        adherentMetierService.removeBiblio(bibliothecaire);
        for (int i = 0; i < bibliothecaires.size(); i++) {
            if (bibliothecaires.get(i).getId() == bibliothecaire.getId()) {
                bibliothecaires.remove(i);
                fireTableRowsDeleted(i, i);
                i = bibliothecaires.size();
            }
        }
        MetierServiceFactory.getBibliotheque().getTab().actualisation();
        MetierServiceFactory.getBibliotheque().getTab().setStatut("<html><body><font color='green'>Le bibliothécaire a été supprimé</font></body></html>", "admin");
    }
    
}
