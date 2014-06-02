/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Physique.Data;

import Metier.AdherentService;
import Metier.AdherentServiceImpl;
import Metier.EmpruntService;
import Metier.EmpruntServiceImpl;
import Metier.LivreService;
import Metier.LivreServiceImpl;
import java.sql.Statement;

/**
 *
 * @author drouinjonathan
 */
public class PhysiqueDataFactory {
    
    private static LivreDataService livreDataService;
    private static EmpruntDataService empruntDataService;
    private static AdherentDataService adherentDataService;
    private static ConfigDataService configDataService;
    
    private static LivreService livreService;
    private static EmpruntService empruntService;
    private static AdherentService adherentService;
    
    private static AuthentificationService authentificationService;
    
    private PhysiqueDataFactory() {}
    
    public static LivreDataService getLivreDataService() {
        if(livreDataService == null) {
            livreDataService = new LivreDataServiceBDDImpl();
        }
        return livreDataService;
    }
    
    public static EmpruntDataService getEmpruntDataService() {
        if(empruntDataService == null) {
            empruntDataService = new EmpruntDataServiceBDDImpl();
        }
        return empruntDataService;
    }
    
    public static AdherentDataService getAdherentDataService() {
        if(adherentDataService == null) {
            adherentDataService = new AdherentDataServiceBDDImpl();
        }
        return adherentDataService;
    }
    
    public static LivreService getLivreMetierService() {
        if(livreService == null) {
            livreService = new LivreServiceImpl();
        }
        return livreService;
    }
    
    public static EmpruntService getEmpruntMetierService() {
        if(empruntService == null) {
            empruntService = new EmpruntServiceImpl();
        }
        return empruntService;
    }
    
    public static AdherentService getAdherentMetierService() {
        if(adherentService == null) {
            adherentService = new AdherentServiceImpl();
        }
        return adherentService;
    }
    
    public static AuthentificationService getAuthentificationService() {
        if(authentificationService == null) {
            authentificationService = new AuthentificationServiceBDDImpl();
        }
        return authentificationService;
    }
    
    public static ConfigDataService getConfigDataService() {
        if(configDataService == null) {
            configDataService = new ConfigServiceDataBDDImpl();
        }
        return configDataService;
    }
    
    public static Statement getStatement() throws Exception {
        String host = ConfigReader.getInstance("serveur.properties").getProperty("serveur.host");
        String table = ConfigReader.getInstance("serveur.properties").getProperty("serveur.base");
        String id = ConfigReader.getInstance("serveur.properties").getProperty("serveur.id");
        String pass = ConfigReader.getInstance("serveur.properties").getProperty("serveur.pass");
        
        JDBCConnectorService cnx = JDBCConnectorService.getInstance("mysql", host, table, "org.gjt.mm.mysql.Driver", id, pass);
        return cnx.getStatement();
    }
    
}
