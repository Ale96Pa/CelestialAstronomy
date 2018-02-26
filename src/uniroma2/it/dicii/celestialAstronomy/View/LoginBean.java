package uniroma2.it.dicii.celestialAstronomy.View;

import uniroma2.it.dicii.celestialAstronomy.Model.Utente;
import uniroma2.it.dicii.celestialAstronomy.Repositories.UserRepository;

public class LoginBean {

    // Atributi
    private String name;
    private String cognome;
    private String username;
    private String password;
    private String email;
    private boolean administrator;

    // Costruttore
    public LoginBean() {
        this.username = "";
        this.password = "";
    }

    // Meotdi Bean
    public String getNome() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }


    public boolean validateUser(){

        boolean validaUtente = false;
        Utente u = UserRepository.findUser(getUsername(), getPassword());
        if(u!= null) {
            setName(u.getName());
            setCognome(u.getCognome());
            setUsername(u.getUsername());
            setPassword(u.getPassword());
            setEmail(u.getEmail());
            setAdministrator(u.isAdministrator());
            validaUtente = true;
        }
        return validaUtente;
    }
}