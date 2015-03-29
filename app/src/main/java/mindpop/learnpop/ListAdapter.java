package mindpop.learnpop;

import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;
/**
 * Created by montselozanod on 3/29/15.
 */
public class ListAdapter extends ArrayAdapter<Resource> {

    public ListAdapter(Context context, List<Resource> items){
        super(context, R.layout.list_item, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);

            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // update the item view
        Resource item = getItem(position);
        viewHolder.tvTitle.setText(item.getTitle());
        viewHolder.tvDescription.setText(item.getSubject());

        return convertView;
    }

    private static class ViewHolder {
        TextView tvTitle;
        TextView tvDescription;
    }
}
