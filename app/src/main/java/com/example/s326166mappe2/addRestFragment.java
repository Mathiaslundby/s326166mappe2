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
public class addRestFragment extends Fragment {

    EditText etName;
    EditText etAddress;
    EditText etNumber;
    EditText etType;
    Button btnAdd;

    View view;

    public addRestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_rest, container, false);
        initUI();
        return view;
    }

    private void initUI() {
        etName = (EditText)view.findViewById(R.id.add_rest_name);

        btnAdd = (Button)view.findViewById(R.id.btn_add_rest);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Restaurant friend", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
