/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Livres;

import Metier.Livre;
import Metier.LivreService;
import Metier.MetierServiceFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Gameone
 */
public class JTableModelBooks extends AbstractTableModel {

    private List<Livre> livres = null;
    private LivreService livreMetierService = MetierServiceFactory.getLivreService();
    private int perpage;
    private int page;
    private String[] entetes = {"Identifiant", "Auteur", "Titre", "Disponible"};

    public JTableModelBooks(int limitDeb, int perpage, int page) throws Exception {
        super();
        this.perpage = perpage;
        this.page = page;
        livres = livreMetierService.getAll(limitDeb, perpage);
    }

    public JTableModelBooks(List<Livre> list, int perpage, int page) {
        super();
        this.livres = list;
        this.perpage = perpage;
        this.page = page;
    }

    public JTableModelBooks(Livre livre, int perpage, int page) {
        super();
        this.perpage = perpage;
        this.page = page;
        this.livres = new ArrayList<>();
        this.livres.add(livre);
    }

    @Override
    public int getRowCount() {
        return livres.size();
    }

    @Override
    public int getColumnCount() {
        return entetes.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return livres.get(rowIndex).getId();
            case 1:
                return livres.get(rowIndex).getAuteur();
            case 2:
                return livres.get(rowIndex).getTitre();
            case 3:
                return livres.get(rowIndex).isDisponibilite();
            default:
                return null;
        }
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 3:
                return Boolean.class;
            default:
                return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return false;
            default:
                return true;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (aValue != null) {
            try {
                Livre livre = livres.get(rowIndex);
                Livre copy = livre.clone();
                switch (columnIndex) {
                    case 1:
                        copy.setAuteur((String) aValue);
                        this.updateLivre(copy);
                        break;
                    case 2:
                        copy.setTitre((String) aValue);
                        this.updateLivre(copy);
                        break;
                    case 3:
                        copy.setDisponibilite((boolean) aValue);
                        this.updateLivre(copy);
                        break;
                }
                livres.set(rowIndex, copy);
            } catch (Exception ex) {
                Logger.getLogger(JTableModelBooks.class.getName()).log(Level.SEVERE, null, ex);
                MetierServiceFactory.getBibliotheque().getTab().setStatut("<html><body><font color='red'>" + ex.getMessage() + "</font></body></html>", "livre");
            }
        }
    }

    public void addLivre(Livre livre) throws Exception {
        livre.setTitre(livre.getTitre().replace("\'", "\\'"));
        livre.setAuteur(livre.getAuteur().replace("\'", "\\'"));
        Livre add = livreMetierService.add(livre);
        livre.setTitre(livre.getTitre().replace("\\'", "\'"));
        livre.setAuteur(livre.getAuteur().replace("\\'", "\'"));
        livres.add(add);
        fireTableRowsInserted(livres.size() - 1, livres.size() - 1);
        if (livres.size() > (perpage * page)) {
            livres.remove(0);
            fireTableRowsDeleted(0, 0);
        }
    }

    public void updateLivre(Livre livre) throws Exception {
        livre.setTitre(livre.getTitre().replace("\'", "\\'"));
        livre.setAuteur(livre.getAuteur().replace("\'", "\\'"));
        livreMetierService.update(livre);
        livre.setTitre(livre.getTitre().replace("\\'", "\'"));
        livre.setAuteur(livre.getAuteur().replace("\\'", "\'"));
        for (int i = 0; i < livres.size(); i++) {
            if (livres.get(i).getId() == livre.getId()) {
                livres.set(i, livre);
                fireTableRowsUpdated(i, i);
                i = livres.size();
            }
        }
        MetierServiceFactory.getBibliotheque().getTab().actualisation();
        MetierServiceFactory.getBibliotheque().getTab().setStatut("<html><body><font color='green'>Le livre a été modifié</font></body></html>", "livre");
    }

    public void removeLivre(Livre livre) throws Exception {
        livreMetierService.remove(livre);
        for (int i = 0; i < livres.size(); i++) {
            if (livres.get(i).getId() == livre.getId()) {
                livres.remove(i);
                fireTableRowsDeleted(i, i);
                i = livres.size();
            }
        }
        MetierServiceFactory.getBibliotheque().getTab().actualisation();
        MetierServiceFactory.getBibliotheque().getTab().setStatut("<html><body><font color='green'>Le livre a été supprimé</font></body></html>", "livre");
    }
}
