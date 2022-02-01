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

/**
 * This class holding selling information and sent them into firebase.
  * @author Hemi
  * @version 1.0
 */

public class Sellinformation extends AppCompatActivity {
    Button button;
    EditText editText1,editText2,editText3,editText4,editText5;
    //EditText tutorName,tutorEmail,tutorGender,universityname,subject;
    // FirebaseDatabase firebaseDatabase;

    /**
     * On create function for start Activitya and sets the hooks.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellinformation);


        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.bc));
        }//status bar or the time bar at the top

        editText1=(EditText)findViewById(R.id.bookName);
        editText2=(EditText)findViewById(R.id.bookMail);
        editText3=(EditText)findViewById(R.id.bookPrice);
        editText4=(EditText)findViewById(R.id.bookaddress);
        editText5=(EditText)findViewById(R.id.bookComment);
        button=(Button)findViewById(R.id.bookBtn);
        final String Uid = FirebaseAuth.getInstance().getCurrentUser().getUid();


        button.setOnClickListener(new View.OnClickListener() {
            /**
             * On click to start activity and store value to firebase.
             * @param v
             */
            @Override
            public void onClick(View v) {

                final String bname = editText1.getText().toString();
                final String bmail = editText2.getText().toString();
                final String bprice =editText3.getText().toString();
                final String baddress =editText4.getText().toString();
                final String bcomment= editText5.getText().toString();


                if(bname.isEmpty()){
                    editText1.setError("Book name must be needed!");
                    return;
                }
                if(bmail.isEmpty()){
                    editText2.setError("Plz,put your email!");
                    return;
                }
                if(baddress.isEmpty()){
                    editText4.setError("Address must required!");
                    return;
                }
                if(bprice.isEmpty()){
                    editText3.setError("Plz,set price!");
                    return;
                }
                if(bcomment.isEmpty()){
                    editText5.setError("Plz,leave a comment");
                    return;
                }
                final String Uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                Book book = new Book(Uid,bname,bmail,baddress,bprice,bcomment);
                FirebaseDatabase.getInstance().getReference("books")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(book);

                Toast.makeText(getApplicationContext(),"Sell Successfull, wait for email !",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Home_page.class));
                finish();


////                startActivity(new Intent(getApplicationContext(),loginActivity.class));
//                startActivity(new Intent(getApplicationContext(),afterRegisterActivity.class));
//                finish();
            }
        });//button action end here
    }
}