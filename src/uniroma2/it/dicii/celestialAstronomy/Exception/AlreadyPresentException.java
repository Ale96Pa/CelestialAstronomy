package uniroma2.it.dicii.celestialAstronomy.Exception;


public class AlreadyPresentException extends Exception {

    public AlreadyPresentException(){
        super("Exception: you insert an element already present in DataBase");
    }
}
