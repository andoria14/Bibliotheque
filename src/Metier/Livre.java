/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;


/**
 *
 * @author drouinjonathan
 */
public class Livre{
    
    private int id;
    private String auteur;
    private String titre;
    private boolean disponibilite;
    
    Livre(String auteur, String titre) {
        this.auteur = auteur;
        this.titre = titre;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the auteur
     */
    public String getAuteur() {
        return auteur;
    }

    /**
     * @param auteur the auteur to set
     */
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    /**
     * @return the titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * @param titre the titre to set
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * @return the disponibilite
     */
    public boolean isDisponibilite() {
        return disponibilite;
    }

    /**
     * @param disponibilite the disponibilite to set
     */
    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }
    
    @Override
    public String toString() {
        return this.titre + " de " + this.auteur;
    }
    
    @Override
    public boolean equals(Object o) {
        boolean retour = false;
        if(o instanceof Livre) {
            Livre l = (Livre) o;
            if(this.hashCode() == l.hashCode()) {
                retour = true;
            }
        }
        return retour;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id;
        hash = 37 * hash + (this.auteur != null ? this.auteur.hashCode() : 0);
        hash = 37 * hash + (this.titre != null ? this.titre.hashCode() : 0);
        hash = 37 * hash + (this.disponibilite ? 1 : 0);
        return hash;
    }
    
    @Override
    public Livre clone() {
        Livre l =  new Livre(auteur, titre);
        l.setDisponibilite(disponibilite);
        l.setId(id);
        return l;
    }
}
