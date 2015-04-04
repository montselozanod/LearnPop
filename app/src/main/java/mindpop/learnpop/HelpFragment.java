package mindpop.learnpop;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;




public class HelpFragment extends Fragment {

    private EditText comments;
    private Button sendButton;
    private final String subject="Creative Teach App - User Email";
    private final String email_mindpop = "info@mindpop.org";
    private final String montse_email = "montse_967@hotmail.com";
    public HelpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_help, container, false);

        comments = (EditText)rootView.findViewById(R.id.comments);
        sendButton = (Button)rootView.findViewById(R.id.btnSend);
        sendButton.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){
                sendEmail();
                comments.setText("");
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    protected  void sendEmail(){
        Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
        // prompts email clients only

        email.setType("message/rfc822");
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{email_mindpop});

        email.putExtra(Intent.EXTRA_SUBJECT, subject);

        email.putExtra(Intent.EXTRA_TEXT, comments.getText().toString());
        try {

            startActivity(Intent.createChooser(email, "Choose an email client from..."));

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), "No email client installed.", Toast.LENGTH_LONG).show();
        }


    }


}
