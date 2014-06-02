/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Physique.Data;

import Metier.AdherentService;
import Metier.Bibliothecaire;
import Metier.MetierServiceFactory;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author drouinjonathan
 */
public class AuthentificationServiceBDDImpl implements AuthentificationService {

    private long id;
    
    private Statement st = null;
    
    private AdherentService adherentMetierService = MetierServiceFactory.getAdherentService();

    private void getStatement() throws Exception {
        this.st = PhysiqueDataFactory.getStatement();
    }

    @Override
    public boolean authentifier(String login, String mdp) throws Exception {
        boolean retour = false;
        this.getStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM adherents WHERE login = '" + login + "'");
        while (rs.next()) {
            Bibliothecaire bibliothecaire = adherentMetierService.newBibliothecaire(rs.getString("nom"), rs.getString("prenom"), login, mdp, false);
            if (rs.getString("login").equalsIgnoreCase(login)) {
                System.out.println(bibliothecaire.getMotDePasse());
                System.out.println(rs.getString("mdp"));
                if (bibliothecaire.isValid(rs.getString("mdp"))) {
                    retour = true;
                    this.id = rs.getInt("id");
                }
            }
        }
        return retour;
    }

    @Override
    public long getId() throws Exception {
        return this.id;
    }
}
