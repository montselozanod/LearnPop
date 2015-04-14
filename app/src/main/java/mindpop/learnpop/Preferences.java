package mindpop.learnpop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.Spinner;

/**
 * Created by montselozanod on 3/20/15.
 */
public class Preferences extends Fragment {
    private CheckedTextView bilingual_check;
    private CheckedTextView media_check;
    private CheckedTextView drama_check;
    private CheckedTextView ela_check;
    private CheckedTextView history_check;
    private CheckedTextView math_check;
    private CheckedTextView movement_check;
    private CheckedTextView music_check;
    private CheckedTextView science_check;
    private CheckedTextView sle_check;
    private CheckedTextView art_check;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_preferences, container, false);
        setSpinnerContent(rootView);
        bilingual_check = (CheckedTextView)rootView.findViewById(R.id.check_bilingual);
        bilingual_check.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ((CheckedTextView) v).toggle();
            }
        });

        return rootView;
    }

    private void setSpinnerContent(View view){
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner_Grades);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.grades_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}

