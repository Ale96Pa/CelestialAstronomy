package uniroma2.it.dicii.celestialAstronomy.Repositories.Utility;

/**
 * In this class there are some useful methods used in the software.
 */

public class Utility {

    /*
    This methods is used to verify if the user insert the ID or the NAME of the filament
    @Return: true only if the string given in input is an integer
     */
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        // Only got here if we didn't return false
        return true;
    }
}