package com.example.precioustimenew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class soldierMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soldier_menu);

        Button prev = (Button)findViewById(R.id.prevButton_SM);
        prev.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(soldierMenu.this, MainActivity.class));

            }
        });

        Button commands = (Button)findViewById(R.id.commandsButton_SM);
        commands.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(soldierMenu.this, soldierCommands.class));

            }
        });

        Button profile = (Button)findViewById(R.id.profileButton_SM);
        profile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(soldierMenu.this, soldierProfile.class));

            }
        });
    }
}