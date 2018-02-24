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
import uniroma2.it.dicii.celestialAstronomy.View.RegionBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class R8_SearchFilamentInRegion {

    /*
    Insert new informations' filament to verify the correct execution
    Data inserted:
    * Filament 123456789 totally included in square and circle
    * Filament 123456788 included in circle but not in square
    * Filament 123456787 not included in circle neither in square
     */
    @Before
    public void insertData(){
        String path1 = CsvFileBean.getAbsolutePath()+"testFilament";
        FileRepository.insertFilamentFile(path1,0,0);

        String path2 = CsvFileBean.getAbsolutePath()+"testPerimeter";
        FileRepository.insertPerimeterFile(path2,9,0);

        String path3 = CsvFileBean.getAbsolutePath()+"testSegment";
        FileRepository.insertSkeletonFile(path3,0,0);
    }

    /*
    Find filaments in region (Square or circle) based on data just inserted
     */
    @Test
    public void test(){
        boolean esito= false;
        RegionBean region = new RegionBean();
        region.setLongitudeCenter(0);
        region.setLatitudeCenter(0);
        region.setSideOrRadius(4);
        region.setType(true);
        ArrayList<Filament> filamentInSquare = FilamentController.findFilamentsInRegion(region); //Square
        for (Filament aResult : filamentInSquare) {
            if (aResult.getID() == 123456789)
                esito = true;
            else if (aResult.getID() == 123456788 || aResult.getID() == 123456787)
                esito = false;
        }
        Assert.assertTrue(esito);

        esito = false;
        region.setType(false);
        int counter = 0;
        ArrayList<Filament> filamentInCircle = FilamentController.findFilamentsInRegion(region); //Circle
        for (Filament aResult : filamentInCircle) {
            if (aResult.getID() == 123456789 || aResult.getID() == 123456788)
                counter++;
                if(counter == 2)
                    esito = true;
            else if (aResult.getID() == 123456787)
                esito = false;
        }
        Assert.assertTrue(esito);
    }



    /*
    Verify the execution of Exception in case of wrong input: side/radius = 0
     */
    @Test
    public void irregularTest(){
        boolean esito= true;
        RegionBean region = new RegionBean();
        region.setLongitudeCenter(0);
        region.setLatitudeCenter(0);
        region.setSideOrRadius(0);
        region.setType(true);
        ArrayList<Filament> filamentInSquare = FilamentController.findFilamentsInRegion(region);
        if(filamentInSquare.size()==0)
            esito=false;
        for (Filament aResult : filamentInSquare) {
            if (aResult.getID() == 123456789)
                esito = true;
            else if (aResult.getID() == 123456788 || aResult.getID() == 123456787)
                esito = false;
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
            String delete1 = "delete from filamento " +
                            "where id='123456789' OR id='123456788' or id='123456787' ";
            String delete2 = "delete from strutturagalattica " +
                    "where filamento='123456789' OR filamento='123456788' or filamento='123456787' ";

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
