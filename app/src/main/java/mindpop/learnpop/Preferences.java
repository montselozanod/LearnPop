package mindpop.learnpop;

import android.content.Context;
import android.content.SharedPreferences;
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

    private CheckedTextView bilingual_check;
    private CheckedTextView media_check;
    private CheckedTextView drama_check;
    private CheckedTextView ela_check;
    private CheckedTextView history_check;
    private CheckedTextView math_check;
    private CheckedTextView movement_check;
    private CheckedTextView music_check;
    private CheckedTextView science_check;
    private CheckedTextView sel_check;
    private CheckedTextView art_check;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_preferences, container, false);
        setSharedPreferences(rootView);
        setSpinnerContent(rootView);
        setCheckTextViews(rootView);

        return rootView;
    }

    private void setSharedPreferences(View v){
        sharedPreferences = getActivity().getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE);

    }

    private void setCheckTextViews(View v){
        bilingual_check = (CheckedTextView)v.findViewById(R.id.check_bilingual);
        bilingual_check.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ((CheckedTextView) v).toggle();
            }
        });

        media_check = (CheckedTextView)v.findViewById(R.id.check_media);
        media_check.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ((CheckedTextView) v).toggle();
            }
        });

        drama_check = (CheckedTextView)v.findViewById(R.id.check_drama);
        drama_check.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ((CheckedTextView) v).toggle();
            }
        });

        ela_check = (CheckedTextView)v.findViewById(R.id.check_ela);
        ela_check.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ((CheckedTextView) v).toggle();
            }
        });

        history_check = (CheckedTextView)v.findViewById(R.id.check_history);
        history_check.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ((CheckedTextView) v).toggle();
            }
        });

        math_check = (CheckedTextView)v.findViewById(R.id.check_math);
        math_check.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ((CheckedTextView) v).toggle();
            }
        });

        movement_check = (CheckedTextView)v.findViewById(R.id.check_movement);
        movement_check.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ((CheckedTextView) v).toggle();
            }
        });

        music_check = (CheckedTextView)v.findViewById(R.id.check_music);
        music_check.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ((CheckedTextView) v).toggle();
            }
        });

        science_check = (CheckedTextView)v.findViewById(R.id.check_science);
        science_check.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ((CheckedTextView) v).toggle();
            }
        });

        sel_check = (CheckedTextView)v.findViewById(R.id.check_sel);
        sel_check.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ((CheckedTextView) v).toggle();
            }
        });

        art_check = (CheckedTextView)v.findViewById(R.id.check_art);
        art_check.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ((CheckedTextView) v).toggle();
            }
        });
    }

    private void setSpinnerContent(View view){
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner_Grades);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.grades_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}

