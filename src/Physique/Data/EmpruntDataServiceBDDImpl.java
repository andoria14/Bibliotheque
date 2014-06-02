/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Physique.Data;

import Metier.Adherent;
import Metier.Emprunt;
import Metier.Livre;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Gameone
 */
public class EmpruntDataServiceBDDImpl implements EmpruntDataService {

    private Statement st = null;
    
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private LivreDataService livreDataService = PhysiqueDataFactory.getLivreDataService();
    private AdherentDataService adherentDataService = PhysiqueDataFactory.getAdherentDataService();

    private void getStatement() throws Exception {
        this.st = PhysiqueDataFactory.getStatement();
    }

    @Override
    public Emprunt add(Emprunt emprunt) throws Exception {
        this.getStatement();
        emprunt.setDateEmprunt(new Date());
        String queryEmprunt = "INSERT INTO emprunts (date, livre, adherent) VALUES ('" + sdf.format(emprunt.getDateEmprunt()) + "','" + emprunt.getLivre().getId() + "','" + emprunt.getAdherent().getId() + "')";
        this.st.executeUpdate(queryEmprunt);
        Livre livre = emprunt.getLivre();
        livre.setDisponibilite(false);
        livreDataService.update(livre);
        queryEmprunt = "SELECT * FROM emprunts ORDER BY id DESC LIMIT 1";
        ResultSet rs = this.st.executeQuery(queryEmprunt);
        while(rs.next()) {
            emprunt.setId(rs.getInt("id"));
        }
        this.st.close();
        return emprunt;
    }

    @Override
    public boolean remove(Emprunt emprunt) throws Exception {
        this.getStatement();
        String query = "DELETE FROM emprunts WHERE id = " + emprunt.getId();
        int retour = this.st.executeUpdate(query);
        this.st.close();
        if (retour > 0) {
            Livre livre = emprunt.getLivre();
            livre.setDisponibilite(true);
            livreDataService.update(livre);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void update(Emprunt emprunt) throws Exception {
        this.getStatement();
        String query = "UPDATE emprunts SET date = '" + sdf.format(emprunt.getDateEmprunt()) + "' WHERE id = " + emprunt.getId();
        this.st.executeUpdate(query);
        this.st.close();
    }

    @Override
    public Emprunt getById(int id) throws Exception {
        this.getStatement();
        Emprunt emprunt = null;
        ResultSet rs = this.st.executeQuery("SELECT * FROM emprunts WHERE id = " + id);
        while (rs.next()) {
            emprunt = PhysiqueDataFactory.getEmpruntMetierService().newEmprunt(livreDataService.getById(rs.getInt("livre")), adherentDataService.getById(rs.getInt("adherent")));
            emprunt.setId(rs.getInt("id"));
            emprunt.setDateEmprunt(sdf.parse(rs.getString("date")));
        }
        this.st.close();
        return emprunt;
    }

    @Override
    public Emprunt getByLivre(Livre livre) throws Exception {
        this.getStatement();
        Emprunt emprunt = null;
        ResultSet rs = this.st.executeQuery("SELECT * FROM emprunts WHERE livre = " + livre.getId());
        while (rs.next()) {
            emprunt = PhysiqueDataFactory.getEmpruntMetierService().newEmprunt(livreDataService.getById(rs.getInt("livre")), adherentDataService.getById(rs.getInt("adherent")));
            emprunt.setId(rs.getInt("id"));
            emprunt.setDateEmprunt(sdf.parse(rs.getString("date")));
        }
        this.st.close();
        return emprunt;
    }

    @Override
    public List<Emprunt> getByAdherent(Adherent adherent) throws Exception {
        List<Emprunt> emprunts = new ArrayList<Emprunt>();
        this.getStatement();
        Emprunt emprunt = null;
        ResultSet rs = this.st.executeQuery("SELECT * FROM emprunts WHERE adherent = " + adherent.getId());
        while (rs.next()) {
            emprunt = PhysiqueDataFactory.getEmpruntMetierService().newEmprunt(livreDataService.getById(rs.getInt("livre")), adherentDataService.getById(rs.getInt("adherent")));
            emprunt.setId(rs.getInt("id"));
            emprunt.setDateEmprunt(sdf.parse(rs.getString("date")));
            emprunts.add(emprunt);
        }
        this.st.close();
        return emprunts;
    }

    @Override
    public List<Emprunt> getByDate(Date date) throws Exception {
        List<Emprunt> emprunts = new ArrayList<Emprunt>();
        this.getStatement();
        Emprunt emprunt = null;
        ResultSet rs = this.st.executeQuery("SELECT * FROM emprunts WHERE date = '" + sdf.format(date) + "'");
        while (rs.next()) {
            emprunt = PhysiqueDataFactory.getEmpruntMetierService().newEmprunt(livreDataService.getById(rs.getInt("livre")), adherentDataService.getById(rs.getInt("adherent")));
            emprunt.setId(rs.getInt("id"));
            emprunt.setDateEmprunt(sdf.parse(rs.getString("date")));
            emprunts.add(emprunt);
        }
        this.st.close();
        return emprunts;
    }

    @Override
    public List<Emprunt> getAll(int debut, int fin) throws Exception {
        List<Emprunt> emprunts = new ArrayList<Emprunt>();
        this.getStatement();
        Emprunt emprunt = null;
        ResultSet rs = this.st.executeQuery("SELECT * FROM emprunts ORDER BY id ASC LIMIT " + debut + ", " + fin);
        while (rs.next()) {
            emprunt = PhysiqueDataFactory.getEmpruntMetierService().newEmprunt(livreDataService.getById(rs.getInt("livre")), adherentDataService.getById(rs.getInt("adherent")));
            emprunt.setId(rs.getInt("id"));
            emprunt.setDateEmprunt(sdf.parse(rs.getString("date")));
            emprunts.add(emprunt);
        }
        this.st.close();
        return emprunts;
    }

    @Override
    public int getRowCount() throws Exception {
        this.getStatement();
        int rowcount = 0;
        ResultSet rs = this.st.executeQuery("SELECT COUNT(*) AS total FROM emprunts");
        while (rs.next()) {
            rowcount = rs.getInt("total");
        }
        this.st.close();
        return rowcount;
    }
}
