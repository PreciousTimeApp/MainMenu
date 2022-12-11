package com.example.precioustimenew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class soldierMenu extends AppCompatActivity {

    public Button previous;
    public Button command;
    public Button profile;
    public ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soldier_menu);
        this.profile = findViewById(R.id.profileButton_SM);
        this.previous = findViewById(R.id.prevButton_SM);
        this.command = findViewById(R.id.commandsButton_SM);
        this.image = findViewById(R.id.ProfilePic_SM);
    }
}