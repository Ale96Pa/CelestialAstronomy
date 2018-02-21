package uniroma2.it.dicii.celestialAstronomy.Model;

public class Star {

    // Attributi
    private int ID;
    private String name;
    private double latitude;
    private double longitude;
    private String type;
    private double flux;

    private double distanceFromBackbone;

    // Costruttore
    public Star() {
    }

    // Metodi
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getFlux() {
        return flux;
    }

    public void setFlux(double flux) {
        this.flux = flux;
    }

    public double getDistanceFromBackbone() {
        return distanceFromBackbone;
    }

    public void setDistanceFromBackbone(double distanceFromBackbone) {
        this.distanceFromBackbone = distanceFromBackbone;
    }
}
