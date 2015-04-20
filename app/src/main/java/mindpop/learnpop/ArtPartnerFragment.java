package mindpop.learnpop;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.drawable.Drawable;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArtPartnerFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressDialog progressDialog;
    JSONParser parser = new JSONParser();
    private static String url_partners = "http://austinartmap.com/CreativeTeach/PHP/getPartners.php";

    private ArrayList<Partner> partnersArray;

    //TAGS
    private final String TAG_SUCCESS = "success";
    private final String TAG_PARTNER = "resources";

    public ArtPartnerFragment() {
        // Required empty public constructor
    }

    public static Drawable LoadImageFromWebOperations(String url) {
        Log.d("URL of LOGO", url);
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {

            Log.d("Drawable", e.toString());
            return null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_art_partner, container, false);
        partnersArray = new ArrayList<Partner>();

        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.list_partners);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        new LoadPartners().execute();
        Resources r = getResources();

        return rootView;
    }

    class LoadPartners extends AsyncTask<String, String, JSONObject>{
        @Override
        protected  void onPreExecute(){
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading partners...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args){
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            JSONObject jsonObject = parser.makeHttpRequest(url_partners, "GET", params);
            try{
                int success = jsonObject.getInt(TAG_SUCCESS);
                if(success == 1){
                    JSONArray parts = jsonObject.getJSONArray(TAG_PARTNER);
                    Log.d("JSONArray in LoadResource", parts.toString());

                    for(int i = 0; i < parts.length(); i++){
                        JSONObject c = parts.getJSONObject(i);
                        Partner part = new Partner();
                        part.setParID(c.getInt("ParID"));
                        part.setParName(c.getString("ParName"));
                        part.setParDescription(c.getString("ParDescription"));
                        part.setParURL(c.getString("ParURL").trim());
                        part.setImageURL(c.getString("ImageURL").trim());
                        Drawable d = LoadImageFromWebOperations(part.getImageURL());
                        part.setImage(d);
                        partnersArray.add(part);
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
            progressDialog.dismiss();
            if(partnersArray.isEmpty())
            {
                Toast.makeText(getActivity(),"No partners right now", Toast.LENGTH_LONG).show();
            }else{
                PartnerAdapter adapter = new PartnerAdapter(getActivity(),partnersArray);
                mRecyclerView.setAdapter(adapter);
            }
        }
    }
}
