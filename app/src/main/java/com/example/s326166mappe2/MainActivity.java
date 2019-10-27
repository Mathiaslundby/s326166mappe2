package com.example.s326166mappe2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity implements FragmentActionListener{

    Toolbar toolbar;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    DbHelper dbHelper;
    Fragment currentFragment;
    MyPreferencesFragment prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DbHelper(this);
        fragmentManager = getSupportFragmentManager();
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setSupportActionBar(toolbar);
        addEventListFragment();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(prefs != null) {
            getFragmentManager().beginTransaction().remove(prefs).commit();
            prefs = null;
            addEventListFragment();
        }
        else {
            Class c = currentFragment.getClass();
            if (c == EventListFragment.class) {
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory(Intent.CATEGORY_HOME);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            } else if (c == AddFriendFragment.class || c == EditFriendFragment.class) {
                addListFragment(FragmentActionListener.FRIENDS);
            } else if (c == AddRestFragment.class || c == EditRestFragment.class){
                addListFragment(FragmentActionListener.RESTS);
            }else {
                addEventListFragment();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        int list = 0;
        if(prefs != null) {
            getFragmentManager().beginTransaction().remove(prefs).commit();
        }
        switch (id) {
            case R.id.events:
                addEventListFragment();
                return super.onOptionsItemSelected(item);

            case R.id.settings:
                showSettings();
                return super.onOptionsItemSelected(item);

            case R.id.friends:
                list = FragmentActionListener.FRIENDS;
                break;

            case R.id.restaurants:
                list = FragmentActionListener.RESTS;
                break;
        }
        addListFragment(list);
        return super.onOptionsItemSelected(item);
    }

    private void showSettings() {
        fragmentManager.beginTransaction().remove(currentFragment).commit();
        currentFragment = null;
        prefs = new MyPreferencesFragment();
        getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, prefs).commit();
    }

    private void addEventListFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        EventListFragment eventListFragment = new EventListFragment();
        eventListFragment.setFragmentActionListener(this);
        currentFragment = eventListFragment;

        fragmentTransaction.replace(R.id.fragmentContainer, eventListFragment);
        fragmentTransaction.commit();
    }

    private void addListFragment(int listType) {

        fragmentTransaction = fragmentManager.beginTransaction();

        MyListFragment lfFragment = new MyListFragment(listType);
        lfFragment.setFragmentActionListener(this);
        currentFragment = lfFragment;

        fragmentTransaction.replace(R.id.fragmentContainer, lfFragment);
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
        currentFragment = addFriendFragment;
        fragmentTransaction.replace(R.id.fragmentContainer, addFriendFragment);
        fragmentTransaction.commit();
    }

    private void addRestaurant() {
        fragmentTransaction = fragmentManager.beginTransaction();
        AddRestFragment addRestFragment = new AddRestFragment();
        currentFragment = addRestFragment;
        fragmentTransaction.replace(R.id.fragmentContainer, addRestFragment);
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

            case FragmentActionListener.EVENTS:
                addEventListFragment();
                break;
        }
    }

    private void addEvent() {
        fragmentTransaction = fragmentManager.beginTransaction();
        AddEventFragment addEventFragment = new AddEventFragment();
        currentFragment = addEventFragment;
        fragmentTransaction.replace(R.id.fragmentContainer, addEventFragment);
        fragmentTransaction.commit();
    }

    private void editRest(Bundle bundle) {
        long id = bundle.getLong(FragmentActionListener.ACTION_ID);
        Restaurant rest = dbHelper.getRest(id);

        fragmentTransaction = fragmentManager.beginTransaction();
        EditRestFragment editRestFragment = new EditRestFragment(rest);
        currentFragment = editRestFragment;

        fragmentTransaction.replace(R.id.fragmentContainer, editRestFragment);
        fragmentTransaction.commit();
    }

    private void editFriend(Bundle bundle) {
        long id = bundle.getLong(FragmentActionListener.ACTION_ID);
        Friend friend = dbHelper.getFriend(id);

        fragmentTransaction = fragmentManager.beginTransaction();
        EditFriendFragment editFriendFragment = new EditFriendFragment(friend);
        currentFragment = editFriendFragment;

        fragmentTransaction.replace(R.id.fragmentContainer, editFriendFragment);
        fragmentTransaction.commit();
    }
}