package com.example.precioustime_one;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CommanderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commander);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

       getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
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
                long startMillis = System.currentTimeMillis();
                Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
                builder.appendPath("time");
                ContentUris.appendId(builder, startMillis);
                Intent intent = new Intent(Intent.ACTION_VIEW)
                        .setData(builder.build());
                startActivity(intent);
                return true;

            case  R.id.item_menu_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this,LoginActivity.class));
                finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    public void addSchedule(View view) {
        startActivity(new Intent( CommanderActivity.this,ScheduleActivity.class));
            }


    public void LogOut(View view)
    {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(CommanderActivity.this,LoginActivity.class));

        finish();
    }


    public void Tasks(View view) {
//        Toast.makeText(this, "TASKS",Toast.LENGTH_LONG).show();
        startActivity(new Intent( CommanderActivity.this,AddTaskActivity.class));

    }

    public void chat(View view) {
        startActivity(new Intent( CommanderActivity.this,ChatTeamActivity.class));

    }

    public void prev(View view) {
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(this, MainActivity.class));
        }
        else {
            // If sign in fails, display a message to the user.
            Toast.makeText(this, "LogOut ",Toast.LENGTH_LONG).show();

        }
    }
}