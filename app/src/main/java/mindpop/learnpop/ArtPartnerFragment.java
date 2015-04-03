package mindpop.learnpop;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andtinder.model.CardModel;
import com.andtinder.view.CardContainer;
import com.andtinder.view.SimpleCardStackAdapter;




/**
 * A simple {@link Fragment} subclass.
 */
public class ArtPartnerFragment extends Fragment {

    private CardContainer mCardContainer;
    private ProgressDialog progressDialog;
    JSONParser parser = new JSONParser();
    private static String url_partners = "http://austinartmap.com/CreativeTeach/PHP/getPartners.php";

    private ArrayList<Partner> partnersArray;

    //TAGS
    private final String TAG_SUCCESS = "success";
    private final String TAG_PARTNER = "partners";

    public ArtPartnerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_art_partner, container, false);

        mCardContainer = (CardContainer) rootView.findViewById(R.id.cardView);
        Resources r = getResources();

        SimpleCardStackAdapter adapter = new SimpleCardStackAdapter(getActivity());

        adapter.add(new CardModel("Title1", "Description goes here", r.getDrawable(R.drawable.picture1)));
        adapter.add(new CardModel("Title2", "Description goes here", r.getDrawable(R.drawable.picture2)));
        adapter.add(new CardModel("Title3", "Description goes here", r.getDrawable(R.drawable.picture3)));
        mCardContainer.setAdapter(adapter);

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

                    for(int i = 0; i < parts.length(); i++){
                        JSONObject c = parts.getJSONObject(i);
                        Partner part = new Partner();
                        part.setParID(c.getInt("ParID"));
                        part.setParName(c.getString("ParName"));
                        part.setParDescription(c.getString("ParDescription"));
                        part.setParURL(c.getString("ParURL"));
                        part.setImageURL(c.getString("ImageURL"));
                        partnersArray.add(part);

                    }

                }else{

                }
            }catch(JSONException e){
                e.printStackTrace();
            }

            return null;
        }

        protected void OnPostExecute(JSONObject result){
            progressDialog.dismiss();
        }
    }


}
