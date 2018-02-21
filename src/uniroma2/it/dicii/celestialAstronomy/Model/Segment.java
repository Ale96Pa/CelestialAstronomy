package uniroma2.it.dicii.celestialAstronomy.Model;

public class Segment {

    // Attributi
    private int ID;
    private double longitude;
    private double latitude;
    private String type;
    private double flux;
    private int nProgressive;

    private GalaxyPosition vertexMin;
    private GalaxyPosition vertexMax;
    private double distanceVertexMin;
    private double distanceVertexMax;

    // Costruttore
    public Segment(){}

    // Metodi
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

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

    public int getnProgressive() {
        return nProgressive;
    }

    public void setnProgressive(int nProgressive) {
        this.nProgressive = nProgressive;
    }

    public GalaxyPosition getVertexMin() {
        return vertexMin;
    }

    public void setVertexMin(GalaxyPosition vertexMin) {
        this.vertexMin = vertexMin;
    }

    public GalaxyPosition getVertexMax() {
        return vertexMax;
    }

    public void setVertexMax(GalaxyPosition vertexMax) {
        this.vertexMax = vertexMax;
    }

    public double getDistanceVertexMin() {
        return distanceVertexMin;
    }

    public void setDistanceVertexMin(double distanceVertexMin) {
        this.distanceVertexMin = distanceVertexMin;
    }

    public double getDistanceVertexMax() {
        return distanceVertexMax;
    }

    public void setDistanceVertexMax(double distanceVertexMax) {
        this.distanceVertexMax = distanceVertexMax;
    }
}
