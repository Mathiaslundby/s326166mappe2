package com.example.s326166mappe2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class EventBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, SetPeriodicService.class);
        context.startService(i);
    }
}
