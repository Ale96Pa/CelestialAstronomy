package uniroma2.it.dicii.celestialAstronomy.Test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import uniroma2.it.dicii.celestialAstronomy.Control.UserController;
import uniroma2.it.dicii.celestialAstronomy.Exception.WrongDataException;
import uniroma2.it.dicii.celestialAstronomy.Repositories.FileRepository;
import uniroma2.it.dicii.celestialAstronomy.Repositories.Utility.UtenteDao;
import uniroma2.it.dicii.celestialAstronomy.View.CsvFileBean;
import uniroma2.it.dicii.celestialAstronomy.View.UserBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class R2_3_4_Administrator {

    /*
    Try to register an irregular user(password with less than 6 characters)
     */
    @Test
    public synchronized void irregularRegistration(){
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
    public synchronized void regularRegistration(){
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
    @Test
    public void updateFile(){
        int control;
        FileRepository.insertFilamentFile(CsvFileBean.getAbsolutePath()+"testFilament",0,0);
        FileRepository.insertPerimeterFile(CsvFileBean.getAbsolutePath()+"test",0,0);
        control = FileRepository.insertPerimeterFile(CsvFileBean.getAbsolutePath()+"testUpdated",0,0);
        Assert.assertNotEquals(control, 0);
    }

    /*
    Insert a perimeter file, but the filaments correlated isn't in DB, violating the foreign-key constraint
     */
    @Test
    public void tryIrregularImport(){
        int control = 0;
        try{
            control = FileRepository.insertPerimeterFile(CsvFileBean.getAbsolutePath()+"testWrong",0 ,0);
            if(control != 3)
                throw new WrongDataException();
        } catch (WrongDataException e){
            e.printStackTrace();
        } finally {
            Assert.assertNotEquals(control, 3);
        }
    }

    /*
    Delete the elements insert in database for these tests
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
                            "where userid='testRegularUsername'";
            String delete2 = "delete from strutturagalattica " +
                             "where tipo='PER' and longitudine='0' ";
            String delete3 = "delete from filamento " +
                             "where nome ='none'";
            statement.executeUpdate(delete);
            statement.executeUpdate(delete2);
            statement.executeUpdate(delete3);
            // Chiusura della connessione
            connection.close();
            statement.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
