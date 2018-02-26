package uniroma2.it.dicii.celestialAstronomy.Exception;

/**
 * This Exception is throwed when the user insert an invalid input: it is used to control the constraint imposed by the
 * customer and to avoid the execution of methods uselessly.
 */

public class WrongDataException extends Exception {

    public WrongDataException(){ super("Exception: you insert wrong data; check your input"); }
}