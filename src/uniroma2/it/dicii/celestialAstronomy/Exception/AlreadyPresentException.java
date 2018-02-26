package uniroma2.it.dicii.celestialAstronomy.Exception;

/**
 * This Exception is throwed when an element is already present in the database: it is used to avoid the insert of
 * duplicated values on primary key.
 */

public class AlreadyPresentException extends Exception {

    public AlreadyPresentException(){
        super("Exception: you insert an element already present in DataBase");
    }
}