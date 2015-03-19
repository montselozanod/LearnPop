package mindpop.learnpop;

/**
 * Created by montselozanod on 3/16/15.
 */
public class Resource {
    //attributes
    private String title;
    private String url;
    private String type;
    private int upVotes;
    private int downVotes;

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

    public void getUpVote(int votes){
        this.upVotes = votes;
    }

    public int setUpVote(){
        return upVotes;
    }

    public void getDownVote(int votes){
        this.downVotes = votes;
    }

    public int settDownVote(){
        return downVotes;
    }

    public int getRank(){
        return upVotes-downVotes;
    }

}
