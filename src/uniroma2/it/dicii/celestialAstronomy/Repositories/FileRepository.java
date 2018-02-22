package uniroma2.it.dicii.celestialAstronomy.Repositories;

import uniroma2.it.dicii.celestialAstronomy.Exception.ImportFileException;
import uniroma2.it.dicii.celestialAstronomy.Exception.NoDataFoundException;
import uniroma2.it.dicii.celestialAstronomy.Repositories.Utility.UtenteDao;
import uniroma2.it.dicii.celestialAstronomy.View.CsvFileBean;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import static java.lang.Math.abs;
import static java.lang.Math.atan;

public class FileRepository {

    /*
    Insert in database data depending on file about perimeter
    Update the table "STRUTTURA GALATTICA"
    @ Return number of tuples read
     */
    public static int insertPerimeterFile(String pathname) {
        Connection connection;
        Statement statement;

        int esito;
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

            // Estrazione dei dati dal file
            BufferedReader br;
            String line;
            String cvsSplitter = ",";
            br = new BufferedReader(new FileReader(pathname));
            esito = 0;
            while ((line = br.readLine()) != null) {
                // String array of element of one tuple
                String[] tuple = line.split(cvsSplitter);
                // Insert of elements in database
                if(esito!=0) {
                    if(!FilamentRepository.searchFilament(tuple[0]))
                        throw new ImportFileException();
                    String insertUpdate =   " INSERT INTO strutturagalattica (filamento, longitudine, latitudine, tipo)"+
                                            " VALUES ('" + tuple[0] + "', '"+ tuple[1] +"', '"+ tuple[2] +"' , 'PER')" +
                                            " ON CONFLICT (filamento, longitudine, latitudine) DO UPDATE " +
                                            " SET filamento = excluded.filamento, longitudine = excluded.longitudine ,"+
                                                " latitudine = excluded.latitudine, tipo= excluded.tipo";
                    statement.executeUpdate(insertUpdate);
                }
                esito++;
            }
            // Chiusura della connessione e del reader
            br.close();
            connection.close();
            statement.close();
        } catch (ClassNotFoundException | SQLException | IOException | ImportFileException e) {
            e.printStackTrace();
            esito = 0;
        }
        return esito-1;
    }

    /*
    Insert in database data depending on file about skeleton
    Update the tables "STRUTTURA GALATTICA" and "SEGMENTO"
    @ Return number of tuples read
     */
    public static int insertSkeletonFile(String pathname){
        Connection connection;
        Statement statement;

        int esito;
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

            // Estrazione dei dati dal file
            BufferedReader br;
            String line;
            String cvsSplitter = ",";
            br = new BufferedReader(new FileReader(pathname));
            esito = 0;
            while ((line = br.readLine()) != null) {
                // String array of element of one tuple
                String[] tuple = line.split(cvsSplitter);
                // Insert of elements in database
                if(esito!=0) {
                    if(!FilamentRepository.searchFilament(tuple[0]))
                        throw new ImportFileException();
                    String insertUpdate1 =  "INSERT INTO strutturagalattica (filamento, longitudine, latitudine, tipo)"+
                                            " VALUES ('" + tuple[0] + "', '"+ tuple[3] +"', '"+ tuple[4] +"' , 'SEG')" +
                                            " ON CONFLICT (filamento, longitudine, latitudine) DO UPDATE " +
                                            " SET filamento = excluded.filamento, longitudine = excluded.longitudine ,"+
                                                    " latitudine = excluded.latitudine, tipo= excluded.tipo";

                    String insertUpdate2 =  "INSERT INTO segmento (id, longitudine, latitudine, tipo, nprogressivo, flusso)" +
                                            "VALUES ('" + tuple[1] + "', '"+ tuple[3] +"', '"+ tuple[4] +"' , '"
                                                        + tuple[2] +"' , '" + tuple[5] +"' , " + tuple[6] +")" +
                                            "ON CONFLICT (id, longitudine, latitudine) DO UPDATE " +
                                            " SET id = excluded.id, longitudine = excluded.longitudine ," +
                                                    " latitudine = excluded.latitudine, tipo= excluded.tipo," +
                                                    " nprogressivo = excluded.nprogressivo, flusso= excluded.flusso";
                    statement.executeUpdate(insertUpdate1);
                    statement.executeUpdate(insertUpdate2);
                }
                esito++;
            }
            // Chiusura della connessione e del reader
            br.close();
            connection.close();
            statement.close();
        } catch (ClassNotFoundException | SQLException | IOException | ImportFileException e) {
            e.printStackTrace();
            esito = 0;
        }
        return esito-1;
    }

    /*
    Insert in database data dependig on file about filament
    Update the table "FILAMENTO"
    @ Return number of tuples read
     */
    public static int insertFilamentFile(String pathname){
        Connection connection;
        Statement statement;

        int esito;
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

            // Estrazione dei dati dal file
            BufferedReader br;
            String line;
            String cvsSplitter = ",";
            br = new BufferedReader(new FileReader(pathname));
            esito = 0;
            while ((line = br.readLine()) != null) {
                // String array of element of one tuple
                String[] tuple = line.split(cvsSplitter);
                // Insert of elements in database
                if(esito!=0) {
                    String insertUpdate =   "INSERT INTO filamento (id, nome, flussototale, densita, temperatura, ellitticita, contrasto)" +
                                            "VALUES ('" + tuple[0] + "', '"+ tuple[1] +"', '"+ tuple[2] + "', '"+
                                                    tuple[3] + "', '"+ tuple[4] + "', '"+ tuple[5] + "', '"+ tuple[6] +"')" +
                                            "ON CONFLICT (id) DO UPDATE " +
                                            " SET id = excluded.id, nome = excluded.nome , flussototale = excluded.flussototale, " +
                                                    "densita= excluded.densita, temperatura = excluded.temperatura, " +
                                                    " ellitticita = excluded.ellitticita, contrasto = excluded.contrasto";
                    statement.executeUpdate(insertUpdate);
                }
                esito++;
            }
            // Chiusura della connessione e del reader
            br.close();
            connection.close();
            statement.close();
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
            esito = 0;
        }
        return esito-1;
    }

    /*
    Insert in database data depending on file about stars, also verifying the inclusion of a star into a filament
    Update the tables "STELLA" and "INCLUSIONE"
    @ Return number of tuples read
     */
    public static int insertStarFile(String pathnameStar){
        String pathnameFilament = CsvFileBean.getAbsolutePath()+"contorni_filamenti_Herschel.csv";
        Connection connection;
        Statement statement;

        int esitoStar;
        int esitoInsert = 0;
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

            // Estrazione dei dati dal file
            BufferedReader brStar;
            String lineStar;
            String cvsSplitter = ",";
            brStar = new BufferedReader(new FileReader(pathnameStar));
            esitoStar = 0;
            while ((lineStar = brStar.readLine()) != null) {
                // String array of element of one tuple
                String[] tupleStar = lineStar.split(cvsSplitter);
                // Insert of elements in database
                if(esitoStar!=0) {
                    String insertUpdate =   "INSERT INTO stella (id, longitudine, latitudine, nome, tipo, flusso)" +
                                            "VALUES ('" + tupleStar[0] + "', '"+ tupleStar[2] +"', '"+ tupleStar[3] + "', '"+
                                                        tupleStar[1] + "', '"+ tupleStar[5] + "', '"+ tupleStar[4] + "')" +
                                            "ON CONFLICT (id) DO UPDATE " +
                                            " SET id = excluded.id, longitudine = excluded.longitudine , " +
                                                        " latitudine = excluded.latitudine, nome= excluded.nome, " +
                                                        " tipo = excluded.tipo, flusso = excluded.flusso";
                    statement.executeUpdate(insertUpdate);

                    /*
                    Update the table "INCLUSIONE" verifying given formula (REQ-FN-9)
                    */
                    String CurrentFilamentID;
                    String BeforeFilamentID = "";
                    String[] CurrentTuplePerimeter;
                    String[] BeforeTuplePerimeter = new String[0];
                    double numerator;
                    double denominator;
                    double sumValue =0;
                    BufferedReader brPerimeter;
                    brPerimeter = new BufferedReader(new FileReader(pathnameFilament));
                    String linePerimeter;
                    int esitoPerimeter=0;
                    while((linePerimeter = brPerimeter.readLine()) != null) {

                        // String array of element of one tuple
                        CurrentTuplePerimeter = linePerimeter.split(cvsSplitter);
                        if (esitoPerimeter != 0) {
                            CurrentFilamentID = CurrentTuplePerimeter[0];
                            if (!CurrentFilamentID.equals(BeforeFilamentID)) {
                                if ((abs(sumValue)) >= Math.toRadians(0.01)) {
                                    if(!FilamentRepository.searchFilament(BeforeFilamentID))
                                        throw new ImportFileException();
                                    String insertUpdateInclusione = "INSERT INTO inclusione (filamento, stella)" +
                                            " VALUES ('" + BeforeFilamentID + "', '" + tupleStar[0] + "')" +
                                            " ON CONFLICT (filamento, stella) DO UPDATE " +
                                            " SET filamento = excluded.filamento, stella=excluded.stella";
                                    statement.executeUpdate(insertUpdateInclusione);
                                    esitoInsert++;
                                }
                                sumValue = 0;
                            } else {
                                numerator = (Double.parseDouble(BeforeTuplePerimeter[1]) - Double.parseDouble(tupleStar[2])) * (Double.parseDouble(CurrentTuplePerimeter[2]) - Double.parseDouble(tupleStar[3])) -
                                        (Double.parseDouble(BeforeTuplePerimeter[2]) - Double.parseDouble(tupleStar[3])) * (Double.parseDouble(CurrentTuplePerimeter[1]) - Double.parseDouble(tupleStar[2]));
                                denominator = (Double.parseDouble(BeforeTuplePerimeter[1]) - Double.parseDouble(tupleStar[2])) * (Double.parseDouble(CurrentTuplePerimeter[1]) - Double.parseDouble(tupleStar[2])) +
                                        (Double.parseDouble(BeforeTuplePerimeter[2]) - Double.parseDouble(tupleStar[3])) * (Double.parseDouble(CurrentTuplePerimeter[2]) - Double.parseDouble(tupleStar[3]));
                                sumValue += atan(Math.toRadians(numerator / denominator));
                            }
                            BeforeFilamentID = CurrentFilamentID;
                            BeforeTuplePerimeter = CurrentTuplePerimeter;
                        }
                        esitoPerimeter++;
                    }
                    brPerimeter.close();
                }
                esitoStar++;
            }
            // Chiusura della connessione e del buffer
            brStar.close();
            connection.close();
            statement.close();
        } catch (ClassNotFoundException | SQLException | IOException | ImportFileException e) {
            e.printStackTrace();
            esitoInsert = -1;
        }
        return esitoInsert;
    }
}



