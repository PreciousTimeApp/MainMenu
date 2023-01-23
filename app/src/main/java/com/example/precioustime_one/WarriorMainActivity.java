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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class WarriorMainActivity extends AppCompatActivity {
    private ValueEventListener valueEventListener = null;
    private DatabaseReference myRef = null;
    FirebaseAuth mAuth=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warrior_main);


        RecyclerView recyclerView =findViewById(R.id.RecyclerMyTeamSchedule);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        final ArrayList<Task> allTasks = new ArrayList<>();


        mAuth = FirebaseAuth.getInstance();
        String CurrentUserEmail;
        String team1=null;
        String name1=null;

        if(!(mAuth.getCurrentUser() ==null)) {
            CurrentUserEmail = mAuth.getCurrentUser().getEmail();
            name1 = CurrentUserEmail.split("\\.")[0];
            team1 = CurrentUserEmail.split("\\.")[1].split("@")[0];
        }

        TaskAdapter taskAdapter =new TaskAdapter(allTasks);
        recyclerView.setAdapter(taskAdapter);

        taskAdapter.setOnItemClickListener(new TaskAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                allTasks.get(position).setStatusTask("done");
                taskAdapter.notifyDataSetChanged();

            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("tasks");


        String finalTeam = team1;
        String finalName = name1;
        valueEventListener = new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                allTasks.clear();

                for (DataSnapshot taskSnapshot:snapshot.getChildren()) {

                    Task currentTask = taskSnapshot.getValue(Task.class);
                    if(currentTask.getTeamTask().equals(finalTeam)&&currentTask.getOwnerTaskName().equals(finalName)){
                        allTasks.add(currentTask);}

                }

                taskAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(WarriorMainActivity.this,"error reading from firebase",Toast.LENGTH_LONG).show();
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


    public void LogOut(View view)
    {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(WarriorMainActivity.this,LoginActivity.class));
        finish();
    }

//    public void chat(View view) {
//        startActivity(new Intent(WarriorMainActivity.this,ChatTeamActivity.class));
//
//    }

    public void prev(View view) {
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(WarriorMainActivity.this, MainActivity.class));
        }
     else {
        // If sign in fails, display a message to the user.
        Toast.makeText(this, "LogOut ",Toast.LENGTH_LONG).show();

    }
    }
    public void schedule(View view) {
        startActivity(new Intent(WarriorMainActivity.this,ScheduleWarriorActivity.class));
    }

//    public void UpdateTask(View view) {
//
//    }
}