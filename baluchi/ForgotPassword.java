package com.example.baluchi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private Button forgot;

    private EditText fEmail;
    
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        auth = FirebaseAuth.getInstance();

        fEmail = findViewById(R.id.forgotEmail);

        forgot = findViewById(R.id.forgot);

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email;

                email = fEmail.getText().toString();

                //send pin to email
                auth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //Display and navigate to
                        Toast.makeText(ForgotPassword.this, "Please check your email for password reset.", Toast.LENGTH_SHORT).show();
                        //Move to sign in
                        Intent signin = new Intent(ForgotPassword.this, Login.class);
                        startActivity(signin);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Display message and clear edittext field.
                        Toast.makeText(ForgotPassword.this, "Password reset unsuccessful", Toast.LENGTH_SHORT).show();
                        fEmail.setText("");
                    }
                });
            }
        });
    }
}