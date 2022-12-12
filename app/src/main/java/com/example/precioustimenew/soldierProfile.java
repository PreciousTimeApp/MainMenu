package com.example.precioustimenew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class soldierProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soldier_profile);

        Button prev = (Button)findViewById(R.id.prevButton_SP);
        prev.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(soldierProfile.this, soldierMenu.class));

            }
        });

        Button save = (Button)findViewById(R.id.saveButton_SP);
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(soldierProfile.this, soldierMenu.class));

            }
        });
    }
}