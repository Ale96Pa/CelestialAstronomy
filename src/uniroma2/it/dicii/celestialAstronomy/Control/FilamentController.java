package uniroma2.it.dicii.celestialAstronomy.Control;

import uniroma2.it.dicii.celestialAstronomy.Exception.WrongDataException;
import uniroma2.it.dicii.celestialAstronomy.Model.Extension;
import uniroma2.it.dicii.celestialAstronomy.Model.Filament;
import uniroma2.it.dicii.celestialAstronomy.Model.GalaxyPosition;
import uniroma2.it.dicii.celestialAstronomy.Model.Star;
import uniroma2.it.dicii.celestialAstronomy.Repositories.FilamentRepository;
import uniroma2.it.dicii.celestialAstronomy.Repositories.StarRepository;
import uniroma2.it.dicii.celestialAstronomy.View.FilamentBean;
import uniroma2.it.dicii.celestialAstronomy.View.RegionBean;
import java.util.ArrayList;
import java.util.Objects;

public class FilamentController {

    /*
    Return the centroide (as a particular GalaxyPosition) of a filament given in input
    {for REQ-FN-5}
     */
    public static GalaxyPosition findCentroide(FilamentBean bean){
        ArrayList<Double> position = FilamentRepository.findCentroide(bean.getIdOrName());
        return new GalaxyPosition(position.get(0), position.get(1));
    }

    /*
    Return the Extension of a filament given in input
    {for REQ-FN-5}
     */
    public static Extension findExtension(FilamentBean bean){
        ArrayList<Double> extensions = FilamentRepository.findPerimeterExtension(bean.getIdOrName());
        return new Extension(extensions.get(0), extensions.get(1));
    }

    /*
    Return the number of segment included in a filament given in input
    {for REQ-FN-5}
     */
    public static int findNumOfSegments(FilamentBean bean){
        return FilamentRepository.findNumberSegments(bean.getIdOrName());
    }

    /*
    Return the list of Filaments that respect the limits of brillance and ellipse
    {for REQ-FN-6}
     */
    public static ArrayList<Filament> findFilamentByEllipseAndContrast(FilamentBean bean, int offset){
        try{
            if(bean.getBrillance() <= 0)
                throw new WrongDataException();
            else if(bean.getMinEllipse() <= 1 || bean.getMaxEllipse() >= 10)
                throw new WrongDataException();
            else if(bean.getMinEllipse() > bean.getMaxEllipse())
                throw new WrongDataException();
        } catch (WrongDataException e){
            e.printStackTrace();
        }
        return FilamentRepository.findFilamentByContrastAndEllipse(bean.getBrillance(), bean.getMinEllipse(), bean.getMaxEllipse(),offset);
    }

    /*
    Return the rate (in per cent) of Filaments that respect the limits of brillance and ellipse
    {for REQ-FN-6}
     */
    public static double rateFilamentRequired(ArrayList<Filament> filamentsRequired){
        int allFilaments = FilamentRepository.numAllFilaments();
        int numFilamentsRequired = filamentsRequired.size();
        return (double) numFilamentsRequired/allFilaments * 100;
    }

    /*
    Return the list of Filaments that have a number of segment conteined into a limited range
    {for REQ-FN-7}
     */
    public static ArrayList<Filament> findFilamentBySegments(FilamentBean bean, int offset){
        try{
            if(bean.getMinNumOfSegment() <= 2)
                throw new WrongDataException();
            else if(bean.getMinNumOfSegment() > bean.getMaxNumOfSegment())
                throw new WrongDataException();
        } catch (WrongDataException e){
            e.printStackTrace();
        }
        return FilamentRepository.findFilamentsByNumOfSegments(bean.getMinNumOfSegment(), bean.getMaxNumOfSegment(), offset);
    }

    /*
    Return the Filaments included in a region given in input (square or circle)
    {for REQ-FN-8}
     */
    public static ArrayList<Filament> findFilamentsInRegion(RegionBean bean){
        try {
            if(bean.getSideOrRadius() <= 0)
                throw new WrongDataException();
        } catch (WrongDataException e){
            e.printStackTrace();
        }
        ArrayList<Filament> filaments;
        if(Objects.equals(bean.getType(), "square"))
            filaments = FilamentRepository.findFilamentInSquare(bean.getLongitudeCenter(), bean.getLatitudeCenter(), bean.getSideOrRadius());
        else
            filaments = FilamentRepository.findFilamentInCircle(bean.getLongitudeCenter(), bean.getLatitudeCenter(), bean.getSideOrRadius());
        return filaments;
    }

    /*
    Return the list of Stars included in a Filament and the distance from backbone
    {for REQ-FN-12}
     */
    public static ArrayList<Star> findStarsByDistanceFromBackbone(FilamentBean bean, int offset){
        return StarRepository.findPositionFromBackbone(bean.getIdOrName(), offset, bean.getOrder());
    }

    /*
    @Return: true if the segment is in DB
    {used to control}
     */
    public static boolean findFilamentInDb(FilamentBean bean){
        return FilamentRepository.searchFilament(bean.getIdOrName());
    }
}
