package com.example.rinder;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {





        Button registerRegister;
        EditText emailRegister,passwordRegister,nameRegister,gender,subject;
        FirebaseAuth auth;
        TextView textView;



        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_registration);

            getSupportActionBar().hide();
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.bc)); //status bar or the time bar at the top
            }
            registerRegister = findViewById(R.id.tutorRegister);
            nameRegister = findViewById(R.id.userName);
            emailRegister = findViewById(R.id.useremailAdress);
            passwordRegister = findViewById(R.id.userPass);
            gender=findViewById(R.id.userPhno);
            subject=findViewById(R.id.userAddress);
            textView=findViewById(R.id.login);

            auth = FirebaseAuth.getInstance();


            registerRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final String name = nameRegister.getText().toString();
                    final String email = emailRegister.getText().toString();
                    String password = passwordRegister.getText().toString();
                    final String Gender =gender.getText().toString();
                    final String sub=subject.getText().toString();


                    if(name.isEmpty()){
                        nameRegister.setError("Field must required!");
                        return;
                    }
                    if(email.isEmpty()){
                        emailRegister.setError("Field must required!");
                        return;
                    }
                    if(password.isEmpty()){
                        passwordRegister.setError("Field must required!");
                        return;
                    }
                    if(password.length()<6){
                        passwordRegister.setError("6 digits password required!");
                        return;
                    }
                    if(Gender.isEmpty()){
                        emailRegister.setError("Field must required!");
                        return;
                    }
                    if(sub.isEmpty()){
                        emailRegister.setError("Field must required!");
                        return;
                    }

                    auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {

                            final String Uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            Users users = new Users(Uid,name, email,Gender,sub);
                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(users);





                            Toast.makeText(getApplicationContext(),"Registered Successfull !",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Log_in.class));
                            finish();





                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),""+e.getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    });







////                startActivity(new Intent(getApplicationContext(),loginActivity.class));
//                startActivity(new Intent(getApplicationContext(),afterRegisterActivity.class));
//                finish();
                }
            });//button action end here


            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Registration.this,Log_in.class));
                    finish();
                }
            });

        }
    }