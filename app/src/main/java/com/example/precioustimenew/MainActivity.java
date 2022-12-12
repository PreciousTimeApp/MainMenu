package com.example.precioustimenew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {    //what happens once the first screen up
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView personalNumber = (TextView) findViewById(R.id.personalNumberText);
        TextView id = (TextView) findViewById(R.id.idText);
        TextView password = (TextView) findViewById(R.id.passwordText);

        Button commander_login = (Button)findViewById(R.id.commander_login_Button);
        commander_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, commanderMenu.class));

            }
        });

        Button warrior_login = (Button)findViewById(R.id.warrior_login_Button);
        warrior_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, soldierMenu.class));

            }
        });

        Button signup = (Button)findViewById(R.id.signUpButton);
        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, signUpActivity.class));

            }
        });


    }

}