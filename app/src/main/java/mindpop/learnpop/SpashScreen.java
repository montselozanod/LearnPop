package mindpop.learnpop;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;

import com.facebook.*;


/**
 * Created by montselozanod on 3/23/15.
 */
public class SpashScreen extends Activity{

    //screen timer
    private static int SPASH_TIME_OUT = 3000;
    //private SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_spash);

       
    }
}
