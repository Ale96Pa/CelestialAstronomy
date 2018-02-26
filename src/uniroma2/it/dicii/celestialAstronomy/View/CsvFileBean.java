package uniroma2.it.dicii.celestialAstronomy.View;

public class CsvFileBean {

    // Attributi
    private static final String absolutePath = "D:\\Alessandro\\Documents\\4.Programmi\\DataBase\\ProgettoDB\\Implementazione\\CelestialAstronomy\\src\\uniroma2\\it\\dicii\\celestialAstronomy\\Files\\";

    private String filename;
    private String type;
    private int numrows;
    private int offset;

    // Metodi bean
    public String getFilename() {
        String effectiveName;
        switch (filename){
            case "1": effectiveName = absolutePath+"contorni_filamenti_Herschel.csv";
                break;
            case "2": effectiveName = absolutePath+"contorni_filamenti_Spitzer.csv";
                break;
            case "3": effectiveName = absolutePath+"scheletro_filamenti_Herschel.csv";
                break;
            case "4": effectiveName= absolutePath+"scheletro_filamenti_Spitzer.csv";
                break;
            case "5": effectiveName= absolutePath+"filamenti_Herschel.csv";
                break;
            case "6": effectiveName= absolutePath+"filamenti_Spitzer.csv";
                break;
            case "7": effectiveName= absolutePath+"stelle_Herschel.csv";
                break;
            default: effectiveName=null;
                break;
        }
        return effectiveName;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getType() {
        String type;
        switch (filename){
            case "1": type = "contorni";
                break;
            case "2": type = "contorni";
                break;
            case "3": type = "scheletro";
                break;
            case "4": type = "scheletro";
                break;
            case "5": type = "filamenti";
                break;
            case "6": type = "filamenti";
                break;
            case "7": type = "stelle";
                break;
            default: type=null;
                break;
        }
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static String getAbsolutePath() {
        return absolutePath;
    }

    public int getNumrows() {
        return numrows;
    }

    public void setNumrows(int numrows) {
        this.numrows = numrows;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}