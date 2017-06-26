package com.example.paco.qapplaapp.Fragments;



import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paco.qapplaapp.Objects.QapplaUser;
import com.example.paco.qapplaapp.Objects.RecyclerAdapter;
import com.example.paco.qapplaapp.R;
import com.example.paco.qapplaapp.collapse.Radio;
import com.example.paco.qapplaapp.collapse.RecyclerViewAdapter;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.android.gms.R.id.toolbar;


/**
 * Created by paco on 06/06/2017.
 */

public class GameSearchFragment extends Fragment {

    Context mContext;
    TextView tvGame;


    private List<QapplaUser> stringArrayList;
    //private RecyclerView recyclerView;
    //private RecyclerAdapter adapter;

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;

    Spinner spConsoles;


    String[] consoles = { "xbox", "ps4", "pc" };

    Button btUser,btEquip;

    ImageView imgGame;
    ImageView imgSearch;
    String game;


    RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    /** FIREBASE **/
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mUsersDatabaseReference;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_game_search, container, false);

        mContext = getActivity();

        /** FIREBASE **/
        firebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mUsersDatabaseReference = mFirebaseDatabase.getReference().child("Users");


       /* stringArrayList = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);

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
        adapter.setFriendsList(stringArrayList);*/

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new RecyclerViewAdapter(mContext);
        recyclerView.setAdapter(adapter);
        List<Radio> radioList = new ArrayList<>();
        radioList.add(new Radio("HaloMaster", R.drawable.halo, "222"));
        radioList.add(new Radio("HaloMaster", R.drawable.halo, "222"));
        radioList.add(new Radio("HaloMaster", R.drawable.halo, "222"));
        radioList.add(new Radio("HaloMaster", R.drawable.halo, "222"));
        radioList.add(new Radio("HaloMaster", R.drawable.halo, "222"));
        radioList.add(new Radio("HaloMaster", R.drawable.halo, "222"));
        radioList.add(new Radio("HaloMaster", R.drawable.halo, "222"));
        radioList.add(new Radio("HaloMaster", R.drawable.halo, "222"));
        radioList.add(new Radio("HaloMaster", R.drawable.halo, "222"));
        radioList.add(new Radio("HaloMaster", R.drawable.halo, "222"));
        radioList.add(new Radio("HaloMaster", R.drawable.halo, "222"));
        radioList.add(new Radio("HaloMaster", R.drawable.halo, "222"));

        adapter.setRadioList(radioList);




        materialDesignFAM = (FloatingActionMenu) view.findViewById(R.id.material_design_android_floating_action_menu1);
        floatingActionButton1 = (FloatingActionButton) view.findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = (FloatingActionButton) view.findViewById(R.id.material_design_floating_action_menu_item2);
        floatingActionButton3 = (FloatingActionButton) view.findViewById(R.id.material_design_floating_action_menu_item3);
        btEquip = (Button) view.findViewById(R.id.btEquip);
        btUser = (Button) view.findViewById(R.id.btUser);
        imgGame = (ImageView) view.findViewById(R.id.imgGame);
        imgSearch = (ImageView) view.findViewById(R.id.imgGameSearch);

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Busqueda", Toast.LENGTH_SHORT).show();
            }
        });




        Intent intent = getActivity().getIntent();


        if (getArguments() != null) {
            game = getArguments().getString("game");
            String platform = getArguments().getString("platform");

            Toast.makeText(mContext, game, Toast.LENGTH_SHORT).show();

            if (game.equals("fifa")) {
                imgGame.setImageResource(R.drawable.fifalog);
            }
            if (game.equals("overwatch")) {
                imgGame.setImageResource(R.drawable.overlog);
            }
            if (game.equals("gow")) {
                imgGame.setImageResource(R.drawable.gowlog);
            }
            if (game.equals("halo")) {
                imgGame.setImageResource(R.drawable.halolog);
            }

            if (game.equals("lol")) {
                imgGame.setImageResource(R.drawable.lolog);
            }
            if (game.equals("hearth")) {
                imgGame.setImageResource(R.drawable.hearthlog);
            }
        }else if (intent.getStringExtra("game") != null ){
            game = intent.getStringExtra("game");
            if (game.equals("fifa")) {
                imgGame.setImageResource(R.drawable.fifalog);
            }
            if (game.equals("overwatch")) {
                imgGame.setImageResource(R.drawable.overlog);
            }
            if (game.equals("gow")) {
                imgGame.setImageResource(R.drawable.gowlog);
            }
            if (game.equals("halo")) {
                imgGame.setImageResource(R.drawable.halolog);
            }

            if (game.equals("lol")) {
                imgGame.setImageResource(R.drawable.lolog);
            }
            if (game.equals("hearth")) {
                imgGame.setImageResource(R.drawable.hearthlog);
            }
        }




        btEquip.setOnClickListener(new View.OnClickListener() {
             @Override
              public void onClick(View view) {
                 onEquipSelected();
                 Toast.makeText(mContext, "Equipos", Toast.LENGTH_SHORT).show();
            }
        });

        btUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUserSelected();
                Toast.makeText(mContext, "Usuarios", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    public void onUserSelected(){

        Drawable background = btUser.getBackground();
        if (background instanceof ShapeDrawable) {
            // cast to 'ShapeDrawable'
            ShapeDrawable shapeDrawable = (ShapeDrawable) background;
            shapeDrawable.getPaint().setColor(getResources().getColor(R.color.colorAccent));
        } else if (background instanceof GradientDrawable) {
            // cast to 'GradientDrawable'
            GradientDrawable gradientDrawable = (GradientDrawable) background;
            //gradientDrawable.setColor(ContextCompat.getColor(mContext,R.color.colorPrimary));
            gradientDrawable.setColor(getResources().getColor(R.color.colorAccent));
        } else if (background instanceof ColorDrawable) {
            // alpha value may need to be set again after this call
            ColorDrawable colorDrawable = (ColorDrawable) background;
            colorDrawable.setColor(getResources().getColor(R.color.colorAccent));
        }


        Drawable background2 = btEquip.getBackground();
        if (background2 instanceof ShapeDrawable) {
            ((ShapeDrawable)background2).getPaint().setColor(Color.WHITE);
        } else if (background2 instanceof GradientDrawable) {
            ((GradientDrawable)background2).setColor(Color.WHITE);
        } else if (background2 instanceof ColorDrawable) {
            ((ColorDrawable)background2).setColor(Color.WHITE);
        }



        btUser.setSelected(true);
        btEquip.setSelected(false);
    }

    public void onEquipSelected(){

        Drawable background = btEquip.getBackground();
        if (background instanceof ShapeDrawable) {
            // cast to 'ShapeDrawable'
            ShapeDrawable shapeDrawable = (ShapeDrawable) background;
            shapeDrawable.getPaint().setColor(getResources().getColor(R.color.colorAccent));
        } else if (background instanceof GradientDrawable) {
            // cast to 'GradientDrawable'
            GradientDrawable gradientDrawable = (GradientDrawable) background;
            //gradientDrawable.setColor(ContextCompat.getColor(mContext,R.color.colorPrimary));
            gradientDrawable.setColor(getResources().getColor(R.color.colorAccent));
        } else if (background instanceof ColorDrawable) {
            // alpha value may need to be set again after this call
            ColorDrawable colorDrawable = (ColorDrawable) background;
            colorDrawable.setColor(getResources().getColor(R.color.colorAccent));
        }


        Drawable background2 = btUser.getBackground();
        if (background2 instanceof ShapeDrawable) {
            ((ShapeDrawable)background2).getPaint().setColor(Color.WHITE);
        } else if (background2 instanceof GradientDrawable) {
            ((GradientDrawable)background2).setColor(Color.WHITE);
        } else if (background2 instanceof ColorDrawable) {
            ((ColorDrawable)background2).setColor(Color.WHITE);
        }


        btEquip.setSelected(true);
        btUser.setSelected(false);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_game, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    public void retrieveUserData(String Uid){

        mUsersDatabaseReference.child(Uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String userName = (String) dataSnapshot.child("userName").getValue();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
