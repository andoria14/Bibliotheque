/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Thread;

import Client.Accueil;
import Client.TableauDeBord;
import Metier.MetierServiceFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author drouinjonathan
 */
public class WaitAndLoad extends Thread {

    private Accueil accueil;
    
    public WaitAndLoad(Accueil accueil) {
        this.accueil = accueil;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(800);
        } catch (InterruptedException ex) {
            Logger.getLogger(WaitAndLoad.class.getName()).log(Level.SEVERE, null, ex);
        }
        MetierServiceFactory.getBibliotheque().getTab().setVisible(true);
        accueil.setVisible(false);
    }
}
