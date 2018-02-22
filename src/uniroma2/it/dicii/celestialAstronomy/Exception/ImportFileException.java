package uniroma2.it.dicii.celestialAstronomy.Exception;

public class ImportFileException extends Exception {

    public ImportFileException(){
        super("Violated some constraint in import file: import filaments BEFORE than perimeter or skeleton or star");
    }
}
