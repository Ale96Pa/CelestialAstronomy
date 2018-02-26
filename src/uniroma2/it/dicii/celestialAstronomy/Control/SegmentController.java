package uniroma2.it.dicii.celestialAstronomy.Control;

import uniroma2.it.dicii.celestialAstronomy.Model.GalaxyPosition;
import uniroma2.it.dicii.celestialAstronomy.Repositories.SegmentRepository;
import uniroma2.it.dicii.celestialAstronomy.View.SegmentBean;
import java.util.ArrayList;

/**
 * In this class you can find all methods to control actions and data related to Segment concept.
 * Here the validation and control of data is managed.
 * The models are used to notify the View about changes.
 */

public class SegmentController {

    /*
    Return the distances of the segment's vertexes (minimum and maximum), where the segment's ID is given in input
    {for REQ-FN-11}
     */
    public static ArrayList<Double> findDistanceOfSegment(SegmentBean bean){
        ArrayList<Double> distances = new ArrayList<>();

        ArrayList<Double> minVertex = SegmentRepository.findMinVertexOfSegment(bean.getID());
        GalaxyPosition minimumVertex = new GalaxyPosition(minVertex.get(0), minVertex.get(1));
        ArrayList<Double> maxVertex = SegmentRepository.findMaxVertexOfSegment(bean.getID());
        GalaxyPosition maximumVertex = new GalaxyPosition(maxVertex.get(0), maxVertex.get(1));

        String filamentContainer = SegmentRepository.findFilamentOfSegment(bean.getID());

        double distanceOfMinimum = SegmentRepository.distanceVertexFromPerimeter(minimumVertex.getLongitude(), minimumVertex.getLatitude(), filamentContainer);
        double distanceOfMaximum = SegmentRepository.distanceVertexFromPerimeter(maximumVertex.getLongitude(), maximumVertex.getLatitude(), filamentContainer);
        distances.add(distanceOfMinimum);
        distances.add(distanceOfMaximum);
        return distances;
    }

    /*
    @Return: true if the segment is in DB
    {used to control}
     */
    public static boolean findSegmentById(SegmentBean bean){
        return SegmentRepository.searchSegmentByID(bean.getID());
    }
}