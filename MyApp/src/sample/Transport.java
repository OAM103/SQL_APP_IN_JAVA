package sample;

public class Transport {
    private Integer TID;
    private Integer TSID;
    private String number;
    private String model;
    private String color;
    private String year;

    public Transport(Integer TID, String number, String model, String color, String year, Integer TSID) {
        this.TID = TID;
        this.TSID = TSID;
        this.number = number;
        this.model = model;
        this.color = color;
        this.year = year;
    }

    public Transport() {}

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getTID() {
        return TID;
    }

    public void setTID(Integer TID) {
        this.TID = TID;
    }

    public Integer getTSID() {
        return TSID;
    }

    public void setTSID(Integer TSID) {
        this.TSID = TSID;
    }
}
