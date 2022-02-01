package com.example.rinder;

import androidx.annotation.NonNull;
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

/**
 * This Class use for log in to Rinder.
 * @author Chamok
 * @version 1.0
 */
public class Log_in extends AppCompatActivity {
    /**
     * Here we select all the xml components.
     */

    TextView register;
    EditText email,password;
    Button login;
    FirebaseAuth auth;

    /**
     * On create function for run set up hooks and Initializing firebase auth.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.bc));
        }//status bar or the time bar at the top


        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.Password);
        auth = FirebaseAuth.getInstance();
        /**
         * Button action start here.
         */
        login.setOnClickListener(new View.OnClickListener() {
            /**
             * On click function for login button.
             * @param v
             */
            @Override
            public void onClick(View v) {
                String Password = password.getText().toString();
                String Email = email.getText().toString();
                 /**
                   * If password filed is not fill with password.
                   */
                if(Password.isEmpty()){
                    password.setError("Filed required!");
                    return;
                }
                /**
                 * If email filed is empty.
                 */
                if(Email.isEmpty()){
                    email.setError("Filed required!");
                    return;
                }

                auth.signInWithEmailAndPassword(Email,Password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    /**
                     * On success function for login.
                     * @param authResult
                     */
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(getApplicationContext(),"Succffully login",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),Home_page.class));
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    /**
                     * On failure function for exception.
                     * @param e
                     */
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),""+e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });




            }
        });


        /**
         *This register in  click listener for going registration class.
         */
        register.setOnClickListener(new View.OnClickListener() {
            /**
             * This on click help to start activity to go registration page.
             * @param v
             */
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Log_in.this,Registration.class));
                finish();
            }
        });

    }
}