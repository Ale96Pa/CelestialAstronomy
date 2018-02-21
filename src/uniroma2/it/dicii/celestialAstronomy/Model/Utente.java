package uniroma2.it.dicii.celestialAstronomy.Model;

public class Utente {

    // Atributi
    private String name;
    private String cognome;
    private String username;
    private String password;
    private String email;
    private boolean administrator;

    // Costruttore
    public Utente(String name, String cognome, String username, String password, String email, boolean administrator) {
        this.name = name;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.email = email;
        this.administrator = administrator;
    }

    // Metodi
    public String getName() {
        return name;
    }

    public String getCognome() {
        return cognome;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdministrator() {
        return administrator;
    }
}

