package com.example.rinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class StudypatnersentMassage extends AppCompatActivity {
    private String mpostkey=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studypatnersent_massage);

        mpostkey=getIntent().getExtras().getString("Package_id");
        Toast.makeText(getApplicationContext(),mpostkey,Toast.LENGTH_SHORT).show();
    }
}