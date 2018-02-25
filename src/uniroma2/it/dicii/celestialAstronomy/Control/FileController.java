package uniroma2.it.dicii.celestialAstronomy.Control;

import uniroma2.it.dicii.celestialAstronomy.Repositories.FileRepository;
import uniroma2.it.dicii.celestialAstronomy.View.CsvFileBean;

/**
 * In this class you can find all methods to control importing of data related from a file.
 * The models are used to notify the View about changes.
 */

public class FileController {

    /*
    Import the data of a file in the Database depending on the file pathname given in input
    @Return the elements updated or inserted
     */
    public static synchronized int importFile(CsvFileBean bean){
        String pathname = bean.getFilename();
        String typeFile = bean.getType();
        int elementsUpdated;

        switch (typeFile){
            case "contorni": elementsUpdated = FileRepository.insertPerimeterFile(pathname, bean.getNumrows(),
                    bean.getOffset());
                break;
            case "scheletro": elementsUpdated = FileRepository.insertSkeletonFile(pathname, bean.getNumrows(),
                    bean.getOffset());
                break;
            case "filamenti": elementsUpdated =  FileRepository.insertFilamentFile(pathname, bean.getNumrows(),
                    bean.getOffset());
                break;
            case "stelle": elementsUpdated =  FileRepository.insertStarFile(pathname, bean.getNumrows(),
                    bean.getOffset(), "contorni_filamenti_Herschel.csv");
                break;
            default: elementsUpdated = 0;
        }
        return elementsUpdated;
    }
}
