package uniroma2.it.dicii.celestialAstronomy.Repositories;

import uniroma2.it.dicii.celestialAstronomy.Exception.AlreadyPresentException;
import uniroma2.it.dicii.celestialAstronomy.Model.Utente;
import uniroma2.it.dicii.celestialAstronomy.Repositories.Utility.UtenteDao;
import java.sql.*;

public class UserRepository {

    /*
    Access to DB to find a user by it's userID and password
    @Parameter: userid, password
    @Return: Utente
     */
    public static Utente findUser(String userid, String pw){
        Utente userLogin = null;
        Connection connection;
        Statement statement;
        ResultSet result;

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

            // Ricerca dell'utente in base a userid e password
            String query =  "SELECT * " +
                            " FROM utenti " +
                            " WHERE userid = '" + userid + "' and password = '" + pw + "' ";
            result = statement.executeQuery(query);

            while (result.next()) {
                String nome = result.getString("nome");
                String cognome = result.getString("cognome");
                String user = result.getString("userid");
                String pass = result.getString("password");
                String mail = result.getString("email");
                boolean admin = result.getBoolean("isadmin");

                userLogin = new Utente(nome, cognome, user, pass, mail, admin);
            }
            // Chiusura della connessione
            connection.close();
            statement.close();
            result.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return userLogin;
    }

    /*
    Access to DB to insert (crud operation) a new user in database
    @Parameter: features of the user to insert (name, surname, userid, password, email, administrator)
    @Return: a boolean that is the outcome of the operation
     */
    public static boolean insertUser(String nome, String cognome, String userid, String pw, String email, boolean admin) {
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
            String insert = "insert into utenti(nome, cognome, userid, password, email, isadmin) " +
                            " values ('" + nome + "' , '" + cognome + "' , '" + userid + "' , '" + pw + "' , '" + email+
                            "' , '" + admin + "' )";
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

