package com.example.precioustimenew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class signUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        Button prev = (Button)findViewById(R.id.prevButton_signUp);
        prev.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(signUpActivity.this, MainActivity.class));

            }
        });

        Button submit = (Button)findViewById(R.id.signUpButton_signUp);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(signUpActivity.this, MainActivity.class));

            }
        });
    }
}