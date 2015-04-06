package mindpop.learnpop;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
public class Articles extends Fragment {

    public static final String PREFS_NAME = "UserPrefs";
    private ProgressDialog pDialog;
    //private Learnpop learnApp;
    private LoadResource loadResource;
    JSONParser jsonParser = new JSONParser();
    ArrayList<Resource> resourceArrayList = new ArrayList<Resource>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_article, container, false);
        //restore prefs
        restorePreferences();
        String [] sub = {"Visual Art", "Science"};
        resourceArrayList = new ArrayList<Resource>();
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_res);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        loadResource = new LoadResource(getActivity(), mRecyclerView, sub ,"LowerElem", 0);

        loadResource.execute();


        //setListAdapter(new ListAdapter(getActivity(), resourceArrayList));
        return rootView;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // remove the dividers from the ListView of the ListFragment

    }

    /*@Override
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
    }*/

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

    private void checkResponseForRequest(JSONObject resourceObj){

    }


}
