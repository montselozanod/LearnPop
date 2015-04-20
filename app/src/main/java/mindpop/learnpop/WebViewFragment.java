package mindpop.learnpop;

import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.util.Log;
import android.widget.ProgressBar;

/**
 * Created by montselozanod on 3/29/15.
 */
public class WebViewFragment extends Fragment {
    private String resourceURL;
    private String renderURL;
    private ProgressBar progress;

    public void init(String url){
        this.resourceURL = url;
    }

    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {

        //inflater.inflate(R.menu.item_detail, menu);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("SwA", "WVF onCreateView");
        View v = inflater.inflate(R.layout.web_layout, container, false);
        setHasOptionsMenu(true);
        progress = (ProgressBar) v.findViewById(R.id.progressBar);
        progress.setMax(100);
        if (resourceURL != null) {
            Log.d("SwA", "Current URL  1["+resourceURL+"]");
            if(resourceURL.endsWith(".pdf")){
                renderURL = "https://docs.google.com/gview?embedded=true&url=" + resourceURL;
            }else{
                renderURL = resourceURL;
            }

            Log.d("URL to render", renderURL);
            WebView wv = (WebView) v.findViewById(R.id.webView);
            wv.getSettings().setJavaScriptEnabled(true);
            wv.setWebChromeClient(new MyWebViewClient());
            wv.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            wv.loadUrl(renderURL);
        }
        return v;
    }

    public void updateUrl(String url) {
        Log.d("SwA", "Update URL ["+url+"] - View ["+getView()+"]");
        resourceURL = url;

        WebView wv = (WebView) getView().findViewById(R.id.webView);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl(url);

    }

    public void setValue(int progress) {
        this.progress.setProgress(progress);
    }

    private class MyWebViewClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            setValue(newProgress);
            super.onProgressChanged(view, newProgress);
        }
    }

}
