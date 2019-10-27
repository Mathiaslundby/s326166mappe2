package com.example.s326166mappe2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class EventListAdapter extends ArrayAdapter<Event> {

    Context mContext;
    DbHelper dbHelper;

    public EventListAdapter(ArrayList<Event> events, Context context) {
        super(context, R.layout.event_row_item, events);
        this.mContext = context;
        this.dbHelper = new DbHelper(mContext);
    }

    private static class ViewHolder {
        TextView tvRest;
        TextView tvTime;
        TextView tvNumber;
        TextView tvFriends;
        ImageView btnDelete;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;

        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.event_row_item, parent, false);
            viewHolder.tvRest = (TextView)convertView.findViewById(R.id.event_rest);
            viewHolder.tvTime = (TextView)convertView.findViewById(R.id.event_time);
            viewHolder.tvNumber = (TextView)convertView.findViewById(R.id.event_rest_number);
            viewHolder.tvFriends = (TextView)convertView.findViewById(R.id.event_friends);
            viewHolder.btnDelete = (ImageView)convertView.findViewById(R.id.row_delete);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        final Event event = (Event) getItem(position);
        Restaurant rest = dbHelper.getRest(event.getRest());
        if(rest == null) return convertView;    //midlertidig løsning
        List<String> friends = new ArrayList<>();
        List<Long> friendIds = dbHelper.getFriendIds(event.get_ID());
        String restNameAndAddress = rest.getName() + ", " + rest.getAddress();

        for (long id : friendIds) {
            if(dbHelper.getFriend(id) != null) {
                friends.add(dbHelper.getFriend(id).getName());
            }
        }

        String friendNames = "";
        for(String s : friends) {
            friendNames += s + "\n";
        }

        viewHolder.tvRest.setText(restNameAndAddress);
        viewHolder.tvTime.setText(event.getTime());
        viewHolder.tvNumber.setText(rest.getPh_no());
        viewHolder.tvFriends.setText(friendNames);

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "DELETED " + event.get_ID(), Toast.LENGTH_SHORT).show();
                dbHelper.deleteEvent(event.getRest());  //Deletes both?
            }
        });

        return convertView;
    }
}
