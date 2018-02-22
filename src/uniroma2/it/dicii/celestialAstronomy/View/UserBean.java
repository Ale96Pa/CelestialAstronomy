package uniroma2.it.dicii.celestialAstronomy.View;

public class UserBean {

    // Atributi
    private String nome;
    private String cognome;
    private String username;
    private String password;
    private String email;
    private boolean administrator;

    // Meotdi Bean
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        boolean valida = false;
        if(getUsername().length() >= 6 && getPassword().length() >= 6)
            valida = true;
        return valida;
    }
}
