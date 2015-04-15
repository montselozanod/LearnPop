package mindpop.learnpop;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

    private Spinner spinner;
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

    private Button btnSave;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_preferences, container, false);
        sharedPreferences = getActivity().getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE);

        setSpinnerContent(rootView);
        setCheckTextViews(rootView);
        setSharedPreferences();

        btnSave = (Button)rootView.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(bilingual, bilingual_check.isChecked());
                editor.putBoolean(media, media_check.isChecked());
                editor.putBoolean(drama, drama_check.isChecked());
                editor.putBoolean(ela, ela_check.isChecked());
                editor.putBoolean(history, history_check.isChecked());
                editor.putBoolean(math, math_check.isChecked());
                editor.putBoolean(movement, movement_check.isChecked());
                editor.putBoolean(music, music_check.isChecked());
                editor.putBoolean(science, science_check.isChecked());
                editor.putBoolean(sel, sel_check.isChecked());
                editor.putBoolean(art, art_check.isChecked());
                editor.commit();
            }
        });

        return rootView;
    }

    private void setSharedPreferences(){

        if(sharedPreferences.contains(bilingual)){
           bilingual_check.setChecked(sharedPreferences.getBoolean(bilingual, false));
        }

        if(sharedPreferences.contains(media)){
            media_check.setChecked(sharedPreferences.getBoolean(media, false));
        }

        if(sharedPreferences.contains(drama)){
            drama_check.setChecked(sharedPreferences.getBoolean(drama, false));
        }

        if(sharedPreferences.contains(ela)){
            ela_check.setChecked(sharedPreferences.getBoolean(ela, false));
        }

        if(sharedPreferences.contains(history)){
            history_check.setChecked(sharedPreferences.getBoolean(history, false));
        }

        if(sharedPreferences.contains(math)){
            math_check.setChecked(sharedPreferences.getBoolean(math, false));
        }

        if(sharedPreferences.contains(movement)){
            movement_check.setChecked(sharedPreferences.getBoolean(movement, false));
        }

        if(sharedPreferences.contains(music)){
            music_check.setChecked(sharedPreferences.getBoolean(music, false));
        }

        if(sharedPreferences.contains(science)){
            science_check.setChecked(sharedPreferences.getBoolean(science, false));
        }

        if(sharedPreferences.contains(sel)){
            sel_check.setChecked(sharedPreferences.getBoolean(sel, false));
        }

        if(sharedPreferences.contains(art)){
            art_check.setChecked(sharedPreferences.getBoolean(art, false));
        }
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
        spinner = (Spinner) view.findViewById(R.id.spinner_Grades);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.grades_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}

