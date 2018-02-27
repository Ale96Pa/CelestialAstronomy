package uniroma2.it.dicii.celestialAstronomy.Test;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(value = Suite.class)
@Suite.SuiteClasses(value = {R1_Login.class, R2_3_4_Administrator.class, R5_InformationFilament.class,
        R6_SearchFilamentByContrastEllipse.class, R7_SearchFilamentByNumberSegments.class, R8_SearchFilamentInRegion.class,
        R9_SearchInclusionStar.class, R10_RateOfStars.class, R11_DistanceVertexSegment.class, R12_PositionStarBackbone.class})
public class AllTest {
}
