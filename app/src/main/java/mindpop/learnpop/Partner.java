package mindpop.learnpop;

/**
 * Created by montselozanod on 3/29/15.
 */
public class Partner {
    private int parID;
    private String parName;
    private String parDescription;
    private String parURL;
    private String imageURL;

    public Partner(){}
    public Partner(int id, String name, String description, String parURL, String imgURL){
        this.parID = id;
        this.parName = name;
        this.parDescription = description;
        this.parURL = parURL;
        this.imageURL = imgURL;
    }

    public int getParID() {
        return parID;
    }

    public void setParID(int parID) {
        this.parID = parID;
    }

    public String getParName() {
        return parName;
    }

    public void setParName(String parName) {
        this.parName = parName;
    }

    public String getParDescription() {
        return parDescription;
    }

    public void setParDescription(String parDescription) {
        this.parDescription = parDescription;
    }

    public String getParURL() {
        return parURL;
    }

    public void setParURL(String parURL) {
        this.parURL = parURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
