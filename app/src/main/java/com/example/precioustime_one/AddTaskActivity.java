package com.example.precioustime_one;

import static android.content.ContentValues.TAG;
import static com.google.firebase.auth.FirebaseAuth.*;

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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.UserInfo;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddTaskActivity extends AppCompatActivity {
    private EditText nameWarrior ;
    private EditText taskToWarrior ;
    private ValueEventListener valueEventListener = null;
    private DatabaseReference myRef = null;
    private final ArrayList<Task> allTasks = new ArrayList<>();
    FirebaseAuth mAuth=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        nameWarrior=findViewById(R.id.textViewNameWarrior);
        taskToWarrior=findViewById(R.id.textViewTask);



        RecyclerView recyclerView =findViewById(R.id.recycleAllTasks);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        TaskAdapter taskAdapter =new TaskAdapter(allTasks);
        recyclerView.setAdapter(taskAdapter);


        mAuth = FirebaseAuth.getInstance();
        String CurrentUserEmail;
        String team1=null;
        if(!(mAuth.getCurrentUser() ==null)) {
            CurrentUserEmail = mAuth.getCurrentUser().getEmail();
             team1 = CurrentUserEmail.split("\\.")[1].split("@")[0];}

        FirebaseDatabase database = FirebaseDatabase.getInstance();
         myRef = database.getReference("tasks");
        String finalTeam = team1;

        valueEventListener = new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                allTasks.clear();
                EditText item;
                for (DataSnapshot taskSnapshot:snapshot.getChildren())
                {
                    Task currentTask = taskSnapshot.getValue(Task.class);

                    if(currentTask.getTeamTask().equals(finalTeam)){
                    allTasks.add(currentTask);}

                }
//
                taskAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AddTaskActivity.this,"error reading from firebase",Toast.LENGTH_LONG).show();
            }
        };
        myRef.addValueEventListener(valueEventListener);
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


    @Override
    protected void onDestroy() {
        if(myRef!=null&& valueEventListener!=null) {
            myRef.removeEventListener(valueEventListener);
            super.onDestroy();
        }
    }


    public void AddTasK(View view)
    {

        mAuth = FirebaseAuth.getInstance();
        String CurrentUserEmail;

        if(!(mAuth.getCurrentUser() ==null)) {
            CurrentUserEmail = mAuth.getCurrentUser().getEmail();
            String name1 = CurrentUserEmail.split("\\.")[0];
            String team1 = CurrentUserEmail.split("\\.")[1].split("@")[0];

        if(!taskToWarrior.getText().toString().isEmpty())
        {
            Toast.makeText(this, "task add",Toast.LENGTH_LONG).show();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Task task =new Task(taskToWarrior.getText().toString(),nameWarrior.getText().toString(),"operative",team1);
        DatabaseReference myRef = database.getReference("tasks").push();
        myRef.setValue(task);
            taskToWarrior.getText().clear();
            nameWarrior.getText().clear();
        }
        else
        {
        Toast.makeText(this,"Please fill all the fields" ,Toast.LENGTH_LONG).show();
         }}

    }


    public void prev(View view) {
        startActivity(new Intent(AddTaskActivity.this,CommanderActivity.class));
    }
}