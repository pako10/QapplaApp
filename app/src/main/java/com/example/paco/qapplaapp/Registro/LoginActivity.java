package com.example.paco.qapplaapp.Registro;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paco.qapplaapp.MainActivity;
import com.example.paco.qapplaapp.Objects.GamerUser;
import com.example.paco.qapplaapp.R;
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

    AlertDialog alert = null;
    TextView tvRegistrate;
    Button btFacebook;


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
        tvRegistrate = (TextView) findViewById(R.id.tvRegistrate);

        connectivity = new Connectivity();
        if (!connectivity.isConnected(this)){
            btLogin.setEnabled(false);
        }

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isEmailValid(etEmail.getText().toString())){
                    String emailError = "Formato de correo inválido.";
                    alertErrorData(emailError);
                    return;
                }else {
                    loginFirebase();
                }
            }
        });

        tvRegistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,RegistryActivity.class);
                startActivity(i);
                finish();
            }
        });

        btFacebook = (Button) findViewById(R.id.button2);
        btFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
                finish();
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
                    alertErrorData("Contraseña o Correo incorrectos");
                }

                if (user != null) {

                    boolean verified = user.isEmailVerified();
                    Toast.makeText(LoginActivity.this, String.valueOf(verified), Toast.LENGTH_LONG).show();
                    if (!verified){

                        alertErrorData("Necesitas confirmar tu cuenta");
                        FirebaseAuth.getInstance().signOut();
                    }else if (verified){

                        String userUid = firebaseAuth.getCurrentUser().getUid();
                        String name = firebaseAuth.getCurrentUser().getDisplayName();
                        //iterateSearch();
                        auntenticar(userUid);
                        Intent i = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(i);
                        finish();
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

    public boolean isEmailValid(CharSequence email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void alertErrorData(String Error){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(Error)
                .setCancelable(false)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

        alert = builder.create();
        alert.show();
    }










}
