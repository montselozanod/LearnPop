package mindpop.learnpop;

import java.util.Date;

/**
 * Created by montselozanod on 3/16/15.
 */
public class Resource {
    //attributes

    private int id;
    private String title; //resName
    private String url;
    private String type;
    private String summary;
    private String gradeLevel;
    private String subject;
    private String author;

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    private String imageURL;
    private int upVotes; //likes
    private int downVotes; //dislikes
    private Date pusblishDate;


    //default constructor
    public Resource(){}

    public Resource(int id, String title, String url, String type, String summary, String gradeLevel, String subject, int upVotes, int downVotes, Date pusblishDate){
        this.id = id;
        this.title = title;
        this.url = url;
        this.type = type;
        this.summary = summary;
        this.gradeLevel = gradeLevel;
        this.subject = subject;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
        this.pusblishDate = pusblishDate;
    }
    public int getResourceId(){
        return id;
    }

    public void setResourceId(int rId){
        this.id = rId;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String tit){
        this.title = tit;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String link){
        this.url = link;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getAuthor(){return author;}

    public void setAuthor(String auth){this.author = auth;}

    public String getSummary(){
        return summary;
    }

    public void setSummary(String summ){
        this.summary = summ;
    }

    public void setGradelevel(String grade){
        this.gradeLevel = grade;
    }

    public String getSubject(){
        return subject;
    }

    public void setSubject(String sub){
        this.subject = sub;
    }

    public String getGradeLevel(){
        return gradeLevel;
    }

    public void setUpVote(int votes){
        this.upVotes = votes;
    }

    public int getUpVote(){
        return upVotes;
    }

    public void setDownVote(int votes){
        this.downVotes = votes;
    }

    public int getDownVote(){
        return downVotes;
    }

    public Date getPublishDate(){
        return pusblishDate;
    }

    public void setPublishDate(Date pubDate){
        this.pusblishDate = pubDate;
    }

    public int getRank(){
        return upVotes-downVotes;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }

        if(!(obj instanceof  Resource)){
            return false;
        }

        Resource other = (Resource)obj;
        return url == other.getUrl();
    }

}
