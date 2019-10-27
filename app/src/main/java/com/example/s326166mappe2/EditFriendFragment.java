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
public class EditFriendFragment extends Fragment {

    DbHelper dbHelper;
    Friend friend;
    View view;
    EditText etFriendName;
    EditText etFriendNumber;
    ImageView btnEdit;
    ImageView btnDelete;

    public EditFriendFragment(Friend friend) {
        this.friend = friend;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit_friend, container, false);
        initUI();
        return view;
    }

    private void initUI() {
        etFriendName = (EditText)view.findViewById(R.id.edit_friend_name);
        etFriendNumber = (EditText)view.findViewById(R.id.edit_friend_number);
        btnEdit = (ImageView)view.findViewById(R.id.btn_edit_friend);
        btnDelete = (ImageView)view.findViewById(R.id.btn_delete_friend);
        dbHelper = new DbHelper(getContext());

        etFriendName.setText(friend.getName());
        etFriendNumber.setText(friend.getPh_no());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                friend.setName(etFriendName.getText().toString());
                friend.setPh_no(etFriendNumber.getText().toString());
                dbHelper.editFriend(friend);
                Toast.makeText(getContext(), "EDITED FRIEND", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteFriend(friend.get_ID());
                Toast.makeText(getContext(), "Deleted " + friend.getName(), Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            }
        });
    }
}
