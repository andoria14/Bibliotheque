/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Observable;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class MAJ extends Observable implements Runnable {

    @Override
    public void run() {
        try {
            double version = 1;
            URL url = new URL("http", "jonathan.drouin.free.fr", "/maj/bibliotheque.txt");
            URLConnection connection = url.openConnection();
            InputStream input = connection.getInputStream();
            String fileName = url.getFile().substring(url.getFile().lastIndexOf('/') + 1);
            FileOutputStream writeFile = new FileOutputStream(fileName);
            byte[] buffer = new byte[1024];
            int read;

            while ((read = input.read(buffer)) > 0) {
                writeFile.write(buffer, 0, read);
            }
            writeFile.flush();
            writeFile.close();
            String lignelu = null;
            Scanner sc = new Scanner(new File("bibliotheque.txt"));
            while (sc.hasNextLine()) {
                lignelu = sc.nextLine();
            }
            double versiondl = Double.parseDouble(lignelu);
            if (version < versiondl) {
                this.setChanged();
                this.notifyObservers(versiondl);
            }
            sc.close();
            File file = new File("bibliotheque.txt");
            file.delete();
        } catch (IOException ex) {
            Logger.getLogger(MAJ.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
