package com.example.s326166mappe2;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFriendFragment extends Fragment {

    View view;
    EditText etName;
    EditText etNumber;
    Button btnAdd;

    public AddFriendFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_friend, container, false);
        initUI();
        return view;
    }

    private void initUI() {
        etName = (EditText)view.findViewById(R.id.add_friend_name);
        etNumber = (EditText)view.findViewById(R.id.add_friend_number);
        btnAdd = (Button)view.findViewById(R.id.btn_add_friend);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Added friend", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
