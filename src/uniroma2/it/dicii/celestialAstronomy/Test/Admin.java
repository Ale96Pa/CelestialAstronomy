package uniroma2.it.dicii.celestialAstronomy.Test;

import uniroma2.it.dicii.celestialAstronomy.Control.InstrumentController;
import uniroma2.it.dicii.celestialAstronomy.Repositories.Utility.UtenteDao;
import uniroma2.it.dicii.celestialAstronomy.View.InstrumentBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Admin extends Thread {

    private int time;
    private int id;

    public Admin(int id, int time){
        this.id=id;
        this.time=time;
    }

    public static void deleteElementForTest(){
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
            String deleteInstrument =   "delete from strumento " +
                                        "where nome='TestInstrument'";
            statement.executeUpdate(deleteInstrument);
            // Chiusura della connessione
            connection.close();
            statement.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized void run(){
        try{
            sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        InstrumentBean instrument = new InstrumentBean();
        instrument.setName("TestInstrument");
        instrument.setBand1(7);
        InstrumentController.registerInstrument(instrument);
        System.out.println("Admin end registration");
    }
}
