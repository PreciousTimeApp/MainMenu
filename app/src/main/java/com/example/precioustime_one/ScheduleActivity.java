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
import java.util.Calendar;

public class ScheduleActivity extends AppCompatActivity {
    private EditText date ;
    private EditText time ;
    private EditText event ;
    private ValueEventListener valueEventListener = null;
    private DatabaseReference myRef = null;
    private final ArrayList<Schedule> allSchedule = new ArrayList<>();
    FirebaseAuth mAuth=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        date = findViewById(R.id.editTextDate);
        time = findViewById(R.id.editTextTime);
        event = findViewById(R.id.editTextEvent);


        RecyclerView recyclerView = findViewById(R.id.recyclerSchedule);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(allSchedule);
        recyclerView.setAdapter(scheduleAdapter);


        mAuth = FirebaseAuth.getInstance();
        String CurrentUserEmail;
        String team1 = null;
        if (!(mAuth.getCurrentUser() == null)) {
            CurrentUserEmail = mAuth.getCurrentUser().getEmail();
            team1 = CurrentUserEmail.split("\\.")[1].split("@")[0];
        }


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("scheduler");
        String finalTeam = team1;


        scheduleAdapter.setOnItemClickListener(new ScheduleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
              myRef.removeValue();
                allSchedule.remove(position);
                scheduleAdapter.notifyDataSetChanged();

            }
        });

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
                Toast.makeText(ScheduleActivity.this,"error reading from firebase",Toast.LENGTH_LONG).show();
            }
        };
        myRef.addValueEventListener(valueEventListener);
//
    }


    @Override
    protected void onDestroy() {
        if(myRef!=null&& valueEventListener!=null) {
            myRef.removeEventListener(valueEventListener);
            super.onDestroy();
        }
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

    public void AddEvent(View view)
    {

        mAuth = FirebaseAuth.getInstance();
        String CurrentUserEmail = null;
        String team1 = null;
        String name1= null;

        if(!(mAuth.getCurrentUser() ==null)) {
            CurrentUserEmail = mAuth.getCurrentUser().getEmail();
            team1 = CurrentUserEmail.split("\\.")[1].split("@")[0];
            name1 = CurrentUserEmail.split("\\.")[0];
        }

        if(!event.getText().toString().isEmpty() && !time.getText().toString().isEmpty()){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Schedule schedule  =new Schedule( event.getText().toString(),time.getText().toString(),date.getText().toString() ,team1);
        DatabaseReference myRef = database.getReference("scheduler").push();
        myRef.setValue(schedule);
            Toast.makeText(this, "event add",Toast.LENGTH_LONG).show();

//



        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setData(CalendarContract.Events.CONTENT_URI);
        intent.putExtra(CalendarContract.Events.TITLE,team1);
        intent.putExtra(CalendarContract.Events.DESCRIPTION,event.getText().toString() );
//        intent.putExtra(CalendarContract.Events.DTSTART,time.getText().toString() );
//        intent.putExtra(CalendarContract.Events.RDATE,time.getText().toString() );
            intent.putExtra(CalendarContract.Events.ALL_DAY,true );
            intent.putExtra(Intent.EXTRA_EMAIL,"Commander.4905@gmail.com" );

            event.getText().clear();
            time.getText().clear();
            date.getText().clear();
////
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
        else {
            Toast.makeText(ScheduleActivity.this,"There is no app that can support this action" ,Toast.LENGTH_LONG).show();
        }
        }
        else{
            Toast.makeText(ScheduleActivity.this,"Please fill all the fields" ,Toast.LENGTH_LONG).show();

        }
    }


    public void prev(View view) {
        startActivity(new Intent(this,CommanderActivity.class));
    }
}