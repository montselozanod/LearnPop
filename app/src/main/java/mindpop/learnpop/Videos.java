package mindpop.learnpop;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andtinder.model.CardModel;
import com.andtinder.view.CardContainer;
import com.andtinder.view.SimpleCardStackAdapter;

/**
 * Created by montselozanod on 3/20/15.
 */
public class Videos extends Fragment {

    private CardContainer mCardContainer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_videos, container, false);

        mCardContainer = (CardContainer) rootView.findViewById(R.id.cardView);
        Resources r = getResources();

        SimpleCardStackAdapter adapter = new SimpleCardStackAdapter(getActivity());

        adapter.add(new CardModel("Title1", "Description goes here", r.getDrawable(R.drawable.picture1)));
        adapter.add(new CardModel("Title2", "Description goes here", r.getDrawable(R.drawable.picture2)));
        adapter.add(new CardModel("Title3", "Description goes here", r.getDrawable(R.drawable.picture3)));
        mCardContainer.setAdapter(adapter);
        return rootView;
    }
}
