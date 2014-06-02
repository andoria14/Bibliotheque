/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Physique.Data;

import Metier.Adherent;
import Metier.Bibliothecaire;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gameone
 */
public class AdherentDataServiceBDDImpl implements AdherentDataService {

    private Statement st = null;

    private void getStatement() throws Exception {
        this.st = PhysiqueDataFactory.getStatement();
    }

    @Override
    public Adherent add(Adherent adherent) throws Exception {
        this.getStatement();
        String query = "INSERT INTO adherents SET nom = '" + adherent.getNom() + "', prenom = '" + adherent.getPrenom() + "'";
        this.st.executeUpdate(query);
        query = "SELECT * FROM adherents ORDER BY id DESC LIMIT 1";
        ResultSet rs = this.st.executeQuery(query);
        while (rs.next()) {
            adherent.setId(rs.getInt("id"));
        }
        this.st.close();
        return adherent;
    }

    @Override
    public void remove(Adherent adherent) throws Exception {
        this.getStatement();
        String query = "DELETE FROM adherents WHERE id = " + adherent.getId();
        this.st.executeUpdate(query);
        this.st.close();
    }

    @Override
    public void update(Adherent adherent) throws Exception {
        this.getStatement();
        String query = "UPDATE adherents SET nom = '" + adherent.getNom() + "', prenom = '" + adherent.getPrenom() + "' WHERE id = " + adherent.getId();
        this.st.executeUpdate(query);
        this.st.close();
    }

    @Override
    public Adherent getById(long id) throws Exception {
        this.getStatement();
        Adherent adherent = null;
        ResultSet rs = this.st.executeQuery("SELECT * FROM adherents WHERE id = " + id);
        while (rs.next()) {
            adherent = PhysiqueDataFactory.getAdherentMetierService().newAdherent(rs.getString("nom"), rs.getString("prenom"));
            adherent.setId(rs.getInt("id"));
        }
        this.st.close();
        return adherent;
    }

    @Override
    public List<Adherent> getByNom(String nom) throws Exception {
        List<Adherent> adherents = new ArrayList<>();
        this.getStatement();
        Adherent adherent = null;
        ResultSet rs = this.st.executeQuery("SELECT * FROM adherents WHERE nom = '" + nom + "' ");
        while (rs.next()) {
            adherent = PhysiqueDataFactory.getAdherentMetierService().newAdherent(rs.getString("nom"), rs.getString("prenom"));
            adherent.setId(rs.getInt("id"));
            adherents.add(adherent);
        }
        this.st.close();
        return adherents;
    }

    @Override
    public List<Adherent> getByPrenom(String prenom) throws Exception {
        List<Adherent> adherents = new ArrayList<>();
        this.getStatement();
        Adherent adherent = null;
        ResultSet rs = this.st.executeQuery("SELECT * FROM adherents WHERE prenom = '" + prenom + "' ");
        while (rs.next()) {
            adherent = PhysiqueDataFactory.getAdherentMetierService().newAdherent(rs.getString("nom"), rs.getString("prenom"));
            adherent.setId(rs.getInt("id"));
            adherents.add(adherent);
        }
        this.st.close();
        return adherents;
    }

    @Override
    public List<Adherent> getAll(int debut, int fin) throws Exception {
        List<Adherent> adherents = new ArrayList<>();
        this.getStatement();
        Adherent adherent = null;
        ResultSet rs = this.st.executeQuery("SELECT * FROM adherents ORDER BY nom ASC LIMIT " + debut + ", " + fin);
        while (rs.next()) {
            adherent = PhysiqueDataFactory.getAdherentMetierService().newAdherent(rs.getString("nom"), rs.getString("prenom"));
            adherent.setId(rs.getInt("id"));
            adherents.add(adherent);
        }
        this.st.close();
        return adherents;
    }

    @Override
    public int getRowCount() throws Exception {
        this.getStatement();
        int rowcount = 0;
        ResultSet rs = this.st.executeQuery("SELECT COUNT(*) AS total FROM adherents");
        while (rs.next()) {
            rowcount = rs.getInt("total");
        }
        this.st.close();
        return rowcount;
    }

    @Override
    public void removeAll() throws Exception {
        this.getStatement();
        String query = "DROP TABLE adherents";
        String query2 = "CREATE TABLE IF NOT EXISTS `adherents` ("
                + "  `id` int(11) NOT NULL auto_increment,"
                + "  `nom` varchar(50) collate latin1_general_ci NOT NULL,"
                + "  `prenom` varchar(50) collate latin1_general_ci NOT NULL,"
                + "  `login` varchar(50) collate latin1_general_ci NOT NULL,"
                + "  `mdp` varchar(50) collate latin1_general_ci NOT NULL,"
                + "  PRIMARY KEY  (`id`)"
                + ") ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=2 ;";
        String query3 = "INSERT INTO `adherents` (`id`, `nom`, `prenom`, `login`, `mdp`) VALUES (1, 'Drouin', 'Jonathan', 'andoria14', '299ea6e855b8c61517920350f32df7f3')";
        this.st.executeUpdate(query);
        this.st.executeUpdate(query2);
        this.st.executeUpdate(query3);
        this.st.close();
    }

