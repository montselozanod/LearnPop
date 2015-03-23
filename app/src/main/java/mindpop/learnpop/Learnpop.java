package mindpop.learnpop;

import android.app.Application;
import com.facebook.model.GraphUser;
/**
 * Created by montselozanod on 3/23/15.
 */
public class Learnpop extends Application {
    private GraphUser user;

    public GraphUser getUser(){
        return user;
    }

    public void setUser(GraphUser graphUser){
        this.user = graphUser;
    }
}
