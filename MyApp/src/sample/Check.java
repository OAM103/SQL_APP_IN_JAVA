package sample;

public class Check {
    private String price;
    private String end_data;

    public Check(String price, String end_data) {
        this.price = price;
        this.end_data = end_data;
    }

    public Check() {}

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getEnd_data() {
        return end_data;
    }

    public void setEnd_data(String end_data) {
        this.end_data = end_data;
    }
}
