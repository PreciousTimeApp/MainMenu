package com.example.precioustime_one;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    public static String CurrentUserEmail;
    public static int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();


        if(!(mAuth.getCurrentUser() ==null)) {
            CurrentUserEmail = mAuth.getCurrentUser().getEmail();
            Toast.makeText(MainActivity.this, "Current User Email: "+CurrentUserEmail,Toast.LENGTH_LONG).show();

        }
    }

    public void Commander(View view) {
        flag =1;
        startActivity(new Intent(this,CommanderActivity.class));
    }

    public void Warrior(View view) {
        flag =0;
        startActivity(new Intent(this,WarriorMainActivity.class));
    }
}