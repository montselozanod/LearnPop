package mindpop.learnpop;

import android.app.Activity;
import android.content.pm.Signature;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import android.widget.ListView;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {


    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;
    //FB LOGIN
    private CallbackManager callbackManager;
    private boolean isResumed = false;
    private AccessTokenTracker accessTokenTracker;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);


        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        mTitle = getResources().getStringArray(R.array.sections)[0];

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = null;
        String[] stringArray = getResources().getStringArray(R.array.sections);
        switch(position){
            case 0:
                // announcements
                fragment = new Announcements();
                mTitle = stringArray[position];
                break;
            case 1:
                //articles
                fragment = new Articles();
                mTitle = stringArray[position];
                break;
            case 2:
                //videos
                fragment = new Videos();
                mTitle = stringArray[position];
                break;
            case 3:
                //more fragment
                fragment = new MoreFragment();
                mTitle = stringArray[position];
                break;
            case 4:
                //favorites
                fragment = new Favorites();
                mTitle = stringArray[position];
                break;
            case 5:
                //art partner
                fragment = new ArtPartnerFragment();
                mTitle = stringArray[position];
                break;
            case 6:
                //share
                fragment = new Share();
                mTitle = stringArray[position];
                break;
            case 7:
                //preferences
                fragment = new Preferences();
                mTitle = stringArray[position];
                break;
            case 8:
                //Help
                fragment = new HelpFragment();
                mTitle = stringArray[position];
                break;
        }
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        isResumed = true;

        // Call the 'activateApp' method to log an app event for use in analytics and advertising
        // reporting.  Do so in the onResume methods of the primary Activities that an app may be
        // launched into.
        AppEventsLogger.activateApp(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        isResumed = false;

        // Call the 'deactivateApp' method to log an app event for use in analytics and advertising
        // reporting.  Do so in the onPause methods of the primary Activities that an app may be
        // launched into.
        AppEventsLogger.deactivateApp(this);
    }

    //@Override
    /*protected void onResumeFragments() {
        super.onResumeFragments();

        if (AccessToken.getCurrentAccessToken() != null) {
            // if the user already logged in, try to show the selection fragment
            showFragment(SELECTION, false);

        } else {
            // otherwise present the splash screen and ask the user to login,
            // unless the user explicitly skipped.
            showFragment(SPLASH, false);
        }
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
    }


    public interface ChangeLinkListener{
        public void onLinkChange(String link);
    }


    /*private void showFragment(int fragmentIndex, boolean addToBackStack) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        for (int i = 0; i < fragments.length; i++) {
            if (i == fragmentIndex) {
                transaction.show(fragments[i]);
            } else {
                transaction.hide(fragments[i]);
            }
        }
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }*/

}

