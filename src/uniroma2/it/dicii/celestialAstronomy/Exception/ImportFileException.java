package uniroma2.it.dicii.celestialAstronomy.Exception;

/**
 * This Exception is throwed when you import a file with components of filaments, but some filaments aren't in the
 * database: it is used to avoid the violation of foreign key constraint.
 */

public class ImportFileException extends Exception {

    public ImportFileException(){
        super("Violated some constraint in import file: import filaments BEFORE than perimeter or skeleton or star");
    }
}