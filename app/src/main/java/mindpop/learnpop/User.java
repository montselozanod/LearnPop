package mindpop.learnpop;

/**
 * Created by montselozanod on 3/19/15.
 */
public class User {

    private int id;
    private String fName;
    private String lName;
    private boolean artInterest;
    private boolean historyInterest;
    private boolean mathInterest;
    private boolean scienceInterest;
    private boolean englishInterest;

    public int getUserId(){
        return id;
    }

    public void setUserId(int uId){
        this.id = uId;
    }

    public String getFName(){
        return fName;
    }

    public void setfName(String name){
        this.fName = name;
    }

    public String getLName(){
        return lName;
    }

    public void setLName(String name){
        this.lName = name;
    }

    public boolean isArtInterest(){
        return artInterest;
    }

    public void setArtInterest(boolean interest){
        this.artInterest = interest;
    }

    public boolean istHistoryInterest(){
        return historyInterest;
    }

    public void setHistoryInterest(boolean interest){
        this.historyInterest = interest;
    }

    public boolean isMathInterest(){
        return mathInterest;
    }

    public void setMathInterest(boolean interest){
        this.mathInterest = interest;
    }

    public boolean isEnglishInterest(){
        return englishInterest;
    }

    public void setEnglishInterest(boolean interest){
        this.englishInterest = interest;
    }

    public boolean isScienceInterest(){
        return scienceInterest;
    }

    public void setScienceInterest(boolean interest){
        this.scienceInterest = interest;
    }


}
