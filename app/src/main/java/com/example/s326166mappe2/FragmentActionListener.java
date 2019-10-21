package com.example.s326166mappe2;

import android.os.Bundle;

public interface FragmentActionListener {

    final int FRIENDS = 1, RESTS = 2, EVENTS = 3;

    int ACTION_EDIT_FRIEND = 1;
    int ACTION_EDIT_RESTAURANT = 2;
    //int ACTION_EDIT_EVENT = 3;
    String ACTION_KEY = "action_key";
    String ACTION_ID = "action";

    public void onAction(Bundle bundle);
}
