package mindpop.learnpop;

import android.app.Application;

import com.facebook.FacebookSdk;

/**
 * Created by montselozanod on 4/3/15.
 */
public class CreativeTeach extends Application {

    User user = new User();

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    public void setUser(User u){
        this.user = u;
    }

    public User getUser(){
        return user;
    }

}