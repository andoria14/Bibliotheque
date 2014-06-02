/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import Physique.Data.EmpruntDataService;
import java.util.Date;
import java.util.List;

/**
 *
 * @author drouinjonathan
 */
public class EmpruntServiceImpl implements EmpruntService {

    private EmpruntDataService empruntPhysiqueService = Physique.Data.PhysiqueDataFactory.getEmpruntDataService();
    
    @Override
    public Emprunt newEmprunt(Livre livre, Adherent adherent) {
        return new Emprunt(livre, adherent);
    }

    @Override
    public Emprunt add(Emprunt emprunt) throws Exception {
        if(emprunt == null) {
            throw new NullPointerException("Emprunt null !");
        }
        if(emprunt.getDateEmprunt() == null) {
            emprunt.setDateEmprunt(new Date());
        }
        return empruntPhysiqueService.add(emprunt);
    }

    @Override
    public boolean remove(Emprunt emprunt) throws Exception {
        if(emprunt == null) {
            throw new NullPointerException("Emprunt null !");
        }
        return empruntPhysiqueService.remove(emprunt);
    }

    @Override
    public void update(Emprunt emprunt) throws Exception {
        if(emprunt == null) {
            throw new NullPointerException("Emprunt null !");
        }
        if(emprunt.getAdherent() == null) {
            throw new NullPointerException("Adhérent null !");
        }
        if(emprunt.getLivre() == null) {
            throw new NullPointerException("Livre null !");
        }
        Emprunt byId = this.getById(emprunt.getId());
        if(byId != null) {
            throw new Exception("L'emprunt spécifié n'a pas été trouvé !");
        }
        empruntPhysiqueService.update(emprunt);
    }

    @Override
    public Emprunt getById(int id) throws Exception {
        if(id < 0) {
            throw new Exception("L'identifiant n'est pas valide...");
        }
        return empruntPhysiqueService.getById(id);
    }

    @Override
    public Emprunt getByLivre(Livre livre) throws Exception {
        if(livre == null) {
            throw new NullPointerException("Livre null !");
        }
        if(livre.getAuteur().equals("")) {
            throw new Exception("Veuillez renseigner l'auteur de ce livre...");
        }
        if(livre.getTitre().equals("")) {
            throw new Exception("Veuillez renseigner le titre de ce livre...");
        }
        return empruntPhysiqueService.getByLivre(livre);
    }

    @Override
    public List<Emprunt> getByAdherent(Adherent adherent) throws Exception {
        if(adherent == null) {
            throw new NullPointerException("Adhérent null !");
        }
        if(adherent.getNom().equals("")) {
            throw new Exception("Veuillez renseigner le nom de l'adhérent !");
        }
        if(adherent.getPrenom().equals("")) {
            throw new Exception("Veuillez renseigner le prénom de l'adhérent !");
        }
        return empruntPhysiqueService.getByAdherent(adherent);
    }

    @Override
    public List<Emprunt> getByDate(Date date) throws Exception {
        if(date == null) {
            throw new NullPointerException("Date null !");
        }
        return empruntPhysiqueService.getByDate(date);
    }

    @Override
    public List<Emprunt> getAll(int debut, int fin) throws Exception {
        if((debut < 0) || (fin < 0)) {
            throw new Exception("Paramètres invalides !");
        }
        return empruntPhysiqueService.getAll(debut, fin);
    }
    
    @Override
    public int getRowCount() throws Exception {
        return empruntPhysiqueService.getRowCount();
    }
    
    
}
