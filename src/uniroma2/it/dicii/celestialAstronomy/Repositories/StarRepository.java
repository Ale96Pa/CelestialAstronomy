package uniroma2.it.dicii.celestialAstronomy.Repositories;

import uniroma2.it.dicii.celestialAstronomy.Model.Star;
import uniroma2.it.dicii.celestialAstronomy.Repositories.Utility.TypeOfStars;
import uniroma2.it.dicii.celestialAstronomy.Repositories.Utility.UtenteDao;
import uniroma2.it.dicii.celestialAstronomy.Repositories.Utility.Utility;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * In this repository there are all queries used in the software related to Star.
 * Here the access to database and the mapping from RELATIONAL to OBJECT ORIENTED concepts are managed (DAO).
 */

public class StarRepository {

    /*
    Access to DB to find all the stars in a filament
    @Parameter: filament's ID
    @Return: list of Stars included in the filament
     */
    public static ArrayList<Star> findStarInFilament(String filamentID_name){
        Connection connection = null;
        Statement statement = null;
        ResultSet result;
        ArrayList<Star> stars = new ArrayList<>();

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
            String query;
            if(Utility.isInteger(filamentID_name))
                query = "SELECT S.* " +
                        " FROM stella S join inclusione I on S.id=I.stella " +
                        " WHERE I.filamento = '" + filamentID_name +"' ";
            else
                query = "SELECT S.* " +
                        " FROM stella S join inclusione I on S.id=I.stella join filamento F on I.filamento=F.id" +
                        " WHERE F.nome = '" + filamentID_name +"' ";
            result = statement.executeQuery(query);

            while (result.next()) {
                Star star = new Star();
                star.setID(result.getInt("id"));
                star.setName(result.getString("nome"));
                star.setLongitude(result.getDouble("longitudine"));
                star.setLatitude(result.getDouble("latitudine"));
                star.setType(result.getString("tipo"));
                star.setFlux(result.getDouble("flusso"));
                stars.add(star);
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
        return stars;
    }

    /*
    Access to DB to find the number of stars for each type included in a filament
    @Parameter: filament's ID
    @Return: HashMap built like <Key, Value> <---> <Type_of_star, number_of_stars_of_that_type>
     */
    public static HashMap<String, Integer> findNumStarByType(String filamentID_name){
        Connection connection = null;
        Statement statement = null;
        ResultSet result;
        HashMap<String,Integer> starByType=new HashMap<>();
        for(TypeOfStars types : TypeOfStars.values()){
            starByType.put(types.toString(),0);
        }
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
            String query;
            if(Utility.isInteger(filamentID_name))
                query =     "SELECT S.tipo, count(S.tipo) as numStarByType " +
                            " FROM stella S join inclusione I on S.id=I.stella " +
                            " WHERE I.filamento = '" + filamentID_name +"' " +
                            " GROUP BY S.tipo";
            else
                query = "SELECT S.tipo, count(S.tipo) as numStarByType " +
                        " FROM stella S join inclusione I on S.id=I.stella join filamento F on I.filamento = F.id" +
                        " WHERE F.nome = '" + filamentID_name +"' " +
                        " GROUP BY S.tipo";
            result = statement.executeQuery(query);

            while (result.next()) {
                String key = result.getString("tipo");
                Integer value = result.getInt("numStarByType");
                starByType.put(key, value);
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
        return starByType;
    }

    /*
    Access to DB to find all stars included in a rectangle
    @Parameter: center's longitude and latitude, base of rectangle, high of rectangle
    @Return: list of Stars included in the rectangle
     */
    public static ArrayList<Star> findStarInRectangle(double longCenter, double latCenter, double base, double high){
        Connection connection = null;
        Statement statement = null;
        ResultSet result;
        ArrayList<Star> stars = new ArrayList<>();

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
            double limit1 = longCenter -(base/2);
            double limit2 = longCenter +(base/2);
            double limit3 = latCenter - (high/2);
            double limit4 = latCenter + (high/2);
            String query =  " SELECT * " +
                            " FROM stella " +
                            " WHERE longitudine >= " + limit1 + " and longitudine<= " + limit2 + " and latitudine>= " +
                                    limit3 + " and latitudine<= " + limit4;
            result = statement.executeQuery(query);

            while (result.next()) {
                Star star = new Star();
                star.setID(result.getInt("id"));
                star.setName(result.getString("nome"));
                star.setLongitude(result.getDouble("longitudine"));
                star.setLatitude(result.getDouble("latitudine"));
                star.setType(result.getString("tipo"));
                star.setFlux(result.getDouble("flusso"));
                stars.add(star);
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
        return stars;
    }

    /*
   Access to DB to find the number of stars for each type of star included in a filament
   @Parameter: filament's ID
   @Return: an HashMap built like <Key, Value> <---> <Type_Of_Star, number_Of_Stars_Of_That_Type>
    */
    public static HashMap<String, Integer> findNumStarByTypeInRectangle(double longCenter, double latCenter,
                                                                        double base, double high){
        Connection connection = null;
        Statement statement = null;
        ResultSet result;
        HashMap<String,Integer> starByType=new HashMap<>();
        for(TypeOfStars types : TypeOfStars.values()){
            starByType.put(types.toString(),0);
        }
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
            double limit1 = longCenter -(base/2);
            double limit2 = longCenter +(base/2);
            double limit3 = latCenter - (high/2);
            double limit4 = latCenter + (high/2);
            String query =  " SELECT tipo, count(tipo) as numStarByType" +
                            " FROM stella  " +
                            " WHERE longitudine >= " + limit1 + " and longitudine<= " + limit2 + " and latitudine>= " +
                                    limit3 + " and latitudine<= " + limit4 +
                            " GROUP BY tipo";
            result = statement.executeQuery(query);

            while (result.next()) {
                String key = result.getString("tipo");
                Integer value = result.getInt("numStarByType");
                starByType.put(key, value);
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
        return starByType;
    }


    /*
    Access to DB to find the stars included both in a rectangle and in a filament
    @Parameter: center's longitude and latitude, rectangle's base, rectangle's high, filament's ID
    @Return: list of Stars with required features
     */
    public static ArrayList<Star> findStarInRectangleAndFilament(double longCenter, double latCenter, double base,
                                                                 double high){
        Connection connection = null;
        Statement statement = null;
        ResultSet result;
        ArrayList<Star> stars = new ArrayList<>();

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
            double limit1 = longCenter -(base/2);
            double limit2 = longCenter +(base/2);
            double limit3 = latCenter - (high/2);
            double limit4 = latCenter + (high/2);
            String query =  " SELECT S.* " +
                            " FROM stella S join inclusione I ON S.id=I.stella" +
                            " WHERE S.longitudine >= " + limit1 + " and S.longitudine<= " + limit2 + " and S.latitudine>= "
                                + limit3 + " and S.latitudine<= " + limit4;
            result = statement.executeQuery(query);

            while (result.next()) {
                Star star = new Star();
                star.setID(result.getInt("id"));
                star.setName(result.getString("nome"));
                star.setLongitude(result.getDouble("longitudine"));
                star.setLatitude(result.getDouble("latitudine"));
                star.setType(result.getString("tipo"));
                star.setFlux(result.getDouble("flusso"));
                stars.add(star);
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
        return stars;
    }

    /*
    Access to DB to find the number of stars for each type included in a rectangle and that are also in a filament
    @Parameter: center's longitude and latitude, rectangle's base and high
    @Return: HashMap built like <Key, Value> <---> <Type_of_star, number_of_stars_of_that_type>
     */
    public static HashMap<String, Integer> findNumStarByTypeInRectangleAndFilament(double longCenter, double latCenter,
                                                                                   double base, double high){
        Connection connection = null;
        Statement statement = null;
        ResultSet result;
        HashMap<String,Integer> starByType=new HashMap<>();
        for(TypeOfStars types : TypeOfStars.values()){
            starByType.put(types.toString(),0);
        }
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
            double limit1 = longCenter -(base/2);
            double limit2 = longCenter +(base/2);
            double limit3 = latCenter - (high/2);
            double limit4 = latCenter + (high/2);
            String query =  " SELECT S.tipo, count(S.tipo) as numStarByType" +
                            " FROM stella S join inclusione I on S.id=I.stella" +
                            " WHERE longitudine >= " + limit1 + " and longitudine<= " + limit2 + " and latitudine>= " +
                                    limit3 + " and latitudine<= " + limit4 +
                            "GROUP BY S.tipo";
            result = statement.executeQuery(query);

            while (result.next()) {
                String key = result.getString("tipo");
                Integer value = result.getInt("numStarByType");
                starByType.put(key, value);
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
        return starByType;
    }

    /*
    Access to DB to find the stars included in a filament that have the minimum distance from the filament's bakbone
    @Parameter: filament's ID
    @Return: list of stars with required features
     */
    public static ArrayList<Star> findPositionFromBackbone(String filamentID, int offset, String order){
        Connection connection = null;
        Statement statement = null;
        ResultSet result;
        ArrayList<Star> stars = new ArrayList<>();
        String pagination = " LIMIT 20 OFFSET " + offset;

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
            String query =  " SELECT STAR.*, min(sqrt(power((STAR.longitudine - SEG.longitudine),2) + power((STAR.latitudine - SEG.latitudine),2))) as distance " +
                            " FROM stella STAR join inclusione I ON STAR.id=I.stella join filamento F on " +
                                    "I.filamento = F.id join strutturagalattica SG on SG.filamento = F.id join " +
                                    "segmento SEG on (SG.longitudine, SG.latitudine)=(SEG.longitudine, SEG.latitudine)"+
                            " WHERE SEG.tipo = 'S' and F.id = '" +filamentID + "' " +
                            " GROUP BY STAR.id ";
            if(order.equals("1"))
                query+= " ORDER BY distance";
            else
                query+= " ORDER BY flusso";

            if(offset>=0)
                result = statement.executeQuery(query + pagination);
            else
                result = statement.executeQuery(query);

            while (result.next()) {
                Star star = new Star();
                star.setID(result.getInt("id"));
                star.setName(result.getString("nome"));
                star.setLongitude(result.getDouble("longitudine"));
                star.setLatitude(result.getDouble("latitudine"));
                star.setType(result.getString("tipo"));
                star.setFlux(result.getDouble("flusso"));
                star.setDistanceFromBackbone(result.getDouble("distance"));
                stars.add(star);
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
        return stars;
    }
}