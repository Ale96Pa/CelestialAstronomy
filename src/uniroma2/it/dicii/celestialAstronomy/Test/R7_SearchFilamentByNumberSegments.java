package uniroma2.it.dicii.celestialAstronomy.Test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uniroma2.it.dicii.celestialAstronomy.Control.FilamentController;
import uniroma2.it.dicii.celestialAstronomy.Model.Filament;
import uniroma2.it.dicii.celestialAstronomy.Repositories.Utility.UtenteDao;
import uniroma2.it.dicii.celestialAstronomy.View.FilamentBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class R7_SearchFilamentByNumberSegments {

    /*
    Insert new informations' filament to verify the correct execution
    Data inserted:
    * Filament 123456789 with segments: 1111111, 1111112, 1111113
    * Filament 123456788 with segment: 1111114
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
            String insert0 = "INSERT INTO filamento(id, nome, flussototale, densita, temperatura, ellitticita, contrasto) " +
                    " VALUES ('123456789 ' , 'aa' , '0' , ' 0 ' , ' 0', '0', '0')";
            String insert1 = "INSERT INTO filamento(id, nome, flussototale, densita, temperatura, ellitticita, contrasto) " +
                    " VALUES ('123456788 ' , 'aa' , '0' , ' 0 ' , ' 0', '0', '0')";

            String insert2 = "INSERT INTO strutturagalattica(filamento, longitudine, latitudine, tipo)" +
                    "VALUES ('123456789', 0, 0, 'SEG') ";
            String insert3 = "INSERT INTO strutturagalattica(filamento, longitudine, latitudine, tipo)" +
                    "VALUES ('123456789', 1, 1, 'SEG') ";
            String insert4 = "INSERT INTO strutturagalattica(filamento, longitudine, latitudine, tipo)" +
                    "VALUES ('123456789', 3, 3, 'SEG') ";
            String insert5 = "INSERT INTO strutturagalattica(filamento, longitudine, latitudine, tipo)" +
                    "VALUES ('123456788', 2, 2, 'SEG') ";

            String insert6 = "INSERT INTO segmento(id, longitudine, latitudine,flusso, tipo, nprogressivo)" +
                    "VALUES ('1111111', 0, 0, 0, 'B', 1) ";
            String insert7 = "INSERT INTO segmento(id, longitudine, latitudine,flusso, tipo, nprogressivo)" +
                    "VALUES ('1111112', 1, 1, 0, 'B', 2 ) ";
            String insert8 = "INSERT INTO segmento(id, longitudine, latitudine,flusso, tipo, nprogressivo)" +
                    "VALUES ('1111113', 2, 2, 0, 'B', 2 ) ";
            String insert9 = "INSERT INTO segmento(id, longitudine, latitudine,flusso, tipo, nprogressivo)" +
                    "VALUES ('1111114', 3, 3, 0, 'B', 2 ) ";
            statement.executeUpdate(insert0);
            statement.executeUpdate(insert1);
            statement.executeUpdate(insert2);
            statement.executeUpdate(insert3);
            statement.executeUpdate(insert4);
            statement.executeUpdate(insert5);
            statement.executeUpdate(insert6);
            statement.executeUpdate(insert7);
            statement.executeUpdate(insert8);
            statement.executeUpdate(insert9);

            // Chiusura della connessione
            connection.close();
            statement.close();
        } catch(ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }
    }


    /*
    Find filaments by number of segments based on data just inserted
     */
    @Test
    public void test(){
        boolean esito = false;
        FilamentBean filamentBean = new FilamentBean();
        filamentBean.setMinNumOfSegment(3);
        filamentBean.setMaxNumOfSegment(7);
        ArrayList<Filament> result = FilamentController.findFilamentBySegments(filamentBean, -1);
        for (Filament aResult : result) {
            if (aResult.getID() == 123456789)
                esito = true;
            else if (aResult.getID() == 123456788)
                esito = false;
        }
        Assert.assertTrue(esito);
    }

    /*
    Verify the execution of Exception in case of wrong input: minimum number of segments = 2
     */
    @Test
    public void irregularTest(){
        boolean esito = false;
        FilamentBean filamentBean = new FilamentBean();
        filamentBean.setMinNumOfSegment(2);
        filamentBean.setMaxNumOfSegment(7);
        ArrayList<Filament> result = FilamentController.findFilamentBySegments(filamentBean, -1);
        for (Filament aResult : result) {
            if (aResult.getID() == 123456789)
                esito = true;
        }
        Assert.assertFalse(esito);
    }

    /*
    Delete the elements inserted for the test
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
            String delete0 = "delete from filamento where id='123456789' or id='123456788'";
            String delete1 = "delete from strutturagalattica where filamento = '123456789' or filamento = '123456788' ";
            String delete2 = "delete from segmento where id='1111111' or id = '1111112' or id='1111113' or id='1111114'";
            statement.executeUpdate(delete0);
            statement.executeUpdate(delete1);
            statement.executeUpdate(delete2);

            // Chiusura della connessione
            connection.close();
            statement.close();
        } catch(ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }
    }

}
