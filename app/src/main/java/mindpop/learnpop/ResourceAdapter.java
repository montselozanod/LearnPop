package mindpop.learnpop;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by montselozanod on 4/5/15.
 */
public class ResourceAdapter extends RecyclerView.Adapter<RecyclerAdapter.ResourceViewHolder> {

    List<Resource> resources;

    ResourceAdapter(List<Resource> list){
        this.resources = list;
    }

    public int getItemCount() {
        return resources.size();
    }

    @Override
    public ResourceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_strategy, viewGroup, false);
        ResourceViewHolder pvh = new ResourceViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ResourceViewHolder aViewHolder, int i) {
        aViewHolder.title.setText(resources.get(i).getTitle());
        aViewHolder.subject.setText(resources.get(i).getSubject());
        aViewHolder.icon.setImageResource(R.drawable.ic_like);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ResourceViewHolder extends RecyclerView.ViewHolder{
        CardView res;
        TextView title;
        TextView subject;
        ImageView icon;

        ResourceViewHolder(View itemView){
            super(itemView);
            res = (CardView)itemView.findViewById(R.id.card_resource);
            title = (TextView)itemView.findViewById(R.id.res_title);
            subject = (TextView)itemView.findViewById(R.id.res_sub);
            icon = (ImageView)itemView.findViewById(R.id.icon_res);
        }

    }
}
