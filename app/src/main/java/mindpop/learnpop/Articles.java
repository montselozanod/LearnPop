package mindpop.learnpop;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import org.json.JSONObject;


/**
 * Created by montselozanod on 3/20/15.
 */
public class Articles extends Fragment {

    public static final String PREFS_NAME = "MyPrefs";
    public static final String USER_PREFERENCES = "MyPrefs" ;
    public static final String bilingual = "bilingualKey";
    public static final String media = "mediaKey";
    public static final String drama = "dramaKey";
    public static final String ela = "elaKey";
    public static final String history = "historyKey";
    public static final String math = "mathKey";
    public static final String movement = "moveKey";
    public static final String music = "musicKey";
    public static final String science = "scienceKey";
    public static final String sel = "selKey";
    public static final String art = "artKey";
    public static final String grade ="gradeKey";

    //private Learnpop learnApp;
    private LoadResource loadResource;
    ArrayList<Resource> resourceArrayList = new ArrayList<Resource>();
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_article, container, false);
        //restore prefs
        restorePreferences();
        resourceArrayList = new ArrayList<Resource>();
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_res);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        loadResource = new LoadResource(getActivity(), getActivity(), mRecyclerView, 0);

        loadResource.execute();


        //setListAdapter(new ListAdapter(getActivity(), resourceArrayList));
        return rootView;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // remove the dividers from the ListView of the ListFragment

    }

    private void restorePreferences(){
        SharedPreferences settings = this.getActivity().getSharedPreferences(PREFS_NAME, 0);
        CreativeTeach ct = (CreativeTeach)getActivity().getApplicationContext();
        User user = ct.getUser();

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
