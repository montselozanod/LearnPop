package mindpop.learnpop;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by montselozanod on 3/20/15.
 */
public class Favorites extends Fragment {
    private LoadResource loadResource;
    private RecyclerView mRecyclerView;
    private final String TAG_SUCCESS = "success";
    private final String TAG_RES = "resources";
    private RecyclerView.LayoutManager mLayoutManager;
    private final String favURL = "http://austinartmap.com/CreativeTeach/PHP/getFavoriteResources.php";
    private JSONParser jsonParser = new JSONParser();
    ArrayList<Resource> list = new ArrayList<Resource>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.list_favorites);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        return rootView;
    }

    class LoadFavs extends AsyncTask<String, String, JSONObject> {
            @Override
            protected  void onPreExecute(){
                super.onPreExecute();
                Log.e("AsyncTask", "onPreExecute");
     /*         progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Loading partners...");
                progressDialog.setIndeterminate(false);
                progressDialog.setCancelable(false);
                progressDialog.show();*/
            }

            @Override
            protected JSONObject doInBackground(String... args){
                Log.d("AsyncTask", "doInBackground");
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("UserID", "20150101"));
                JSONObject jsonObject = jsonParser.makeHttpRequest(favURL, "GET", params);
                try{
                    int success = jsonObject.getInt(TAG_SUCCESS);
                    if(success == 1){
                        JSONArray parts = jsonObject.getJSONArray(TAG_RES);
                        Log.d("JSONArray in LoadResource", parts.toString());

                        for(int i = 0; i < parts.length(); i++){
                            JSONObject c = parts.getJSONObject(i);
                            Resource res = new Resource();
                            res.setTitle(c.getString("ResName").trim());
                            res.setResourceId(c.getInt("ResID"));
                            res.setSubject(c.getString("Subject").trim());
                            res.setType(c.getString("ResType").trim());
                            res.setUpVote(c.getInt("Likes"));
                            res.setDownVote(c.getInt("Dislikes"));
                            res.setAuthor(c.getString("Author").trim());
                            res.setImageURL("ImageURL");
                            //date format
                            //2014-02-28
                            String dateString =c.getString("PublishingDate");

                            try{
                                SimpleDateFormat formatter =
                                        new SimpleDateFormat("yyyy-MM-dd");
                                Date date = formatter.parse(dateString);
                                res.setPublishDate(date); //change to date
                            }catch(ParseException e){
                                e.printStackTrace();
                            }
                            res.setUrl(c.getString("ResURL"));
                            res.setSummary(c.getString("Summary"));
                            list.add(res);
                        }

                        Log.d("Finish Loading", "partnersArray");
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
                Log.d("PostExecute", "post");
                super.onPostExecute(result);
//            progressDialog.dismiss();
                // for performance given that changes in content do not change the layout size of the RecyclerView
                ResourceAdapter adapter = new ResourceAdapter(getActivity(), list);
                mRecyclerView.setAdapter(adapter);

            }

    }
}
