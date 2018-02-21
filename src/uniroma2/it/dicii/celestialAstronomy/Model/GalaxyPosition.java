package uniroma2.it.dicii.celestialAstronomy.Model;

public class GalaxyPosition {

    // Attributi
    private double longitude;
    private double latitude;

    // Costruttore
    public GalaxyPosition(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    //Metodi
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
