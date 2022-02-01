package com.example.rinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class xyz extends AppCompatActivity {

    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xyz);

        editText=findViewById(R.id.name);
        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase db= FirebaseDatabase.getInstance();
                DatabaseReference root = db.getReference();
                root.setValue(editText.getText().toString());
                editText.setText("");
                Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show();

            }
        });

    }
}