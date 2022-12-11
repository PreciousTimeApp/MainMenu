package com.example.precioustimenew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public EditText personalNumber;
    public EditText id;
    public EditText password;
    public Button login;
    public Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {    //what happens once the first screen up
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        personalNumber = findViewById(R.id.personalNumberText);
        id = findViewById(R.id.idText);
        password = findViewById(R.id.passwordText);
        login = findViewById(R.id.loginButton);
        signup = findViewById(R.id.signUpButton);
    }

}