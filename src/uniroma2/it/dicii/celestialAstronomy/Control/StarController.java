package uniroma2.it.dicii.celestialAstronomy.Control;

import uniroma2.it.dicii.celestialAstronomy.Exception.WrongDataException;
import uniroma2.it.dicii.celestialAstronomy.Model.Star;
import uniroma2.it.dicii.celestialAstronomy.Repositories.StarRepository;
import uniroma2.it.dicii.celestialAstronomy.Repositories.Utility.TypeOfStars;
import uniroma2.it.dicii.celestialAstronomy.View.RegionBean;
import uniroma2.it.dicii.celestialAstronomy.View.StarBean;
import java.util.ArrayList;
import java.util.HashMap;

public class StarController {

    /*
    Return the list of stars included in a filament
    {for REQ-FN-9}
     */
    public static ArrayList<Star> findStarsInFilament(StarBean bean){
        return StarRepository.findStarInFilament(bean.getFilamentID());
    }

    /*
    Return the rate (in per cent) for each type of star included in a filament
    {for REQ-FN-9}
     */
    public static HashMap rateByType(StarBean bean, ArrayList<Star> starsInFilament){
        HashMap<String, Integer> DbStarsByType = StarRepository.findNumStarByType(bean.getFilamentID());
        HashMap<String, Double> rateStarsByType = new HashMap<>();
        for(TypeOfStars allType : TypeOfStars.values()){
            double rate = (double) DbStarsByType.get(allType.toString())/starsInFilament.size() * 100;
            rateStarsByType.put(allType.toString(), rate);
        }
        return rateStarsByType;
    }

    /*
    Return the list of stars included in a rectangle
    {for REQ-FN-10}
     */
    public static ArrayList<Star> findStarInRectangle (RegionBean bean){
        try{
            if(bean.getBase()<=0 || bean.getHigh()<=0)
                throw new WrongDataException();
        } catch (WrongDataException e){
            e.printStackTrace();
        }
        return StarRepository.findStarInRectangle(bean.getLongitudeCenter(), bean.getLatitudeCenter(), bean.getBase(), bean.getHigh());
    }

    /*
    Return the list of stars included both in a rectangle and in a filament
    {for REQ-FN-10}
     */
    private static ArrayList<Star> findStarInRectangleAndFilament(RegionBean bean) {
        try{
            if(bean.getBase()<=0 || bean.getHigh()<=0)
                throw new WrongDataException();
        } catch (WrongDataException e){
            e.printStackTrace();
        }
        return StarRepository.findStarInRectangleAndFilament(bean.getLongitudeCenter(), bean.getLatitudeCenter(), bean.getBase(), bean.getHigh());
    }

    /*
   Return the list of stars included in a rectangle, but not in a filament
   {for REQ-FN-10}
    */
    public static ArrayList<Star> findStarOutOfFilament(RegionBean bean){

        ArrayList<Star> stars = findStarInRectangle(bean);
        ArrayList<Star> starsInFilament = findStarInRectangleAndFilament(bean);

        for (Star aStarsInFilament : starsInFilament) {
            for (int j = 0; j < stars.size(); j++) {
                Star s2 = stars.get(j); //all stars
                if (aStarsInFilament.getID() == s2.getID()) {
                    stars.remove(j);
                }
            }
        }
        return stars;
    }

    /*
    Return the rate (in per cent) of each type of stars included both in a rectangle and in a filament
    {for REQ-FN-10}
     */
    public static HashMap rateByTypeInFilament(RegionBean bean){
        HashMap<String, Integer> StarsByTypeInRegion = StarRepository.findNumStarByTypeInRectangle(bean.getLongitudeCenter(),bean.getLatitudeCenter(),bean.getBase(),bean.getHigh());
        HashMap<String,Integer> StarsByTypeInRegionAndFilament =StarRepository.findNumStarByTypeInRectangleAndFilament(bean.getLongitudeCenter(),bean.getLatitudeCenter(),bean.getBase(),bean.getHigh());
        HashMap<String, Double> rateStarsByType = new HashMap<>();
        for(TypeOfStars allType : TypeOfStars.values()){
            double rate = (double) StarsByTypeInRegionAndFilament.get(allType.toString())/StarsByTypeInRegion.get(allType.toString()) * 100;
            rateStarsByType.put(allType.toString(), rate);
        }
        return rateStarsByType;
    }

    /*
    Return the rate (in per cent) of each type of stars included rectangle but not in a filament
    {for REQ-FN-10}
     */
    public static HashMap rateByTypeOutFilament(RegionBean bean){
        HashMap rateInRectangle = StarRepository.findNumStarByTypeInRectangle(bean.getLongitudeCenter(), bean.getLatitudeCenter(), bean.getBase(), bean.getHigh());
        HashMap rateInRectangleAndFilament = StarRepository.findNumStarByTypeInRectangleAndFilament(bean.getLongitudeCenter(), bean.getLatitudeCenter(), bean.getBase(), bean.getHigh());
        for(TypeOfStars allType : TypeOfStars.values()){
            String key = allType.toString();
            int valueInRectangle = (int) rateInRectangle.get(key);
            int valueInRectangleAndFilament = (int) rateInRectangleAndFilament.get(key);
            int valueOut = valueInRectangle-valueInRectangleAndFilament;
            double rate = (double) valueOut/valueInRectangle*100;
            rateInRectangle.put(key, rate);
        }
        return rateInRectangle;
    }
}
