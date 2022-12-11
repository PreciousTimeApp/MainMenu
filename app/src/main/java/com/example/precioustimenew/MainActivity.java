package com.example.precioustimenew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public EditText personalNumber;
    public EditText password;
    public Button login;
    public Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {    //what happens once the first screen up
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.personalNumber = findViewById(R.id.personalNumberText);
        this.password = findViewById(R.id.passwordTextSingup);
        this.login = findViewById(R.id.loginButton);
        this.signup = findViewById(R.id.signUpButton);
    }

}