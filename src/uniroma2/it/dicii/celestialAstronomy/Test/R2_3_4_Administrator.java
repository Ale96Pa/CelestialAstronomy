package uniroma2.it.dicii.celestialAstronomy.Test;

//TODO: FINISh !!!

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import uniroma2.it.dicii.celestialAstronomy.Control.UserController;
import uniroma2.it.dicii.celestialAstronomy.Exception.WrongDataException;
import uniroma2.it.dicii.celestialAstronomy.Repositories.Utility.UtenteDao;
import uniroma2.it.dicii.celestialAstronomy.View.UserBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class R2_3_4_Administrator {

    /*
    Register an irregular user(password with less than 6 characters)
     */
    @Test
    public void irregularRegistration(){
        boolean esito = true;
        UserBean user = new UserBean();
        user.setUsername("morethan6");
        user.setPassword("wrong");
        try{
            esito = UserController.registerUser(user);
            if(!esito)
                throw new WrongDataException();
        } catch (WrongDataException e){
            e.printStackTrace();
        } finally {
            Assert.assertFalse(esito);
        }
    }

    /*
    Register a regular user (with at least 6 characters for username and password)
    In similar way (so not much significant) should be insert satellites and instruments
     */
    @Test
    public void regularRegistration(){
        boolean esito = false;
        UserBean user = new UserBean();
        user.setUsername("testRegularUsername");
        user.setPassword("testRegularPassword");
        try{
            esito = UserController.registerUser(user);
            if(!esito)
                throw new WrongDataException();
        } catch (WrongDataException e){
            e.printStackTrace();
        } finally {
            Assert.assertTrue(esito);
        }
    }

    /*
    Insert a file, then insert the same file with updated data, verifying the update
     */
    public void updateFile(){

    }

    /*
    Insert a perimeter file, but the filaments correlated isn't in DB, violating the foreign-key constraint
     */

    /*
    Delete the elements insert in database for this test
     */
    @After
    public void deleteElementInserted(){
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
            String delete = "delete from utenti " +
                            "where username='testRegularUsername'";
            statement.executeUpdate(delete);
            // Chiusura della connessione
            connection.close();
            statement.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
