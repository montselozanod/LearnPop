package mindpop.learnpop;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by montselozanod on 3/20/15.
 */
public class Share extends Fragment {
    private ProgressDialog pDialog;
    private TextView name;
    private TextView resUrl;
    private Spinner spinnerGrades;
    private Spinner spinnerSubjects;
    private Spinner spinnerType;
    private Button btnSend;
    private String request_url = "http://austinartmap.com/CreativeTeach/PHP/insertResource_v3.php";
    private JSONParser jsonParser = new JSONParser();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_share, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSpinnerContent(rootView);
        name = (TextView)rootView.findViewById(R.id.resName);
        resUrl = (TextView)rootView.findViewById(R.id.editURL);
        btnSend = (Button)rootView.findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new CreateNewResource().execute();
            }
        });
        return rootView;
    }

    private void setSpinnerContent(View view){

        spinnerGrades = (Spinner) view.findViewById(R.id.spinner_Grades);
        ArrayAdapter <CharSequence> adapterG = ArrayAdapter.createFromResource(getActivity(), R.array.grades_array, android.R.layout.simple_spinner_item);
        spinnerGrades.setAdapter(adapterG);
        adapterG.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGrades.setPromptId(R.string.grade_prompt);


        spinnerSubjects = (Spinner) view.findViewById(R.id.spinner_Subjects);
        ArrayAdapter <CharSequence> adapterS = ArrayAdapter.createFromResource(getActivity(), R.array.subjects_array, android.R.layout.simple_spinner_item);
        spinnerSubjects.setAdapter(adapterS);
        adapterS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubjects.setPromptId(R.string.subject_prompt);

        spinnerType = (Spinner) view.findViewById(R.id.spinner_Type);
        ArrayAdapter <CharSequence> adapterT = ArrayAdapter.createFromResource(getActivity(), R.array.type_array, android.R.layout.simple_spinner_item);
        spinnerType.setAdapter(adapterT);
        adapterT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setPromptId(R.string.type_prompt);

    }

    class CreateNewResource extends AsyncTask<String, String, String>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Uploading Resource...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        private String getGradeParamName(String completeGrade){
            String param = "";
            switch (completeGrade){
                case "Early Childhood":
                    param = "Childhood";
                    break;
                case "Lower Elementary":
                    param = "LowerElem";
                    break;
                case "Upper Elementary":
                    param = "UpperElem";
                    break;
                case "Middle School":
                    param = "Middle";
                    break;
                case "High School":
                    param ="High";
                    break;
            }
            return param;
        }

        protected String doInBackground(String... args){
            String resName = name.getText().toString();
            String url = resUrl.getText().toString();
            String type = spinnerType.getSelectedItem().toString();
            String subject = spinnerSubjects.getSelectedItem().toString();
            String grade = spinnerGrades.getSelectedItem().toString();

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("ResName", resName));
            params.add(new BasicNameValuePair("ResURL", url));
            params.add(new BasicNameValuePair("ResType", type));
            params.add(new BasicNameValuePair("Subject[]", subject));
            params.add(new BasicNameValuePair("GradeLevel[]", getGradeParamName(grade)));

            JSONObject json = jsonParser.makeHttpRequest(request_url, "POST", params);

            Log.d("Create resource response", json.toString());

            return null;
        }

        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
            //if success
            name.setText("");
            resUrl.setText("");
            Toast.makeText(getActivity(), "Resource uploaded!", Toast.LENGTH_SHORT).show();
        }
    }
}
