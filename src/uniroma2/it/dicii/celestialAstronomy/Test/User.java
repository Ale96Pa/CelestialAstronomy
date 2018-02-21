package uniroma2.it.dicii.celestialAstronomy.Test;

import uniroma2.it.dicii.celestialAstronomy.Control.FilamentController;
import uniroma2.it.dicii.celestialAstronomy.View.FilamentBean;

public class User extends Thread {

    private int time;
    private int id;

    public User(int id, int time){
        this.id=id;
        this.time=time;
    }

    public void run(){
        try{
            sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        FilamentBean filament = new FilamentBean();
        filament.setIdOrName("45");
        FilamentController.findNumOfSegments(filament);
        FilamentController.findCentroide(filament);
        FilamentController.findExtension(filament);
        System.out.println("User " + id + " ends to use application");
    }
}
