package com.example.rinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    TextView textView1,textView2,textView3;
    private DatabaseReference mDatabaseUser;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.bc));
        }//status bar or the time bar at the top


        //hooks
        textView1=(TextView)findViewById(R.id.pname);
        textView2=(TextView)findViewById(R.id.pgender);
        textView3=(TextView)findViewById(R.id.psub);


        mDatabaseUser= FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid());

        mDatabaseUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Users userProfileInfo = snapshot.getValue(Users.class);
                textView1.setText(userProfileInfo.getName());
//                textView2.setText(userProfileInfo.getGender());
//                textView3.setText(userProfileInfo.getSubject());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}