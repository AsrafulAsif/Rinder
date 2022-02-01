package com.example.rinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class JoinTutor extends AppCompatActivity {
    Button register;
    EditText tutorName,tutorEmail,tutorGender,universityname,subject;
   // FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_tutor);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.bc));
        }//status bar or the time bar at the top

        tutorName=(EditText)findViewById(R.id.userName);
        tutorEmail=(EditText)findViewById(R.id.useremailAdress);
        tutorGender=(EditText)findViewById(R.id.userPhno);
        universityname=(EditText)findViewById(R.id.userPass);
        subject=(EditText)findViewById(R.id.userAddress);
        register=(Button)findViewById(R.id.tutorRegister);
        final String Uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String tname = tutorName.getText().toString();
                final String temail = tutorEmail.getText().toString();
                final String tgender =tutorGender.getText().toString();
                final String tuni =universityname.getText().toString();
                final String tsub=subject.getText().toString();


                if(tname.isEmpty()){
                    tutorName.setError("Field must required!");
                    return;
                }
                if(temail.isEmpty()){
                    tutorEmail.setError("Field must required!");
                    return;
                }
                if(tuni.isEmpty()){
                    universityname.setError("Field must required!");
                    return;
                }
                if(tuni.length()<3){
                    universityname.setError("University full name required!");
                    return;
                }
                if(tgender.isEmpty()){
                    tutorGender.setError("Field must required!");
                    return;
                }
                if(tsub.isEmpty()){
                    subject.setError("Field must required!");
                    return;
                }
                final String Uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                        Tutor tutor = new Tutor(Uid,tname,temail,tgender,tuni,tsub);
                FirebaseDatabase.getInstance().getReference("tutors")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(tutor);

                        Toast.makeText(getApplicationContext(),"Registered as a Tutor Successfull !",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),Home_page.class));
                        finish();


////                startActivity(new Intent(getApplicationContext(),loginActivity.class));
//                startActivity(new Intent(getApplicationContext(),afterRegisterActivity.class));
//                finish();
            }
        });//button action end here


    }
}
