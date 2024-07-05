package com.example.baluchi;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private Button login, sign;

    private EditText username, password;

    private TextView forgotPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        username = findViewById(R.id.username);

        password = findViewById(R.id.password);

        login = findViewById(R.id.login);

        sign = findViewById(R.id.lSignup);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(Login.this, SignUp.class);
                startActivity(signup);
                finish();
            }
        });

        forgotPassword = findViewById(R.id.forgot);

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotPassword = new Intent(Login.this, ForgotPassword.class);
                startActivity(forgotPassword);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //variables
                String email, pass;

                email = username.getText().toString();
                pass = password.getText().toString();
                //check user
                mAuth.signInWithEmailAndPassword(email, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Log.d(TAG, "User logged in");

                        //Go to home page
                        Intent home = new Intent(Login.this, HomePage.class);
                        startActivity(home);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "User logged in");
                        Intent register = new Intent(Login.this, SignUp.class);
                        startActivity(register);
                    }
                });

            }
        });
    }
}