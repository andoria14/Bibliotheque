/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import Physique.Data.AdherentDataService;
import Physique.Data.EmpruntDataService;
import Physique.Data.PhysiqueDataFactory;
import java.util.List;

/**
 *
 * @author drouinjonathan
 */
public class AdherentServiceImpl implements AdherentService {

    private AdherentDataService adherentPhysiqueService = Physique.Data.PhysiqueDataFactory.getAdherentDataService();
    private EmpruntDataService empruntDataService = PhysiqueDataFactory.getEmpruntDataService();

    @Override
    public Adherent newAdherent(String nom, String prenom) {
        return new Adherent(nom, prenom);
    }

    @Override
    public Bibliothecaire newBibliothecaire(String nom, String prenom, String login, String mdp, boolean encode) throws Exception {
        return new Bibliothecaire(nom, prenom, login, mdp, encode);
    }

    @Override
    public Adherent add(Adherent adherent) throws Exception {
        if (adherent == null) {
            throw new NullPointerException("Adhérent null !");
        }
        if (adherent.getNom().equals("")) {
            throw new Exception("Veuillez renseigner le nom de l'adhérent !");
        }
        if (adherent.getPrenom().equals("")) {
            throw new Exception("Veuillez renseigner le prénom de l'adhérent !");
        }
        List<Adherent> byNom = this.getByNom(adherent.getNom());
        for (int i = 0; i < byNom.size(); i++) {
            if (byNom.get(i).getPrenom().equals(adherent.getPrenom())) {
                throw new Exception(adherent.toString() + " existe déjà !");
            }
        }
        return adherentPhysiqueService.add(adherent);
    }

    @Override
    public void remove(Adherent adherent) throws Exception {
        if (adherent == null) {
            throw new NullPointerException("Adhérent null !");
        }
        if (adherent.getNom().equals("")) {
            throw new Exception("Veuillez renseigner le nom de l'adhérent !");
        }
        if (adherent.getPrenom().equals("")) {
            throw new Exception("Veuillez renseigner le prénom de l'adhérent !");
        }
        if (adherent.getId() == 1) {
            throw new Exception("Cet adhérent ne peut pas être supprimé !");
        }
        if(!this.getBiblioById(adherent.getId()).getLogin().equals("")) {
            throw new Exception("Cet adhérent est un bibliothécaire !");
        }
        List<Emprunt> byAdherent = empruntDataService.getByAdherent(adherent);
        if (byAdherent != null) {
            if (byAdherent.size() > 0) {
                if(byAdherent.size() < 2 ) {
                    throw new Exception("Cet adhérent possède encore " + byAdherent.size() + " livre...");
                }else{
                    throw new Exception("Cet adhérent possède encore " + byAdherent.size() + " livres...");
                }
            }
        }
        Adherent byId = this.getById(adherent.getId());
        if (byId == null) {
            throw new Exception(adherent.toString() + " n'a pas été trouvé !");
        }
        adherentPhysiqueService.remove(adherent);
    }

    @Override
    public void update(Adherent adherent) throws Exception {
        if (adherent == null) {
            throw new NullPointerException("Adhérent null !");
        }
        if (adherent.getNom().equals("")) {
            throw new Exception("Veuillez renseigner le nom de l'adhérent !");
        }
        if (adherent.getPrenom().equals("")) {
            throw new Exception("Veuillez renseigner le prénom de l'adhérent !");
        }
        Adherent byId = this.getById(adherent.getId());
        if (byId == null) {
            throw new Exception("Cet adhérent n'existe pas !");
        }
        adherentPhysiqueService.update(adherent);
    }

    @Override
    public Adherent getById(long id) throws Exception {
        if (id < 1) {
            throw new Exception("L'identifiant n'est pas valide...");
        }
        return adherentPhysiqueService.getById(id);
    }

    @Override
    public Bibliothecaire getBiblioById(long id) throws Exception {
        if (id < 1) {
            throw new Exception("L'identifiant n'est pas valide...");
        }
        return adherentPhysiqueService.getBiblioById(id);
    }

    @Override
    public List<Adherent> getByNom(String nom) throws Exception {
        if (nom.equals("")) {
            throw new Exception("Veuillez préciser le nom de l'adhérent...");
        }
        nom = nom.replace("\'", "\\'");
        return adherentPhysiqueService.getByNom(nom);
    }

    @Override
    public List<Adherent> getByPrenom(String prenom) throws Exception {
        if (prenom.equals("")) {
            throw new Exception("Veuillez préciser le prénom de l'adhérent...");
        }
        prenom = prenom.replace("\'", "\\'");
        return adherentPhysiqueService.getByPrenom(prenom);
    }

    @Override
    public List<Adherent> getAll(int debut, int fin) throws Exception {
        return adherentPhysiqueService.getAll(debut, fin);
    }

    @Override
    public int getRowCount() throws Exception {
        return adherentPhysiqueService.getRowCount();
    }
    
    @Override
    public int getBiblioRowCount() throws Exception {
        return adherentPhysiqueService.getBiblioRowCount();
    }

