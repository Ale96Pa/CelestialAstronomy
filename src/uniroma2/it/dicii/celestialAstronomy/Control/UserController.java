package uniroma2.it.dicii.celestialAstronomy.Control;

import uniroma2.it.dicii.celestialAstronomy.Exception.WrongDataException;
import uniroma2.it.dicii.celestialAstronomy.Repositories.UserRepository;
import uniroma2.it.dicii.celestialAstronomy.View.UserBean;

/**
 * In this class you can find all methods to control actions and data related to User concept.
 * Here the validation of data is managed.
 * Here the insert of elements in the database is managed.
 */

public class UserController {

    /*
    Insert of a new user in the database using data given in input
     */
    public static synchronized boolean registerUser(UserBean bean) {
        boolean esito;
        try {
            if (!bean.validateUser())
                throw new WrongDataException();
            else
                esito = UserRepository.insertUser(bean.getNome(), bean.getCognome(), bean.getUsername(), bean.getPassword(),
                        bean.getEmail(), bean.isAdministrator());
        } catch (WrongDataException e) {
            esito = false;
            e.printStackTrace();
        }
        return esito;
    }
}
