package com.example.precioustimenew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class soldierCommands extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soldier_commands);

        Button prev = (Button)findViewById(R.id.pervButton_SC);
        prev.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(soldierCommands.this, soldierMenu.class));

            }
        });
    }
}