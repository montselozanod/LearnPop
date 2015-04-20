package mindpop.learnpop;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class PartnerItem extends Fragment {

    private Partner partner;

    public PartnerItem(){}

    public void init(Partner r) {
        // Required empty public constructor
        this.partner = r;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_partner_item, container, false);
        TextView title = (TextView)rootView.findViewById(R.id.namePartner);
        TextView summary = (TextView)rootView.findViewById(R.id.sumPartner);
        Button btnView = (Button) rootView.findViewById(R.id.btnViewPartner);

        title.setText(partner.getParName());
        summary.setText(partner.getParDescription());
        btnView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                WebViewFragment webFrag = new WebViewFragment();
                webFrag.init(partner.getParURL());
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, webFrag).addToBackStack(null).commit();
            }
        });
        return rootView;
    }


}
