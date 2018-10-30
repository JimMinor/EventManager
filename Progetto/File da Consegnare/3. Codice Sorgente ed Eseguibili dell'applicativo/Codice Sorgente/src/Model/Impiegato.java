package Model;

import sun.util.resources.LocaleData;

import java.time.LocalDate;
import java.util.Objects;

public class Impiegato  {


    private String username;
    private String password;
    private String amministratore;
    private int id;


    public Impiegato () {}

    public Impiegato(String username, String password, String amministratore, int id) {
        this.username = username;
        this.password = password;
        this.amministratore = amministratore;
        this.id = id;
    }

    //GETTERS

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAmministratore() {
        return amministratore;
    }

    public int getId() {
        return id;
    }


    // SETTERS


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAmministratore(String amministratore) {
        this.amministratore = amministratore;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Impiegato)) return false;
        Impiegato impiegato = (Impiegato) o;
        return getId() == impiegato.getId() &&
                Objects.equals(getUsername(), impiegato.getUsername()) &&
                Objects.equals(getPassword(), impiegato.getPassword()) &&
                Objects.equals(getAmministratore(), impiegato.getAmministratore());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUsername(), getPassword(), getAmministratore(), getId());
    }
}
