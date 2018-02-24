package uniroma2.it.dicii.celestialAstronomy.Test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uniroma2.it.dicii.celestialAstronomy.Control.FilamentController;
import uniroma2.it.dicii.celestialAstronomy.Model.Filament;
import uniroma2.it.dicii.celestialAstronomy.Repositories.FileRepository;
import uniroma2.it.dicii.celestialAstronomy.Repositories.Utility.UtenteDao;
import uniroma2.it.dicii.celestialAstronomy.View.CsvFileBean;
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
        String path1 = CsvFileBean.getAbsolutePath()+"testFilament";
        FileRepository.insertFilamentFile(path1,0,0);

        String path2 = CsvFileBean.getAbsolutePath()+"testPerimeter";
        FileRepository.insertPerimeterFile(path2,0,0);

        String path3 = CsvFileBean.getAbsolutePath()+"testSegment";
        FileRepository.insertSkeletonFile(path3,0,0);
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
