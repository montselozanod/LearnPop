package mindpop.learnpop;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;


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
        likesTxt.setText(String.valueOf(resource.getUpVote()));
        unlikesTxt.setText(String.valueOf(resource.getDownVote()));

        viewButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                WebViewFragment webFrag = new WebViewFragment();
                webFrag.init(resource.getUrl());
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, webFrag).addToBackStack(null).commit();
            }
        });

        return rootView;
    }


}
