package model;

import org.apache.tomcat.jdbc.pool.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimeZone;

/**
 * Classe del pool di connessioni per il database gamepark.
 *
 * @author Salvatore Sautariello
 * @version 1.0
 */
public class ConPool {
    /**
     * Sorgente di dati per il pool di connessioni
     */
    private static DataSource dataSource;

    /**
     * Restituisce una connessione dal pool. Se il pool non esiste, viene creato.
     * @return Una connessione al database gamepark.
     * @throws SQLException se si verifica un errore di accesso al database
     */
    public static Connection getConnection() throws SQLException{
        if (dataSource==null){
            PoolProperties p= new PoolProperties();
            p.setUrl("jdbc:mysql://localhost:3306/ecommerce_gamepark?serverTimezone=" + TimeZone.getDefault().getID());
            p.setDriverClassName("com.mysql.jdbc.Driver");
            p.setUsername("root");
            p.setPassword("root");
            p.setMaxActive(100);
            p.setInitialSize(10);
            p.setMinIdle(10);
            p.setRemoveAbandonedTimeout(60);
            p.setRemoveAbandoned(true);

            dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
            dataSource.setPoolProperties(p);
        }
        return dataSource.getConnection();
    }
}
