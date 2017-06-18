package com.example.paco.qapplaapp.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paco.qapplaapp.Objects.QapplaUser;
import com.example.paco.qapplaapp.Objects.RecyclerAdapter;
import com.example.paco.qapplaapp.Prueba.Helper;
import com.example.paco.qapplaapp.Prueba.Item;
import com.example.paco.qapplaapp.Prueba.ItemAdapter;
import com.example.paco.qapplaapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paco on 22/04/2017.
 */

public class ProfileFragment extends Fragment {

    private List<QapplaUser> stringArrayList;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mUsersDatabaseReference;
    TextView tvUsername;

    Context mContext;

    private ListView listView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle sevedInstanceState){
        final View view = inflater.inflate(R.layout.layout_profile, container, false);

       /* Toolbar toolbar = (Toolbar) view.findViewById(R.id.gmail_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle(null);
        setHasOptionsMenu(true);*/



        firebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mUsersDatabaseReference = mFirebaseDatabase.getReference().child("Users");
        tvUsername = (TextView) view.findViewById(R.id.tvUserName);


        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            String userUid = firebaseAuth.getCurrentUser().getUid();
            retrieveUserData(userUid);
        }

        mContext = getActivity();
        stringArrayList = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);



        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(mContext);
        recyclerView.setAdapter(adapter);

        stringArrayList.add(new QapplaUser("haloMaster"));
        stringArrayList.add(new QapplaUser("haloMaster"));
        stringArrayList.add(new QapplaUser("haloMaster"));
        stringArrayList.add(new QapplaUser("haloMaster"));
        stringArrayList.add(new QapplaUser("haloMaster"));
        stringArrayList.add(new QapplaUser("haloMaster"));
        stringArrayList.add(new QapplaUser("haloMaster"));
        stringArrayList.add(new QapplaUser("haloMaster"));
        stringArrayList.add(new QapplaUser("haloMaster"));
        stringArrayList.add(new QapplaUser("haloMaster"));
        adapter.setFriendsList(stringArrayList);


     /*   CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.gmail_coordinator);
        View bottomSheet = coordinatorLayout.findViewById(R.id.gmail_bottom_sheet);

        BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);

        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                switch (newState) {

                    case BottomSheetBehavior.STATE_DRAGGING:

                        break;

                    case BottomSheetBehavior.STATE_COLLAPSED:

                        break;

                    case BottomSheetBehavior.STATE_EXPANDED:

                        break;


                }

            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {

            }
        });*/


/*
        this.listView = (ListView) view.findViewById(R.id.listView);


        List items = new ArrayList();
        items.add(new Item(R.drawable.halo, "Following",
                "http://www.imdb.com/title/tt0154506/"));
        items.add(new Item(R.drawable.halo, "Memento",
                "http://www.imdb.com/title/tt0209144/"));
        items.add(new Item(R.drawable.halo, "Batman Begins",
                "http://www.imdb.com/title/tt0372784/"));
        items.add(new Item(R.drawable.halo, "The Prestige",
                "http://www.imdb.com/title/tt0482571/"));
        items.add(new Item(R.drawable.halo, "The Dark Knight",
                "http://www.imdb.com/title/tt0468569/"));
        items.add(new Item(R.drawable.halo, "Inception",
                "http://www.imdb.com/title/tt1375666/"));
        items.add(new Item(R.drawable.halo,
                "The Dark Knight Rises", "http://www.imdb.com/title/tt1345836/"));
        items.add(new Item(R.drawable.halo, "Following",
                "http://www.imdb.com/title/tt0154506/"));
        items.add(new Item(R.drawable.halo, "Memento",
                "http://www.imdb.com/title/tt0209144/"));
        items.add(new Item(R.drawable.halo, "Batman Begins",
                "http://www.imdb.com/title/tt0372784/"));
        items.add(new Item(R.drawable.halo, "The Prestige",
                "http://www.imdb.com/title/tt0482571/"));
        items.add(new Item(R.drawable.halo, "The Dark Knight",
                "http://www.imdb.com/title/tt0468569/"));
        items.add(new Item(R.drawable.halo, "Inception",
                "http://www.imdb.com/title/tt1375666/"));
        items.add(new Item(R.drawable.halo,
                "The Dark Knight Rises", "http://www.imdb.com/title/tt1345836/"));

        // Sets the data behind this ListView
        this.listView.setAdapter(new ItemAdapter(mContext, items));

        Helper.getListViewSize(listView);*/



        return view;
    }


    public void retrieveUserData(String Uid){

        mUsersDatabaseReference.child(Uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String userName = (String) dataSnapshot.child("userName").getValue();
                tvUsername.setText(userName);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_borrar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_rules) {
            Toast.makeText(mContext, "reglas", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_payment) {
            Toast.makeText(mContext, "pagore", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_condition_terms) {
            Toast.makeText(mContext, "condiciones", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_sign_out) {
            Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
