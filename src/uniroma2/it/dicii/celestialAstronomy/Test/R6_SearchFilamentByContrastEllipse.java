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

public class R6_SearchFilamentByContrastEllipse {

    /*
    Insert new informations' filament to verify the correct execution
    Data inserted:
    * Filament 123456789 with contrast=1.2 and ellipse=2
    * Filament 123456788 with contrast=1.3 and ellipse=4
    * Filament 123456787 with contrast=0.7 and ellipse=6
    * Filament 123456786 with contrast=2.3 and ellipse=8
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
            String insert1 = "INSERT INTO filamento(id, nome, flussototale, densita, temperatura, ellitticita, contrasto) " +
                    " VALUES ('123456789' , 'aaB' , '0' , ' 0 ' , ' 0', '2', '1.2')";
            String insert2 = "INSERT INTO filamento(id, nome, flussototale, densita, temperatura, ellitticita, contrasto) " +
                    " VALUES ('123456788' , 'aaC' , '0' , ' 0 ' , ' 0', '4', '1.3')";
            String insert3 = "INSERT INTO filamento(id, nome, flussototale, densita, temperatura, ellitticita, contrasto) " +
                    " VALUES ('123456787' , 'aaD' , '0' , ' 0 ' , ' 0', '6', '0.7')";
            String insert4 = "INSERT INTO filamento(id, nome, flussototale, densita, temperatura, ellitticita, contrasto) " +
                    " VALUES ('123456786' , 'aaE' , '0' , ' 0 ' , ' 0', '8', '2.3')";
            statement.executeUpdate(insert1);
            statement.executeUpdate(insert2);
            statement.executeUpdate(insert3);
            statement.executeUpdate(insert4);

            // Chiusura della connessione
            connection.close();
            statement.close();
        } catch(ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    Find filament by contrast and ellipse based on data just inserted
     */
    @Test
    public void test(){
        boolean esito = false;
        FilamentBean filamentBean = new FilamentBean();
        filamentBean.setBrillance(10);
        filamentBean.setMinEllipse(3);
        filamentBean.setMaxEllipse(7);

        ArrayList<Filament> result = FilamentController.findFilamentByEllipseAndContrast(filamentBean, -1);
        for (Filament aResult : result) {
            if (aResult.getID() == 123456788)
                esito = true;
            else if (aResult.getID() == 123456789 || aResult.getID() == 123456787 || aResult.getID() == 123456786)
                esito = false;
        }
        Assert.assertTrue(esito);
    }

    /*
    Verify the execution of Exception in case of wrong input: maximum ellipse = 50
     */
    @Test
    public void IrregularTest(){
        boolean esito = false;
        FilamentBean filamentBean = new FilamentBean();
        filamentBean.setBrillance(10);
        filamentBean.setMinEllipse(3);
        filamentBean.setMaxEllipse(50); // WRONG DATA
        ArrayList<Filament> result = FilamentController.findFilamentByEllipseAndContrast(filamentBean, -1);
        for (Filament aResult : result) {
            if (aResult.getID() == 123456788)
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
            String delete = "delete from filamento " +
                            "where id='123456789' OR id='123456788' or id='123456787' or id='123456786'";
            statement.executeUpdate(delete);

            // Chiusura della connessione
            connection.close();
            statement.close();
        } catch(ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }
    }
}
