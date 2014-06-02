/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 *
 * @author drouinjonathan
 */
public class Bibliothecaire extends Adherent {

    private String login;
    private String mdp;

    Bibliothecaire(String nom, String prenom, String login, String mdp, boolean encode) {
        super(nom, prenom);
        this.login = login;
        this.mdp = mdp;
        if (!encode) {
            this.mdp = this.codeMD5(this.mdp);
        }
    }

    private String codeMD5(String mdp) {
        String code = "";
        byte[] b = null;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            b = md.digest(mdp.getBytes());
            for (int i = 0; i < b.length; i++) {
                int x = b[i];
                if (x < 0) {
                    x += 256;
                }

                if (x > 15) {
                    code += Integer.toHexString(x);
                } else {
                    code += "0" + Integer.toHexString(x);
                }
            }
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex.getMessage());
        }
        return code;
    }

    public boolean isValid(String mdp) {
        boolean retour = false;
        if (mdp.equals(this.mdp)) {
            retour = true;
        }
        return retour;
    }

    public String getMotDePasse() {
        return this.mdp;
    }
    
    public String getLogin() {
        return this.login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    @Override
    public Bibliothecaire clone() {
        Bibliothecaire b = new Bibliothecaire(super.getNom(), super.getPrenom(), login, mdp, true);
        b.setId(super.getId());
        return b;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.login);
        hash = 17 * hash + Objects.hashCode(this.mdp);
        return hash;
    }
    
    @Override
    public boolean equals(Object o) {
        boolean retour = false;
        if(o instanceof Bibliothecaire) {
            Bibliothecaire bibliothecaire = (Bibliothecaire) o;
            if(this.hashCode() == bibliothecaire.hashCode()) {
                retour = true;
            }
        }
        return retour;
    }
}
