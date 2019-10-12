package com.example.s326166mappe2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    Toolbar tb;
    TextView tv;
    RestaurantListFragment rListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.text);
        tb = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(tb);
    }

    @Override
    protected void onResume() {
        super.onResume();
        rListFragment = new RestaurantListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, rListFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.add_restaurant:
                addRestaurant();
            return true;

            case R.id.add_friend:
                addFriend();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void addRestaurant() {
        Intent i = new Intent(this, AddRestaurantActivity.class);
        startActivity(i);
    }

    public void addFriend() {
        Intent i = new Intent(this, AddFriendActivity.class);
        startActivity(i);
    }

}
