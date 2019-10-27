package com.example.s326166mappe2;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFriendFragment extends Fragment {

    View view;
    EditText etName;
    EditText etNumber;
    ImageView btnAdd;

    DbHelper dbHelper;

    public AddFriendFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Add friend");
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
        btnAdd = (ImageView) view.findViewById(R.id.btn_add_friend);
        dbHelper = new DbHelper(getContext());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Friend friend = new Friend();
                friend.setName(etName.getText().toString());
                friend.setPh_no(etNumber.getText().toString());
                dbHelper.addFriend(friend);

                Toast.makeText(getContext(), "Friend added!", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            }
        });
    }
}
