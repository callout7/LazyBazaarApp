package com.example.surya.lazymarket;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private DatabaseReference mDatabase;
    private UserItemAdapter mAdapter;
    private List<Upload> mUploads;

    SharedPreferences prefs;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences = this.getSharedPreferences("com.example.surya.lazymarket",MODE_PRIVATE);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Activity");
        String headerText = sharedPreferences.getString("username","No name");
        String imggUrl = prefs.getString("imgUrl", null);

        TextView hText = findViewById(R.id.profileName);
        hText.setText(headerText);
        ImageView hImage = findViewById(R.id.profilePic);
        Picasso.get().load(imggUrl).into(hImage);

        mRecyclerView=findViewById(R.id.container);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUploads = new ArrayList<>();

        mDatabase = FirebaseDatabase.getInstance().getReference("uploads");
        mDatabase.orderByChild("emailId").equalTo(sharedPreferences.getString("email","No data")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Upload upload = postSnapshot.getValue(Upload.class);
                    mUploads.add(upload);
                }
                mAdapter = new UserItemAdapter(UserActivity.this, mUploads);

                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(UserActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}
