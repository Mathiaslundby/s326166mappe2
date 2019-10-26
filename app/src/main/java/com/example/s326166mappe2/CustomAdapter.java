package com.example.s326166mappe2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<DataModel> {

    Context mContext;
    int listType;

    public CustomAdapter(ArrayList<DataModel> data, Context context, int listType) {
        super(context, R.layout.row_item, data);
        this.mContext = context;
        this.listType = listType;
    }

    private static class ViewHolder {
        TextView txtTitle;
        TextView txtSubtitle;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;

        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.txtTitle = (TextView)convertView.findViewById(R.id.row_name);
            viewHolder.txtSubtitle = (TextView)convertView.findViewById(R.id.row_number);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        switch (listType) {
            case FragmentActionListener.FRIENDS:
                Friend friend = (Friend)getItem(position);
                viewHolder.txtTitle.setText(friend.getName());
                viewHolder.txtSubtitle.setText(friend.getPh_no());
                break;

            case FragmentActionListener.RESTS:
                Restaurant restaurant = (Restaurant)getItem(position);
                viewHolder.txtTitle.setText(restaurant.getName());
                viewHolder.txtSubtitle.setText(restaurant.getAddress());
                break;
        }
        return convertView;
    }
}