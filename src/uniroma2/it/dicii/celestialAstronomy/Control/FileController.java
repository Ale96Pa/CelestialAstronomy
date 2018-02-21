package uniroma2.it.dicii.celestialAstronomy.Control;

import uniroma2.it.dicii.celestialAstronomy.Repositories.FileRepository;
import uniroma2.it.dicii.celestialAstronomy.View.CsvFileBean;

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
            case "contorni": elementsUpdated = FileRepository.insertPerimeterFile(pathname);
                break;
            case "scheletro": elementsUpdated = FileRepository.insertSkeletonFile(pathname);
                break;
            case "filamenti": elementsUpdated =  FileRepository.insertFilamentFile(pathname);
                break;
            case "stelle": elementsUpdated =  FileRepository.insertStarFile(pathname);
                break;
            default: elementsUpdated = 0;
        }
        return elementsUpdated;
    }
}
