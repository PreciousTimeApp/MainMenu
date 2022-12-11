package com.example.precioustimenew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class commanderProfile extends AppCompatActivity {
    public Button previous;
    public ImageView image;
    public EditText fullName;
    public EditText mail;
    public EditText phone;
    public EditText rank;
    public TextView personalNum;
    public Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commander_profile);
        this.previous = findViewById(R.id.prevButton_CP);
        this.image = findViewById(R.id.imageView_CP);
        this.fullName = findViewById(R.id.editTextFullName_CP);
        this.mail = findViewById(R.id.editTextMail_CP);
        this.phone = findViewById(R.id.editTextPhone_CP);
        this.rank = findViewById(R.id.editTextRank_CP);
        this.personalNum =  findViewById(R.id.textViewPersonalNumber_CP);
        this.save = findViewById(R.id.saveButton_CP);
    }
}