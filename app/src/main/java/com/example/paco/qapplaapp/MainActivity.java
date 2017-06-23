package com.example.paco.qapplaapp;

import android.app.Dialog;
import android.app.DialogFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.example.paco.qapplaapp.Fragments.GameSearchFragment;
import com.example.paco.qapplaapp.Fragments.Games.PcFragment;
import com.example.paco.qapplaapp.Fragments.HomeFragment;
import com.example.paco.qapplaapp.Fragments.Matches.MatchesFragment;
import com.example.paco.qapplaapp.Fragments.ProfileFragment;
import com.example.paco.qapplaapp.Objects.FriendRequest;
import com.example.paco.qapplaapp.Objects.GamerUser;
import com.example.paco.qapplaapp.Objects.Match;
import com.example.paco.qapplaapp.Utils.BottomNavigationViewHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText tvBuscar;
    TextView tvResponse;
    Button btBuscar;

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mUsersDatabaseReference;
    DatabaseReference mUsuariosDatabaseReference;
    DatabaseReference mPruebas;


    FloatingSearchView floatingSearchView;


    String platform;
    String game;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mUsersDatabaseReference = mFirebaseDatabase.getReference().child("Users");
        mUsuariosDatabaseReference = mFirebaseDatabase.getReference().child("Planner");

        floatingSearchView = (FloatingSearchView) findViewById(R.id.searchViewQappla);




        Bundle extras = getIntent().getExtras();
        if (extras != null){
            platform = extras.getString("platform");
            game = extras.getString("game");
            setFragment(3);
            //Toast.makeText(this, platform, Toast.LENGTH_SHORT).show();
        }else {
            setFragment(0);
        }

    }

    static public class MyDialogFragment extends DialogFragment {


    }

    public void search(){


        mUsersDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot uniqueUserSnapshot : dataSnapshot.getChildren()) {
                    GamerUser user = uniqueUserSnapshot.getValue(GamerUser.class);
                    String userName = user.getUserName();
                    String city = user.getCity();
                    String query = tvBuscar.getText().toString();
                    String[] split = query.split(" ");


                    for (int i = 0;i < split.length;i++) {
                        if (split[i].length() < 3){
                            //Do nothing
                        } else {
                            if (userName.contains(split[i]) || city.contains(split[i])) {
                                Toast.makeText(MainActivity.this, user.getEmail(), Toast.LENGTH_LONG).show();
                                tvResponse.setText(user.getEmail() + ",");
                            }
                        }


                        //Toast.makeText(RegistryActivity.this, String.valueOf(uniqueUserSnapshot), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

 /*   public void search2(){

        mUsuariosDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot uniqueUserSnapshot : dataSnapshot.getChildren()) {
                    Planner user = uniqueUserSnapshot.getValue(Planner.class);
                    String descrip = user.getDescripcion();
                    String city = user.getCity();
                    String query = tvBuscar.getText().toString();
                    String[] split = query.split(" ");
                    String ciudad = "Guanajuato";

                    for (int i = 0;i < split.length;i++) {
                        if (split[i].length() < 3) {
                            //Do nothing
                        } else {
                            if (descrip.contains(split[i])&& city.contains(ciudad) ) {
                                Toast.makeText(MainActivity.this, user.getEmail(), Toast.LENGTH_LONG).show();
                                tvResponse.setText(user.getEmail() + ",");
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }*/

    public void sendFriendRequest() {
        String id = "JOqtomcfS0QDGhZPWtUL2Fe6PFu2";
        final String userName = "qapplaNoob";
        FriendRequest friendRequest = new FriendRequest(id,userName,"0");
        mUsersDatabaseReference.child("fXNKIYlcJ1WafMTBC0piEWLpcn33").child("friendRequest").push().setValue(friendRequest);
        mUsersDatabaseReference.child("fXNKIYlcJ1WafMTBC0piEWLpcn33").child("friendRequest").orderByChild("userName").equalTo(userName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot uniqueDataSnapshot : dataSnapshot.getChildren()) {
                    String key = uniqueDataSnapshot.getKey();
                    Toast.makeText(MainActivity.this, "KEY: " + key, Toast.LENGTH_SHORT).show();
                   /* String userName2 = (String) uniqueDataSnapshot.child("userName").getValue();
                    if (userName2.equals(userName)) {
                        String key = uniqueDataSnapshot.getKey();
                        Toast.makeText(MainActivity.this, "KEY: " + key, Toast.LENGTH_SHORT).show();
                        break;
                    }*/

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void acceptFriendRequest(){
        /** PRIMERO OBTENEMOS EL USERUID DEL USUARIO, OBTENEMOS EL UID DEL USUARIO QUE MANDO SOLICITUD Y EL KEY DE LA SOLICITUD RECIBIDA
         * PARA BORRAR Y ACEPTAR LA SOLICITUD  **/

        /** CREAMOS UN LIST PARA AGREGAR AL USUARIO ACEPTADO A NUESTRA LISTA DE AMIGOS **/
        List<String> adds = new ArrayList<String>();

        /** AGREGAMOS EL USERUID DE LA SOLICITUD DE AMISTAD A NUESTRA LISTA DE AMIGOS */
        adds.add("JOqtomcfS0QDGhZPWtUL2Fe6PFu2");

        /** USAMOS EL USERUID DEL USUARIO ACTIVO mUsersDatabaseReference.child(userUid) Y LE ASIGNAMOS 1 PARA ACEPTADO */
        mUsersDatabaseReference.child("fXNKIYlcJ1WafMTBC0piEWLpcn33").child("friendRequest").child("-Ki1axOawM5zEbt2_9iU").child("requestResponse").setValue("1");
        mUsersDatabaseReference.child("fXNKIYlcJ1WafMTBC0piEWLpcn33").child("friends").setValue(adds);
        //firebase.child(id).removeValue();
        /** EN EL OBJETO DE EL USUARIO DUEÑO mUsersDatabaseReference.child("USERUID") BORRAMOS EL CHILD DE LA SOLICITUD PARA NO DEJAR LA SOLICITUD **/
        mUsersDatabaseReference.child("fXNKIYlcJ1WafMTBC0piEWLpcn33").child("friendRequest").child("-Ki1axOawM5zEbt2_9iU").removeValue();

        /** CREAMOS UN LIST CON EL USERUID DEL USUARIO QUE ACEPTO LA SOLICITUD DE AMISTAS*/
        List<String> friends = new ArrayList<String>();
        friends.add("fXNKIYlcJ1WafMTBC0piEWLpcn33");

        /** CON EL USERUID DEL USUARIO QUE MANDO LA SOLICITUD, LE AGREGAMOS A SU LISTA DE AMIGOS EL USUARIO QUE ACEPTO SU SOLICITUD **/
        mUsersDatabaseReference.child("JOqtomcfS0QDGhZPWtUL2Fe6PFu2").child("friends").setValue(friends);
    }

    public void rejectFriendRequest(){
        /** PRIMERO OBTENEMOS EL USERUID DEL USUARIO, OBTENEMOS EL UID DEL USUARIO QUE MANDO SOLICITUD Y EL KEY DE LA SOLICITUD RECIBIDA
         * PARA BORRAR LA SOLICITUD  **/


        /** USAMOS EL USERUID DEL USUARIO ACTIVO mUsersDatabaseReference.child(userUid) Y LE ASIGNAMOS 2 PARA RECHAZADO */
        mUsersDatabaseReference.child("fXNKIYlcJ1WafMTBC0piEWLpcn33").child("friendRequest").child("-Ki1axOawM5zEbt2_9iU").child("requestResponse").setValue("2");

        /** EN EL OBJETO DE EL USUARIO DUEÑO mUsersDatabaseReference.child("USERUID") BORRAMOS EL CHILD DE LA SOLICITUD PARA NO DEJAR LA SOLICITUD **/
        mUsersDatabaseReference.child("fXNKIYlcJ1WafMTBC0piEWLpcn33").child("friendRequest").child("-Ki1axOawM5zEbt2_9iU").removeValue();
    }


    public void createMatch(){
        String date = "13/04/2017";
        String hour = "14:00";
        String adversary = "HaloMaste";
        Integer bet = 100;
        String game = "Halo";

        Match match = new Match(date,hour,adversary,bet,game);

        mUsersDatabaseReference.child("fXNKIYlcJ1WafMTBC0piEWLpcn33").child("match").push().setValue(match);

    }



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setFragment(0);
                    return true;
                case R.id.navigation_dashboard:
                    setFragment(2);
                    return true;
                case R.id.navigation_notifications:
                    setFragment(1);
                    return true;
                case R.id.navigation_profile:
                    setFragment(4);
                    return true;
            }
            return false;
        }

    };


    /**
     * Metodo por el medio del cual extraemos los fragmest de sus clases java
     */
    public void setFragment(int position){
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        switch (position){
            case 0:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                HomeFragment homeFragment = new HomeFragment();
                fragmentTransaction.replace(R.id.content,homeFragment);
                fragmentTransaction.commit();
                break;
            case 1:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                ProfileFragment profileFragment= new ProfileFragment();
                fragmentTransaction.replace(R.id.content, profileFragment);
                fragmentTransaction.commit();
                break;

            case 2:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                MatchesFragment matchesFragment = new MatchesFragment();
                fragmentTransaction.replace(R.id.content, matchesFragment);
                fragmentTransaction.commit();
                break;
            case 3:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                GameSearchFragment gameSearchFragment = new GameSearchFragment();
                fragmentTransaction.replace(R.id.content, gameSearchFragment);
                fragmentTransaction.commit();
                break;

            case 4:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                com.example.paco.qapplaapp.collapse.MainActivity collapse = new com.example.paco.qapplaapp.collapse.MainActivity();
                fragmentTransaction.replace(R.id.content, collapse);
                fragmentTransaction.commit();
                break;


        }
    }





}
