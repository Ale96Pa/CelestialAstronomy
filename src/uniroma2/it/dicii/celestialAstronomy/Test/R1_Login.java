package uniroma2.it.dicii.celestialAstronomy.Test;

import org.junit.Assert;
import org.junit.Test;
import uniroma2.it.dicii.celestialAstronomy.View.LoginBean;

public class R1_Login {

    /*
    Access of an registered user in the system (userid: ale123, pw: ale123)
     */
    @Test
    public void regularAccess(){
        LoginBean login = new LoginBean();
        login.setUsername("ale123");
        login.setPassword("ale123");
        Assert.assertTrue(login.validateUser());
    }

    /*
    Access of an unregistered user (userid: ghost, pw: void)
    Attention: this user never should be in DB because both userid and password have less then 6 characters
     */
    @Test
    public void irregularAccess(){
        LoginBean login = new LoginBean();
        login.setUsername("ghost");
        login.setPassword("void");
        Assert.assertFalse(login.validateUser());
    }
}
