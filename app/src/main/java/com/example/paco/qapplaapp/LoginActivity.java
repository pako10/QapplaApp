package com.example.paco.qapplaapp;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.paco.qapplaapp.Objects.GamerUser;
import com.example.paco.qapplaapp.Utils.Connectivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.R.id.list;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mUsersDatabaseReference;
    EditText etEmail;
    EditText etPass;
    Button btLogin;
    Connectivity connectivity;



    private FloatingActionButton fab;
    private CoordinatorLayout layoutMain;
    private RelativeLayout layoutButtons;
    private RelativeLayout layoutContent;
    private boolean isOpen = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mUsersDatabaseReference = mFirebaseDatabase.getReference().child("Users");

        btLogin = (Button) findViewById(R.id.btLoguear);
        etEmail = (EditText) findViewById(R.id.etLogEmail);
        etPass = (EditText) findViewById(R.id.etLogPass);

        connectivity = new Connectivity();
        if (!connectivity.isConnected(this)){
            btLogin.setEnabled(false);
        }

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginFirebase();
            }
        });





    }

    public void loginFirebase(){
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (!task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Autentification failed:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                }

                if (user != null) {

                    boolean verified = user.isEmailVerified();
                    Toast.makeText(LoginActivity.this, String.valueOf(verified), Toast.LENGTH_LONG).show();
                    if (!verified){
                        Toast.makeText(LoginActivity.this, "Necesitas confirmar tu cuenta", Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().signOut();
                    }else if (verified){

                        String userUid = firebaseAuth.getCurrentUser().getUid();
                        String name = firebaseAuth.getCurrentUser().getDisplayName();
                        //iterateSearch();
                        auntenticar(userUid);
                        Intent i = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(i);
                        Toast.makeText(LoginActivity.this, userUid, Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }

    public void resetPassword(){
        String email = etEmail.getText().toString();
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Enviado" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this, "Failed" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getUserData(String Uid){

        mUsersDatabaseReference.child(Uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<GamerUser> yuser = new ArrayList<GamerUser>();
                for (DataSnapshot uniqueUserSnapshot : dataSnapshot.getChildren()){
                    yuser.add(uniqueUserSnapshot.getValue(GamerUser.class));

                   // GamerUser user = uniqueUserSnapshot.getValue(GamerUser.class);
                   // String userName = yuser.getUserName();
                   // Toast.makeText(LoginActivity.this, userName, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void auntenticar(String userUid){

        mUsersDatabaseReference.child(userUid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    String userName = (String) dataSnapshot.child("userName").getValue();
                    Toast.makeText(LoginActivity.this, userName, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }






}