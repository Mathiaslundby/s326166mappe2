package com.example.s326166mappe2;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditFriendFragment extends Fragment {

    Friend friend;
    View view;
    EditText etFriendName;
    EditText etFriendNumber;

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

        etFriendName.setText(friend.getName());
        etFriendNumber.setText(friend.getPh_no());
    }
}
