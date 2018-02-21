package uniroma2.it.dicii.celestialAstronomy.Repositories;

import uniroma2.it.dicii.celestialAstronomy.Exception.AlreadyPresentException;
import uniroma2.it.dicii.celestialAstronomy.Repositories.Utility.UtenteDao;
import java.time.LocalDate;
import java.sql.*;

public class SatelliteRepository {

    /*
    Access to DB to insert a new satellite (with attributes Nome, DataInizio, DataFine (optional))
    {for REQ-FN-3}
     */
    public static boolean insertSatellite(String nome, LocalDate datainizio, LocalDate datafine) {
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

            String insert;
            // Scrittura dell'istruzione CRUD sql
            if(datafine.getYear()== 0000){
                insert= "insert into satellite(nome, datainizio) " +
                        " values ('" + nome + "' , '" + datainizio + "' )";
            } else{
                insert = "insert into satellite(nome, datainizio, datafine) " +
                         " values ('" + nome + "' , '" + datainizio + "' , '" + datafine + "' )";
            }

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

    /*
    Access to DB to insert a new agency (with attributes Nome)
    {for REQ-FN-3}
     */
    public static boolean insertAgencies(String agenzia) {
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
            String insert = "insert into agenzia(nome) " +
                            " values ('" + agenzia + "' )";
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

    /*
    Access to DB to insert a new couple satellite-agency (necessary for the relationship betweern the entities)
    {for REQ-FN-3}
     */
    public static boolean updateMissione(String satellite, String agenzia) {
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
            String insert = "insert into missione(satellite, agenzia) " +
                            " values ('" + satellite + "' , '" + agenzia +"' )";
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
