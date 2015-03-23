package mindpop.learnpop;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
/**
 * Created by montselozanod on 3/21/15.
 */
//tutorial based on http://www.androidhive.info/2012/08/android-session-management-using-shared-preferences/
public class SessionManagement {
    SharedPreferences pref;

    Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;

    //file name pref
    private static final String PREF_NAME = "LearnpopPref";
    // keys
    private static final String is_Login = "IsLoggedIn";
    private static final String key_name= "name";
    private static final String key_email="email";

    public SessionManagement(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String name, String email){
        editor.putBoolean(is_Login, true);
        editor.putString(key_name, name);
        editor.putString(key_email, email);
        //commit changes
        editor.commit();
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(key_name, pref.getString(key_name, null));

        // user email id
        user.put(key_email, pref.getString(key_email, null));

        // return user
        return user;
    }

    public void checkLogin(){

        if(!this.isLoggedIn()){
            Intent i = new Intent(_context, LoginActivity.class);
            //close all activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //add flag to start new activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //start activity
            _context.startActivity(i);
        }
    }

    //quick check for login
    public boolean isLoggedIn(){
        return pref.getBoolean(is_Login, false);
    }


}
