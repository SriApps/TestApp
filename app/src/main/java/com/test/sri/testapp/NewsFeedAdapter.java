package com.test.sri.testapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Myworld on 22/02/2018.
 */

public class NewsFeedAdapter extends ArrayAdapter<NewsFeedListModel> {
    private Activity activity;
    private List<NewsFeedListModel> newsFeedList;


    public NewsFeedAdapter(Activity activity,  int resource,List<NewsFeedListModel> newsFeedList) {
        super(activity,resource,newsFeedList);
        this.activity = activity;
        this.newsFeedList = newsFeedList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            // inflate UI from XML file
            convertView = inflater.inflate(R.layout.main_list_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        }
        else {

            holder = (ViewHolder) convertView.getTag();
        }

        NewsFeedListModel newsFeedListModel = getItem(position);
        holder.title.setText(newsFeedListModel.getTitle());
        holder.description.setText(newsFeedListModel.getDescription());
        Picasso.with(activity)
                .load(newsFeedListModel.getImageHref())
                .error(R.drawable.sample)
                .placeholder(R.drawable.sample)// Image to load when something goes wrong
                .into(holder.image);
        return convertView;
    }

    private static class ViewHolder {
        private TextView title;
        private TextView description;
        private ImageView image;

        public ViewHolder(View v) {
            title =  v.findViewById(R.id.title_text_view);
            description =  v.findViewById(R.id.description_text_view);
            image =  v.findViewById(R.id.place_image_view);

        }
    }

}
