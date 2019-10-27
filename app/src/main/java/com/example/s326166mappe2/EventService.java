package com.example.s326166mappe2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class EventService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        Intent i = new Intent(this, MainActivity.class);
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        PendingIntent pintent = PendingIntent.getActivity(this,0, i,0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Upcoming event")
                .setContentText("You have an event at a restaurant today")
                .setSmallIcon(R.drawable.ic_restaurant)
                .setContentIntent(pintent)
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, notification);

        return super.onStartCommand(intent, flags, startId);
    }
}
