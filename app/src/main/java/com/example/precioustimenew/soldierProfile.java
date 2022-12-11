package com.example.precioustimenew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class soldierProfile extends AppCompatActivity {
    public Button previous;
    public ImageView image;
    public EditText fullName;
    public EditText mail;
    public EditText phone;
    public EditText rank;
    public EditText commander;
    public TextView personalNum;
    public Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soldier_profile);
        this.previous = findViewById(R.id.prevButton_SP);
        this.image = findViewById(R.id.imageView_SP);
        this.fullName = findViewById(R.id.editTextFullName_SP);
        this.mail = findViewById(R.id.editTextMail_SP);
        this.phone = findViewById(R.id.editTextPhoneNumber_SP);
        this.rank = findViewById(R.id.editTextRank_SP);
        this.commander = findViewById(R.id.editTextCommander_SP);
        this.personalNum =  findViewById(R.id.textViewPersonalNumber_SP);
        this.save = findViewById(R.id.saveButton_SP);
    }
}