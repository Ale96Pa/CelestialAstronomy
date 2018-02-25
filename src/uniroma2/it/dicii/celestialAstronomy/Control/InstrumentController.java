package uniroma2.it.dicii.celestialAstronomy.Control;

import uniroma2.it.dicii.celestialAstronomy.Repositories.InstrumentRepository;
import uniroma2.it.dicii.celestialAstronomy.View.InstrumentBean;

/**
 * In this class you can find all methods to control actions and data related to Instrument concept.
 * Here the validation and control of data is managed.
 * Here the insert of elements in the database is managed.
 */

public class InstrumentController {

    /*
    Insert a new instrument in the database (one insert for each valid band)
     */
    public static synchronized boolean registerInstrument(InstrumentBean bean){
        boolean esito = false;
        if(bean.getBand1() != 0){
            esito = InstrumentRepository.insertInstrument(bean.getName(), bean.getBand1());
        }
        if(bean.getBand2() != 0){
            esito = InstrumentRepository.insertInstrument(bean.getName(), bean.getBand2());
        }
        if(bean.getBand3() != 0){
            esito = InstrumentRepository.insertInstrument(bean.getName(), bean.getBand3());
        }
        if(bean.getBand4() != 0){
            esito = InstrumentRepository.insertInstrument(bean.getName(), bean.getBand4());
        }
        if(bean.getBand5() != 0){
            esito = InstrumentRepository.insertInstrument(bean.getName(), bean.getBand5());
        }
        return esito;
    }
}
