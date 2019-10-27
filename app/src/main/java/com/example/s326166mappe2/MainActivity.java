package com.example.s326166mappe2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentActionListener{

    Toolbar toolbar;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DbHelper(this);
        fragmentManager = getSupportFragmentManager();
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setSupportActionBar(toolbar);
        addListFragment(FRIENDS);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        int list = 0;

        switch (id) {
            case R.id.friends:
                list = FragmentActionListener.FRIENDS;
                break;

            case R.id.restaurants:
                list = FragmentActionListener.RESTS;
                break;

            case R.id.events:
                addEventListFragment();
                return super.onOptionsItemSelected(item);
        }
        addListFragment(list);
        return super.onOptionsItemSelected(item);
    }

    private void addEventListFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        EventListFragment eventListFragment = new EventListFragment();
        eventListFragment.setFragmentActionListener(this);

        fragmentTransaction.replace(R.id.fragmentContainer, eventListFragment);
        fragmentTransaction.addToBackStack("lists");
        fragmentTransaction.commit();
    }

    private void addListFragment(int listType) {

        fragmentTransaction = fragmentManager.beginTransaction();

        MyListFragment lfFragment = new MyListFragment(listType);
        lfFragment.setFragmentActionListener(this);

        fragmentTransaction.replace(R.id.fragmentContainer, lfFragment);
        fragmentTransaction.addToBackStack("lists");
        fragmentTransaction.commit();
    }

    private void addToList(Bundle bundle) {
        int item = bundle.getInt(FragmentActionListener.KEY_ADD);

        switch (item) {
            case FragmentActionListener.FRIENDS:
                addFriend();
                break;

            case FragmentActionListener.RESTS:
                addRestaurant();
                break;
        }
    }

    private void addFriend() {
        fragmentTransaction = fragmentManager.beginTransaction();
        AddFriendFragment addFriendFragment = new AddFriendFragment();
        fragmentTransaction.replace(R.id.fragmentContainer, addFriendFragment);
        fragmentTransaction.addToBackStack("add");
        fragmentTransaction.commit();
    }

    private void addRestaurant() {
        fragmentTransaction = fragmentManager.beginTransaction();
        AddRestFragment addRestFragment = new AddRestFragment();
        fragmentTransaction.replace(R.id.fragmentContainer, addRestFragment);
        fragmentTransaction.addToBackStack("add");
        fragmentTransaction.commit();
    }

    @Override
    public void onAction(Bundle bundle) {
        int action = bundle.getInt(FragmentActionListener.ACTION_KEY);
        switch (action) {
            case FragmentActionListener.ACTION_EDIT_FRIEND:
                editFriend(bundle);
                break;

            case  FragmentActionListener.ACTION_EDIT_RESTAURANT:
                editRest(bundle);
                break;

            case FragmentActionListener.ACTION_ADD:
                addToList(bundle);
                break;

            case FragmentActionListener.ACTION_ADD_EVENT:
                addEvent();
                break;
        }
    }

    private void addEvent() {
        fragmentTransaction = fragmentManager.beginTransaction();
        AddEventFragment addEventFragment = new AddEventFragment();
        fragmentTransaction.replace(R.id.fragmentContainer, addEventFragment);
        fragmentTransaction.addToBackStack("list");
        fragmentTransaction.commit();
    }

    private void editRest(Bundle bundle) {
        long id = bundle.getLong(FragmentActionListener.ACTION_ID);
        Restaurant rest = dbHelper.getRest(id);

        fragmentTransaction = fragmentManager.beginTransaction();
        EditRestFragment editRestFragment = new EditRestFragment(rest);

        fragmentTransaction.replace(R.id.fragmentContainer, editRestFragment);
        fragmentTransaction.addToBackStack("edit");
        fragmentTransaction.commit();
    }

    private void editFriend(Bundle bundle) {
        long id = bundle.getLong(FragmentActionListener.ACTION_ID);
        Friend friend = dbHelper.getFriend(id);

        fragmentTransaction = fragmentManager.beginTransaction();
        EditFriendFragment editFriendFragment = new EditFriendFragment(friend);

        fragmentTransaction.replace(R.id.fragmentContainer, editFriendFragment);
        fragmentTransaction.addToBackStack("edit");
        fragmentTransaction.commit();
    }
}