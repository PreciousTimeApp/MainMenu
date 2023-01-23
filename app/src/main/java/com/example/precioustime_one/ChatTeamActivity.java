package com.example.precioustime_one;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
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


import java.sql.Date;
import java.util.ArrayList;
import java.util.Objects;

public class ChatTeamActivity extends AppCompatActivity {
    private ValueEventListener valueEventListener = null;
    private DatabaseReference myRef = null;
    private  int i =0;
    private EditText messageChat ;
    FirebaseAuth mAuth=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_team);

        messageChat=findViewById(R.id.textView_message);

        final ArrayList<Chat> chats = new ArrayList<>();


        RecyclerView recyclerView = findViewById(R.id.recyclerView_chatTeam);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ChatAdapter chatAdapter = new ChatAdapter(chats);
        recyclerView.setAdapter(chatAdapter);


        mAuth = FirebaseAuth.getInstance();
        String CurrentUserEmail;
        String team1 = null;
        if(!(mAuth.getCurrentUser() ==null)) {
            CurrentUserEmail = mAuth.getCurrentUser().getEmail();
             team1 = CurrentUserEmail.split("\\.")[1].split("@")[0];

        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("chats");

        String finalTeam = team1;
        valueEventListener = new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chats.clear();

                for (DataSnapshot chatSnapshot:snapshot.getChildren())
                {
                    Chat currentChat = chatSnapshot.getValue(Chat.class);
                    if(Objects.equals(currentChat.getTeam(), finalTeam)){
                    chats.add(currentChat);}

                }
//
                chatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ChatTeamActivity.this,"error reading from firebase",Toast.LENGTH_LONG).show();
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

    public void send(View view)
    {
        mAuth = FirebaseAuth.getInstance();
       String CurrentUserEmail;

        if(!(mAuth.getCurrentUser() ==null)) {
            CurrentUserEmail = mAuth.getCurrentUser().getEmail();
            String name1 = CurrentUserEmail.split("\\.")[0];
            String team1 = CurrentUserEmail.split("\\.")[1].split("@")[0];

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        myRef = database.getReference("chats");
        if( !messageChat.getText().toString().isEmpty()){
            Chat massage = new Chat( name1,  messageChat.getText().toString() , team1);
            DatabaseReference myRef = database.getReference("chats").push();
            myRef.setValue(massage);
            messageChat.getText().clear();
        }
        else {
            Toast.makeText(ChatTeamActivity.this,"Please fill all the fields" ,Toast.LENGTH_LONG).show();
        }
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

    public void prev(View view)
    {
        if(MainActivity.flag==0) {
            startActivity(new Intent(this, WarriorMainActivity.class));
        }
        else{
            startActivity(new Intent(this, CommanderActivity.class));
        }

    }
}


