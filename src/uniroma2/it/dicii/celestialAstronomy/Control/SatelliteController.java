package uniroma2.it.dicii.celestialAstronomy.Control;

import uniroma2.it.dicii.celestialAstronomy.Repositories.SatelliteRepository;
import uniroma2.it.dicii.celestialAstronomy.View.SatelliteBean;

/**
 * In this class you can find all methods to control actions and data related to Satellite concept.
 * Here the validation and control of data is managed.
 * Here the insert of elements in the database is managed.
 */

public class SatelliteController {

    /*
   Insert a new satellite in the database (one insert for each valid agency)
    */
    public static synchronized boolean registerSatellite(SatelliteBean bean){
        boolean esito = false;
        if(bean.getAgencies1() != null){
            esito = SatelliteRepository.insertSatellite(bean.getName(), bean.getAgencies1(), bean.getInitialdate(),
                    bean.getEnddate());
        }
        if(bean.getAgencies2() != null){
            esito = SatelliteRepository.insertSatellite(bean.getName(), bean.getAgencies2(), bean.getInitialdate(),
                    bean.getEnddate());
        }
        if(bean.getAgencies3() != null){
            esito = SatelliteRepository.insertSatellite(bean.getName(), bean.getAgencies3(), bean.getInitialdate(),
                    bean.getEnddate());
        }
        if(bean.getAgencies4() != null){
            esito = SatelliteRepository.insertSatellite(bean.getName(), bean.getAgencies4(), bean.getInitialdate(),
                    bean.getEnddate());
        }
        if(bean.getAgencies5() != null){
            esito = SatelliteRepository.insertSatellite(bean.getName(), bean.getAgencies5(), bean.getInitialdate(),
                    bean.getEnddate());
        }
        return esito;
    }
}
