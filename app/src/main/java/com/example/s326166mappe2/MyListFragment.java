package com.example.s326166mappe2;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyListFragment extends Fragment {

    View view;
    ImageView btnAdd;
    ArrayList<DataModel> data;
    ListView listView;
    DbHelper dbHelper;
    FragmentActionListener fragmentActionListener;

    private static CustomAdapter adapter;

    int listType;

    public MyListFragment(int listType) {
        this.listType = listType;
    }

    public void setFragmentActionListener(FragmentActionListener fragmentActionListener) {
        this.fragmentActionListener = fragmentActionListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list, container, false);
        initUI();
        return view;
    }

    private void initUI() {
        listView = (ListView)view.findViewById(R.id.friendsList);
        data = new ArrayList<>();
        dbHelper = new DbHelper(getContext());

        switch (listType) {
            case FragmentActionListener.FRIENDS:
                data.addAll(dbHelper.getAllFriends());
                break;

            case FragmentActionListener.RESTS:
                data.addAll(dbHelper.getAllRests());
                break;
        }

        adapter = new CustomAdapter(data, getContext(), listType);
        listView.setAdapter(adapter);

        btnAdd = (ImageView)view.findViewById(R.id.list_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fragmentActionListener != null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(FragmentActionListener.ACTION_KEY, FragmentActionListener.ACTION_ADD);
                    bundle.putInt(FragmentActionListener.KEY_ADD, listType);
                    fragmentActionListener.onAction(bundle);
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(fragmentActionListener != null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(FragmentActionListener.ACTION_KEY, listType);
                    bundle.putLong(FragmentActionListener.ACTION_ID, data.get(position).get_ID());
                    fragmentActionListener.onAction(bundle);
                }
            }
        });
    }
}
