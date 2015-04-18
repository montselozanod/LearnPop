package mindpop.learnpop;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
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
    private SharedPreferences sharedPreferences;
    public static final String USER_PREFERENCES = "MyPrefs" ;
    private String FAV_TAG = "favorites";
    private Gson gson = new Gson();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.list_favorites);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        sharedPreferences = getActivity().getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE);

        //get favorites
        if(sharedPreferences.contains(FAV_TAG)){
            String favs = sharedPreferences.getString(FAV_TAG, "");
            Log.d("favorites", favs);
            Type type = new TypeToken<ArrayList<Resource>>(){}.getType();
            list = new ArrayList<Resource>();
            list = gson.fromJson(favs, type);
            ResourceAdapter adapter = new ResourceAdapter(getActivity(), list);
            mRecyclerView.setAdapter(adapter);
        }else{
            Toast.makeText(getActivity(), "No favorites saved!", Toast.LENGTH_LONG).show();
        }
        

        return rootView;
    }

}
