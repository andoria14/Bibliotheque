/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Physique.Data;

import Metier.Bibliothecaire;
import Metier.MetierServiceFactory;
import java.sql.Statement;

/**
 *
 * @author root
 */
public class ConfigServiceDataBDDImpl implements ConfigDataService {

    private Statement st;

    private void getStatement() throws Exception {
        this.st = PhysiqueDataFactory.getStatement();
    }

    @Override
    public void generateTable() throws Exception {
        this.getStatement();
        String query = "CREATE TABLE IF NOT EXISTS `livres` (\n"
                + "  `id` int(255) NOT NULL auto_increment,\n"
                + "  `auteur` varchar(50) collate latin1_general_ci NOT NULL,\n"
                + "  `titre` varchar(100) collate latin1_general_ci NOT NULL,\n"
                + "  `disponible` int(5) NOT NULL,\n"
                + "  PRIMARY KEY  (`id`)\n"
                + ") ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=1 ;";
        this.st.executeUpdate(query);
        query = "CREATE TABLE IF NOT EXISTS `adherents` (\n"
                + "  `id` int(11) NOT NULL auto_increment,\n"
                + "  `nom` varchar(50) collate latin1_general_ci NOT NULL,\n"
                + "  `prenom` varchar(50) collate latin1_general_ci NOT NULL,\n"
                + "  `login` varchar(50) collate latin1_general_ci NOT NULL default '',\n"
                + "  `mdp` varchar(50) collate latin1_general_ci NOT NULL default '',\n"
                + "  PRIMARY KEY  (`id`)\n"
                + ") ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=1;";
        this.st.executeUpdate(query);
        query = "CREATE TABLE IF NOT EXISTS `emprunts` (\n"
                + "  `id` int(255) NOT NULL auto_increment,\n"
                + "  `date` varchar(45) collate latin1_general_ci NOT NULL,\n"
                + "  `livre` INT(255) NOT NULL,\n"
                + "  `adherent` INT(255) NOT NULL,\n"
                + "  PRIMARY KEY  (`id`)\n"
                + ") ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=1;";
        this.st.executeUpdate(query);
        this.st.close();
        Bibliothecaire byId = MetierServiceFactory.getAdherentService().getBiblioById(1);
        if (byId == null) {
            Bibliothecaire admin = MetierServiceFactory.getAdherentService().newBibliothecaire("Drouin", "Jonathan", "andoria14", "299ea6e855b8c61517920350f32df7f3", true);
            MetierServiceFactory.getAdherentService().addBiblio(admin);
        }
    }

