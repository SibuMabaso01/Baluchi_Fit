package com.example.baluchi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class Welcome_page extends AppCompatActivity {

    private Button signup, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        signup = findViewById(R.id.wSignUp);

        register = findViewById(R.id.wLogin);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signIn = new Intent(Welcome_page.this, SignUp.class);
                startActivity(signIn);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUp = new Intent(Welcome_page.this, Login.class);
                startActivity(signUp);
            }
        });
    }

}