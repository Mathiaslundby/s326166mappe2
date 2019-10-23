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
public class AddRestFragment extends Fragment {

    EditText etName;
    EditText etAddress;
    EditText etNumber;
    EditText etType;
    Button btnAdd;
    View view;

    DbHelper dbHelper;

    public AddRestFragment() {
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
        etAddress = (EditText)view.findViewById(R.id.add_rest_address);
        etNumber = (EditText)view.findViewById(R.id.add_rest_number);
        etType = (EditText)view.findViewById(R.id.add_rest_Type);
        btnAdd = (Button)view.findViewById(R.id.btn_add_rest);
        dbHelper = new DbHelper(getContext());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Restaurant rest = new Restaurant();
                rest.setName(etName.getText().toString());
                rest.setAddress(etAddress.getText().toString());
                rest.setPh_no(etNumber.getText().toString());
                rest.setType(etType.getText().toString());

                dbHelper.addRestaurant(rest);
                Toast.makeText(getContext(), "Restaurant added!", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            }
        });
    }
}
