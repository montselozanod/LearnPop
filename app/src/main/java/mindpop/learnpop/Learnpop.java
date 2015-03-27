package mindpop.learnpop;

import android.app.Application;
import com.facebook.FacebookSdk;
/**
 * Created by montselozanod on 3/23/15.
 */
public class Learnpop extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
