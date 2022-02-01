package com.example.rinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Pview extends AppCompatActivity {

    private String mpostkey=null;
    private String mname=null;
    private String mgender=null;
    private String msub=null;
    private String mmail=null;

    private TextView textView1,textView2,textView3,textView4;
    private Button button;
    private DatabaseReference mDatabaseUser;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private boolean isPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pview);


        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.bc));
        }//status bar or the time bar at the top

        //hooks
        textView1=(TextView)findViewById(R.id.namiset);
        textView2=(TextView)findViewById(R.id.genderset);
        textView3=(TextView)findViewById(R.id.subset);
        textView4=(TextView)findViewById(R.id.mailset);
        button=(Button)findViewById(R.id.connectbtn);

        //here is my profile uid for current user
       // final String Uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        mpostkey=getIntent().getExtras().getString("Package_id");
       // Toast.makeText(getApplicationContext(),mpostkey,Toast.LENGTH_SHORT).show();

        mname=getIntent().getExtras().getString("name");
       // Toast.makeText(getApplicationContext(),mname,Toast.LENGTH_SHORT).show();
        mgender=getIntent().getExtras().getString("gender");
        msub=getIntent().getExtras().getString("subject");
        mmail=getIntent().getExtras().getString("email");
        textView1.setText(mname);
        textView2.setText(mgender);
        textView3.setText(msub);
        textView4.setText(mmail);
        button.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
               /* final String rname=mname;
                final String rgender=mgender;
                final String rsub =msub;
                final String rmail=mmail;
                final String key=mpostkey;
                //Request request = new Request(key,rname,rgender,rsub,rmail);
                Users users = new Users(uid,name,email,gender,subject);
                FirebaseDatabase.getInstance().getReference("request")
                        .child(mpostkey).setValue(request);

                Toast.makeText(getApplicationContext(),"Request Sent!",Toast.LENGTH_SHORT).show();
                finish();

                startActivity(new Intent(getApplicationContext(),Findstudyp.class));*/


                mDatabaseUser= FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid());

                mDatabaseUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Users userProfileInfo = snapshot.getValue(Users.class);
                        FirebaseDatabase.getInstance().getReference("request")
                                .child(mpostkey).child(user.getUid()).setValue(userProfileInfo);
                        finish();

                        startActivity(new Intent(getApplicationContext(), Findstudyp.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Pview.this, Findstudyp.class);
        finish();
        startActivity(intent);
    }
}