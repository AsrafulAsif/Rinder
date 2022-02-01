package com.example.rinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * It's the activity where all the user find Study partner
 * @author Asif 1813425642
 * @version 1.0
 */

public class Findstudyp extends AppCompatActivity {

    /**
     * Here we select Recyclerview for showing database details in card format
     * and also Initializing firebase real time database and firebase auth
     */

    private  RecyclerView recyclerView;

    private DatabaseReference mDatabaseP;
    private FirebaseAuth mAuth;

    /**
     * It is a onCreate function for Initializing Hooks
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findstudyp);
        /**
         * Here we hide the Action bar and color the status bar.
         */
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.black2));
        }//status bar or the time bar at the top
        /**
         * Hooks for recyclerview
         */

        recyclerView=(RecyclerView)findViewById(R.id.recv);

        mAuth=FirebaseAuth.getInstance();
        mDatabaseP = FirebaseDatabase.getInstance().getReference().child("users");
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }


    /**
     * Creating onStart function to read firebase real time database.
     */

    @Override
    protected void onStart() {
        super.onStart();
        final String Uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("users")
                .limitToLast(50);
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(query, model.class)
                        .build();
        //Recycler for viewing the information of posts from database
      //  FirebaseDatabase.getInstance().getReference().child("users").child(Uid);

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<model, Findstudyp.PackageViewHolder>(options) {
            /**
             * Creating View holder to fit data into single row xml .
             * @param parent
             * @param viewType
             * @return
             */
            @Override
            public Findstudyp.PackageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // Used same procedure as the posting options for pulling and setting information from database
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.singlerow, parent, false);
                //FirebaseDatabase.getInstance().getReference().child("users").child(Uid);
                return new Findstudyp.PackageViewHolder(view);
            }

            /**
             * Creating Adapter to hold data.
             * @param holder
             * @param position
             * @param model
             */
            @Override
            protected void onBindViewHolder(Findstudyp.PackageViewHolder holder, int position, model model) {

                String post_key = getRef(position).getKey();
                final String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                holder.setName(model.getName());
                holder.setGender(model.getGender());
                holder.setSub(model.getSubject());
                holder.setEmail(model.getEmail());

/*                if(model.getUid() ==userid ){

                    Uid.setVisibility(View.GONE);
                }*/





                holder.mView.setOnClickListener(new View.OnClickListener() {
                    /**
                     * It is the onClick function to sent data to Profile view
                     * @param v
                     */
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(PackageOptions.this,post_key,Toast.LENGTH_SHORT).show();

                        Intent SinglePackageIntent = new Intent(Findstudyp.this,Pview.class);
                        SinglePackageIntent.putExtra("Package_id",post_key);
                        SinglePackageIntent.putExtra("name",model.getName());
                        SinglePackageIntent.putExtra("gender",model.getGender());
                        SinglePackageIntent.putExtra("email",model.getEmail());
                        SinglePackageIntent.putExtra("subject",model.getSubject());
                        finish();
                        startActivity(SinglePackageIntent);


                    }
                });

            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }

    /**
     * Package holder to fit data into recycler view
     */
    public static class PackageViewHolder extends RecyclerView.ViewHolder{

        View mView;


        FirebaseAuth mAuth;


        /**
         * Holder function to fit data as a item
         * @param itemView
         */
        public PackageViewHolder(View itemView) {
            super(itemView);

            mView=itemView;

            mAuth= FirebaseAuth.getInstance();
        }


        /**
         * Set Name into Text view in single row using model java.
         * @param Lname
         */
        public void setName(String Lname){

            TextView name = (TextView) mView.findViewById(R.id.listname);
            name.setText(Lname);

        }

        /**
         * Set Gender into Text view in single row using model java.
         * @param Ggender
         */
        public void setGender(String Ggender){

            TextView gender = (TextView) mView.findViewById(R.id.listgender);
            gender.setText(Ggender);

        }

        /**
         * Set Subject into Text view in single row using model java.
         * @param Ssub
         */
        public void setSub(String Ssub){

            TextView subject = (TextView) mView.findViewById(R.id.listsub);
            subject.setText(Ssub);

        }

        /**
         * Set email into Text view in single row using model java.
         * @param Semail
         */
        public void setEmail(String Semail){

            TextView email = (TextView) mView.findViewById(R.id.listmail);
            email.setText(Semail);

        }


    }

    /**
     * onStop function use for to stop read data from firebase.
     */
    @Override
    protected void onStop() {
        super.onStop();

    }



}