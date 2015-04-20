package mindpop.learnpop;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import com.facebook.Profile;
import com.facebook.login.*;
import com.facebook.login.LoginFragment;


public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Profile p = Profile.getCurrentProfile();
       if (savedInstanceState == null && p == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new mindpop.learnpop.LoginFragment())
                    .commit();
        }else if(savedInstanceState == null && p != null){
           Intent intent = new Intent(this, MainActivity.class);
           startActivity(intent);
       }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

}