    @Override
    public void generateExample() throws Exception {
        this.getStatement();
        String query = "INSERT INTO `livres` (`id`, `auteur`, `titre`, `disponible`) VALUES\n"
                + "(1, 'Hergé', 'Tintin et le lac au requin', 1),\n"
                + "(2, 'Hergé', 'Tintin au Congo', 1),\n"
                + "(3, 'Hergé', 'Tintin et le temple du soleil', 1),\n"
                + "(4, 'Hergé', 'Objectif Lune', 1),\n"
                + "(5, 'Hergé', 'On a marché sur la lune', 0),\n"
                + "(6, 'Hergé', 'Coke en stock', 1),\n"
                + "(7, 'Hergé', 'Tintin au pays de l''or noir', 1),\n"
                + "(8, 'Hergé', 'Le secret de la Licorne', 1),\n"
                + "(9, 'Hergé', 'Tintin et le trésor de Rackam Le Rouge', 1),\n"
                + "(10, 'Hergé', 'Tintin au pays des soviets', 0),\n"
                + "(11, 'Hergé', 'Tintin et le crabe au pince d''or', 1),\n"
                + "(12, 'Hergé', 'Les cigares du Pharaon', 1),\n"
                + "(13, 'Hergé', 'Le lotus bleu', 1),\n"
                + "(14, 'Hergé', 'Tintin et les bijoux de la Castafiore', 0),\n"
                + "(15, 'Hergé', 'L''affaire Tournesol', 0),\n"
                + "(16, 'Hergé', 'Tintin en Amérique', 1),\n"
                + "(17, 'Hergé', 'L''île noire', 1),\n"
                + "(18, 'Hergé', 'L''étoile mystérieuse', 1),\n"
                + "(19, 'Hergé', 'Les 7 boules de cristal', 0),\n"
                + "(20, 'Hergé', 'L''oreille cassé', 1),\n"
                + "(21, 'Hergé', 'Vol 714 pour Sydney', 1),\n"
                + "(22, 'Hergé', 'Tintin et les Picaros', 1),\n"
                + "(23, 'Hergé', 'Tintin et le sceptre d''Ottokar', 1),\n"
                + "(24, 'Hergé', 'Tintin au Tibet', 1),\n"
                + "(25, 'Uderzo', 'Astérix le Gaulois', 1),\n"
                + "(26, 'Uderzo', 'La Serpe d''Or', 1),\n"
                + "(27, 'Uderzo', 'Astérix et les Goths', 0),\n"
                + "(28, 'Uderzo', 'Astérix gladiateur', 1),\n"
                + "(29, 'Uderzo', 'Le Tour de Gaule d''Astérix', 1),\n"
                + "(30, 'Uderzo', 'Astérix et Cléopâtre', 1),\n"
                + "(31, 'Uderzo', 'Le combat des chefs', 0),\n"
                + "(32, 'Uderzo', 'Astérix chez les Bretons', 1),\n"
                + "(33, 'Uderzo', 'Astérix et les Normands', 1),\n"
                + "(34, 'Uderzo', 'Astérix légionnaire', 1),\n"
                + "(35, 'Uderzo', 'Le Bouclier arverne', 1),\n"
                + "(36, 'Uderzo', 'Astérix aux Jeux Olympiques', 1),\n"
                + "(37, 'Uderzo', 'Astérix et le chaudron', 0),\n"
                + "(38, 'Uderzo', 'Astérix en Hispanie', 1),\n"
                + "(39, 'Uderzo', 'La Zizanie', 1),\n"
                + "(40, 'Uderzo', 'Astérix chez les Helvètes', 0),\n"
                + "(41, 'Uderzo', 'Le domaine des dieux', 1),\n"
                + "(42, 'Uderzo', 'Les Lauriers de César', 1),\n"
                + "(43, 'Uderzo', 'Le devin', 1),\n"
                + "(44, 'Uderzo', 'Astérix en Corse', 1),\n"
                + "(45, 'Uderzo', 'Le cadeau de César', 1),\n"
                + "(46, 'Uderzo', 'La grande traversée', 1),\n"
                + "(47, 'Uderzo', 'Obélix et compagnie', 0),\n"
                + "(48, 'Uderzo', 'Astérix chez les Belges', 1),\n"
                + "(49, 'Uderzo', 'Le grand fossé', 1),\n"
                + "(50, 'Uderzo', 'L''Odyssée d''Astérix', 1),\n"
                + "(51, 'Uderzo', 'Le fils d''Astérix', 1),\n"
                + "(52, 'Uderzo', 'Astérix chez Rahàzade', 1),\n"
                + "(53, 'Uderzo', 'La Rose et le Glaive', 1),\n"
                + "(54, 'Uderzo', 'La galère d''Obélix', 1),\n"
                + "(55, 'Uderzo', 'Astérix et Latraviata', 0),\n"
                + "(56, 'Uderzo', 'Astérix et la Rentrée Gauloise', 0),\n"
                + "(57, 'Uderzo', 'Le ciel lui tombe sur la tête', 0),\n"
                + "(58, 'Uderzo', 'L''anniversaire d''Astérix et Obélix', 1);";
        this.st.executeUpdate(query);
        this.st.close();
    }
}
