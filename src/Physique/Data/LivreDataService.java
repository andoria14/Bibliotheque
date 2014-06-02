/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Physique.Data;

import Metier.*;
import java.util.List;

/**
 *
 * @author drouinjonathan
 */
public interface LivreDataService {
    
    public Livre add(Livre livre) throws Exception;
    
    public void remove(Livre livre) throws Exception;
    
    public void update(Livre livre) throws Exception;
    
    public List<Livre> getByAuteur(String auteur) throws Exception;
    
    public List<Livre> getByTitre(String titre) throws Exception;
    
    public List<Livre> getByMotsClefs(List<String> motsClefs) throws Exception;
    
    public List<Livre> getAll(int limitDeb, int limitFin) throws Exception;
    
    public Livre getById(int id) throws Exception;
    
    public int getRowCount(int limitDeb, int limitFin) throws Exception;
    
    public void removeAll() throws Exception;
    
}
