package sample;

public class Users {
    private Integer UID;
    private String name;
    private String surname;
    private String telephone;
    private String address;

    public Users(Integer UID, String name, String surname, String telephone, String address) {
        this.UID = UID;
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.address = address;
    }

    public Users(){}



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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUID() {
        return UID;
    }

    public void setUID(Integer UID) {
        this.UID = UID;
    }
}
