/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author drouinjonathan
 */
public class Emprunt {
    
    private int id;
    private Date dateEmprunt;
    private Livre livre;
    private Adherent adherent;

    Emprunt(Livre livre, Adherent adherent) {
        this.livre = livre;
        this.adherent = adherent;
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
     * @return the dateEmprunt
     */
    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    /**
     * @param dateEmprunt the dateEmprunt to set
     */
    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    /**
     * @return the livre
     */
    public Livre getLivre() {
        return livre;
    }

    /**
     * @param livre the livre to set
     */
    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    /**
     * @return the adherent
     */
    public Adherent getAdherent() {
        return adherent;
    }

    /**
     * @param adherent the adherent to set
     */
    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return this.livre.toString()+" a été emprunté par "+this.adherent.toString()+" le "+sdf.format(this.dateEmprunt);
    }
    
    @Override
    public boolean equals(Object o) {
        boolean retour = false;
        if(o instanceof Emprunt) {
            Emprunt e = (Emprunt) o;
            if(this.hashCode() == e.hashCode()) {
                retour = true;
            }
        }
        return retour;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.id;
        hash = 41 * hash + (this.dateEmprunt != null ? this.dateEmprunt.hashCode() : 0);
        hash = 41 * hash + (this.livre != null ? this.livre.hashCode() : 0);
        hash = 41 * hash + (this.adherent != null ? this.adherent.hashCode() : 0);
        return hash;
    }
    
    @Override
    public Emprunt clone() {
        Emprunt e = new Emprunt(livre, adherent);
        e.setDateEmprunt(dateEmprunt);
        e.setId(id);
        return e;
    }
    
}
