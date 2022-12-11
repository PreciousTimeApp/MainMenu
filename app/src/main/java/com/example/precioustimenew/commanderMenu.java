package com.example.precioustimenew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class commanderMenu extends AppCompatActivity {
    public Button profile;
    public Button previous;
    public Button command;
    public ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commander_menu);
        this.profile = findViewById(R.id.profileButton_CM);
        this.previous = findViewById(R.id.prevButton_CM);
        this.command = findViewById(R.id.commandsButton_CM);
        this.image = findViewById(R.id.ProfilePic_CM);

    }
}