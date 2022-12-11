package com.example.precioustimenew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class signUpActivity extends AppCompatActivity {
    public Button previous;
    public Switch isCommander;
    public EditText personalNum;
    public EditText pwd;
    public Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.previous = findViewById(R.id.prevButton_signUp);
        this.isCommander =  findViewById(R.id.isCommander);
        this.personalNum =  findViewById(R.id.personalNumberSingup);
        this.pwd =  findViewById(R.id.passwordTextSingup);
        this.signUpButton = findViewById(R.id.signUpButton_signUp);
    }
}