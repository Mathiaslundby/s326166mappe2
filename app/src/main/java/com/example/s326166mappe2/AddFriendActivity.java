package com.example.s326166mappe2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddFriendActivity extends AppCompatActivity {

    DbHandler db;
    Toolbar tb;
    EditText name;
    EditText tlf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        name = (EditText)findViewById(R.id.friend_name);
        tlf = (EditText)findViewById(R.id.friend_tlf);
        db = new DbHandler(this);

        tb = (Toolbar)findViewById(R.id.friend_toolbar);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void addFriend(View v) {
        Friend f = new Friend(name.getText().toString(), tlf.getText().toString());
        db.addFriend(f);
    }
}
