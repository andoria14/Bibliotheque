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
public interface LivreService {
    
    public Livre newLivre(String auteur, String titre);
    
    public Livre add(Livre livre) throws Exception;
    
    public void remove(Livre livre) throws Exception;
    
    public void update(Livre livre) throws Exception;
    
    public List<Livre> getByAuteur(String auteur) throws Exception;
    
    public List<Livre> getByTitre(String titre) throws Exception;
    
    public List<Livre> getByMotsClefs(List<String> motsClefs) throws Exception;
    
    public List<Livre> getAll(int limitDeb, int limitFin) throws Exception;
    
    public Livre getById(int id) throws Exception;
    
    public int getRowCount(int debut, int fin) throws Exception;
    
    public void removeAll() throws Exception;
    
}
