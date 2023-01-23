package com.example.precioustime_one;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ScheduleWarriorActivity extends AppCompatActivity {

        private ValueEventListener valueEventListener = null;
        private DatabaseReference myRef = null;
        FirebaseAuth mAuth=null;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_schedule_warrior);


            final ArrayList<Schedule> allSchedule = new ArrayList<>();

            RecyclerView recyclerView =findViewById(R.id.recyclerSchedule1);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            ScheduleAdapter scheduleAdapter =new ScheduleAdapter(allSchedule);
            recyclerView.setAdapter(scheduleAdapter);

            mAuth = FirebaseAuth.getInstance();
            String CurrentUserEmail;
            String team1 = null;
            String name1=null;
            if (!(mAuth.getCurrentUser() == null)) {
                CurrentUserEmail = mAuth.getCurrentUser().getEmail();
                name1 = CurrentUserEmail.split("\\.")[0];
                team1 = CurrentUserEmail.split("\\.")[1].split("@")[0];
            }


            FirebaseDatabase database = FirebaseDatabase.getInstance();
            myRef = database.getReference("scheduler");
            String finalTeam = team1;
            valueEventListener =new ValueEventListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    allSchedule.clear();

                    for (DataSnapshot scheduleSnapshot:snapshot.getChildren())
                    {
                        Schedule currentSchedule = scheduleSnapshot.getValue(Schedule.class);
                        if(currentSchedule.getTeam().equals(finalTeam)){
                        allSchedule.add(currentSchedule);}

                    }
//
                    scheduleAdapter.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(ScheduleWarriorActivity.this,"error reading from firebase",Toast.LENGTH_LONG).show();
                }
            };
            myRef.addValueEventListener(valueEventListener);

        }


        @Override
        protected void onDestroy() {
            if(myRef!=null&& valueEventListener!=null) {
                myRef.removeEventListener(valueEventListener);
                super.onDestroy();
            }
        }


    public void perv(View view) {
        startActivity(new Intent(this,WarriorMainActivity.class));

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
}