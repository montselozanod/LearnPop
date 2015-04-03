package mindpop.learnpop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by montselozanod on 3/20/15.
 */
public class Share extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_share, container, false);
        setSpinnerContent(rootView);
        return rootView;
    }

    private void setSpinnerContent(View view){

        Spinner spinnerGrades = (Spinner) view.findViewById(R.id.spinner_Grades);
        ArrayAdapter <CharSequence> adapterG = ArrayAdapter.createFromResource(getActivity(), R.array.grades_array, android.R.layout.simple_spinner_item);
        adapterG.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGrades.setAdapter(adapterG);

        Spinner spinnerSubjects = (Spinner) view.findViewById(R.id.spinner_Subjects);
        ArrayAdapter <CharSequence> adapterS = ArrayAdapter.createFromResource(getActivity(), R.array.subjects_array, android.R.layout.simple_spinner_item);
        adapterS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubjects.setAdapter(adapterS);

        Spinner spinnerType = (Spinner) view.findViewById(R.id.spinner_Type);
        ArrayAdapter <CharSequence> adapterT = ArrayAdapter.createFromResource(getActivity(), R.array.type_array, android.R.layout.simple_spinner_item);
        adapterT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapterT);
    }
}
