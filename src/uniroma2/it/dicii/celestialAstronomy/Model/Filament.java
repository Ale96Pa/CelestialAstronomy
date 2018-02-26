package uniroma2.it.dicii.celestialAstronomy.Model;

public class Filament {

    //Attributi
    private int ID;
    private String name;
    private double totalFlux;
    private double density;
    private double temperature;
    private double ellipse;
    private double constrast;

    private int numOfSegments;

    // Costruttore
    public Filament() {
    }

    //Metodi
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

    public double getTotalFlux() {
        return totalFlux;
    }

    public void setTotalFlux(double totalFlux) {
        this.totalFlux = totalFlux;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getEllipse() {
        return ellipse;
    }

    public void setEllipse(double ellipse) {
        this.ellipse = ellipse;
    }

    public double getConstrast() {
        return constrast;
    }

    public void setConstrast(double constrast) {
        this.constrast = constrast;
    }

    public int getNumOfSegments() {
        return numOfSegments;
    }

    public void setNumOfSegments(int numOfSegments) {
        this.numOfSegments = numOfSegments;
    }
}
