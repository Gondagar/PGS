package pl.well.entity;

public class User {

    protected int id;
    protected String name;
    protected String surname;
    protected String email;
    String pass;

    int[] presence;


    //   protected int age;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int[] getPresence() {
        return presence;
    }

    public void setPresence(int[] presence) {
        this.presence = presence;
    }
}
