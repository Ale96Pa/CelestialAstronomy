package uniroma2.it.dicii.celestialAstronomy.Control;

import uniroma2.it.dicii.celestialAstronomy.Exception.WrongDataException;
import uniroma2.it.dicii.celestialAstronomy.Repositories.UserRepository;
import uniroma2.it.dicii.celestialAstronomy.View.UserBean;

public class UserController {

    /*
    Insert of a new user in the database using data given in input
     */
    public static synchronized boolean registerUser(UserBean bean) {
        boolean esito = false;
        try {
            if (!bean.validateUser())
                throw new WrongDataException();
            esito = UserRepository.insertUser(bean.getNome(), bean.getCognome(), bean.getUsername(), bean.getPassword(),
                    bean.getEmail(), bean.isAdministrator());
        } catch (WrongDataException e) {
            e.printStackTrace();
        }
        return esito;
    }
}
