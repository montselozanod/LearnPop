package mindpop.learnpop;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * Created by montselozanod on 3/20/15.
 */
public class Videos extends Fragment {

    private LoadResource loadResource;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_article, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

        String [] sub = {"Visual Art", "Science"};
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_res);
        mRecyclerView   .setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        loadResource = new LoadResource(getActivity(), getActivity(), mRecyclerView, 1);

        loadResource.execute();

        return rootView;
    }
}
