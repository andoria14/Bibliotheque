/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Client.Thread.MAJ;
import Client.Thread.WaitAndLoad;
import Metier.Bibliothecaire;
import Metier.Bibliotheque;
import Metier.MetierServiceFactory;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author drouinjonathan
 */
public class Accueil extends javax.swing.JDialog implements Observer {

    private Bibliotheque bibliotheque = MetierServiceFactory.getBibliotheque();
    private TableauDeBord tableauDeBord = null;

    /**
     * Creates new form Accueil
     */
    public Accueil(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        bibliotheque.addObserver(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButtonMAJ = new javax.swing.JButton();
        jButtonConfig = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPasswordFieldMdP = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldIdentifiant = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabelStatut = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Centre d'authentification");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Utopia", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Médiathèque");

        jButtonMAJ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/maj.png"))); // NOI18N
        jButtonMAJ.setToolTipText("Le logiciel est à jour...");
        jButtonMAJ.setEnabled(false);
        jButtonMAJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMAJActionPerformed(evt);
            }
        });

        jButtonConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/config.png"))); // NOI18N
        jButtonConfig.setToolTipText("Configuration du logiciel");
        jButtonConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfigActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonMAJ, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jButtonMAJ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonConfig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Veuillez vous identifier...");

        jPasswordFieldMdP.setToolTipText("Renseigner votre code d'accès et appuyer sur la touche Entrée");
        jPasswordFieldMdP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPasswordFieldMdPKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Mot de passe :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Login :");

        jTextFieldIdentifiant.setToolTipText("Renseigner votre identifiant");

        jLabel5.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Appuyer sur la touche Entrée pour vous authentifier...");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldIdentifiant)
                    .addComponent(jPasswordFieldMdP, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(140, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldIdentifiant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jPasswordFieldMdP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabelStatut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelStatut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelStatut, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPasswordFieldMdPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordFieldMdPKeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            try {
                char[] mdp = jPasswordFieldMdP.getPassword();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < mdp.length; i++) {
                    sb.append(mdp[i]);
                }
                bibliotheque.authentifier(jTextFieldIdentifiant.getText(), sb.toString());
            } catch (Exception ex) {
                Logger.getLogger(Accueil.class.getName()).log(Level.SEVERE, null, ex);
                jLabelStatut.setText("<html><body><font color='red'>"+ ex.getMessage() + "</font></body></html>");
            }
        }
    }//GEN-LAST:event_jPasswordFieldMdPKeyTyped

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        jTextFieldIdentifiant.requestFocus();
    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        /*MAJ maj = new MAJ();
        maj.addObserver(this);
        Thread th = new Thread(maj);
        th.start();*/
        
        File config = new File("serveur.properties");
        if(!config.exists()) {
            JOptionPane.showMessageDialog(this, "Le logiciel a détecté qu'il s'agit de votre première utilisation !\nIl est fortement conseillé de configurer les paramètres de connexion du logiciel et de générer les tables dont le logiciel a besoin...\nPour cela, cliquez sur le bouton Configuration en haut à gauche de l'accueil.\n   - Paramètres de connexion dans le menu Base de données.\n   - Génération des tables automatiques dans le menu Options");
        }
    }//GEN-LAST:event_formWindowOpened

    private void jButtonMAJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMAJActionPerformed
        try {
            // TODO add your handling code here:
            URI uri = URI.create("http://jonathan.drouin.free.fr");
            Desktop.getDesktop().browse(uri);
        } catch (IOException ex) {
            Logger.getLogger(Accueil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonMAJActionPerformed

    private void jButtonConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfigActionPerformed
        // TODO add your handling code here:
        Config config = new Config(null, true);
        config.setVisible(true);
    }//GEN-LAST:event_jButtonConfigActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Accueil dialog = new Accueil(new javax.swing.JFrame(), false);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConfig;
    private javax.swing.JButton jButtonMAJ;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelStatut;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPasswordField jPasswordFieldMdP;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldIdentifiant;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Bibliotheque) {
            if (arg instanceof Bibliothecaire) {
                jLabelStatut.setText("<html><body><font color='green'>Accès autorisé !</font></body></html>");
                tableauDeBord = new TableauDeBord();
                bibliotheque.setTab(tableauDeBord);
                WaitAndLoad waitAndLoad = new WaitAndLoad(this);
                waitAndLoad.start();
            } else if (arg == null) {
                if (tableauDeBord != null) {
                    tableauDeBord.dispose();
                } else {
                    jLabelStatut.setText("<html><body><font color='red'>Accès refusé !</font></body></html>");
                }
                if (!this.isVisible()) {
                    jLabelStatut.setText("");
                    jTextFieldIdentifiant.setText("");
                    jPasswordFieldMdP.setText("");
                    this.setVisible(true);
                }
            }
        } else if (o instanceof MAJ) {
            if (arg instanceof Double) {
                Double newversion = (Double) arg;
                jLabelStatut.setText("<html><body><font color='green'>Une mise à jour est disponible ! (Version "+newversion+")</font></body></html>");
                jButtonMAJ.setToolTipText("Une mise à jour est disponible !");
                jButtonMAJ.setEnabled(true);
            }
        }
    }
}
