package uniroma2.it.dicii.celestialAstronomy.Repositories.Utility;

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
    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        // Only got here if we didn't return false
        return true;
    }

}
