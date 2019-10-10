package com.example.s326166mappe2;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantListFragment extends Fragment {

    DbHandler db;

    public RestaurantListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_list, container, false);

        db = new DbHandler(getActivity());
        ArrayList<String> menuItems = new ArrayList<String>();

        List<Restaurant> rList = db.getAllRestaurants();

        for (Restaurant r : rList) {
            menuItems.add(r.getName());
        }

        ListView lv = (ListView)view.findViewById(R.id.rest_listView);

        ArrayAdapter<String> lvAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                menuItems
        );

        lv.setAdapter(lvAdapter);

        // Inflate the layout for this fragment
        return view;
    }

}
