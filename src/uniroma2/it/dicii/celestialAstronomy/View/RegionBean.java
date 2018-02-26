package uniroma2.it.dicii.celestialAstronomy.View;

public class RegionBean {

    // Attributi
    private double longitudeCenter;
    private double latitudeCenter;
    private double sideOrRadius;
    private boolean type;
    private double base;
    private double high;

    // Metodi
    public double getLongitudeCenter() {
        return longitudeCenter;
    }

    public void setLongitudeCenter(double longitudeCenter) {
        this.longitudeCenter = longitudeCenter;
    }

    public double getLatitudeCenter() {
        return latitudeCenter;
    }

    public void setLatitudeCenter(double latitudeCenter) {
        this.latitudeCenter = latitudeCenter;
    }

    public double getSideOrRadius() {
        return sideOrRadius;
    }

    public void setSideOrRadius(double sideOrRadius) {
        this.sideOrRadius = sideOrRadius;
    }

    public String getType() {
        String effectiveType;
        switch (String.valueOf(type)){
            case "true": effectiveType = "square";
            break;
            case "false": effectiveType = "circle";
            break;
            default: effectiveType=null;
        }
        return effectiveType;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }
}