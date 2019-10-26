package com.example.s326166mappe2;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventListFragment extends Fragment {

    View view;
    ImageView btnAdd;
    ListView lvEvents;

    DbHelper dbHelper;
    ArrayList<Event> events;
    FragmentActionListener fragmentActionListener;

    private static EventListAdapter adapter;

    public EventListFragment() {
        // Required empty public constructor
    }

    public void setFragmentActionListener(FragmentActionListener fragmentActionListener) {
        this.fragmentActionListener = fragmentActionListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_event_list, container, false);
        initUI();
        return view;
    }

    private void initUI() {
        btnAdd = (ImageView)view.findViewById(R.id.event_add);
        lvEvents = (ListView)view.findViewById(R.id.lvEvents);

        dbHelper = new DbHelper(getContext());
        events = new ArrayList<>();
        events.addAll(dbHelper.getAllEvents());

        adapter = new EventListAdapter(events, getContext());
        lvEvents.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {  //Bugged somehow ?
            @Override
            public void onClick(View v) {
                if(fragmentActionListener != null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(FragmentActionListener.ACTION_KEY, FragmentActionListener.ACTION_ADD_EVENT);
                    fragmentActionListener.onAction(bundle);
                }
            }
        });
    }
}
