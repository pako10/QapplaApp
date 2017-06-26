package com.example.paco.qapplaapp.Registro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.paco.qapplaapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class GamerTagActivity extends AppCompatActivity {

    EditText etXboxTag,etPsnTag,etBattleTag,etLolTag;

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mUsersDatabaseReference;

    Button btEnlazar;

    String userUid;

    List<String> tags;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamer_tag);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**INSTANCIA DE FIREBASE**/
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mUsersDatabaseReference = mFirebaseDatabase.getReference().child("Users");


        etXboxTag = (EditText) findViewById(R.id.xboxTag);
        etPsnTag = (EditText) findViewById(R.id.psnTag);
        etBattleTag = (EditText) findViewById(R.id.battleTag);
        etLolTag = (EditText) findViewById(R.id.lolTag);

        tags = new ArrayList<String>();

        Intent intent = getIntent();
        userUid = intent.getStringExtra("userUid");

        btEnlazar = (Button) findViewById(R.id.btEnlazar);

        btEnlazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etXboxTag.getText().toString() != null){
                    tags.add(etXboxTag.getText().toString());
                }
                if (etPsnTag.getText().toString() != null){
                    tags.add(etPsnTag.getText().toString());
                }
                if (etBattleTag.getText().toString() != null){
                    tags.add(etBattleTag.getText().toString());
                }
                if (etLolTag.getText().toString() != null){
                    tags.add(etLolTag.getText().toString());
                }

                mUsersDatabaseReference.child(userUid).child("gamerTags").setValue(tags).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Intent i = new Intent(GamerTagActivity.this,WelcomeActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }
                });
            }
        });



    }

}
