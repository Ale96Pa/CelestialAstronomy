package uniroma2.it.dicii.celestialAstronomy.Repositories;

import uniroma2.it.dicii.celestialAstronomy.Exception.AlreadyPresentException;
import uniroma2.it.dicii.celestialAstronomy.Repositories.Utility.UtenteDao;
import java.sql.*;

/**
 * In this repository there are all updates of data from user-input to database.
 * Here the access to database and the mapping from RELATIONAL to OBJECT ORIENTED concepts are managed (DAO).
 */

public class InstrumentRepository {

    /*
    Access to DB to insert a new instrument (with attributes Nome and Tipo)
    {for REQ-FN-3}
     */
    public static boolean insertInstrument(String nome, double banda) {
        boolean esito= false;
        Connection connection;
        Statement statement;

        try {
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
            String insert = "insert into strumento(nome, banda) " +
                            " values ('" + nome + "' , '" + banda + "' )";
            int updatedTuple = statement.executeUpdate(insert);
            if(updatedTuple == 0)
                throw new AlreadyPresentException();
            else
                esito = true;

            // Chiusura della connessione
            connection.close();
            statement.close();
        } catch (ClassNotFoundException | SQLException | AlreadyPresentException e) {
            e.printStackTrace();
        }
        return esito;
    }
}