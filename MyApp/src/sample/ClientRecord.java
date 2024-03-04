package sample;

public class ClientRecord {
    private String data;
    private String comments;

    public ClientRecord(String data, String comments) {
        this.comments = comments;
        this.data = data;
    }

    public ClientRecord() {}

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
}
