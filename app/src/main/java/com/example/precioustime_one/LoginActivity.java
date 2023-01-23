package com.example.precioustime_one;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    public  static EditText email ;
    public  static EditText password ;
    public static String CurrentUserEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        if(!(mAuth.getCurrentUser() ==null)) {
            CurrentUserEmail = mAuth.getCurrentUser().getEmail();
            Toast.makeText(LoginActivity.this, "Current User Email: "+CurrentUserEmail,Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null)
        {
            startActivity(new Intent(LoginActivity.this,MainActivity.class
            ));}

    }
    public void Register(View view)
    {
         email =  findViewById(R.id.editTextTextEmailAddress);
         password =findViewById(R.id.editTextTextPassword);

        if(!email.getText().toString().isEmpty()){

        mAuth.createUserWithEmailAndPassword(email.getText().toString()
                        ,password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            CurrentUserEmail = mAuth.getCurrentUser().getEmail();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Register failed.",Toast.LENGTH_LONG).show();

                        }
                    }
                });

        }
        else{
            Toast.makeText(this,"Please fill all the fields" ,Toast.LENGTH_LONG).show();
        }
    }

    public void Singin(View view) {

        email =  findViewById(R.id.editTextTextEmailAddress);
        password =findViewById(R.id.editTextTextPassword);


        mAuth.signInWithEmailAndPassword(email.getText().toString()
                        ,password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            CurrentUserEmail = mAuth.getCurrentUser().getEmail();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Sing in failed.",Toast.LENGTH_LONG).show();

                        }
                    }

                });
    }




    public void prev(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }
}