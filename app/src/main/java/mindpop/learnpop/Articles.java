package mindpop.learnpop;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;
import android.widget.ListView;
import android.util.Log;
import android.os.Bundle;
import android.os.AsyncTask;
import android.support.v4.app.ListFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
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
        Date d = new GregorianCalendar(2015, 3, 10).getTime();

        resourceArrayList.add(new Resource(1, "I'm only 22", "http://www.readunwritten.com/2015/03/10/im-only-22-i-dont-want-someone-else-to-be-my-whole-world/", "Article", "Sum", "Elem", "art", 3, 0, d));
        resourceArrayList.add(new Resource(1, "I'm only 22", "http://www.readunwritten.com/2015/03/10/im-only-22-i-dont-want-someone-else-to-be-my-whole-world/", "Article", "Sum", "Elem", "art", 3, 0, d));
        resourceArrayList.add(new Resource(1, "I'm only 22", "http://www.readunwritten.com/2015/03/10/im-only-22-i-dont-want-someone-else-to-be-my-whole-world/", "Article", "Sum", "Elem", "art", 3, 0, d));

        setListAdapter(new ListAdapter(getActivity(), resourceArrayList));
        //learnApp = ((Learnpop)getApplicationContext());
        return rootView;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // remove the dividers from the ListView of the ListFragment
        getListView().setDivider(null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // retrieve theListView item
        Resource item = resourceArrayList.get(position);
        // do something
        //Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();

        WebViewFragment webFrag = new WebViewFragment();
        webFrag.init(item.getUrl());

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.drawer_layout, webFrag);
        transaction.addToBackStack(null);
        transaction.commit();
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
