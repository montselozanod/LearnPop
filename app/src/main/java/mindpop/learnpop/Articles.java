package mindpop.learnpop;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.util.Log;
import android.os.Bundle;
import android.os.AsyncTask;
import android.support.v4.app.ListFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by montselozanod on 3/20/15.
 */
public class Articles extends ListFragment {

    public static final String PREFS_NAME = "UserPrefs";
    private ProgressDialog pDialog;
    private Learnpop learnApp;
    private LoadResource loadResource;
    JSONParser jsonParser = new JSONParser();
    ArrayList<Resource> resourceArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_article, container, false);
        //restore prefs
        restorePreferences();
        //loadResource = new LoadResource(getActivity(),  ,);
        resourceArrayList = new ArrayList<Resource>();


        //learnApp = ((Learnpop)getApplicationContext());
        return rootView;

    }

    private void restorePreferences(){
        SharedPreferences settings = this.getActivity().getSharedPreferences(PREFS_NAME, 0);
        boolean visual = settings.getBoolean("visual", false);
        boolean drama = settings.getBoolean("drama", false);
        boolean socials = settings.getBoolean("socials", false);
        boolean science = settings.getBoolean("science", false);
        boolean math = settings.getBoolean("math", false);
        boolean movement = settings.getBoolean("movement", false);
        boolean digital = settings.getBoolean("digital", false);
        boolean bilingual = settings.getBoolean("bilingual", false);
        boolean ela = settings.getBoolean("ela", false);
        boolean music = settings.getBoolean("music", false);

        String gradelevel = settings.getString("grade", "LowerElem");
    }

}
