package com.example.s326166mappe2;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditRestFragment extends Fragment {

    DbHelper dbHelper;
    Restaurant rest;
    View view;
    EditText etRestName;
    EditText etRestAddress;
    EditText etRestNumber;
    EditText etRestType;
    ImageView btnEdit;
    ImageView btnDelete;

    public EditRestFragment(Restaurant rest) {
        this.rest = rest;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit_rest, container, false);
        initUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Edit restaurant");
    }

    private void initUI() {
        etRestName = (EditText)view.findViewById(R.id.edit_rest_name);
        etRestAddress = (EditText)view.findViewById(R.id.edit_rest_address);
        etRestNumber = (EditText)view.findViewById(R.id.edit_rest_number);
        etRestType = (EditText)view.findViewById(R.id.edit_rest_Type);
        btnEdit = (ImageView)view.findViewById(R.id.btn_edit_rest);
        btnDelete = (ImageView)view.findViewById(R.id.btn_delete_rest);
        dbHelper = new DbHelper(getContext());

        etRestName.setText(rest.getName());
        etRestAddress.setText(rest.getAddress());
        etRestNumber.setText(rest.getPh_no());
        etRestType.setText(rest.getType());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rest.setName(etRestName.getText().toString());
                rest.setAddress(etRestAddress.getText().toString());
                rest.setPh_no(etRestNumber.getText().toString());
                rest.setType(etRestType.getText().toString());

                dbHelper.editRestaurant(rest);
                getActivity().onBackPressed();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteRestaurant(rest.get_ID());
                dbHelper.deleteEvent(rest.get_ID());
                Toast.makeText(getContext(), "Deleted " + rest.getName(), Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            }
        });
    }
}
