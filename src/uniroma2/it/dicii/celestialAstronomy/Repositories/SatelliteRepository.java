package uniroma2.it.dicii.celestialAstronomy.Repositories;

import uniroma2.it.dicii.celestialAstronomy.Exception.AlreadyPresentException;
import uniroma2.it.dicii.celestialAstronomy.Repositories.Utility.UtenteDao;
import java.time.LocalDate;
import java.sql.*;

public class SatelliteRepository {

    /*
    Access to DB to insert a new satellite (with attributes Nome, Agenzia, DataInizio, DataFine (optional))
    {for REQ-FN-3}
     */
    public static boolean insertSatellite(String nome, String agenzia, LocalDate datainizio, LocalDate datafine) {
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
                insert= "insert into satellite(nome, agenzia, datainizio) " +
                        " values ('" + nome + "' , '" + agenzia + "' , '" + datainizio +"' )";
            } else{
                insert = "insert into satellite(nome, agenzia, datainizio, datafine) " +
                         " values ('" + nome + "' , '" + agenzia + "' , '" + datainizio + "' , '" + datafine + "' )";
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
}
