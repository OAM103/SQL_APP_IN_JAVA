package sample;

public class RecordTable {
    String data;
    String master;
    String mas_tel;

    String end_data;
    String price;

    int number;
    String comments;

    public RecordTable(int number,String master, String mas_tel, String data,String end_data, String comments, String price) {
        super();
        this.number = number;
        this.data = data;
        this.master = master;
        this.mas_tel = mas_tel;
        this.end_data = end_data;
        this.comments = comments;
        this.price = price;
    }
    public RecordTable() {}

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getMas_tel() {
        return mas_tel;
    }

    public void setMas_tel(String mas_tel) {
        this.mas_tel = mas_tel;
    }

    public String getEnd_data() {
        return end_data;
    }

    public void setEnd_data(String end_data) {
        this.end_data = end_data;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
