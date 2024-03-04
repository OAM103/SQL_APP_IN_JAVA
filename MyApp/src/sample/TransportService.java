package sample;

public class TransportService {
    private String recommendation;
    private String last_data;
    private String next_data;

    public TransportService(String recommendation,String last_data, String next_data) {
        this.recommendation = recommendation;
        this.last_data = last_data;
        this.next_data = next_data;
    }

    public TransportService() {}

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getLast_data() {
        return last_data;
    }

    public void setLast_data(String last_data) {
        this.last_data = last_data;
    }

    public String getNext_data() {
        return next_data;
    }

    public void setNext_data(String next_data) {
        this.next_data = next_data;
    }
}
