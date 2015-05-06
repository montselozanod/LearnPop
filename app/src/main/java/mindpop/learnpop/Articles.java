package mindpop.learnpop;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
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

    //private Learnpop learnApp;
    private LoadResource loadResource;
    ArrayList<Resource> resourceArrayList = new ArrayList<Resource>();
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_article, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        //restore prefs
        resourceArrayList = new ArrayList<Resource>();
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_res);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        loadResource = new LoadResource(getActivity(), getActivity(), mRecyclerView, 0);

        loadResource.execute();

        return rootView;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
