package mindpop.learnpop;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment {

    private LoadResource loadResource;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    public MoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_more, container, false);
        // Inflate the layout for this fragment
        String [] sub = {"Visual Art", "Science"};
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_more);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        loadResource = new LoadResource(getActivity(), getActivity(), mRecyclerView, sub ,"LowerElem", 2);

        loadResource.execute();
        return rootView;
    }


}
