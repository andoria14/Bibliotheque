/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import java.util.List;

/**
 *
 * @author drouinjonathan
 */
public interface AdherentService {
    
    public Adherent newAdherent(String nom, String prenom);
    public Bibliothecaire newBibliothecaire(String nom, String prenom, String login, String mdp, boolean encode) throws Exception;
    
    public Adherent add(Adherent adherent) throws Exception;
    public Bibliothecaire addBiblio(Bibliothecaire bibliothecaire) throws Exception;
    
    public void remove(Adherent adherent) throws Exception;
    public void removeBiblio(Bibliothecaire bibliothecaire) throws Exception;
    
    public void update(Adherent adherent) throws Exception;
    public void updateBiblio(Bibliothecaire bibliothecaire) throws Exception;
    
    public Adherent getById(long id) throws Exception;
    public Bibliothecaire getBiblioById(long id) throws Exception;
    
    public List<Adherent> getByNom(String nom) throws Exception;
    public List<Bibliothecaire> getBiblioByNom(String nom) throws Exception;
    
    public List<Adherent> getByPrenom(String prenom) throws Exception;
    public List<Bibliothecaire> getBiblioByPrenom(String prenom) throws Exception;
    
    public Bibliothecaire getByLogin(String login) throws Exception;
    
    public List<Adherent> getAll(int debut, int fin) throws Exception;
    public List<Bibliothecaire> getAllBiblio(int debut, int fin) throws Exception;
    
    public int getRowCount() throws Exception;
    public int getBiblioRowCount() throws Exception;
    
    public void removeAll() throws Exception; //Non utilisée
    
}
