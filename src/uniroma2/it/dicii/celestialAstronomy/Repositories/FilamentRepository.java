package uniroma2.it.dicii.celestialAstronomy.Repositories;

import uniroma2.it.dicii.celestialAstronomy.Model.Filament;
import uniroma2.it.dicii.celestialAstronomy.Repositories.Utility.UtenteDao;
import uniroma2.it.dicii.celestialAstronomy.Repositories.Utility.Utility;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import static com.sun.org.apache.xalan.internal.lib.ExsltMath.power;
import static java.lang.Math.sqrt;

public class FilamentRepository {

    /*
    Access to DB to find centroide's longitude and latitude of a filament
    @ Parameter: filament's ID or Name
    @ Return a list of two Double (longitude and latitude)
     */
    public static ArrayList<Double> findCentroide(String filamentID_name) {
        ArrayList<Double> centroide = new ArrayList<>();
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
            String query;
            if(Utility.isInteger(filamentID_name)){
                query =  "SELECT avg(longitudine) as long, avg(latitudine) as lat " +
                         " FROM strutturagalattica SG join filamento F on SG.filamento = F.id" +
                         " WHERE SG.filamento = '" + filamentID_name + "' and SG.tipo='PER'";
            } else {
                query =  "SELECT avg(longitudine) as long, avg(latitudine) as lat " +
                         " FROM strutturagalattica SG join filamento F on SG.filamento = F.id" +
                         " WHERE F.nome = '" + filamentID_name + "'  and SG.tipo='PER'";
            }
            result = statement.executeQuery(query);

            while (result.next()) {
                Double avgLong = result.getDouble("long");
                Double avgLat = result.getDouble("lat");

                centroide.add(avgLong);
                centroide.add(avgLat);
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
        return centroide;
    }

    /*
    Acces to DB to find the extension of a filament( both for longitude and latitude)
    @ Parameter: filament's ID or name
    @ Return a list of two Double (longitude and latitude extensions)
     */
    public static ArrayList<Double> findPerimeterExtension(String filamentID_name) {
        ArrayList<Double> extension = new ArrayList<>();
        // Initialize an array of kind (Longitude, Latitude) for each point necessary to calculate the extension
        ArrayList<Double> maxPointLong = new ArrayList<>();
        ArrayList<Double> minPointLong = new ArrayList<>();
        ArrayList<Double> maxPointLat = new ArrayList<>();
        ArrayList<Double> minPointLat = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet result= null;

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
            String queryForMaxPointLong; // to find the point with maximum longitude
            String queryForMinPointLong; // to find the point with minimum longitude
            String queryForMaxPointLat; // to find the point with maximum latitude
            String queryForMinPointLat; // to find the point with minimum latitude

            if(Utility.isInteger(filamentID_name)){
                // In case of ID inserted
                queryForMaxPointLong = "SELECT SG.latitudine , SG.longitudine " +
                        " FROM strutturagalattica SG join filamento F on SG.filamento = F.id" +
                        " WHERE SG.filamento ='" + filamentID_name +"' and SG.longitudine =(SELECT (max(SG.longitudine)) " +
                                            " FROM strutturagalattica SG join filamento F on SG.filamento = F.id" +
                                            " WHERE SG.filamento ='" + filamentID_name + "' and SG.tipo = 'PER') ";

                queryForMinPointLong = "SELECT SG.latitudine, SG.longitudine " +
                        " FROM strutturagalattica SG join filamento F on SG.filamento = F.id" +
                        " WHERE SG.filamento ='" + filamentID_name +"' and SG.longitudine =(SELECT (min(SG.longitudine)) " +
                                            " FROM strutturagalattica SG join filamento F on SG.filamento = F.id" +
                                            " WHERE SG.filamento = '" + filamentID_name + "' and SG.tipo = 'PER') ";

                queryForMaxPointLat = "SELECT SG.longitudine ,SG.latitudine" +
                        " FROM strutturagalattica SG join filamento F on SG.filamento = F.id" +
                        " WHERE SG.filamento ='" + filamentID_name +"' and SG.latitudine =(SELECT (max(SG.latitudine)) " +
                                            " FROM strutturagalattica SG join filamento F on SG.filamento = F.id" +
                                            " WHERE SG.filamento = '" + filamentID_name + "' and SG.tipo = 'PER') ";

                queryForMinPointLat = "SELECT SG.longitudine,SG.latitudine " +
                        "FROM strutturagalattica SG join filamento F on SG.filamento = F.id" +
                        " WHERE SG.filamento ='" + filamentID_name +"' and SG.latitudine =(SELECT (min(SG.latitudine)) " +
                                            " FROM strutturagalattica SG join filamento F on SG.filamento = F.id" +
                                            " WHERE SG.filamento = '" + filamentID_name + "' and SG.tipo = 'PER') ";

            } else {
                // In case of NAME inserted
                queryForMaxPointLong = "SELECT SG.latitudine , SG.longitudine " +
                        "FROM strutturagalattica SG join filamento F on SG.filamento = F.id" +
                        " WHERE F.nome ='" + filamentID_name +"' and SG.longitudine =(SELECT (max(SG.longitudine)) " +
                                            " FROM strutturagalattica SG join filamento F on SG.filamento = F.id" +
                                            " WHERE F.nome = '" + filamentID_name + "' and SG.tipo = 'PER') ";

                queryForMinPointLong = "SELECT SG.latitudine, SG.longitudine" +
                        " FROM strutturagalattica SG join filamento F on SG.filamento = F.id" +
                        " WHERE F.nome ='" + filamentID_name +"' and SG.longitudine =(SELECT (min(SG.longitudine)) " +
                                            " FROM strutturagalattica SG join filamento F on SG.filamento = F.id" +
                                            " WHERE F.nome = '" + filamentID_name + "' and SG.tipo = 'PER') ";

                queryForMaxPointLat = "SELECT SG.longitudine ,SG.latitudine" +
                        " FROM strutturagalattica SG join filamento F on SG.filamento = F.id" +
                        " WHERE F.nome ='" + filamentID_name +"' and SG.latitudine =(SELECT (max(SG.latitudine))  " +
                                            " FROM strutturagalattica SG join filamento F on SG.filamento = F.id" +
                                            " WHERE F.nome = '" + filamentID_name + "' and SG.tipo = 'PER') ";

                queryForMinPointLat = "SELECT SG.longitudine,SG.latitudine " +
                        "FROM strutturagalattica SG join filamento F on SG.filamento = F.id" +
                        " WHERE F.nome ='" + filamentID_name +"' and SG.latitudine =(SELECT (min(SG.latitudine))  " +
                                            " FROM strutturagalattica SG join filamento F on SG.filamento = F.id" +
                                            " WHERE F.nome ='" + filamentID_name + "' and SG.tipo = 'PER') ";
            }

            result = statement.executeQuery(queryForMaxPointLong);
            while(result.next()) {
                maxPointLong.add(result.getDouble("longitudine"));
                maxPointLong.add(result.getDouble("latitudine"));
            }
            result.close();

            result = statement.executeQuery(queryForMinPointLong);
            while(result.next()) {
                minPointLong.add(result.getDouble("longitudine"));
                minPointLong.add(result.getDouble("latitudine"));
            }
            result.close();

            result= statement.executeQuery(queryForMaxPointLat);
            while(result.next()) {
                maxPointLat.add(result.getDouble("longitudine"));
                maxPointLat.add(result.getDouble("latitudine"));
            }
            result.close();

            result=statement.executeQuery(queryForMinPointLat);
            while(result.next()) {
                minPointLat.add(result.getDouble("longitudine"));
                minPointLat.add(result.getDouble("latitudine"));
            }
            result.close();

            double avgLong = sqrt(power((maxPointLong.get(0)-minPointLong.get(0)),2)+power((maxPointLong.get(1)
                    -minPointLong.get(1)),2));
            double avgLat = sqrt(power((maxPointLat.get(0)-minPointLat.get(0)),2)+power((maxPointLat.get(1)
                    -minPointLat.get(1)),2));
            extension.add(avgLong);
            extension.add(avgLat);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                Objects.requireNonNull(connection).close();
                Objects.requireNonNull(statement).close();
                Objects.requireNonNull(result).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return extension;
    }

    /*
    Access to DB to find the number og segments in one filament
    @ Parameter: filament's ID or name
    @ Return an integer (number og segments)
    */
    public static int findNumberSegments(String filamentID_name) {
        int numOfSeg = 0;
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
            String query;
            if(Utility.isInteger(filamentID_name)){
                query =  "SELECT count(DISTINCT S.id) as numberOfSegments" +
                        " FROM strutturagalattica SG join filamento F on SG.filamento = F.id join segmento S ON " +
                                    "(SG.longitudine, SG.latitudine) = (S.longitudine, S.latitudine)" +
                        " WHERE SG.filamento = '" + filamentID_name + "' ";
            } else {
                query =  "SELECT count(DISTINCT S.id) as numberOfSegments" +
                        " FROM strutturagalattica SG join filamento F on SG.filamento = F.id join segmento S ON " +
                                    "(SG.longitudine, SG.latitudine) = (S.longitudine, S.latitudine)" +
                        " WHERE F.nome = '" + filamentID_name + "' ";
            }
            result = statement.executeQuery(query);

            while (result.next()) {
                numOfSeg = result.getInt("numberOfSegments");
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
        return numOfSeg;
    }

    /*
    Access to DB to find filaments that respect the limits of ellipse and brillance
    @ Parameter: percent of brillance, minimum ellipse, maximum ellipse
    @ Return: a list of Filaments that respect the features
     */
    public static ArrayList<Filament> findFilamentByContrastAndEllipse(double percentBrillanza, double minEllipse, double maxEllipse, int offset) {
        Connection connection = null;
        Statement statement = null;
        ResultSet result;
        ArrayList<Filament> filaments = new ArrayList<>();
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
            String query =  "SELECT DISTINCT * " +
                            " FROM filamento " +
                            " WHERE ellitticita >= '" + minEllipse + "' and ellitticita <= '" + maxEllipse + "' and " +
                                "contrasto > (" + percentBrillanza + "/100)+1 " +
                            " ORDER BY id";
            if(offset>=0)
                result = statement.executeQuery(query + pagination);
            else
                result = statement.executeQuery(query);

            while (result.next()) {
                Filament filament = new Filament();
                filament.setID(result.getInt("id"));
                filament.setName(result.getString("nome"));
                filament.setTotalFlux(result.getDouble("flussototale"));
                filament.setTemperature(result.getDouble("temperatura"));
                filament.setEllipse(result.getDouble("ellitticita"));
                filament.setConstrast(result.getDouble("contrasto"));
                filament.setDensity(result.getDouble("densita"));
                filaments.add(filament);
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
        return filaments;
    }


    /*
    Access to DB to find the number of all possible (and different) filaments
    @Parameter: none
    @Return: an integer that is the total number of filaments
     */
    public static int numAllFilaments(){
        Connection connection = null;
        Statement statement = null;
        ResultSet result;
        int total=0;

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
            String query =  "SELECT count(*) as totaleFilamenti" +
                            " FROM filamento ";
            result = statement.executeQuery(query);

            while (result.next()) {
                total=result.getInt("totaleFilamenti");
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
        return total;
    }

    /*
    Access to DB to find Filaments that have a number of segments included in a range
    @Parameter: minimum limit (of the range), maximum limit (of the range)
    @Return: a list of Filaments that respect the features
     */
    public static ArrayList<Filament> findFilamentsByNumOfSegments(int minSeg,int maxSeg, int offset) {
        Connection connection = null;
        Statement statement = null;
        ResultSet result;
        ArrayList<Filament> filaments = new ArrayList<>();
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
            String query =  "SELECT F.* " +
                            " FROM filamento F join strutturagalattica SG on F.id=SG.filamento join segmento S on " +
                                    "(SG.longitudine,SG.latitudine)=(S.longitudine,S.latitudine) " +
                            " WHERE SG.tipo= 'SEG' " +
                            " GROUP BY F.id" +
                            " HAVING count(DISTINCT S.id)<='" + maxSeg + "' and count(DISTINCT S.id)>='" + minSeg + "' ";
            if(offset>=0)
                result = statement.executeQuery(query + pagination);
            else
                result = statement.executeQuery(query);

            while (result.next()) {
                Filament filament = new Filament();
                filament.setID(result.getInt("id"));
                filament.setName(result.getString("nome"));
                filament.setTotalFlux(result.getDouble("flussototale"));
                filament.setTemperature(result.getDouble("temperatura"));
                filament.setEllipse(result.getDouble("ellitticita"));
                filament.setConstrast(result.getDouble("contrasto"));
                filament.setDensity(result.getDouble("densita"));
                filaments.add(filament);
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
        return filaments;
    }

    /*
    Access to DB to find filaments that are wholly included in a square
    @Parameter: longitude and latitude of the square's center, square's side
    @Return: list of Filaments included in the square
     */
    public static ArrayList<Filament> findFilamentInSquare(double longcenter,double latcenter,double side){
        Connection connection = null;
        Statement statement = null;
        ResultSet result;
        ArrayList<Filament> filaments = new ArrayList<>();

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
            double limit1 = longcenter -(side/2);
            double limit2 = longcenter +(side/2);
            double limit3 = latcenter - (side/2);
            double limit4 = latcenter + (side/2);
            String query =  "SELECT F.* " +
                            " FROM filamento F join strutturagalattica SG on F.id=SG.filamento " +
                            " WHERE SG.tipo= 'PER' " +
                            " GROUP BY F.id " +
                            " HAVING min(SG.longitudine)>= " + limit1 + " and max(SG.longitudine)<= " + limit2 +
                                    " and min(SG.latitudine)>= " + limit3 + " and max(SG.latitudine)<= " + limit4 +
                            " ORDER BY F.id";
            result = statement.executeQuery(query);

            while (result.next()) {
                Filament filament = new Filament();
                filament.setID(result.getInt("id"));
                filament.setName(result.getString("nome"));
                filament.setTotalFlux(result.getDouble("flussototale"));
                filament.setDensity(result.getDouble("densita"));
                filament.setTemperature(result.getDouble("temperatura"));
                filament.setEllipse(result.getDouble("ellitticita"));
                filament.setConstrast(result.getDouble("contrasto"));
                filaments.add(filament);
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
        return filaments;
    }

    /*
    Access to DB to find filaments that are wholly included in a circle
    @Parameter: longitude and latitude of the circle's center, circle's radius
    @Return: list of Filaments included in the circle
     */
    public static ArrayList<Filament> findFilamentInCircle(double longcenter,double latcenter,double radius){
        Connection connection = null;
        Statement statement = null;
        ResultSet result;
        ArrayList<Filament> filaments = new ArrayList<>();

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
            String query =  "SELECT F.* " +
                            " FROM filamento F join strutturagalattica SG on F.id=SG.filamento " +
                            " WHERE SG.tipo= 'PER' " +
                            " EXCEPT " +
                            " SELECT F.* " +
                            " FROM filamento F join strutturagalattica SG on F.id=SG.filamento " +
                            " WHERE SG.tipo= 'PER' " +
                            " GROUP BY F.id, SG.longitudine, SG.latitudine " +
                            " HAVING " + radius + " <= sqrt(power((SG.longitudine - " + longcenter + "),2) + power((SG.latitudine - " + latcenter + "),2)) ";
            result = statement.executeQuery(query);

            while (result.next()) {
                Filament filament = new Filament();
                filament.setID(result.getInt("id"));
                filament.setName(result.getString("nome"));
                filament.setTotalFlux(result.getDouble("flussototale"));
                filament.setDensity(result.getDouble("densita"));
                filament.setTemperature(result.getDouble("temperatura"));
                filament.setEllipse(result.getDouble("ellitticita"));
                filament.setConstrast(result.getDouble("contrasto"));
                filaments.add(filament);
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
        return filaments;
    }

    /*
    Access to DB to find if a filament is present in DB; this method is used to avoid sending an error for the calculation
    of informations required
    @Parameter: filament's ID or Name
    @Return: true only if the filament is in DB
     */
    public static boolean searchFilament(String filamentIdOrName){
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
            String query;
            // Esecuzione della query
            if(Utility.isInteger(filamentIdOrName)){
                query =  "SELECT DISTINCT id " +
                         "FROM filamento " +
                         "WHERE id = '" + filamentIdOrName +"' ";
            } else {
                query =  "SELECT DISTINCT nome " +
                         "FROM filamento " +
                         "WHERE nome = '" + filamentIdOrName +"' ";
            }
            result = statement.executeQuery(query);

            if(result.next())
                esito = true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(connection).close();
                Objects.requireNonNull(statement).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return esito;
    }
}
