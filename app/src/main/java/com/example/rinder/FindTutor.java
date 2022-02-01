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
 * This class is for Find tutor.
 * @author Piyash
 * @version 1.0
 *
 */

public class FindTutor extends AppCompatActivity {
    private RecyclerView recyclerView;

    private DatabaseReference mDatabaseP;
    private FirebaseAuth mAuth;

    /**
     * This is an on create function for accessing xml file and Initializing Hooks.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_tutor);


        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.black2));
        }//status bar or the time bar at the top

        recyclerView=(RecyclerView)findViewById(R.id.tutorrecv);

        mAuth=FirebaseAuth.getInstance();

        mDatabaseP = FirebaseDatabase.getInstance().getReference().child("tutors");



        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    /**
     * This is a on start function for start the activity.
     */
    @Override
    protected void onStart() {
        super.onStart();

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("tutors")
                .limitToLast(50);
        FirebaseRecyclerOptions<model2> options =
                new FirebaseRecyclerOptions.Builder<model2>()
                        .setQuery(query, model2.class)
                        .build();
        //Recycler for viewing the information of posts from database
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<model2, FindTutor.PackageViewHolder>(options) {
            @Override
            public FindTutor.PackageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // Used same procedure as the posting options for pulling and setting information from database
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.tutorsinglerow, parent, false);

                return new FindTutor.PackageViewHolder(view);
            }

            /**
             * This is bind view holder for holding data.
             * @param holder
             * @param position
             * @param model2
             */

            @Override
            protected void onBindViewHolder(FindTutor.PackageViewHolder holder, int position, model2 model2) {

                String post_key = getRef(position).getKey();
                final String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                holder.setName(model2.getTutorname());
                holder.setGender(model2.getTutorgender());
                holder.setUniversity(model2.getTutoruniversity());
                holder.setEmail(model2.getTutoremail());
                holder.setSub(model2.getTsubject());







                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    /**
                     * On click function for access the recycler view.
                     * By pressing on click function, It will help to go to gmail.
                     */
                    public void onClick(View v) {
                        //Toast.makeText(PackageOptions.this,post_key,Toast.LENGTH_SHORT).show();

/*                        Intent SinglePackageIntent = new Intent(FindTutor.this,tutorprofileview.class);
                        SinglePackageIntent.putExtra("Package_id",post_key);
                        SinglePackageIntent.putExtra("name",model2.getTutorname());
                        SinglePackageIntent.putExtra("gender",model2.getTutorgender());
                        SinglePackageIntent.putExtra("email",model2.getTutoremail());
                        SinglePackageIntent.putExtra("University",model2.getTutoruniversity());
                        SinglePackageIntent.putExtra("subject",model2.getTsubject());
                        startActivity(SinglePackageIntent);*/
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("plain/text");
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { model2.getTutoremail() });
                        intent.putExtra(Intent.EXTRA_SUBJECT, "I need you as a tutor!");
                        intent.putExtra(Intent.EXTRA_TEXT, " ");
                        startActivity(Intent.createChooser(intent, ""));

                    }
                });

            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }

    /**
     * This static class help to fit data into holder.
     */
    public static class PackageViewHolder extends RecyclerView.ViewHolder{

        View mView;


        FirebaseAuth mAuth;



        public PackageViewHolder(View itemView) {
            super(itemView);

            mView=itemView;


            mAuth= FirebaseAuth.getInstance();




        }

        /**
         * Set tutor name in rec view.
         * @param Lname
         */

        public void setName(String Lname){

            TextView name = (TextView) mView.findViewById(R.id.listTname);
            name.setText(Lname);

        }

        /**
         * set tutor gender.
         * @param Ggender
         */

        public void setGender(String Ggender){

            TextView gender = (TextView) mView.findViewById(R.id.listTgender);
            gender.setText(Ggender);

        }

        /**
         * set tutor subject.
         * @param Ssub
         */


        public void setSub(String Ssub){

            TextView subject = (TextView) mView.findViewById(R.id.listTsub);
            subject.setText(Ssub);

        }

        /**
         * Set tutor university name.
         * @param Tuni
         */

        public void setUniversity(String Tuni){

            TextView uni = (TextView) mView.findViewById(R.id.listTuni);
            uni.setText(Tuni);

        }

        /**
         * set tutor email.
         * @param temail
         */
        public void setEmail(String temail)
        {
            TextView eemail=(TextView)mView.findViewById(R.id.listTemail);
            eemail.setText(temail);
        }


    }

    /**
     * on stop function to stop activity.
     */
    @Override
    protected void onStop() {
        super.onStop();

    }

}