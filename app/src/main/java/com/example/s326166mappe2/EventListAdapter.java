package com.example.s326166mappe2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
        ListView lvFriends;
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
            viewHolder.lvFriends = (ListView)convertView.findViewById(R.id.lv_event_friends);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Event event = (Event) getItem(position);
        Restaurant rest = dbHelper.getRest(event.getRest());
        List<String> friends = new ArrayList<>();
        List<Long> friendIds = dbHelper.getFriendIds(event.get_ID());

        for (long id : friendIds) {
            friends.add(dbHelper.getFriend(id).getName());
        }
        String time = event.getTime();
        viewHolder.tvRest.setText(rest.getName());
        viewHolder.tvTime.setText(event.getTime());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, friends);
        viewHolder.lvFriends.setAdapter(adapter);

        return convertView;
    }
}
