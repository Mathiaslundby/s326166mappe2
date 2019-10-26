package com.example.s326166mappe2;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddEventFragment extends Fragment {

    View view;
    TextView tvEmptyList;
    EditText etDate;
    EditText etTime;
    EditText etRest;
    ListView lvFriends;
    Button btnSelectFriends;
    ImageView btnAdd;

    DbHelper dbHelper;
    List<Friend> allFriends;
    List<String> selectedFriends;
    Restaurant selectedRest;

    boolean[] prevChecked;
    boolean emptyList;

    public AddEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_event, container, false);
        initUI();
        return view;
    }

    private void initUI() {
        tvEmptyList = (TextView)view.findViewById(R.id.list_event_empty);
        etDate = (EditText) view.findViewById(R.id.event_add_date);
        etTime = (EditText) view.findViewById(R.id.event_add_time);
        etRest = (EditText) view.findViewById(R.id.event_add_rest);
        lvFriends = (ListView)view.findViewById(R.id.lvFriends);
        btnSelectFriends = (Button)view.findViewById(R.id.select_friends);
        btnAdd = (ImageView)view.findViewById(R.id.btn_add_event);

        dbHelper = new DbHelper(getContext());
        allFriends = dbHelper.getAllFriends();
        prevChecked = new boolean[allFriends.size()];
        for (int i = 0; i < prevChecked.length; i++) {
            prevChecked[i] = false;
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEvent();
            }
        });

        btnSelectFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFriends = new ArrayList<>();
                setFriends();
            }
        });

        etRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRest();
            }
        });

        final Calendar calendar = Calendar.getInstance();
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                String date = day + "/" + (month + 1) + "/" + year;
                                etDate.setText(date);
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
                datePickerDialog.show();
            }
        });

        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = hourOfDay + ":" + minute;
                        etTime.setText(time);
                    }
                },0,0, true);
                timePickerDialog.show();
            }
        });
    }

    private void setRest() {
        final List<Restaurant> rests = dbHelper.getAllRests();
        final String[] restNames = new String[rests.size()];
        for (int i = 0; i < rests.size(); i++) {
            restNames[i] = rests.get(i).getName() + ", " + rests.get(i).getAddress();
        }

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
        mBuilder.setTitle("Select restaurant");
        mBuilder.setSingleChoiceItems(restNames, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                etRest.setText(restNames[which]);
                dialog.dismiss();
            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void setFriends() {
        emptyList = true;
        final String[] friendNames = new String[allFriends.size()];
        final boolean[] checkedFriends = new boolean[allFriends.size()];
        for (int i = 0; i < allFriends.size(); i++) {
            friendNames[i] = allFriends.get(i).getName() + ", " + allFriends.get(i).getPh_no();
            checkedFriends[i] = prevChecked[i];
        }

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
        mBuilder.setTitle("Select friends");

        mBuilder.setMultiChoiceItems(friendNames, checkedFriends, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                checkedFriends[which] = isChecked;
            }
        });

        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                for (int i = 0; i < checkedFriends.length; i++) {
                    if(checkedFriends[i]) {
                        prevChecked[i] = true;
                        selectedFriends.add(friendNames[i]);
                        emptyList = false;
                    }
                    else {
                        prevChecked[i] = false;
                    }
                }

                if (emptyList) {
                    tvEmptyList.setVisibility(View.VISIBLE);
                } else {
                    tvEmptyList.setVisibility(View.INVISIBLE);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, selectedFriends);
                lvFriends.setAdapter(adapter);
            }
        });

        mBuilder.setNegativeButton("Cancel", null);

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void addEvent() {
        String[] rest = etRest.getText().toString().split(", ");
        String[][] friends = new String[selectedFriends.size()][2];
        for(int i = 0; i < selectedFriends.size(); i++) {
            String[] f = lvFriends.getAdapter().getItem(i).toString().split(", ");
            friends[i][0] = f[0];
            friends[i][1] = f[1];
        }

        String time = etDate.getText().toString() + " " + etTime.getText().toString();
        Restaurant r = dbHelper.getSelectedRest(rest[0], rest[1]);
        long[] friendIds = new long[selectedFriends.size()];
        for (int i = 0; i < friends.length; i++) {
            friendIds[i] = dbHelper.getSelectedFriendId(friends[i][0], friends[i][1]);
        }

        Event event = new Event(r.get_ID(), time);
        dbHelper.addEvent(event);

        Log.d("LOGGING", "" + selectedFriends.size());

        long eventId = dbHelper.getEventId();
        for (long friendId : friendIds) {
            dbHelper.addEventFriend(eventId, friendId);
        }
    }
}
