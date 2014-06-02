/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Physique.Data;

import Metier.Adherent;
import Metier.Emprunt;
import Metier.Livre;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Gameone
 */
public interface EmpruntDataService {
    
    public Emprunt add(Emprunt emprunt) throws Exception;
    
    public boolean remove(Emprunt emprunt) throws Exception;
    
    public void update(Emprunt emprunt) throws Exception;
    
    public Emprunt getById(int id) throws Exception;
    
    public Emprunt getByLivre(Livre livre) throws Exception;
    
    public List<Emprunt> getByAdherent(Adherent adherent) throws Exception;
    
    public List<Emprunt> getByDate(Date date) throws Exception;
    
    public List<Emprunt> getAll(int debut, int fin) throws Exception;
    
    public int getRowCount() throws Exception;
    
}
