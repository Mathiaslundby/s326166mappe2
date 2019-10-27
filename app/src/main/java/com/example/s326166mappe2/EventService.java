package com.example.s326166mappe2;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.telephony.SmsManager;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EventService extends Service {

    Calendar cal;
    SimpleDateFormat dateFormat;
    DbHelper dbHelper;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        cal = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String today = dateFormat.format(cal.getTime());

        dbHelper = new DbHelper(getApplicationContext());
        boolean eventToday = false;

        for (Event e : dbHelper.getAllEvents()) {
            String eventDate = e.getTime().split(" ")[0];
            if(eventDate.equals(today)) {
                eventToday = true;
                break;
            }
        }

        if(!eventToday) {
            return super.onStartCommand(intent, flags, startId);
        }

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

        //sendSMS();

        return super.onStartCommand(intent, flags, startId);
    }

    /*
     * Incomplete function
     *
     * Get all events
     * Find events happening today
     * Find friends belonging to events happening today
     * Find numbers belonging to those friends
     * Send an sms for each number
     */

    public void sendSMS() {
        int MY_PERMISSIONS_REQUEST_SEND_SMS = ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        int MY_PHONE_STATE_PERMISSION = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if(MY_PERMISSIONS_REQUEST_SEND_SMS == PackageManager.PERMISSION_GRANTED &&
                MY_PHONE_STATE_PERMISSION == PackageManager.PERMISSION_GRANTED) {
            SmsManager smsManager = SmsManager.getDefault();

        }
        else{ActivityCompat.requestPermissions((Activity)getApplicationContext(),
                        new String[]{ Manifest.permission.SEND_SMS,
                        Manifest.permission.READ_PHONE_STATE }, 0);
        }
    }
}
