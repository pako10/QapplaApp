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
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paco.qapplaapp.Objects.GamerUser;
import com.example.paco.qapplaapp.Objects.QapplaUser;
import com.example.paco.qapplaapp.R;
import com.example.paco.qapplaapp.Objects.Qapla;
import com.example.paco.qapplaapp.collapse.RecyclerViewAdapter;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


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


    List<Qapla> qaplaList;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_game_search, container, false);

        mContext = getActivity();

        /** FIREBASE **/
        firebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mUsersDatabaseReference = mFirebaseDatabase.getReference().child("Users");



        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new RecyclerViewAdapter(mContext);
        recyclerView.setAdapter(adapter);
        qaplaList = new ArrayList<>();





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
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                DialogSearchFragment frag = new DialogSearchFragment();
                frag.show(ft,"txn_tag");
            }
        });




        Intent intent = getActivity().getIntent();

        if (getArguments() != null) {
            game = getArguments().getString("game");
            String gameId = getArguments().getString("code");

            Toast.makeText(mContext, gameId, Toast.LENGTH_SHORT).show();
            retrieveUserData(gameId);

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

            retrieveUserData(game);
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


    public void retrieveUserData(String gameId){


        mUsersDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Toast.makeText(mContext, dataSnapshot.toString(), Toast.LENGTH_SHORT).show();

                for (DataSnapshot uniqueUserSnapshot : dataSnapshot.getChildren()) {
                    GamerUser user = uniqueUserSnapshot.getValue(GamerUser.class);

                  // Toast.makeText(mContext, user.getUserName(), Toast.LENGTH_SHORT).show();

                    //String userName = (String) userSnapshot.child("userName").getValue();
                    List<String> games = user.getGames();

                    if (games != null) {

                        for (int i = 0; i < games.size();i++){

                            if (games.get(i).equals("xOver")){

                                String userWins = String.valueOf(user.getWins());
                                String userLoses = String.valueOf(user.getLosses());
                                String userLevel = String.valueOf(user.getLevel());
                                String userBio = String.valueOf(user.getBio());

                                qaplaList.add(new Qapla(user.getUserName(),userWins,userLoses,userLevel,userBio));

                                //Toast.makeText(mContext, user.getUserName(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        //Toast.makeText(mContext, games.toString(), Toast.LENGTH_SHORT).show();
                    }
                    adapter.setQaplaList(qaplaList);
                    adapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
