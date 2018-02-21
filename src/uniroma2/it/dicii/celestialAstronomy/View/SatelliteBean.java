package uniroma2.it.dicii.celestialAstronomy.View;

import java.time.LocalDate;

public class SatelliteBean {

    // Attributi
    private String name;

    private int initialday;
    private int initialmonth;
    private int initialyear;
    private LocalDate initialdate;

    private int endday;
    private int endmonth;
    private int endyear;
    private LocalDate enddate;

    private String agencies1;
    private String agencies2;
    private String agencies3;
    private String agencies4;
    private String agencies5;

    // Metodi
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInitialday() {
        return initialday;
    }

    public void setInitialday(int initialday) {
        this.initialday = initialday;
    }

    public int getInitialmonth() {
        return initialmonth;
    }

    public void setInitialmonth(int initialmonth) {
        this.initialmonth = initialmonth;
    }

    public int getInitialyear() {
        return initialyear;
    }

    public void setInitialyear(int initialyear) {
        this.initialyear = initialyear;
    }

    public LocalDate getInitialdate() {
        initialdate = LocalDate.of(getInitialyear(), getInitialmonth(), getInitialday());
        return initialdate;
    }

    public void setInitialdate(LocalDate initialdate) {
        this.initialdate = initialdate;
    }

    public int getEndday() {
        return endday;
    }

    public void setEndday(int endday) {
        this.endday = endday;
    }

    public int getEndmonth() {
        return endmonth;
    }

    public void setEndmonth(int endmonth) {
        this.endmonth = endmonth;
    }

    public int getEndyear() {
        return endyear;
    }

    public void setEndyear(int endyear) {
        this.endyear = endyear;
    }

    public LocalDate getEnddate() {
        if(getEndmonth() != 0)
            enddate = LocalDate.of(getEndyear(), getEndmonth(), getEndday());
        else
            enddate = null;
        return enddate;
    }

    public void setEnddate(LocalDate enddate) {
        this.enddate = enddate;
    }

    public String getAgencies1() {
        return agencies1;
    }

    public void setAgencies1(String agencies1) {
        this.agencies1 = agencies1;
    }

    public String getAgencies2() {
        return agencies2;
    }

    public void setAgencies2(String agencies2) {
        this.agencies2 = agencies2;
    }

    public String getAgencies3() {
        return agencies3;
    }

    public void setAgencies3(String agencies3) {
        this.agencies3 = agencies3;
    }

    public String getAgencies4() {
        return agencies4;
    }

    public void setAgencies4(String agencies4) {
        this.agencies4 = agencies4;
    }

    public String getAgencies5() {
        return agencies5;
    }

    public void setAgencies5(String agencies5) {
        this.agencies5 = agencies5;
    }
}
