/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

/**
 *
 * @author drouinjonathan
 */
public class Adherent {
    
    private int id;
    private String nom;
    private String prenom;

    Adherent(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
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
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    @Override
    public String toString() {
        return this.prenom+" "+this.nom;
    }
    
    @Override
    public boolean equals(Object o) {
        boolean retour = false;
        if(o instanceof Adherent) {
            Adherent a = (Adherent) o;
            if(this.hashCode() == a.hashCode()) {
                retour = true;
            }
        }
        return retour;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id;
        hash = 37 * hash + (this.nom != null ? this.nom.hashCode() : 0);
        hash = 37 * hash + (this.prenom != null ? this.prenom.hashCode() : 0);
        return hash;
    }
    
    @Override
    public Adherent clone() {
        Adherent a = new Adherent(nom, prenom);
        a.setId(id);
        return a;
    }
    
}
