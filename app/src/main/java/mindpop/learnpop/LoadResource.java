package mindpop.learnpop;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.content.Context;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by montselozanod on 3/27/15.
 */

public class LoadResource extends AsyncTask<String, String,JSONObject> {

    public AsyncResponse delegate = null;
    private Context _context;
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    private ArrayList<Resource> resourcesArrayList = new ArrayList<Resource>();
    private static String urlResources = "http://austinartmap.com/CreativeTeach/PHP/getResourcesList.php";
    private String [] subjects;
    private String grade;
    private JSONArray resources;
    private final String TAG_SUCCESS = "success";
    private final String TAG_RES = "resources";
    private int type; // 0 = articles, 1 = videos, 2 = other
    private RecyclerView mRecyclerView;

    public LoadResource(Context context, RecyclerView viewer, String [] subjects, String grade, int type){
        this._context = context;
        this.subjects = subjects;
        this.grade = grade;
        this.type = type;
        this.mRecyclerView = viewer;
    }

    private String getResTypeQuery(){
        String query = "";
        switch(type){
            case 0: query = "Strategy"; break;
            case 1: query = "Video"; break;
            case 2: query = "OtherR"; break;
        }
        return query;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        /*pDialog = new ProgressDialog(_context);
        pDialog.setMessage("Loading resources...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();*/
    }

    protected JSONObject doInBackground(String... args){

        //parameters
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();

            //params.add(new BasicNameValuePair("GradeLevel[]", grade));
       

        for(int i = 0; i < subjects.length; i++){
            params.add(new BasicNameValuePair("Subject[]", subjects[i]));
        }
        JSONObject json = jsonParser.makeHttpRequest(urlResources, "GET", params);

        Log.d("Resources: ", json.toString());

        try{
            int success = json.getInt(TAG_SUCCESS);

            if(success == 1){
                resources = json.getJSONArray(TAG_RES);
                Log.d("JSONArray in LoadResource", resources.toString());
                for (int i = 0; i < resources.length(); i++){
                    JSONObject c = resources.getJSONObject(i);

                    Resource res = new Resource();
                    res.setTitle(c.getString("ResName"));
                    res.setResourceId(c.getInt("ResID"));
                    res.setSubject(c.getString("Subject"));
                    res.setType(c.getString("ResType"));
                    res.setUpVote(c.getInt("Likes"));
                    res.setDownVote(c.getInt("Dislikes"));
                    res.setAuthor(c.getString("Author"));
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
//                    Log.d("Resource", res.getPublishDate().toString());
                    if(res.getType().equals(getResTypeQuery())) //add if resource type is the same
                    resourcesArrayList.add(res);
                }
            }else{
                //if there are no resources

            }

        }catch(JSONException e){
            e.printStackTrace();
        }

        return null;
    }

    protected  void onPostExecute(JSONObject result)
    {
        //pDialog.dismiss();
       // delegate.processFinish(result);
        ResourceAdapter adapter = new ResourceAdapter(resourcesArrayList);
        mRecyclerView.setAdapter(adapter);

    }

}