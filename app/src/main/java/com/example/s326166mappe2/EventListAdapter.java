package com.example.s326166mappe2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
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
    FragmentActionListener fragmentActionListener;

    public EventListAdapter(ArrayList<Event> events, Context context) {
        super(context, R.layout.event_row_item, events);
        this.mContext = context;
        this.dbHelper = new DbHelper(mContext);
    }

    private static class ViewHolder {
        TextView tvRest;
        TextView tvTime;
        TextView tvFriends;
        ImageView btnDelete;
    }

    public void setFragmentActionListener(FragmentActionListener fragmentActionListener) {
        this.fragmentActionListener = fragmentActionListener;
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
            viewHolder.tvFriends = (TextView)convertView.findViewById(R.id.event_friends);
            viewHolder.btnDelete = (ImageView)convertView.findViewById(R.id.row_delete);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        final Event event = (Event) getItem(position);
        Restaurant rest = dbHelper.getRest(event.getRest());
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
        viewHolder.tvFriends.setText(friendNames);

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(mContext)
                        .setTitle("Delete event")
                        .setMessage("Are you sure you want to delete this event?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mContext, "Deleted event", Toast.LENGTH_SHORT).show();
                                dbHelper.deleteEvent(event.get_ID());
                                dbHelper.deleteEventFriends(event.get_ID());
                                if(fragmentActionListener != null) {
                                    Bundle bundle = new Bundle();
                                    bundle.putInt(FragmentActionListener.ACTION_KEY, FragmentActionListener.EVENTS);
                                    fragmentActionListener.onAction(bundle);
                                }
                            }
                        })
                        .setNeutralButton(android.R.string.no, null)
                        .setIcon(R.drawable.ic_delete_row)
                        .show();
            }
        });

        return convertView;
    }
}
