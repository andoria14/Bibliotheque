/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Physique.Data;

import Metier.Adherent;
import Metier.Bibliothecaire;
import java.util.List;

/**
 *
 * @author Gameone
 */
public interface AdherentDataService {
    
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
    
    public void removeAll() throws Exception;
    
}
