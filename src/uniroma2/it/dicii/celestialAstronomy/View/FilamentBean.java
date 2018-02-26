package uniroma2.it.dicii.celestialAstronomy.View;

public class FilamentBean {

    // Attributi
    private static String idOrName;
    private static double brillance;
    private static double minEllipse;
    private static double maxEllipse;
    private static int minNumOfSegment;
    private static int maxNumOfSegment;

    private static int pages; // number of pages necessary to paginate the results
    private static String order; // type of order for REQ-FN-12 (order by Flux or Extension)

    // Metodi
    public static String getIdOrName() {
        return idOrName;
    }

    public void setIdOrName(String idOrName) {
        FilamentBean.idOrName = idOrName;
    }

    public static double getBrillance() {
        return brillance;
    }

    public void setBrillance(double brillance) {
        FilamentBean.brillance = brillance;
    }

    public static double getMinEllipse() {
        return minEllipse;
    }

    public void setMinEllipse(double minEllipse) {
        FilamentBean.minEllipse = minEllipse;
    }

    public static double getMaxEllipse() {
        return maxEllipse;
    }

    public void setMaxEllipse(double maxEllipse) {
        FilamentBean.maxEllipse = maxEllipse;
    }

    public static int getMinNumOfSegment() {
        return minNumOfSegment;
    }

    public void setMinNumOfSegment(int minNumOfSegment) {
        FilamentBean.minNumOfSegment = minNumOfSegment;
    }

    public static int getMaxNumOfSegment() {
        return maxNumOfSegment;
    }

    public void setMaxNumOfSegment(int maxNumOfSegment) {
        FilamentBean.maxNumOfSegment = maxNumOfSegment;
    }

    public static int getPages() {
        return pages;
    }

    public void setPages(int page) {
        FilamentBean.pages = page;
    }

    public static String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        FilamentBean.order = order;
    }
}