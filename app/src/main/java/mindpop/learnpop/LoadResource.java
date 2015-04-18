package mindpop.learnpop;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
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

    private Context _context;
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    private ArrayList<Resource> resourcesArrayList = new ArrayList<Resource>();
    private static String urlResources = "http://austinartmap.com/CreativeTeach/PHP/getResourcesList_v2.php";
    private JSONArray resources;
    private final String TAG_SUCCESS = "success";
    private final String TAG_RES = "resources";
    private int type; // 0 = articles, 1 = videos, 2 = other
    private RecyclerView mRecyclerView;
    private FragmentActivity activity;
    //parameters
    ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
    private SharedPreferences sharedPreferences;
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

    public LoadResource(FragmentActivity ac, Context context, RecyclerView viewer,int type){
        this.activity = ac;
        this._context = context;
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
        pDialog = new ProgressDialog(_context);
        pDialog.setMessage("Loading resources...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    private void readPreferences(){
        sharedPreferences = _context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE);

        if(sharedPreferences.contains(bilingual) && sharedPreferences.getBoolean(bilingual, false))
            params.add(new BasicNameValuePair("Subject[]", _context.getString(R.string.sub1)));
        if(sharedPreferences.contains(media) && sharedPreferences.getBoolean(media, false))
            params.add(new BasicNameValuePair("Subject[]", _context.getString(R.string.sub2)));
        if(sharedPreferences.contains(drama) && sharedPreferences.getBoolean(drama, false))
            params.add(new BasicNameValuePair("Subject[]", _context.getString(R.string.sub3)));
        if(sharedPreferences.contains(ela) && sharedPreferences.getBoolean(ela, false))
            params.add(new BasicNameValuePair("Subject[]", _context.getString(R.string.sub4)));
        if(sharedPreferences.contains(history) && sharedPreferences.getBoolean(history, false))
            params.add(new BasicNameValuePair("Subject[]", _context.getString(R.string.sub5)));
        if(sharedPreferences.contains(math) && sharedPreferences.getBoolean(math, false))
            params.add(new BasicNameValuePair("Subject[]", _context.getString(R.string.sub6)));
        if(sharedPreferences.contains(movement) && sharedPreferences.getBoolean(movement, false))
            params.add(new BasicNameValuePair("Subject[]", _context.getString(R.string.sub7)));
        if(sharedPreferences.contains(music) && sharedPreferences.getBoolean(music, false))
            params.add(new BasicNameValuePair("Subject[]", _context.getString(R.string.sub8)));
        if(sharedPreferences.contains(science) && sharedPreferences.getBoolean(science, false))
            params.add(new BasicNameValuePair("Subject[]", _context.getString(R.string.sub9)));
        if(sharedPreferences.contains(sel) && sharedPreferences.getBoolean(sel, false))
            params.add(new BasicNameValuePair("Subject[]", _context.getString(R.string.sub10)));
        if(sharedPreferences.contains(art) && sharedPreferences.getBoolean(art, false))
            params.add(new BasicNameValuePair("Subject[]", _context.getString(R.string.sub11)));

        if(sharedPreferences.contains(grade))
        {
            params.add(new BasicNameValuePair("GradeLevel[]", getGradeParamName(sharedPreferences.getString(grade, ""))));
        }
    }

    private String getGradeParamName(String completeGrade){
       String param = "";
        switch (completeGrade){
            case "Early Childhood":
                param = "Childhood";
                break;
            case "Lower Elementary":
                param = "LowerElem";
                break;
            case "Upper Elementary":
                param = "UpperElem";
                break;
            case "Middle School":
                param = "Middle";
                break;
            case "High School":
                param ="High";
                break;
        }
        return param;
    }

    protected JSONObject doInBackground(String... args){

    //
       
        readPreferences(); // get parameters
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
        pDialog.dismiss();
       // delegate.processFinish(result);
        ResourceAdapter adapter = new ResourceAdapter(activity, resourcesArrayList);
        mRecyclerView.setAdapter(adapter);

    }

}