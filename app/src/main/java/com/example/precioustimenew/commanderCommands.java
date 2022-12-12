package com.example.precioustimenew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Button;
import android.widget.Spinner;


public class commanderCommands extends AppCompatActivity {
    public Button previous;
    public Spinner task;
    public Spinner soldier;
    public Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commander_commands);

        Button prev = (Button)findViewById(R.id.prevButton_CC);
        prev.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(commanderCommands.this, commanderMenu.class));

            }
        });

        previous = findViewById(R.id.prevButton_CC);
        task = findViewById(R.id.spinnerWhichSoldier_CC);
        soldier = findViewById(R.id.spinnerTask_CC);
        send = findViewById(R.id.SendTask_CC);

    }
}