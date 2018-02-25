package uniroma2.it.dicii.celestialAstronomy.Exception;

/**
 * This Exception is used when no results are found in the database: it is used to stop the process, avoiding the
 * executions of methods uselessly.
 */

public class NoDataFoundException extends Exception {

    public NoDataFoundException(){
        super("0 data found: end process");
    }
}
