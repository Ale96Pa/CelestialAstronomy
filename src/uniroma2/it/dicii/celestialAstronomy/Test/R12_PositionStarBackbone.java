package uniroma2.it.dicii.celestialAstronomy.Test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uniroma2.it.dicii.celestialAstronomy.Control.FilamentController;
import uniroma2.it.dicii.celestialAstronomy.Control.SegmentController;
import uniroma2.it.dicii.celestialAstronomy.Model.Star;
import uniroma2.it.dicii.celestialAstronomy.Repositories.FileRepository;
import uniroma2.it.dicii.celestialAstronomy.Repositories.Utility.UtenteDao;
import uniroma2.it.dicii.celestialAstronomy.View.CsvFileBean;
import uniroma2.it.dicii.celestialAstronomy.View.FilamentBean;
import uniroma2.it.dicii.celestialAstronomy.View.SegmentBean;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class R12_PositionStarBackbone {

    /*
    Insert new informations' filament to verify the correct execution
    [... metti dati da inserire ...]
     */
    @Before
    public void insertData(){
        String path1 = CsvFileBean.getAbsolutePath()+"testFilament";
        FileRepository.insertFilamentFile(path1,0,0);

        String path2 = CsvFileBean.getAbsolutePath()+"testPerimeter";
        FileRepository.insertPerimeterFile(path2,0,0);

        String path3 = CsvFileBean.getAbsolutePath()+"testSegment";
        FileRepository.insertSkeletonFile(path3,0,0);

        String path4 = CsvFileBean.getAbsolutePath()+"testStar";
        FileRepository.insertStarFile(path4, 0, 0, "testPerimeter");
    }

    /*
     */
    @Test
    public void test(){
        FilamentBean filamentBean = new FilamentBean();
        filamentBean.setIdOrName("123456789");
        filamentBean.setOrder("1");

        ArrayList<Star> stars = FilamentController.findStarsByDistanceFromBackbone(filamentBean, -1);
        Assert.assertEquals(String.valueOf(stars.get(0).getDistanceFromBackbone()), "2.23606797749979");
        Assert.assertEquals(String.valueOf(stars.get(1).getDistanceFromBackbone()), "5.656854249492381");
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
            String delete = "delete from filamento " +
                    "where nome = 'none'";
            String delete2 = "delete from segmento "+
                    "where id = '1111111' or id = '1111112' or id = '1111113' or id = '1111114' or id = '1111115'";
            String delete3 = "delete from stella " +
                    "where id = '1111111' or id= '1111112' or id = '1111113'";
            statement.executeUpdate(delete);
            statement.executeUpdate(delete2);
            statement.executeUpdate(delete3);

            // Chiusura della connessione
            connection.close();
            statement.close();
        } catch(ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }
    }
}
