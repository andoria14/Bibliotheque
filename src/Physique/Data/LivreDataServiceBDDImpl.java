/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Physique.Data;

import Metier.Livre;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author drouinjonathan
 */
public class LivreDataServiceBDDImpl implements LivreDataService {

    private Statement st = null;

    private void getStatement() throws Exception {
        this.st = PhysiqueDataFactory.getStatement();
    }

    @Override
    public Livre add(Livre livre) throws Exception {
        this.getStatement();
        int disponible = 0;
        if (livre.isDisponibilite()) {
            disponible = 1;
        }
        String query = "INSERT INTO livres (auteur, titre, disponible) VALUES ('" + livre.getAuteur() + "','" + livre.getTitre() + "'," + disponible + ")";
        this.st.executeUpdate(query);
        query = "SELECT * FROM livres ORDER BY id DESC LIMIT 1";
        ResultSet rs = this.st.executeQuery(query);
        while (rs.next()) {
            livre.setId(rs.getInt("id"));
        }
        this.st.close();
        return livre;
    }

    @Override
    public void remove(Livre livre) throws Exception {
        this.getStatement();
        String query = "DELETE FROM livres WHERE id = " + livre.getId();
        this.st.executeUpdate(query);
        this.st.close();
    }

    @Override
    public void update(Livre livre) throws Exception {
        this.getStatement();
        int disponible = 0;
        if (livre.isDisponibilite()) {
            disponible = 1;
        }
        String query = "UPDATE livres SET auteur = '" + livre.getAuteur() + "', titre = '" + livre.getTitre() + "', disponible = " + disponible + " WHERE id = " + livre.getId();
        this.st.executeUpdate(query);
        this.st.close();
    }

    @Override
    public List<Livre> getByAuteur(String auteur) throws Exception {
        List<Livre> livres = new ArrayList<>();
        this.getStatement();
        Livre livre = null;
        ResultSet rs = this.st.executeQuery("SELECT * FROM livres WHERE auteur = '" + auteur + "' ");
        while (rs.next()) {
            boolean disponible = false;
            if (rs.getInt("disponible") == 1) {
                disponible = true;
            }
            livre = PhysiqueDataFactory.getLivreMetierService().newLivre(rs.getString("auteur"), rs.getString("titre"));
            livre.setId(rs.getInt("id"));
            livre.setDisponibilite(disponible);
            livres.add(livre);
        }
        this.st.close();
        return livres;
    }

    @Override
    public List<Livre> getByTitre(String titre) throws Exception {
        List<Livre> livres = new ArrayList<>();
        this.getStatement();
        Livre livre = null;
        ResultSet rs = this.st.executeQuery("SELECT * FROM livres WHERE titre = '" + titre + "' ");
        while (rs.next()) {
            boolean disponible = false;
            if (rs.getInt("disponible") == 1) {
                disponible = true;
            }
            livre = PhysiqueDataFactory.getLivreMetierService().newLivre(rs.getString("auteur"), rs.getString("titre"));
            livre.setId(rs.getInt("id"));
            livre.setDisponibilite(disponible);
            livres.add(livre);
        }
        this.st.close();
        return livres;
    }

    @Override
    public List<Livre> getByMotsClefs(List<String> motsClefs) throws Exception {
        List<Livre> livres = new ArrayList<>();
        this.getStatement();
        Livre livre = null;
        for (int i = 0; i < motsClefs.size(); i++) {
            ResultSet rs = this.st.executeQuery("SELECT * FROM livres WHERE auteur LIKE '%" + motsClefs.get(i) + "%' OR titre LIKE '%" + motsClefs.get(i) + "%'");
            while (rs.next()) {
                boolean disponible = false;
                if (rs.getInt("disponible") == 1) {
                    disponible = true;
                }
                livre = PhysiqueDataFactory.getLivreMetierService().newLivre(rs.getString("auteur"), rs.getString("titre"));
                livre.setId(rs.getInt("id"));
                livre.setDisponibilite(disponible);
                boolean nouveau = true;
                for (int j = 0; j < livres.size(); j++) {
                    if (livres.get(j).getId() == livre.getId()) {
                        nouveau = false;
                        j = livres.size();
                    }
                }
                if (nouveau) {
                    livres.add(livre);
                }
            }
        }
        this.st.close();
        return livres;
    }

    @Override
    public List<Livre> getAll(int limitDeb, int limitFin) throws Exception {
        List<Livre> livres = new ArrayList<>();
        this.getStatement();
        Livre livre = null;
        ResultSet rs = this.st.executeQuery("SELECT * FROM livres ORDER BY id ASC LIMIT " + limitDeb + ", " + limitFin);
        while (rs.next()) {
            boolean disponible = false;
            if (rs.getInt("disponible") == 1) {
                disponible = true;
            }
            livre = PhysiqueDataFactory.getLivreMetierService().newLivre(rs.getString("auteur"), rs.getString("titre"));
            livre.setId(rs.getInt("id"));
            livre.setDisponibilite(disponible);
            livres.add(livre);
        }
        this.st.close();
        return livres;
    }

    @Override
    public Livre getById(int id) throws Exception {
        this.getStatement();
        Livre livre = null;
        ResultSet rs = this.st.executeQuery("SELECT * FROM livres WHERE id = " + id);
        while (rs.next()) {
            boolean disponible = false;
            if (rs.getInt("disponible") == 1) {
                disponible = true;
            }
            livre = PhysiqueDataFactory.getLivreMetierService().newLivre(rs.getString("auteur"), rs.getString("titre"));
            livre.setId(rs.getInt("id"));
            livre.setDisponibilite(disponible);
        }
        this.st.close();
        return livre;
    }

    @Override
    public int getRowCount(int limitDeb, int limitFin) throws Exception {
        this.getStatement();
        int rowcount = 0;
        ResultSet rs = this.st.executeQuery("SELECT COUNT(*) AS total FROM livres");
        while (rs.next()) {
            rowcount = rs.getInt("total");
        }
        this.st.close();
        return rowcount;
    }

    @Override
    public void removeAll() throws Exception {
        this.getStatement();
        String query = "DROP TABLE livres";
        String query2 = "CREATE TABLE IF NOT EXISTS `livres` ("
                + "  `id` int(255) NOT NULL auto_increment,"
                + "  `auteur` varchar(50) collate latin1_general_ci NOT NULL,"
                + "  `titre` varchar(100) collate latin1_general_ci NOT NULL,"
                + "  `disponible` int(5) NOT NULL,"
                + "  PRIMARY KEY  (`id`)"
                + ") ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=59 ;";
        this.st.executeUpdate(query);
        this.st.executeUpdate(query2);
        this.st.close();
    }
}
