package uniroma2.it.dicii.celestialAstronomy.Repositories;

import uniroma2.it.dicii.celestialAstronomy.Repositories.Utility.UtenteDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * In this repository there are all queries used in the software related to Segment.
 * Here the access to database and the mapping from RELATIONAL to OBJECT ORIENTED concepts are managed (DAO).
 */

public class SegmentRepository {

    /*
    Access to DB to find the minimum vertex of a segment
    @Parameter: segment's ID
    @Return: minimum vertex's longitude and latitude
     */
    public static ArrayList<Double> findMinVertexOfSegment(String segmentID){
        ArrayList<Double> vertexMin = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet result;

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
            // Esecuzione della query
            String query =  "SELECT S.longitudine, S.latitudine " +
                            " FROM segmento S join strutturagalattica SG on (S.longitudine, S.latitudine)=(SG.longitudine, SG.latitudine)" +
                            " WHERE S.id = '" + segmentID + "' and SG.tipo = 'SEG' and " +
                                                        "S.nprogressivo = ( SELECT min(nprogressivo)" +
                                                                            " FROM segmento S1" +
                                                                            " WHERE S1.id = '" + segmentID +"')";
            result = statement.executeQuery(query);

            while (result.next()) {
                double vertMinLong = result.getDouble("longitudine");
                double vertMinLat = result.getDouble("latitudine");
                vertexMin.add(vertMinLong);
                vertexMin.add(vertMinLat);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                Objects.requireNonNull(connection).close();
                Objects.requireNonNull(statement).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return vertexMin;
    }

    /*
    Access to DB to find the maximum vertex of a segment
    @Parameter: segment's ID
    @Return: maximum vertex's longitude and latitude
     */
    public static ArrayList<Double> findMaxVertexOfSegment(String segmentID){
        ArrayList<Double> vertexMax = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet result;

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
            // Esecuzione della query
            String query =  "SELECT S.longitudine, S.latitudine " +
                            " FROM segmento S join strutturagalattica SG on (S.longitudine, S.latitudine)=(SG.longitudine, SG.latitudine)" +
                            " WHERE S.id = '" + segmentID + "' and SG.tipo = 'SEG' and " +
                                                    "S.nprogressivo = ( SELECT max(nprogressivo)" +
                                                                        " FROM segmento S1" +
                                                                        " WHERE S1.id = '" + segmentID +"')";
            result = statement.executeQuery(query);

            while (result.next()) {
                double vertMaxLong = result.getDouble("longitudine");
                double vertMaxLat = result.getDouble("latitudine");
                vertexMax.add(vertMaxLong);
                vertexMax.add(vertMaxLat);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                Objects.requireNonNull(connection).close();
                Objects.requireNonNull(statement).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return vertexMax;
    }

    /*
    Access to DB to find the filament's ID that contain a given segment
    @Parameter: segment's ID
    @Return: string that is the filament's ID including the segment
     */
    public static String findFilamentOfSegment(String segmentID){
        String filamentID=null;
        Connection connection = null;
        Statement statement = null;
        ResultSet result;

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
            // Esecuzione della query
            String query =  "SELECT DISTINCT SG.filamento " +
                            " FROM segmento S join strutturagalattica SG on (S.longitudine, S.latitudine)=(SG.longitudine, SG.latitudine)" +
                            " WHERE S.id = '" + segmentID + "' and SG.tipo = 'SEG' ";
            result = statement.executeQuery(query);

            while (result.next()) {
                filamentID = result.getString("filamento");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                Objects.requireNonNull(connection).close();
                Objects.requireNonNull(statement).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return filamentID;
    }

    /*
    Access to DB to find the distance of a vertex from the perimeter
    @Parameter: vertex's longitude and latitude, filament's ID
    @Return: a double that is the distance calculated
     */
    public static double distanceVertexFromPerimeter(double longVertex, double latVertex, String filamentID ){
        double distance = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet result;

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
            // Esecuzione della query
            String query =  "SELECT min(sqrt(power((SG.longitudine - " + longVertex + "),2) + power((SG.latitudine - " + latVertex + "),2))) as distance " +
                            " FROM strutturagalattica SG " +
                            " WHERE SG.filamento = '" + filamentID + "' and SG.tipo = 'PER'";
            result = statement.executeQuery(query);

            while (result.next()) {
                distance = result.getDouble("distance");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                Objects.requireNonNull(connection).close();
                Objects.requireNonNull(statement).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return distance;
    }

    /*
    Access to DB to find if a segment is present in DB; this method is used to avoid sending error for the calculation
    of distance from backbone
    @Parameter: segment's ID
    @Return: true only if the segment is in DB
     */
    public static boolean searchSegmentByID(String segmentID){
        Connection connection = null;
        Statement statement = null;
        ResultSet result;
        boolean esito = false;

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
            // Esecuzione della query
            String query =  "SELECT DISTINCT id " +
                            "FROM segmento " +
                            "WHERE id = '" + segmentID +"' ";
            result = statement.executeQuery(query);

            if(result.next())
                esito = true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                Objects.requireNonNull(connection).close();
                Objects.requireNonNull(statement).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return esito;
    }
}