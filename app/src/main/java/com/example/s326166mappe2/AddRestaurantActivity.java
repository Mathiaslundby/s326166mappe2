package com.example.s326166mappe2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddRestaurantActivity extends AppCompatActivity {

    Toolbar tb;
    DbHandler db;
    EditText name;
    EditText address;
    EditText tlf;
    EditText type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);

        db = new DbHandler(this);
        name = (EditText)findViewById(R.id.rest_name);
        address = (EditText)findViewById(R.id.rest_address);
        tlf = (EditText)findViewById(R.id.rest_tlf);
        type = (EditText)findViewById(R.id.rest_type);


        tb = (Toolbar)findViewById(R.id.rest_toolbar);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void addRestaurant(View v) {
        Restaurant r = new Restaurant();
        r.setName(name.getText().toString());
        r.setAddress(address.getText().toString());
        r.setTlf(tlf.getText().toString());
        r.setType(type.getText().toString());
        db.addRestaurant(r);
    }
}