    @Override
    public void removeAll() throws Exception {
        adherentPhysiqueService.removeAll();
    }

    @Override
    public List<Bibliothecaire> getAllBiblio(int debut, int fin) throws Exception {
        return adherentPhysiqueService.getAllBiblio(debut, fin);
    }

    @Override
    public Bibliothecaire addBiblio(Bibliothecaire bibliothecaire) throws Exception {
        if (bibliothecaire == null) {
            throw new NullPointerException("Bibliothecaire null !");
        }
        if (bibliothecaire.getNom().equals("")) {
            throw new Exception("Veuillez renseigner le nom du bibliothécaire !");
        }
        if (bibliothecaire.getPrenom().equals("")) {
            throw new Exception("Veuillez renseigner le prénom du bibliothécaire !");
        }
        boolean nouveau = true;
        List<Bibliothecaire> byNom = this.getBiblioByNom(bibliothecaire.getNom());
        for (int i = 0; i < byNom.size(); i++) {
            if (byNom.get(i).getPrenom().equals(bibliothecaire.getPrenom())) {
                if (!byNom.get(i).getLogin().equals("")) {
                    throw new Exception("Ce bibliothécaire existe déjà !");
                }
            }
        }
        Bibliothecaire byLogin = this.getByLogin(bibliothecaire.getLogin());
        if(byLogin != null) {
            throw new Exception("Ce login est déjà pris !");
        }
        List<Adherent> adByNom = this.getByNom(bibliothecaire.getNom());
        for(int i=0;i<adByNom.size();i++) {
            if(adByNom.get(i).getPrenom().equals(bibliothecaire.getPrenom())) {
                nouveau = false;
                bibliothecaire.setId(adByNom.get(i).getId());
                adherentPhysiqueService.updateBiblio(bibliothecaire);
            }
        }
        if(nouveau) {
            return adherentPhysiqueService.addBiblio(bibliothecaire);
        }else{
            return bibliothecaire;
        }
    }

    @Override
    public void updateBiblio(Bibliothecaire bibliothecaire) throws Exception {
        if (bibliothecaire == null) {
            throw new NullPointerException("Bibliothecaire null !");
        }
        if (bibliothecaire.getNom().equals("")) {
            throw new Exception("Veuillez renseigner le nom du bibliothécaire !");
        }
        if (bibliothecaire.getPrenom().equals("")) {
            throw new Exception("Veuillez renseigner le prénom du bibliothécaire!");
        }
        if((bibliothecaire.getId() != MetierServiceFactory.getBibliotheque().getBibliothecaireConnecte().getId()) && (!MetierServiceFactory.getBibliotheque().isSuperAdminConnected())) {
            throw new Exception("Vous n'êtes pas autorisé à modifier ce bibliothécaire !");
        }
        Bibliothecaire byLogin = this.getByLogin(bibliothecaire.getLogin());
        if(byLogin != null) {
            if(bibliothecaire.getId() != byLogin.getId()) {
                throw new Exception("Ce login est déjà pris !");
            }
        }
        Bibliothecaire byId = this.getBiblioById(bibliothecaire.getId());
        if (byId == null) {
            throw new Exception("Ce bibliothécaire n'existe pas !");
        }
        adherentPhysiqueService.updateBiblio(bibliothecaire);
    }

    @Override
    public void removeBiblio(Bibliothecaire bibliothecaire) throws Exception {
        if (bibliothecaire == null) {
            throw new NullPointerException("Bibliothecaire null !");
        }
        if (bibliothecaire.getNom().equals("")) {
            throw new Exception("Veuillez renseigner le nom du bibliothecaire !");
        }
        if (bibliothecaire.getPrenom().equals("")) {
            throw new Exception("Veuillez renseigner le prénom du bibliothecaire !");
        }
        if (bibliothecaire.getId() == 1) {
            throw new Exception("Ce bibliothecaire ne peut pas être supprimé !");
        }
        Bibliothecaire byId = adherentPhysiqueService.getBiblioById(bibliothecaire.getId());
        if (byId == null) {
            throw new Exception(bibliothecaire.toString() + " n'a pas été trouvé !");
        }
        adherentPhysiqueService.removeBiblio(bibliothecaire);
    }

    @Override
    public Bibliothecaire getByLogin(String login) throws Exception {
        if ((login == null) || (login.equals(""))) {
            throw new NullPointerException("Login null");
        }
        return adherentPhysiqueService.getByLogin(login);
    }

    @Override
    public List<Bibliothecaire> getBiblioByNom(String nom) throws Exception {
        if((nom == null) || (nom.equals(""))) {
            throw new NullPointerException("Nom null");
        }
        return adherentPhysiqueService.getBiblioByNom(nom);
    }

    @Override
    public List<Bibliothecaire> getBiblioByPrenom(String prenom) throws Exception {
        if((prenom == null) || (prenom.equals(""))) {
            throw new NullPointerException("Prénom null");
        }
        return adherentPhysiqueService.getBiblioByPrenom(prenom);
    }
}
