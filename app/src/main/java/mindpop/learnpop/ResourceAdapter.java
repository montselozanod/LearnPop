package mindpop.learnpop;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by montselozanod on 4/5/15.
 */
public class ResourceAdapter extends RecyclerView.Adapter<ResourceAdapter.ResourceViewHolder> {

    List<Resource> resources;
    private FragmentActivity _activity;

    ResourceAdapter(FragmentActivity ac, List<Resource> list){
        this._activity= ac;
        this.resources = list;
    }

    public int getItemCount() {
        return resources.size();
    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {

            Log.d("Drawable", e.toString());
            return null;
        }
    }


    @Override
    public ResourceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_strategy, viewGroup, false);
        ResourceViewHolder pvh = new ResourceViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ResourceViewHolder aViewHolder, int i) {
        aViewHolder.setClickListener(new ResourceViewHolder.ClickListener(){
            @Override
            public void onClick(View v, int pos, boolean isLongClick){
                Resource item = resources.get(pos);
                /*WebViewFragment webFrag = new WebViewFragment();
                webFrag.init(item.getUrl());*/
                WebItem webItem = new WebItem();
                webItem.init(item);
                _activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, webItem).addToBackStack(null).commit();
            }
        });

        aViewHolder.title.setText(resources.get(i).getTitle());
        aViewHolder.subject.setText(resources.get(i).getSubject());
        try{
            URL url = new URL("http://www.flowerwallpapers.org/wallpapers/image/Purple-Flowers-Wallpaper.jpg");
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            aViewHolder.icon.setImageBitmap(bmp);
        }catch(Exception e){

        }
        //Drawable d = LoadImageFromWebOperations(resources.get(i).getImageURL());
        //aViewHolder.icon.setImageResource(d);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ResourceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        CardView res;
        TextView title;
        TextView subject;
        ImageView icon;

        private ClickListener clickListener;

        public interface ClickListener{

            public void onClick(View v, int position, boolean isLongClick);
        }

        ResourceViewHolder(View itemView){
            super(itemView);
            res = (CardView)itemView.findViewById(R.id.card_resource);
            title = (TextView)itemView.findViewById(R.id.res_title);
            subject = (TextView)itemView.findViewById(R.id.res_sub);
            icon = (ImageView)itemView.findViewById(R.id.icon_res);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

            public void setClickListener(ClickListener clickListener) {
                this.clickListener = clickListener;
            }

            @Override
            public void onClick(View v) {

                // If not long clicked, pass last variable as false.
                clickListener.onClick(v, getPosition(), false);
            }

            @Override
            public boolean onLongClick(View v) {

                // If long clicked, passed last variable as true.
                clickListener.onClick(v, getPosition(), true);
                return true;
            }
        }

}

