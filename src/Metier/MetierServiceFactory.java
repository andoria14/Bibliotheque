/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

/**
 *
 * @author drouinjonathan
 */
public class MetierServiceFactory {
    
    private static LivreService livreService;
    private static AdherentService adherentService;
    private static EmpruntService empruntService;
    private static Bibliotheque bibliotheque;
    
    private MetierServiceFactory() {}
    
    public static LivreService getLivreService() {
        if(livreService == null) {
            livreService = new LivreServiceImpl();
        }
        return livreService;
    }
    
    public static AdherentService getAdherentService() {
        if(adherentService == null) {
            adherentService = new AdherentServiceImpl();
        }
        return adherentService;
    }
    
    public static EmpruntService getEmpruntService() {
        if(empruntService == null) {
            empruntService = new EmpruntServiceImpl();
        }
        return empruntService;
    }
    
    public static Bibliotheque getBibliotheque() {
        if(bibliotheque == null) {
            bibliotheque = new Bibliotheque();
        }
        return bibliotheque;
    }
    
}
