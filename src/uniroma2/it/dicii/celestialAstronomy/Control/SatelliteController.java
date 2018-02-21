package uniroma2.it.dicii.celestialAstronomy.Control;

import uniroma2.it.dicii.celestialAstronomy.Repositories.SatelliteRepository;
import uniroma2.it.dicii.celestialAstronomy.View.SatelliteBean;

public class SatelliteController {

    /*
   Insert a new satellite in the database, updating also the tables "AGENZIA" and "MISSIONE" if necessary
    */
    public static synchronized boolean registerSatellite(SatelliteBean bean){
        boolean esito = SatelliteRepository.insertSatellite(bean.getName(), bean.getInitialdate(), bean.getEnddate());
        if(bean.getAgencies1() != null){
            SatelliteRepository.insertAgencies(bean.getAgencies1());
            esito = SatelliteRepository.updateMissione(bean.getName(), bean.getAgencies1());
        }
        if(bean.getAgencies2() != null){
            SatelliteRepository.insertAgencies(bean.getAgencies2());
            esito = SatelliteRepository.updateMissione(bean.getName(), bean.getAgencies2());
        }
        if(bean.getAgencies3() != null){
            SatelliteRepository.insertAgencies(bean.getAgencies3());
            esito = SatelliteRepository.updateMissione(bean.getName(), bean.getAgencies3());
        }
        if(bean.getAgencies4() != null){
            SatelliteRepository.insertAgencies(bean.getAgencies4());
            esito = SatelliteRepository.updateMissione(bean.getName(), bean.getAgencies4());
        }
        if(bean.getAgencies5() != null){
            SatelliteRepository.insertAgencies(bean.getAgencies5());
            esito = SatelliteRepository.updateMissione(bean.getName(), bean.getAgencies5());
        }
        return esito;
    }
}
