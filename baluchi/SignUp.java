package com.example.baluchi;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    private EditText bName, bSurname, bNumber, bEmail, bPassword, bRePassword;

    private Button signUp;

    private TextView signedUp;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Get instance
        mAuth = FirebaseAuth.getInstance();

        bName = findViewById(R.id.name);

        bSurname = findViewById(R.id.surname);

        bNumber = findViewById(R.id.number);

        bEmail = findViewById(R.id.email);

        bPassword = findViewById(R.id.password);

        bRePassword = findViewById(R.id.rtPassword);

        signedUp = findViewById(R.id.signedUp);

        signUp = findViewById(R.id.signUp);

        signedUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent log;
                log = new Intent(SignUp.this, Login.class);
                startActivity(log);
                finish();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Define variables
                String name, surname, number, email, password, rePassword;

                //Assign values to variables
                name = bName.getText().toString();
                surname = bSurname.getText().toString();
                number = bNumber.getText().toString();
                email = bEmail.getText().toString();
                password = bPassword.getText().toString();
                rePassword = bRePassword.getText().toString();

                if(name.isEmpty()){
                    bName.setError("Name field is empty");
                }else if(surname.isEmpty()){
                    bSurname.setError("Surname field is empty");
                }else if(number.isEmpty()){
                    bNumber.setError("Number field is empty");
                }else if(email.isEmpty()){
                    bEmail.setError("Email field is empty");
                }
                /*email check
                else if(!(email.contains("@gmail.com")) || !(email.contains("@yahoo")) || !(email.contains("@microsoft.com"))){

                }*/
                else if(password.isEmpty()){
                    bPassword.setError("Password field is empty");
                }else if(rePassword.isEmpty()){
                    bRePassword.setError("Retype Password field is empty");
                }
                //password check
                else if(!(password.matches(rePassword))){
                    bPassword.setText("");
                    bRePassword.setText("");
                    bPassword.setError("Passwords don't match");
                }else {
                    //Create new users
                    mAuth.createUserWithEmailAndPassword(email, password);

                    //save user information in database
                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    //Add information to database
                    Map<String, Object> newUser = new HashMap<>();

                    //Add data to hashmap
                    newUser.put("name:", name);
                    newUser.put("surname:", surname);
                    newUser.put("email:", email);
                    newUser.put("number:", number);
                    newUser.put("password:", password);
                    newUser.put("points:", 0);

                    //adding to database
                    db.collection("users")
                            .add(newUser)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                    Intent home = new Intent(SignUp.this, HomePage.class);
                                    startActivity(home);
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error adding document", e);
                                    Intent userProfile = new Intent(SignUp.this, SignUp.class);
                                    startActivity(userProfile);
                                }
                            });
                }
            }
        });

    }

}