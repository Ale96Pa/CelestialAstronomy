package uniroma2.it.dicii.celestialAstronomy.Test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uniroma2.it.dicii.celestialAstronomy.Control.StarController;
import uniroma2.it.dicii.celestialAstronomy.Model.Star;
import uniroma2.it.dicii.celestialAstronomy.Repositories.FileRepository;
import uniroma2.it.dicii.celestialAstronomy.Repositories.Utility.UtenteDao;
import uniroma2.it.dicii.celestialAstronomy.View.CsvFileBean;
import uniroma2.it.dicii.celestialAstronomy.View.StarBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class R9_SearchInclusionStar {

    /*
    Insert new informations' filament and stars to verify the correct execution
    Data inserted:
    * Filament with id: 123456789 with perimeter defined by the following points: (2,5), (-2,1), (-2,5) and (3,1)
    * Star: 1111111 in (-1,2) (included in the filament)
    * Star: 1111113 in (20,20)
    */
    @Before
    public void insertData(){
        String path1 = CsvFileBean.getAbsolutePath()+"testFilament";
        FileRepository.insertFilamentFile(path1,0,0);

        String path2 = CsvFileBean.getAbsolutePath()+"testPerimeter";
        FileRepository.insertPerimeterFile(path2,4,13);

        String path3 = CsvFileBean.getAbsolutePath()+"testStar";
        FileRepository.insertStarFile(path3,0,0 ,"testPerimeter");
    }

    /*
    Find the stars included in a filament based on data just inserted
     */
    @Test
    public void test(){
        boolean esito = false;
        StarBean starBean = new StarBean();
        starBean.setFilamentID("123456789");
        ArrayList<Star> stars = StarController.findStarsInFilament(starBean);
        for (Star aResult : stars) {
            if (aResult.getID() == 1111111){
                esito = true;
            }
            else if (aResult.getID() == 1111113){
                esito = false;
            }
        }
        Assert.assertTrue(esito);
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
            String delete1 = "delete from filamento where nome='none' ";
            String delete2 = "delete from stella where nome='none' ";
            String delete3 = "delete from strutturagalattica where filamento='123456789' ";
            String delete4 = "delete from inclusione where filamento = '123456789' ";
            statement.executeUpdate(delete1);
            statement.executeUpdate(delete2);
            statement.executeUpdate(delete3);
            statement.executeUpdate(delete4);


            // Chiusura della connessione
            connection.close();
            statement.close();
        } catch(ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }
    }
}
