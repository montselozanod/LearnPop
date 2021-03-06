package mindpop.learnpop;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.andtinder.model.CardModel;
import com.andtinder.view.SimpleCardStackAdapter;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by montselozanod on 3/20/15.
 */
public class Announcements extends Fragment {

    private ProgressDialog progressDialog;
    private JSONParser parser = new JSONParser();
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Announcement> list = new ArrayList<Announcement>();
    private final String announce_url = "http://austinartmap.com/CreativeTeach/PHP/getAnnouncements.php";

    //TAGS
    private final String TAG_SUCCESS = "success";
    private final String TAG_PARTNER = "resources";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_announce, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        new LoadAnnouncements().execute();

        return rootView;
    }

    class LoadAnnouncements extends AsyncTask<String, String, JSONObject> {
        @Override
        protected  void onPreExecute(){
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        private String getDate(){
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            Calendar cal = Calendar.getInstance();
            return dateFormat.format(cal.getTime());
        }

        @Override
        protected JSONObject doInBackground(String... args){
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            getDate();
            params.add(new BasicNameValuePair("StartDate", getDate()));
            JSONObject jsonObject = parser.makeHttpRequest(announce_url, "GET", params);
            try{
                int success = jsonObject.getInt(TAG_SUCCESS);
                if(success == 1){
                    JSONArray parts = jsonObject.getJSONArray(TAG_PARTNER);

                    for(int i = 0; i < parts.length(); i++){
                        JSONObject c = parts.getJSONObject(i);
                        Announcement ann = new Announcement();
                        ann.setMessage(c.getString("Message").trim());
                        ann.setEnd(c.getString("EndDate").trim());
                        ann.setImg(R.drawable.ann_icon);
                        list.add(ann);
                    }

                }else{
                    //no partners
                }
            }catch(JSONException e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(JSONObject result){
            super.onPostExecute(result);
            if ((progressDialog != null) && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            // for performance given that changes in content do not change the layout size of the RecyclerView
            if(list.isEmpty())
            {
                Toast.makeText(getActivity(),"No announcements right now", Toast.LENGTH_LONG).show();
            }else{
                RecyclerAdapter adapter = new RecyclerAdapter(list);
                mRecyclerView.setAdapter(adapter);
            }

        }
    }
}

class Announcement {

    private String message;
    private String start;
    private String end;
    private int img;

    public Announcement(){}
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
