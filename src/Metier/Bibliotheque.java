/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import Client.TableauDeBord;
import Physique.Data.AuthentificationService;
import Physique.Data.PhysiqueDataFactory;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author drouinjonathan
 */
public class Bibliotheque extends Observable {

    private TableauDeBord tableauDeBord;
    private Bibliothecaire bibliothecaireConnecte = null;
    private final EmpruntService empruntMetierService = MetierServiceFactory.getEmpruntService();
    private final AdherentService adherentMetierService = MetierServiceFactory.getAdherentService();
    private final AuthentificationService authentificationPhysiqueService = PhysiqueDataFactory.getAuthentificationService();
    private boolean superadmin = false;

    public Emprunt emprunter(Livre livre, Adherent adherent) throws Exception {
        if (livre == null) {
            throw new NullPointerException("Livre null !");
        }
        if (adherent == null) {
            throw new NullPointerException("Adhérent null !");
        }
        if (!livre.isDisponibilite()) {
            throw new Exception("Le livre n'est pas disponible...");
        }
        List<Emprunt> byAdherent = empruntMetierService.getByAdherent(adherent);
        if (byAdherent.size() >= 3) {
            throw new Exception(adherent.toString() + " a déjà emprunté 3 livres !");
        }
        for (int i = 0; i < byAdherent.size(); i++) {
            if (byAdherent.get(i).getLivre().getTitre().equals(livre.getTitre())) {
                if(byAdherent.get(i).getLivre().getAuteur().equals(livre.getAuteur())) {
                    throw new Exception(adherent.toString() + " a déjà en sa possession un exemplaire de ce livre !");
                }
            }
        }
        return empruntMetierService.add(empruntMetierService.newEmprunt(livre, adherent));
    }

    public boolean rendre(Emprunt emprunt) throws Exception {
        boolean retour = false;
        if (emprunt.getLivre() == null) {
            throw new NullPointerException("Livre null !");
        }
        if (emprunt.getAdherent() == null) {
            throw new NullPointerException("Adhérent null !");
        }
        if (emprunt.getLivre().isDisponibilite()) {
            throw new Exception("Ce livre n'a pas été emprunté !");
        }
        List<Emprunt> listEmpruntByAdherent = empruntMetierService.getByAdherent(emprunt.getAdherent());
        boolean trouve = false;
        for (int i = 0; i < listEmpruntByAdherent.size(); i++) {
            if (listEmpruntByAdherent.get(i).getLivre().equals(emprunt.getLivre())) {
                trouve = true;
                i = listEmpruntByAdherent.size();
            }
        }
        if (!trouve) {
            throw new Exception("Cet adhérent n'a pas emprunté ce livre !");
        }
        emprunt.getLivre().setTitre(emprunt.getLivre().getTitre().replace("\'", "\\'"));
        emprunt.getLivre().setAuteur(emprunt.getLivre().getAuteur().replace("\'", "\\'"));
        retour = empruntMetierService.remove(emprunt);
        return retour;
    }

    public void authentifier(String login, String mdp) throws Exception {
        boolean authentifier = authentificationPhysiqueService.authentifier(login, mdp);
        if (authentifier) {
            System.out.println("" + authentificationPhysiqueService.getId());
            Adherent byId = adherentMetierService.getById(authentificationPhysiqueService.getId());
            this.bibliothecaireConnecte = adherentMetierService.newBibliothecaire(byId.getNom(), byId.getPrenom(), login, mdp, false);
            this.bibliothecaireConnecte.setId(byId.getId());
        }
        if(this.bibliothecaireConnecte != null) {
            if(this.bibliothecaireConnecte.getId() == 1) {
                superadmin = true;
            }else{
                superadmin = false;
            }
        }
        this.newEvent(this.bibliothecaireConnecte);
    }

    public void deconnecter() {
        this.bibliothecaireConnecte = null;
        this.newEvent(this.bibliothecaireConnecte);
    }

    public Bibliothecaire getBibliothecaireConnecte() {
        return this.bibliothecaireConnecte;
    }
    
    public void newEvent(Object arg) {
        this.setChanged();
        this.notifyObservers(arg);
    }
    
    public void setTab(TableauDeBord tableauDeBord) {
        this.tableauDeBord = tableauDeBord;
    }
    
    public TableauDeBord getTab() {
        return this.tableauDeBord;
    }
    
    public boolean isSuperAdminConnected() {
        return superadmin;
    }
}
