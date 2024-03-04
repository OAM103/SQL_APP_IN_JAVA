package sample;

public class DiagnosticCard {
    private String comments;
    private Integer MID;

    public DiagnosticCard(String comments, Integer MID) {
        this.comments = comments;
        this.MID = MID;
    }

    public DiagnosticCard() {}

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getMID() {
        return MID;
    }

    public void setMID(Integer MID) {
        this.MID = MID;
    }
}
