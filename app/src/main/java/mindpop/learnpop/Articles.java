package mindpop.learnpop;

import android.app.ProgressDialog;
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

    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();
    ArrayList<Resource> resourceArrayList;

    private static String urlResources = "http://portfolioartstudio.com/php/getResourceHtmlTable.php";
    private final String TAG_SUCCESS = "success";
    private final String TAG_Type = "Type";
    private final String Tag_Subject = "Subject";
    private final String Tag_Grade = "GradeLevel";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_article, container, false);

        resourceArrayList = new ArrayList<Resource>();
        new LoadResources().execute();


        return rootView;

    }

    class LoadResources extends AsyncTask<String, String, String>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Loading resources...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected String doInBackground(String... args){
            String result = "";
            //parameters
            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            JSONObject json = jsonParser.makeHttpRequest(urlResources, "GET", params);

            Log.d("Resources: ", json.toString());

            try{
                int success = json.getInt(TAG_SUCCESS);
                if(success == 1){
                    //resourceArrayList = json.getJSONArray();
                }
            }catch(JSONException e){
                e.printStackTrace();
            }

            return result;
        }

        protected  void onPostExecute(String result){
            try{
                JSONArray jArray = new JSONArray(result);
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    Resource rsc = new Resource();
                    rsc.setTitle(json_data.getString("ResName"));
                    rsc.setSubject(json_data.getString("Subject"));
                    rsc.setGradelevel(json_data.getString("GradeLevel"));
                    rsc.setUpVote(json_data.getInt("Likes"));
                    resourceArrayList.add(i, rsc);
                }
            }catch(JSONException e){
                e.printStackTrace();
                Log.e("log_tag","Error parsing data "+ e.toString());
            }
        }
    }



}
