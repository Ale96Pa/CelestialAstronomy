package uniroma2.it.dicii.celestialAstronomy.Test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import uniroma2.it.dicii.celestialAstronomy.Repositories.Utility.UtenteDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class R12_PositionStarBackbone {

    /*
    Insert new informations' filament to verify the correct execution
    [... metti dati da inserire ...]
     */
    @Before
    public void insertData(){
        Connection connection;
        Statement statement;

        try
        {
            // Caricamento del Driver
            String driver = UtenteDao.getDriverClassName();
            Class.forName(driver);
            // Creazione della Connessione
            String urlDB = UtenteDao.getDbUrl();
            String username = UtenteDao.getUSER();
            String password = UtenteDao.getPASS();
            connection = DriverManager.getConnection(urlDB, username, password);
            // Creazione dello Statement per le interrogazioni
            statement = connection.createStatement();

            // Scrittura dell'istruzione CRUD sql
            String insert1 = "INSERT INTO filamento(id, nome, flussototale, densita, temperatura, ellitticita, contrasto) " +
                    " VALUES ('123456789' , 'aaB' , '0' , ' 0 ' , ' 0', '2', '1.2')";
            String insert2 = "INSERT INTO filamento(id, nome, flussototale, densita, temperatura, ellitticita, contrasto) " +
                    " VALUES ('123456788' , 'aaC' , '0' , ' 0 ' , ' 0', '4', '1.3')";
            String insert3 = "INSERT INTO filamento(id, nome, flussototale, densita, temperatura, ellitticita, contrasto) " +
                    " VALUES ('123456787' , 'aaD' , '0' , ' 0 ' , ' 0', '6', '0.7')";
            String insert4 = "INSERT INTO filamento(id, nome, flussototale, densita, temperatura, ellitticita, contrasto) " +
                    " VALUES ('123456786' , 'aaE' , '0' , ' 0 ' , ' 0', '8', '2.3')";
            statement.executeUpdate(insert1);
            statement.executeUpdate(insert2);
            statement.executeUpdate(insert3);
            statement.executeUpdate(insert4);

            // Chiusura della connessione
            connection.close();
            statement.close();
        } catch(ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    Find centroide, extensione e number of segments of filament just inserted
     */
 //   @Test

    /*
    Delete the elements inserted for the testPerimeter
     */
    @After
    public void deleteData(){
        Connection connection;
        Statement statement;

        try
        {
            // Caricamento del Driver
            String driver = UtenteDao.getDriverClassName();
            Class.forName(driver);
            // Creazione della Connessione
            String urlDB = UtenteDao.getDbUrl();
            String username = UtenteDao.getUSER();
            String password = UtenteDao.getPASS();
            connection = DriverManager.getConnection(urlDB, username, password);
            // Creazione dello Statement per le interrogazioni
            statement = connection.createStatement();

            // Scrittura dell'istruzione CRUD sql
            String delete = "delete from filamento " +
                    "where id='123456789' OR id='123456788' or id='123456787' or id='123456786'";
            statement.executeUpdate(delete);

            // Chiusura della connessione
            connection.close();
            statement.close();
        } catch(ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }
    }
}
