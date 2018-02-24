package uniroma2.it.dicii.celestialAstronomy.Control;

import uniroma2.it.dicii.celestialAstronomy.Exception.WrongDataException;
import uniroma2.it.dicii.celestialAstronomy.Repositories.UserRepository;
import uniroma2.it.dicii.celestialAstronomy.View.UserBean;

import static java.lang.Math.abs;
import static java.lang.Math.atan;

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

    public static void main(String args[]){
        foo(7,7);
        foo(-1, 2);
    }


    public static void foo(double num1, double num2){
        double num = (1-num1) * (3-num2) - (1-num2) * (-2-num1);
        double den = (1-num1) * (-2-num1) + (1-num2) * (3-num2);
        double res = num/den;
        double at = atan(res);
        double att = Math.toRadians(at);

        double num22 = (-2-num1) * (1-num2) - (3-num2) * (-2-num1);
        double den22 = (-2-num1) * (-2-num1) + (3-num2) * (1-num2);
        double res22 = num22/den22;
        double at2 = atan(res22);
        double att2 = Math.toRadians(at2);

        double sum = abs(att+att2);
        System.out.println(sum);
    }
}
