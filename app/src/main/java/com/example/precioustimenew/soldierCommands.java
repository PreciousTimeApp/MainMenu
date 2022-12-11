package com.example.precioustimenew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;

public class soldierCommands extends AppCompatActivity {
    public Button send;
    public Spinner commands;
    public Button previous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soldier_commands);
        this.send = findViewById(R.id.send_SC);
        this.previous = findViewById(R.id.pervButton_SC);
        this.commands = findViewById(R.id.spinner_SC);

    }
}