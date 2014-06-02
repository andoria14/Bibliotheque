/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import Physique.Data.EmpruntDataService;
import Physique.Data.LivreDataService;
import Physique.Data.PhysiqueDataFactory;
import java.util.List;

/**
 *
 * @author drouinjonathan
 */
public class LivreServiceImpl implements LivreService {

    private LivreDataService livrePhysiqueService = PhysiqueDataFactory.getLivreDataService();
    private EmpruntDataService empruntPhysiqueService = PhysiqueDataFactory.getEmpruntDataService();
    
    @Override
    public Livre newLivre(String auteur, String titre) {
        return new Livre(auteur, titre);
    }

    @Override
    public Livre add(Livre livre) throws Exception {
        if(livre == null) {
            throw new NullPointerException("Livre null !");
        }
        if(livre.getAuteur().equals("")) {
            throw new Exception("Veuillez renseigner le nom de l'auteur...");
        }
        if(livre.getTitre().equals("")) {
            throw new Exception("Veuillez renseigner le titre du livre...");
        }
        return livrePhysiqueService.add(livre);
    }

    @Override
    public void remove(Livre livre) throws Exception {
        if(livre == null) {
            throw new NullPointerException("Livre null !");
        }
        if(livre.getAuteur().equals("")) {
            throw new Exception("Veuillez renseigner le nom de l'auteur...");
        }
        if(livre.getTitre().equals("")) {
            throw new Exception("Veuillez renseigner le titre du livre...");
        }
        Livre byId = this.getById(livre.getId());
        if(!byId.equals(livre)) {
            throw new Exception("Ce livre n'existe pas !");
        }
        Emprunt byLivre = empruntPhysiqueService.getByLivre(livre);
        if(byLivre != null) {
            throw new Exception("On ne peut supprimer un livre emprunté !");
        }
        livrePhysiqueService.remove(livre);
    }

    @Override
    public void update(Livre livre) throws Exception {
        if(livre == null) {
            throw new NullPointerException("Livre null !");
        }
        if(livre.getAuteur().equals("")) {
            throw new Exception("Veuillez renseigner le nom de l'auteur...");
        }
        if(livre.getTitre().equals("")) {
            throw new Exception("Veuillez renseigner le titre du livre...");
        }
        Livre byId = this.getById(livre.getId());
        if(byId.getId() != (livre.getId())) {
            throw new Exception("Ce livre n'existe pas !");
        }
        Emprunt byLivre = empruntPhysiqueService.getByLivre(livre);
        if(byLivre != null) {
            throw new Exception("On ne peut modifier un livre emprunté !");
        }
        livrePhysiqueService.update(livre);
    }

    @Override
    public List<Livre> getByAuteur(String auteur) throws Exception {
        if(auteur.equals("")) {
            throw new Exception("Veuillez renseigner le nom de l'auteur...");
        }
        return livrePhysiqueService.getByAuteur(auteur);
    }

    @Override
    public List<Livre> getByTitre(String titre) throws Exception {
        if(titre.equals("")) {
            throw new Exception("Veuillez renseigner le titre du livre...");
        }
        return livrePhysiqueService.getByTitre(titre);
    }

    @Override
    public List<Livre> getByMotsClefs(List<String> motsClefs) throws Exception {
        if(motsClefs.isEmpty()) {
            throw new Exception("Veuillez préciser au moins un mot clé...");
        }
        return livrePhysiqueService.getByMotsClefs(motsClefs);
    }

    @Override
    public List<Livre> getAll(int limitDeb, int limitFin) throws Exception {
        if((limitDeb < 0) || (limitFin < 0)) {
            throw new Exception("Paramètres invalides !");
        }
        return livrePhysiqueService.getAll(limitDeb, limitFin);
    }

    @Override
    public Livre getById(int id) throws Exception {
        if(id < 0) {
            throw new Exception("L'identifiant n'est pas valide !");
        }
        return livrePhysiqueService.getById(id);
    }

    @Override
    public int getRowCount(int debut, int fin) throws Exception {
        if((debut < 0) || (fin < 0)) {
            throw new Exception("Paramètres incorrects !");
        }
        return livrePhysiqueService.getRowCount(debut, fin);
    }

    @Override
    public void removeAll() throws Exception {
        livrePhysiqueService.removeAll();
    }
    
}
