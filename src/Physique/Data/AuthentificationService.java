/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Physique.Data;

/**
 *
 * @author drouinjonathan
 */
public interface AuthentificationService {

    public boolean authentifier(String login, String mdp) throws Exception;
    
    public long getId() throws Exception;
    
}
