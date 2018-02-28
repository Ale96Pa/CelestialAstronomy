package uniroma2.it.dicii.celestialAstronomy.Repositories;

import uniroma2.it.dicii.celestialAstronomy.Exception.ImportFileException;
import uniroma2.it.dicii.celestialAstronomy.Repositories.Utility.UtenteDao;
import uniroma2.it.dicii.celestialAstronomy.View.CsvFileBean;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import static java.lang.Math.abs;
import static java.lang.Math.atan;

/**
 * In this repository there are all updates of data from csv file to database.
 * Here the access to database and the mapping from RELATIONAL to OBJECT ORIENTED concepts are managed (DAO).
 */

public class FileRepository {
    private static String idfilamentospeciale;
    /*
    Insert in database data depending on file about perimeter
    Update the table "STRUTTURA GALATTICA"
    You can insert how many rows of file you want insert and the position of the sick to start read file: a ZERO
    number of rows means ALL row
    @ Return number of tuples read
     */
    public static int insertPerimeterFile(String pathname, int numRows, int offset) {
        Connection connection = null;
        Statement statement = null;

        int sick;
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
            sick = 0;
            while ((line = br.readLine()) != null) {
                // String array of element of one tuple
                String[] tuple = line.split(cvsSplitter);
                // Insert of elements in database
                String insertUpdate =   " INSERT INTO strutturagalattica (filamento, longitudine, latitudine, tipo)"+
                        " VALUES ('" + tuple[0] + "', '"+ tuple[1] +"', '"+ tuple[2] +"' , 'PER')" +
                        " ON CONFLICT (filamento, longitudine, latitudine) DO UPDATE " +
                        " SET filamento = excluded.filamento, longitudine = excluded.longitudine ,"+
                        " latitudine = excluded.latitudine, tipo= excluded.tipo";
                if(sick!=0) {
                    if(!FilamentRepository.searchFilament(tuple[0]))
                        throw new ImportFileException();
                    if(numRows == 0) // all rows
                        statement.executeUpdate(insertUpdate);
                    else {
                        if(sick >= offset)
                            statement.executeUpdate(insertUpdate);
                        if(sick == offset+numRows)
                            break;
                    }
                }
                sick++;
                if(sick == offset+numRows)
                    break;
            }
            // Chiusura della connessione e del reader
            br.close();
        } catch (ClassNotFoundException | SQLException | IOException | ImportFileException e) {
            e.printStackTrace();
            sick = 0;
        } finally {
            try {
                Objects.requireNonNull(connection).close();
                Objects.requireNonNull(statement).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return sick-1;
    }

    /*
    Insert in database data depending on file about skeleton
    Update the tables "STRUTTURA GALATTICA" and "SEGMENTO"
    You can insert how many rows of file you want insert and the position of the sick to start read file: a ZERO
    number of rows means ALL row
    @ Return number of tuples read
     */
    public static int insertSkeletonFile(String pathname, int numRows, int offset){
        Connection connection = null;
        Statement statement = null;

        int sick;
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
            sick = 0;
            while ((line = br.readLine()) != null) {
                // String array of element of one tuple
                String[] tuple = line.split(cvsSplitter);
                // Insert of elements in database
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
                if(sick!=0) {
                    if(!FilamentRepository.searchFilament(tuple[0]))
                        throw new ImportFileException();
                    if(numRows == 0){  // all rows
                        statement.executeUpdate(insertUpdate1);
                        statement.executeUpdate(insertUpdate2);
                    }
                    else {
                        if(sick >= offset)
                            statement.executeUpdate(insertUpdate1);
                        statement.executeUpdate(insertUpdate2);
                        if(sick == offset+numRows)
                            break;
                    }
                }
                sick++;
            }
            // Chiusura della connessione e del reader
            br.close();

        } catch (ClassNotFoundException | SQLException | IOException | ImportFileException e) {
            e.printStackTrace();
            sick = 0;
        } finally {
            try {
                Objects.requireNonNull(connection).close();
                Objects.requireNonNull(statement).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return sick-1;
    }

    /*
    Insert in database data dependig on file about filament
    Update the table "FILAMENTO"
    You can insert how many rows of file you want insert and the position of the sick to start read file: a ZERO
    number of rows means ALL row
    @ Return number of tuples read
     */
    public static int insertFilamentFile(String pathname, int numRows, int offset) {
        Connection connection = null;
        Statement statement = null;

        int sick;
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
            sick = 0;
            while ((line = br.readLine()) != null) {
                // String array of element of one tuple
                String[] tuple = line.split(cvsSplitter);
                // Insert of elements in database
                String insertUpdate =   "INSERT INTO filamento (id, nome, flussototale, densita, temperatura, ellitticita, contrasto)" +
                        "VALUES ('" + tuple[0] + "', '"+ tuple[1] +"', '"+ tuple[2] + "', '"+
                        tuple[3] + "', '"+ tuple[4] + "', '"+ tuple[5] + "', '"+ tuple[6] +"')" +
                        "ON CONFLICT (id) DO UPDATE " +
                        " SET id = excluded.id, nome = excluded.nome , flussototale = excluded.flussototale, " +
                        "densita= excluded.densita, temperatura = excluded.temperatura, " +
                        " ellitticita = excluded.ellitticita, contrasto = excluded.contrasto";
                if(sick!=0) {
                    if(numRows == 0) // all rows
                        statement.executeUpdate(insertUpdate);
                    else {
                        if(sick >= offset)
                            statement.executeUpdate(insertUpdate);
                        if(sick == offset+numRows)
                            break;
                    }
                }
                sick++;
            }
            // Chiusura della connessione e del reader
            br.close();
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
            sick = 0;
        } finally {
            try {
                Objects.requireNonNull(connection).close();
                Objects.requireNonNull(statement).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return sick-1;
    }

    /*
    Insert in database data depending on file about stars, also verifying the inclusion of a star into a filament
    Update the tables "STELLA" and "INCLUSIONE"
    You can insert how many rows of file you want insert and the position of the sick to start read file: a ZERO
    number of rows means ALL row
    @ Return number of tuples read
     */
    public static int insertStarFile(String pathnameStar, int numRows, int offset, String pathPerimeter){
        String pathnameFilament = CsvFileBean.getAbsolutePath()+pathPerimeter;
        Connection connection = null;
        Statement statement = null;

        int sick;
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

            sick = 0;
            while ((lineStar = brStar.readLine()) != null) {
                // String array of element of one tuple
                String[] tupleStar = lineStar.split(cvsSplitter);
                // Insert of elements in database
                String insertUpdate =   "INSERT INTO stella (id, longitudine, latitudine, nome, tipo, flusso)" +
                        "VALUES ('" + tupleStar[0] + "', '"+ tupleStar[2] +"', '"+ tupleStar[3] + "', '"+
                        tupleStar[1] + "', '"+ tupleStar[5] + "', '"+ tupleStar[4] + "')" +
                        "ON CONFLICT (id) DO UPDATE " +
                        " SET id = excluded.id, longitudine = excluded.longitudine , " +
                        " latitudine = excluded.latitudine, nome= excluded.nome, " +
                        " tipo = excluded.tipo, flusso = excluded.flusso";
                if(sick!=0) {
                    if(numRows == 0) // all rows
                        statement.executeUpdate(insertUpdate);
                    else {
                        if(sick >= offset)
                            statement.executeUpdate(insertUpdate);
                        if(sick == offset+numRows)
                            break;
                    }
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
                            if (CurrentFilamentID.equalsIgnoreCase(BeforeFilamentID)) {
                                // Calculate the sum based on previous and current filament
                                numerator = (Double.parseDouble(BeforeTuplePerimeter[1]) - Double.parseDouble(tupleStar[2])) * (Double.parseDouble(CurrentTuplePerimeter[2]) - Double.parseDouble(tupleStar[3])) -
                                        (Double.parseDouble(BeforeTuplePerimeter[2]) - Double.parseDouble(tupleStar[3])) * (Double.parseDouble(CurrentTuplePerimeter[1]) - Double.parseDouble(tupleStar[2]));
                                denominator = (Double.parseDouble(BeforeTuplePerimeter[1]) - Double.parseDouble(tupleStar[2])) * (Double.parseDouble(CurrentTuplePerimeter[1]) - Double.parseDouble(tupleStar[2])) +
                                        (Double.parseDouble(BeforeTuplePerimeter[2]) - Double.parseDouble(tupleStar[3])) * (Double.parseDouble(CurrentTuplePerimeter[2]) - Double.parseDouble(tupleStar[3]));
                                double angle = atan(numerator / denominator);
                                sumValue += angle;
                            } else {
                                if ((abs(Math.toRadians(sumValue))) >= 0.01) {
                                    if(!FilamentRepository.searchFilament(BeforeFilamentID))
                                        throw new ImportFileException();
                                    String insertUpdateInclusione = "INSERT INTO inclusione (filamento, stella)" +
                                            " VALUES ('" + BeforeFilamentID + "', '" + tupleStar[0] + "')" +
                                            " ON CONFLICT (filamento, stella) DO UPDATE " +
                                            " SET filamento = excluded.filamento, stella=excluded.stella";
                                    statement.executeUpdate(insertUpdateInclusione);
                                    esitoInsert++;
                                }
                                sumValue=0;
                            }
                            BeforeFilamentID = CurrentFilamentID;
                            BeforeTuplePerimeter = CurrentTuplePerimeter;
                        }
                        esitoPerimeter++;
                    }
                    brPerimeter.close();
                }
                sick++;
            }
            // Chiusura della connessione e del buffer
            brStar.close();

        } catch (ClassNotFoundException | SQLException | IOException | ImportFileException e) {
            e.printStackTrace();
            esitoInsert = -1;
        }
        finally {
            try {
                Objects.requireNonNull(connection).close();
                Objects.requireNonNull(statement).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return esitoInsert;
    }
}