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

        Friend f = new Friend("Jarsveen", "911");
        /*
        dbHelper.addFriend(f);
        Restaurant r = new Restaurant("Max", "Stortinget", "87654321", "Burger");
        dbHelper.addRestaurant(r);
        dbHelper.addRestaurant(r);
        dbHelper.addRestaurant(r);
        dbHelper.addRestaurant(r);
*/

        fragmentManager = getSupportFragmentManager();
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        }

        fragmentTransaction = fragmentManager.beginTransaction();

        MyListFragment lfFragment = new MyListFragment(list);
        lfFragment.setFragmentActionListener(this);

        fragmentTransaction.replace(R.id.fragmentContainer, lfFragment);
        fragmentTransaction.addToBackStack("lists");
        fragmentTransaction.commit();

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onAction(Bundle bundle) {
        int action = bundle.getInt(FragmentActionListener.ACTION_KEY);
        switch (action) {
            case FragmentActionListener.ACTION_EDIT_FRIEND:
                editFriend(bundle);
                break;
        }
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
