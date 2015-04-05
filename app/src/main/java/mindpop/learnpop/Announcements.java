package mindpop.learnpop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by montselozanod on 3/20/15.
 */
public class Announcements extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Announcement> list;
    private final String announce_url = "http://austinartmap.com/CreativeTeach/PHP/getAnnouncements.php?StartDate=20150101";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_announce, container, false);

        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.my_recycler_view);
        initializeData();
        // for performance given that changes in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        RecyclerAdapter adapter = new RecyclerAdapter(list);
        mRecyclerView.setAdapter(adapter);
        return rootView;
    }

    private void initializeData(){
        list = new ArrayList<Announcement>();
        list.add(new Announcement("Emma Wilson", "23 years old", R.drawable.cats));
        list.add(new Announcement("Hello", "Hola", R.drawable.camera));
    }
}

class Announcement {

    private String message;
    private String start;
    private String end;
    private int img;

    public Announcement(String msg, String st, int im){
        this.message = msg;
        this.start = st;
        this.img = im;
    }
    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }


}
