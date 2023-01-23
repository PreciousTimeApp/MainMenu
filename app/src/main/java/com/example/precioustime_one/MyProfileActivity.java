package com.example.precioustime_one;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MyProfileActivity extends AppCompatActivity {
    private EditText name;
    private EditText email;
    private EditText team;


    private DatabaseReference myRef = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
         FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
         String CurrentUserEmail;

        name = findViewById(R.id.textView_fullName);
        email = findViewById(R.id.editTextTextEmailAddress);
        team = findViewById(R.id.textViewTeamNum);

        if (!(mAuth.getCurrentUser() == null)) {
            CurrentUserEmail = mAuth.getCurrentUser().getEmail();
            String name1 = CurrentUserEmail.split("\\.")[0];
            String team1 = CurrentUserEmail.split("\\.")[1].split("@")[0];

            email.setText("email: " + CurrentUserEmail);
            name.setText("name: " + name1);
            team.setText("team: " + team1);
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case  R.id.item_menu_my_chat:
                startActivity(new Intent(this,ChatTeamActivity.class));
                return true;
            case  R.id.item_menu_my_profile:
                startActivity(new Intent(this,MyProfileActivity.class));
                return true;
            case  R.id.item_menu_app:

                Intent  intent = new Intent(Intent.ACTION_VIEW, Uri.parse( "com.google.android.calendar"));
                startActivity(intent);

                if(intent != null){
                    startActivity(intent);
                }
                else {
                    Toast.makeText(this, "There is no package",Toast.LENGTH_LONG).show();
                }
                return true;

            case  R.id.item_menu_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this,LoginActivity.class));
                finish();
                startActivity(new Intent(this,LoginActivity.class));

                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}