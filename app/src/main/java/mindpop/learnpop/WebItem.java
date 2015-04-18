package mindpop.learnpop;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WebItem extends Fragment {


    private Resource resource;
    private TextView title;
    private TextView summary;
    private TextView likesTxt;
    private TextView unlikesTxt;
    private Button viewButton;
    private CheckBox fav_button;
    private CheckBox like_button;
    private CheckBox unlike_button;
    private JSONParser jsonParser = new JSONParser();

    public WebItem() {
        // Required empty public constructor
    }

    public void init(Resource res){this.resource = res;}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_web_item, container, false);

        title = (TextView)rootView.findViewById(R.id.titleTxt);
        summary = (TextView)rootView.findViewById(R.id.sumTXT);
        viewButton = (Button) rootView.findViewById(R.id.btnView);
        likesTxt = (TextView) rootView.findViewById(R.id.numLikes);
        unlikesTxt = (TextView) rootView.findViewById(R.id.numDislikes);
        fav_button = (CheckBox) rootView.findViewById(R.id.fav_check);
        like_button = (CheckBox) rootView.findViewById(R.id.check_like);
        unlike_button = (CheckBox) rootView.findViewById(R.id.check_dislike);



        title.setText(resource.getTitle());
        summary.setText(resource.getSummary());
        //updateLikeValues();

        viewButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                WebViewFragment webFrag = new WebViewFragment();
                webFrag.init(resource.getUrl());
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, webFrag).addToBackStack(null).commit();
            }
        });

        like_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if unlike button is pressed and eliminate press
                if(unlike_button.isChecked()){
                    unlike_button.setChecked(false);
                    resource.setDownVote(resource.getDownVote() -1);
                    resource.setUpVote(resource.getUpVote() + 1);
                }else{
                    if(like_button.isChecked()) {
                        resource.setUpVote(resource.getUpVote() + 1);
                    }else{
                        resource.setUpVote(resource.getUpVote() - 1);
                    }
                }

                updateLikeValues();

            }
        });

        unlike_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if unlike button is pressed and eliminate press
                if(like_button.isChecked()){
                    like_button.setChecked(false);
                    resource.setUpVote(resource.getUpVote() -1);
                    resource.setDownVote(resource.getDownVote() + 1);
                }else{
                    if(unlike_button.isChecked()){
                        //add 1 to resource
                        resource.setDownVote(resource.getDownVote() + 1);
                    }else{
                        resource.setDownVote(resource.getDownVote() - 1);
                    }
                }
                updateLikeValues();

            }
        });

        fav_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });

        return rootView;
    }

    private void updateLikeValues(){
        like_button.setText(String.valueOf(resource.getUpVote()));
        unlikesTxt.setText(String.valueOf(resource.getDownVote()));
    }

   /*class UpdateResource extends AsyncTask<String, String, JSONObject>{
       @Override
       protected void onPreExecute(){
           super.onPreExecute();
       }

       protected String doInBackground(String... args){

           List<NameValuePair> params = new ArrayList<NameValuePair>();


           //JSONObject json = jsonParser.makeHttpRequest(request_url, "POST", params);

           //Log.d("Create resource response", json.toString());

           return null;
       }
   }*/

}
