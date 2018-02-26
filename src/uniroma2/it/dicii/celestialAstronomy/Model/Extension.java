package uniroma2.it.dicii.celestialAstronomy.Model;

public class Extension {

    // Attributi
    private double longitudinalExtension;
    private double latitudinalExtension;

    // Costruttore
    public Extension(double longitudinalExtension, double latitudinalExtension) {
        this.longitudinalExtension = longitudinalExtension;
        this.latitudinalExtension = latitudinalExtension;
    }

    // Metodi
    public double getLongitudinalExtension() {
        return longitudinalExtension;
    }

    public void setLongitudinalExtension(double longitudinalExtension) {
        this.longitudinalExtension = longitudinalExtension;
    }

    public double getLatitudinalExtension() {
        return latitudinalExtension;
    }

    public void setLatitudinalExtension(double latitudinalExtension) {
        this.latitudinalExtension = latitudinalExtension;
    }
}