package mindpop.learnpop;


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import android.util.Log;

import java.util.List;

/**
 * Created by montselozanod on 4/4/15.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.AnnouncementViewHolder> {

    List<Announcement> announces;

    RecyclerAdapter(){}

    RecyclerAdapter(List<Announcement> list_announces){

        this.announces = list_announces;
        Log.d("Chabuz", announces.get(0).getMessage());
    }

    @Override
    public int getItemCount() {
        return announces.size();
    }

    @Override
    public AnnouncementViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_announce, viewGroup, false);
        AnnouncementViewHolder pvh = new AnnouncementViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(AnnouncementViewHolder aViewHolder, int i) {
        aViewHolder.message.setText(announces.get(i).getMessage());
        aViewHolder.dates.setText(announces.get(i).getEnd());
        aViewHolder.icon.setImageResource(announces.get(i).getImg());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class AnnouncementViewHolder extends RecyclerView.ViewHolder{
        CardView announce;
        TextView message;
        TextView dates;
        ImageView icon;

        AnnouncementViewHolder(View itemView){
            super(itemView);
            announce = (CardView)itemView.findViewById(R.id.announce);
            message = (TextView)itemView.findViewById(R.id.ann_message);
            dates = (TextView)itemView.findViewById(R.id.ann_dates);
            icon = (ImageView)itemView.findViewById(R.id.icon_photo);

        }

    }
}

