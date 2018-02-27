package uniroma2.it.dicii.celestialAstronomy.Test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uniroma2.it.dicii.celestialAstronomy.Control.SegmentController;
import uniroma2.it.dicii.celestialAstronomy.Repositories.FileRepository;
import uniroma2.it.dicii.celestialAstronomy.Repositories.SegmentRepository;
import uniroma2.it.dicii.celestialAstronomy.Repositories.Utility.UtenteDao;
import uniroma2.it.dicii.celestialAstronomy.View.CsvFileBean;
import uniroma2.it.dicii.celestialAstronomy.View.SegmentBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class R11_DistanceVertexSegment {

    /*
    Insert new informations' filament to verify the correct execution
    Data inserted:
    * Segment id: 1111115 with following points:
    * (,)
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
     */
    @Test
    public void test(){
        SegmentBean segmentBean = new SegmentBean();
        segmentBean.setID("1111115");

        ArrayList<Double> distance = SegmentController.findDistanceOfSegment(segmentBean);
        Assert.assertEquals(String.valueOf(distance.get(0)), "3.1622776601683795");
        Assert.assertEquals(String.valueOf(distance.get(1)), "7.211102550927978");
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
            String delete1 = "delete from filamento " +
                            "where nome='none'";
            String delete2 = "delete from segmento "+
                    "where id = '1111111' or id = '1111112' or id = '1111113' or id = '1111114' or id = '1111115'";
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
