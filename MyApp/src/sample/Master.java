package sample;

public class Master {
    private String name;
    private String surname;
    private String telephone;

    private Integer ID;

    public Master(String name, String surname, String telephone,Integer ID) {
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.ID = ID;
    }

    public Master() {}

    public Master(Integer mID, String mname, String msurname, String mtelephone) {
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }
}
