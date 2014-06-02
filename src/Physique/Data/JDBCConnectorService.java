package Physique.Data;



import java.sql.*;

public class JDBCConnectorService {

    private String dbDriver;
    private String dbURL;
    private String dbUser;
    private String dbPasswd;
    private Connection cnx;
    private Statement st;
    private boolean connected;

    public static JDBCConnectorService getInstance(String base, String host, String nomBase, String dbDriver, String dbUser, String dbPasswd) throws Exception {
        JDBCConnectorService connector = new JDBCConnectorService(base, host, nomBase, dbDriver, dbUser, dbPasswd);
        connector.connect();
        return connector;
    }

    private JDBCConnectorService(String base, String host, String nomBase, String dbDriver, String dbUser, String dbPasswd) {
        this.dbDriver = dbDriver;
        this.dbUser = dbUser;
        this.dbPasswd = dbPasswd;
        this.dbURL = "jdbc:" + base + "://" + host + "/" + nomBase;
    }

    public Statement getStatement() throws Exception {
        if (!this.connected) {
            throw new Exception("Impossible de se connecter");
        }
        return this.st;
    }

    private void connect() throws Exception {
        System.out.println("Log: Tentative de connexion à : " + dbURL);
        try {
            Class.forName(dbDriver);
            this.cnx = DriverManager.getConnection(dbURL, dbUser, dbPasswd);
            this.st = this.cnx.createStatement();
            this.connected = true;
            System.out.println("Log: Connexion reussie.");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Log: Echec de la connexion.");
            //Logger.getLogger(JDBCConnectorService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