    @Override
    public List<Bibliothecaire> getAllBiblio(int debut, int fin) throws Exception {
        List<Bibliothecaire> bibliothecaires = new ArrayList<>();
        this.getStatement();
        Bibliothecaire bibliothecaire = null;
        ResultSet rs = this.st.executeQuery("SELECT * FROM adherents ORDER BY nom ASC LIMIT " + debut + ", " + fin);
        while (rs.next()) {
            if(!rs.getString("login").equals("")) {
                bibliothecaire = PhysiqueDataFactory.getAdherentMetierService().newBibliothecaire(rs.getString("nom"), rs.getString("prenom"), rs.getString("login"), rs.getString("mdp"), true);
                bibliothecaire.setId(rs.getInt("id"));
                bibliothecaires.add(bibliothecaire);
            }
        }
        this.st.close();
        return bibliothecaires;
    }

    @Override
    public Bibliothecaire addBiblio(Bibliothecaire bibliothecaire) throws Exception {
        this.getStatement();
        String query = "INSERT INTO adherents (nom, prenom, login, mdp) VALUES ('" + bibliothecaire.getNom() + "','" + bibliothecaire.getPrenom() + "','"+bibliothecaire.getLogin()+"','"+bibliothecaire.getMotDePasse()+"')";
        this.st.executeUpdate(query);
        query = "SELECT * FROM adherents ORDER BY id DESC LIMIT 1";
        ResultSet rs = this.st.executeQuery(query);
        while (rs.next()) {
            bibliothecaire.setId(rs.getInt("id"));
        }
        this.st.close();
        return bibliothecaire;
    }

    @Override
    public void updateBiblio(Bibliothecaire bibliothecaire) throws Exception {
        this.getStatement();
        String query = "UPDATE adherents SET nom = '" + bibliothecaire.getNom() + "', prenom = '" + bibliothecaire.getPrenom() + "', login = '"+bibliothecaire.getLogin()+"', mdp = '"+bibliothecaire.getMotDePasse()+"' WHERE id = " + bibliothecaire.getId();
        this.st.executeUpdate(query);
        this.st.close();
    }

    @Override
    public Bibliothecaire getBiblioById(long id) throws Exception {
        this.getStatement();
        Bibliothecaire bibliothecaire = null;
        ResultSet rs = this.st.executeQuery("SELECT * FROM adherents WHERE id = " + id);
        while (rs.next()) {
            bibliothecaire = PhysiqueDataFactory.getAdherentMetierService().newBibliothecaire(rs.getString("nom"), rs.getString("prenom"), rs.getString("login"), rs.getString("mdp"), true);
            bibliothecaire.setId(rs.getInt("id"));
        }
        this.st.close();
        return bibliothecaire;
    }

    @Override
    public void removeBiblio(Bibliothecaire bibliothecaire) throws Exception {
        this.getStatement();
        String query = "UPDATE adherents SET login = '', mdp = '' WHERE id = "+bibliothecaire.getId();
        this.st.executeUpdate(query);
        this.st.close();
    }

    @Override
    public Bibliothecaire getByLogin(String login) throws Exception {
        this.getStatement();
        Bibliothecaire bibliothecaire = null;
        ResultSet rs = this.st.executeQuery("SELECT * FROM adherents WHERE login = '" + login + "' ");
        while (rs.next()) {
            bibliothecaire = PhysiqueDataFactory.getAdherentMetierService().newBibliothecaire(rs.getString("nom"), rs.getString("prenom"), login, rs.getString("mdp"), true);
            //Long dans une base de données
            //A voir avec le prof...
            bibliothecaire.setId(rs.getInt("id"));
        }
        this.st.close();
        return bibliothecaire;
    }

    @Override
    public List<Bibliothecaire> getBiblioByNom(String nom) throws Exception {
        List<Bibliothecaire> bibliothecaires = new ArrayList<Bibliothecaire>();
        this.getStatement();
        Bibliothecaire bibliothecaire = null;
        ResultSet rs = this.st.executeQuery("SELECT * FROM adherents WHERE nom = '" + nom + "' AND login != '' ");
        while (rs.next()) {
            bibliothecaire = PhysiqueDataFactory.getAdherentMetierService().newBibliothecaire(rs.getString("nom"), rs.getString("prenom"), rs.getString("login"), rs.getString("mdp"), true);
            bibliothecaire.setId(rs.getInt("id"));
            bibliothecaires.add(bibliothecaire);
        }
        this.st.close();
        return bibliothecaires;
    }

    @Override
    public List<Bibliothecaire> getBiblioByPrenom(String prenom) throws Exception {
        List<Bibliothecaire> bibliothecaires = new ArrayList<>();
        this.getStatement();
        Bibliothecaire bibliothecaire = null;
        ResultSet rs = this.st.executeQuery("SELECT * FROM adherents WHERE prenom = '" + prenom + "' AND login != '' ");
        while (rs.next()) {
            bibliothecaire = PhysiqueDataFactory.getAdherentMetierService().newBibliothecaire(rs.getString("nom"), rs.getString("prenom"), rs.getString("login"), rs.getString("mdp"), true);
            bibliothecaire.setId(rs.getInt("id"));
            bibliothecaires.add(bibliothecaire);
        }
        this.st.close();
        return bibliothecaires;
    }

    @Override
    public int getBiblioRowCount() throws Exception {
        this.getStatement();
        int rowcount = 0;
        ResultSet rs = this.st.executeQuery("SELECT COUNT(*) AS total FROM adherents WHERE login != ''");
        while (rs.next()) {
            rowcount = rs.getInt("total");
        }
        this.st.close();
        return rowcount;
    }
}
