package uniroma2.it.dicii.celestialAstronomy.Test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uniroma2.it.dicii.celestialAstronomy.Control.FilamentController;
import uniroma2.it.dicii.celestialAstronomy.Model.Extension;
import uniroma2.it.dicii.celestialAstronomy.Model.GalaxyPosition;
import uniroma2.it.dicii.celestialAstronomy.Repositories.FileRepository;
import uniroma2.it.dicii.celestialAstronomy.Repositories.Utility.UtenteDao;
import uniroma2.it.dicii.celestialAstronomy.View.CsvFileBean;
import uniroma2.it.dicii.celestialAstronomy.View.FilamentBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class R5_InformationFilament {

    /*
    Insert new informations' filament to verify the correct execution
    Data inserted:
    * Filament with ID: 123456789;
    * Following points of perimeter: (11,5) (3,4) (2,5) (3,20)
    * Following segments: 1111111 and 1111112
    */
    @Before
    public void insertData() {
        String path1 = CsvFileBean.getAbsolutePath()+"testFilament";
        FileRepository.insertFilamentFile(path1,0,0);

        String path2 = CsvFileBean.getAbsolutePath()+"testPerimeter";
        FileRepository.insertPerimeterFile(path2,4,10);

        String path3 = CsvFileBean.getAbsolutePath()+"testSegment";
        FileRepository.insertSkeletonFile(path3,0,0);
    }


    /*
    Find centroide, extension and number of segments of filament just inserted
     */
    @Test
    public void test(){
        FilamentBean filamentBean = new FilamentBean();
        filamentBean.setIdOrName("123456789");
        // Test Centroide
        GalaxyPosition centroide = FilamentController.findCentroide(filamentBean);
        Assert.assertEquals(String.valueOf(centroide.getLongitude()), "4.75");
        Assert.assertEquals(String.valueOf(centroide.getLatitude()), "8.5");

        // Test Number of segment
        int numseg = FilamentController.findNumOfSegments(filamentBean);
        Assert.assertEquals(numseg, 3);

        // Test Extension
        Extension extension = FilamentController.findExtension(filamentBean);
        Assert.assertEquals(String.valueOf(extension.getLongitudinalExtension()), "9.0");
        Assert.assertEquals(String.valueOf(extension.getLatitudinalExtension()), "16.0" );
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
            String delete0 = "delete from filamento where id='123456789'";
            String delete1 = "delete from strutturagalattica where filamento = '123456789' ";
            String delete2 = "delete from segmento where id='1111111' or id='1111112'";
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
