package com.example.precioustimenew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class commanderProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commander_profile);

        Button prev = (Button)findViewById(R.id.prevButton_CP);
        prev.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(commanderProfile.this, commanderMenu.class));

            }
        });

        Button save = (Button)findViewById(R.id.saveButton_CP);
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(commanderProfile.this, commanderMenu.class));

            }
        });
    }
}