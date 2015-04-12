package mindpop.learnpop;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.facebook.Profile;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by montselozanod on 4/3/15.
 */
public class CreativeTeach extends Application {

    Profile userProfile;
    User user = new User();

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    public Profile getUserProfile(){return userProfile;}
    public void setUserProfile(Profile user_profile){this.userProfile = user_profile;}
    public void setUser(User u){
        this.user = u;
    }

    public User getUser(){
        return user;
    }

    public void printKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("vivz.slidenerd.facebookv40helloworld", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("VIVZ", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

}