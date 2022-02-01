package com.example.rinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RequestViewProfile extends AppCompatActivity {


    private String mpostkey=null;
    private String mname=null;
    private String mgender=null;
    private String msub=null;
    private String mmail=null;
    private DatabaseReference mDatabaseUser;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    private TextView textView1,textView2,textView3,textView4;
    private Button button,btn;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_view_profile);

        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.bc));
        }//status bar or the time bar at the top
        textView1=(TextView)findViewById(R.id.namiset);
        textView2=(TextView)findViewById(R.id.genderset);
        textView3=(TextView)findViewById(R.id.subset);
        textView4=(TextView)findViewById(R.id.mailset);
        button=(Button)findViewById(R.id.acceptbtn);
        btn=(Button)findViewById(R.id.rejectbtn);
        final String Uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        mDatabase=FirebaseDatabase.getInstance().getReference().child("request").child(Uid);

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


        button.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                final String rname=mname;
                final String rgender=mgender;
                final String rsub =msub;
                final String key=mpostkey;
                final String rmail=mmail;
                Request request = new Request(key,rname,rgender,rsub,rmail);
                FirebaseDatabase.getInstance().getReference("mystudypatners")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(mpostkey).setValue(request);

              /*  mDatabaseUser= FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid());

                mDatabaseUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Users userProfileInfo = snapshot.getValue(Users.class);
                        FirebaseDatabase.getInstance().getReference("mystudypatners")
                                .child(user.getUid()).child(mpostkey).setValue(userProfileInfo);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/



               //removing request
                Toast.makeText(getApplicationContext(),"Accepted!",Toast.LENGTH_SHORT).show();
                mDatabase.child(key).removeValue();
                startActivity(new Intent(getApplicationContext(),Studypartner.class));
                finish();

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(RequestViewProfile.this);
                builder.setMessage("Are you sure you want to remove the post ? ").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String key=mpostkey;
                        mDatabase.child(key).removeValue();
                        Intent ToCurrentUserPost = new Intent(RequestViewProfile.this,RequestList.class);
                        finish();
                        startActivity(ToCurrentUserPost);


                    }
                }).setNegativeButton("Cancel",null);
                AlertDialog alert=builder.create();
                alert.show();

            }
        });



    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(RequestViewProfile.this,RequestList.class);
        finish();
        startActivity(intent);
    }

}