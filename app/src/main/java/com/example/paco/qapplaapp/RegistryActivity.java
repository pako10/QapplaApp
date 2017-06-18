package com.example.paco.qapplaapp;

import android.animation.Animator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.paco.qapplaapp.Objects.FriendRequest;
import com.example.paco.qapplaapp.Objects.GamerUser;
import com.example.paco.qapplaapp.Objects.Match;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.example.paco.qapplaapp.R.id.fab;

public class RegistryActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    EditText etEmail;
    EditText etPass;
    EditText etCity;
    EditText etUserName;
    EditText etConfirmPass;
    Button btLog;
    private FirebaseDatabase mFirebaseDatabase;
    AlertDialog alert = null;

    FloatingActionButton fab;
    boolean exist = false;


    private DatabaseReference mUsersDatabaseReference;
    private ChildEventListener mChildEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mUsersDatabaseReference = mFirebaseDatabase.getReference().child("Users");

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);
        etCity = (EditText) findViewById(R.id.etCity);
        etUserName = (EditText) findViewById(R.id.etUserName);
        etConfirmPass = (EditText) findViewById(R.id.etConfirmPass);
        Button btregistrar = (Button) findViewById(R.id.button);
        btLog = (Button) findViewById(R.id.btLog);

        btLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegistryActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        btregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // buscar3();
                //buscar();
               // checkUserName();
               // iterateSearch();

               // regiterUser();

                if(!isEmailValid(etEmail.getText().toString())){
                    String emailError = "Formato de correo inválido.";
                    alertErrorData(emailError);
                    return;
                }else {

                        verifyData();

                }
                //createUser();
            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegistryActivity.this, MainActivity.class);
                startActivity(i);
                finish();

             //  showCreateHeaderDialog();
              // iterateSearch();
            }
        });
    }

    public void verifyData(){
        String pass = etPass.getText().toString();
        String cPass = etConfirmPass.getText().toString();

        if (!pass.equals(cPass)){
            alertConfirmPass();

        }else {
            register();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void regiterUser(){
        final String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();

        mFirebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull final Task<AuthResult> task) {


                 if (!task.isSuccessful()){
                    Toast.makeText(RegistryActivity.this, "Authentication failed" + task.getException(), Toast.LENGTH_LONG).show();
                     return;
                }
                final String userUid = mFirebaseAuth.getCurrentUser().getUid();
                Toast.makeText(RegistryActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_LONG).show();

                     mUsersDatabaseReference.addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(DataSnapshot dataSnapshot) {
                             for (DataSnapshot uniqueUserSnapshot : dataSnapshot.getChildren()) {
                                 GamerUser user = uniqueUserSnapshot.getValue(GamerUser.class);
                                 String userName = user.getUserName();
                                 String etUser = etUserName.getText().toString();

                                 if (userName.equals(etUser)){
                                     Toast.makeText(RegistryActivity.this, "Ya existe  " + userName, Toast.LENGTH_SHORT).show();
                                     mFirebaseAuth.getCurrentUser().delete();
                                 }else if (!userName.equals(etUser)){
                                     Toast.makeText(RegistryActivity.this, "No existe ", Toast.LENGTH_SHORT).show();


                                     mFirebaseAuth.getCurrentUser().sendEmailVerification();
                                     createUser(userUid);
                                     mFirebaseAuth.signOut();
                                     break;
                                 }

                                 break;
                             }
                         }

                         @Override
                         public void onCancelled(DatabaseError databaseError) {

                         }
                     });



            }
        });
    }

    public void createUser(String userUid){
        String user = etUserName.getText().toString();
        String city = etCity.getText().toString();
        String email = etEmail.getText().toString();
        int credits = 0;
        String country = "Mexico";
        List<String> equip = Arrays.asList();
        String rank = "Bronce";
        int experience = 0;
        List<String> games = Arrays.asList();/**CREA UNA LISTA VACIA ESPERANDO LA LISTA DE JUEGOS**/
        List<String> tournaments = Arrays.asList();/**CREA UNA LISTA VACIA ESPERANDO LA LISTA DE ID DE TORNEOS**/
        List<String> friends = Arrays.asList();/**CREA UNA LISTA VACIA ESPERANDO LA LISTA DE ID DE CONTACTOS**/
        Match match = new Match(); /** CREAMOS UN OBJETO VACIO PARA CREAR LOS MAXIMOS 10 MATCH DE EL USUARIO **/
        boolean status = false;
        FriendRequest friendRequest = new FriendRequest();
        int wins = 0;
        int losses = 0;
        String bio = "";

        

        Toast.makeText(this, "Se creo un usuario", Toast.LENGTH_SHORT).show();
        //String userUid = "examples";

        GamerUser gamerUser = new GamerUser(userUid,user,email,credits,city,country,equip,rank,experience,games,tournaments,friends,match,status,friendRequest,wins,losses,bio);

       /* DatabaseReference mnuevo = FirebaseDatabase.getInstance().getReference().child("Mensaje");
        mnuevo.push().setValue("SI mete este");*/

        mUsersDatabaseReference.child(userUid).setValue(gamerUser);

    }

    public void readUsers(){


        final String userame = etUserName.getText().toString();
        Query query = mUsersDatabaseReference.orderByChild("userName").equalTo(userame);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    String userName = (String) userSnapshot.child("userName").getValue();

                    Toast.makeText(RegistryActivity.this, userName, Toast.LENGTH_SHORT).show();



                    String userNameUpper = userName.toLowerCase();
                    String etUserUpper = userame.toLowerCase();
                    if (etUserUpper.contains(userNameUpper)){
                       // Toast.makeText(RegistryActivity.this, userName, Toast.LENGTH_SHORT).show();
                        alertConfirmUserName();
                        break;

                    }else if (!etUserUpper.contains(userNameUpper)){
                        alertEmailAlreadyExist();
                        Toast.makeText(RegistryActivity.this, "No existe ", Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public boolean userExist(){

        final String userame = etUserName.getText().toString();
        Query query = mUsersDatabaseReference.orderByChild("userName").equalTo(userame);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    String userName = (String) userSnapshot.child("userName").getValue();

                    String userNameUpper = userName.toLowerCase();
                    String etUserUpper = userame.toLowerCase();

                    Toast.makeText(RegistryActivity.this, userNameUpper + " " + etUserUpper, Toast.LENGTH_SHORT).show();
                    if (etUserUpper.equals(userNameUpper)){
                       exist = true;

                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return exist;
    }

    public boolean userExist2(){

        exist = false;
        final String userame = etUserName.getText().toString();
        //Query query = mUsersDatabaseReference.orderByChild("userName").equalTo(userame);

        Toast.makeText(this, "entra aqui", Toast.LENGTH_SHORT).show();
        mUsersDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    String userName = (String) userSnapshot.child("userName").getValue();

                    String userNameUpper = userName.toLowerCase();
                    String etUserUpper = userame.toLowerCase();

                    Toast.makeText(RegistryActivity.this, userNameUpper + " " + etUserUpper, Toast.LENGTH_SHORT).show();
                    if (etUserUpper.equals(userNameUpper)){
                        Toast.makeText(RegistryActivity.this, "aca no ENTRA", Toast.LENGTH_SHORT).show();
                        exist = true;
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return exist;
    }

    public void userExist3(){

        exist = false;
        final String userame = etUserName.getText().toString();
        //Query query = mUsersDatabaseReference.orderByChild("userName").equalTo(userame);

        mUsersDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                search:
                {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        String userName = (String) userSnapshot.child("userName").getValue();

                        String userNameUpper = userName.toLowerCase();
                        String etUserUpper = userame.toLowerCase();

                        if (etUserUpper.equals(userNameUpper)) {
                            alertConfirmUserName();
                            exist = true;
                            break search;
                        }else {
                            exist = false;
                        }


                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



    public void leer(){
        final String email = etEmail.getText().toString();
        final String pass = etPass.getText().toString();


        final String etUser = etUserName.getText().toString();
        Query query = mUsersDatabaseReference.orderByChild("userName").equalTo(etUser);
        mUsersDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot uniqueUserSnapshot : dataSnapshot.getChildren()) {
                    String userName = (String) uniqueUserSnapshot.child("userName").getValue();
                   // GamerUser user = uniqueUserSnapshot.getValue(GamerUser.class);
                    //String userName = user.getUserName();
                    //String etUser = etUserName.getText().toString();

                    String userNameUpper = userName.toLowerCase();
                    String etUserUpper = etUser.toLowerCase();

                    if (userNameUpper.equals(etUserUpper)){
                        alertConfirmUserName();
                        break;

                    }else {
                        Toast.makeText(RegistryActivity.this, "No existe ", Toast.LENGTH_SHORT).show();

                        mFirebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.getException() instanceof FirebaseAuthUserCollisionException){
                                    /*try {
                                       throw task.getException();
                                    } catch(FirebaseAuthUserCollisionException e) {
                                        etEmail.setError("Correo ya registrado");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }*/
                                    alertEmailAlreadyExist();
                                    Toast.makeText(RegistryActivity.this, "Authentication failed" + task.getException(), Toast.LENGTH_LONG).show();
                                    return;
                                }
                                final String userUid = mFirebaseAuth.getCurrentUser().getUid();
                                Toast.makeText(RegistryActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_LONG).show();

                                mFirebaseAuth.getCurrentUser().sendEmailVerification();
                                createUser(userUid);
                                mFirebaseAuth.signOut();
                                alertSucessful();


                            }
                        });

                    }


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void buscar(){

        mUsersDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot uniqueUserSnapshot : dataSnapshot.getChildren()) {
                    GamerUser user = uniqueUserSnapshot.getValue(GamerUser.class);
                    String userName = user.getUserName();
                    String etUser = etUserName.getText().toString();

                    if (userName.contains(etUser)){
                        Toast.makeText(RegistryActivity.this, "Su Email es " + user.getEmail(), Toast.LENGTH_SHORT).show();
                    }

                    //Toast.makeText(RegistryActivity.this, String.valueOf(uniqueUserSnapshot), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void register(){

        final String email = etEmail.getText().toString();
        final String pass = etPass.getText().toString();

        mFirebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.getException() instanceof FirebaseAuthUserCollisionException){
                                    /*try {
                                       throw task.getException();
                                    } catch(FirebaseAuthUserCollisionException e) {
                                        etEmail.setError("Correo ya registrado");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }*/
                    alertEmailAlreadyExist();
                    Toast.makeText(RegistryActivity.this, "Authentication failed" + task.getException(), Toast.LENGTH_LONG).show();
                    return;
                }
                final String userUid = mFirebaseAuth.getCurrentUser().getUid();
                Toast.makeText(RegistryActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_LONG).show();

                mFirebaseAuth.getCurrentUser().sendEmailVerification();
                createUser(userUid);
                mFirebaseAuth.signOut();
                alertSucessful();


            }
        });
    }



    public void iterateSearch(){

        mUsersDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot uniqueUserSnapshot : dataSnapshot.getChildren()) {
                    GamerUser user = uniqueUserSnapshot.getValue(GamerUser.class);
                    String userName = user.getUserName();
                    String etUser = etUserName.getText().toString();

                    if (userName.equals(etUser)){
                        Toast.makeText(RegistryActivity.this, "Ya existe  " + userName, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    //Toast.makeText(RegistryActivity.this, String.valueOf(uniqueUserSnapshot), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    private void alertConfirmUserName(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("El nombre de usuario ingresado ya esta en uso")
                .setCancelable(false)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        alert = builder.create();
        alert.show();
    }

    private void alertConfirmPass(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Las contraseñas no coinciden")
                .setCancelable(false)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        etPass.setText("");
                        etConfirmPass.setText("");
                    }
                });

        alert = builder.create();
        alert.show();
    }

    private void alertEmailAlreadyExist(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("El email que ingreso ya se encuentra en uso")
                .setCancelable(false)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

        alert = builder.create();
        alert.show();
    }



    private void alertSucessful(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Usuario registrado exitosamente. Revise su correo")
                .setCancelable(false)
                .setPositiveButton("Entrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent k = new Intent(RegistryActivity.this, LoginActivity.class);
                        startActivity(k);
                    }
                }).setNegativeButton("Inicio", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent j = new Intent(RegistryActivity.this, MainActivity.class);
                startActivity(j);
            }
        });

        alert = builder.create();
        alert.show();
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

    /**ANIMATION CLOSE**7
     *
     *
     */





}
