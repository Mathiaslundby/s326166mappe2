package com.example.s326166mappe2;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyPreferencesFragment extends PreferenceFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        findPreference("pref_notify").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (!((CheckBoxPreference)findPreference("pref_notify")).isChecked()) {
                    Intent intent = new Intent();
                    intent.setAction("com.example.s326166mappe1.myBroadcast");
                    getActivity().sendBroadcast(intent);
                }
                else {
                    Intent intent = new Intent(getContext(), EventService.class);
                    PendingIntent pintent = PendingIntent.getService(getContext(),0,intent,0);
                    AlarmManager alarm = (AlarmManager)getActivity().getSystemService(getContext().ALARM_SERVICE);

                    if(alarm != null) {
                        alarm.cancel(pintent);
                    }
                }
                return true;
            }
        });

        findPreference("pref_sms").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if(!((CheckBoxPreference)findPreference("pref_sms")).isChecked()) {
                    //Turn SMS service on
                }
                else {
                    //Turn SMS service off
                }
                return true;
            }
        });
    }
}
