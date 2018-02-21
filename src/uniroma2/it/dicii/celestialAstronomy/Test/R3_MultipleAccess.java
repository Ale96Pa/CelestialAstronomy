package uniroma2.it.dicii.celestialAstronomy.Test;


public class R3_MultipleAccess extends Thread {

    public static void multipleAccess(){
        Admin.deleteElementForTest();
        Admin admin1 = new Admin(1, 1000);
        Thread a1 = new Thread(admin1);

        User user1 = new User(2,990);
        Thread u1 = new Thread(user1);
        User user2 = new User(3,1000);
        Thread u2 = new Thread(user2);
        User user3 = new User(4,1010);
        Thread u3 = new Thread(user3);

        System.out.println("User 1 use application");
        u1.start();
        System.out.println("Admin update data");
        a1.start();
        System.out.println("User 2 use application");
        u2.start();
        System.out.println("User 3 use application");
        u3.start();
    }

    public static void main(String args[]){
        multipleAccess();
    }
}
