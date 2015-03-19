package mindpop.learnpop;

import java.util.Date;

/**
 * Created by montselozanod on 3/16/15.
 */
public class Resource {
    //attributes
    private String title;
    private String url;
    private String type;
    private String summary;
    private int gradeLevel;
    private int upVotes; //likes
    private int downVotes; //dislikes
    private Date pusblishDate;
    private boolean status;


    //default constructor
    public Resource(){}

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

    public String getSummary(){
        return summary;
    }

    public void setSummary(String summ){
        this.summary = summ;
    }

    public void getGradelevel(int grade){
        this.gradeLevel = grade;
    }

    public int setGradeLevel(){
        return gradeLevel;
    }

    public void getUpVote(int votes){
        this.upVotes = votes;
    }

    public int setUpVote(){
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
    
    public boolean getStatus(){
        return status;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public int getRank(){
        return upVotes-downVotes;
    }

}
