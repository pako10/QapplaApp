package com.example.paco.qapplaapp.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paco.qapplaapp.Objects.QapplaUser;
import com.example.paco.qapplaapp.Objects.RecyclerAdapter;
import com.example.paco.qapplaapp.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by paco on 06/06/2017.
 */

public class GameSearchFragment extends Fragment {

    Context mContext;
    TextView tvGame;


    private List<QapplaUser> stringArrayList;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;

    Spinner spConsoles;


    String[] consoles = { "xbox", "ps4", "pc" };

    Button btUser,btEquip;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_game_search, container, false);

        mContext = getActivity();


        stringArrayList = new ArrayList<>();

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
        adapter.setFriendsList(stringArrayList);




        materialDesignFAM = (FloatingActionMenu) view.findViewById(R.id.material_design_android_floating_action_menu1);
        floatingActionButton1 = (FloatingActionButton) view.findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = (FloatingActionButton) view.findViewById(R.id.material_design_floating_action_menu_item2);
        floatingActionButton3 = (FloatingActionButton) view.findViewById(R.id.material_design_floating_action_menu_item3);
        btEquip = (Button) view.findViewById(R.id.btEquip);
        btUser = (Button) view.findViewById(R.id.btUser);
        spConsoles = (Spinner) view.findViewById(R.id.spConsole);

        ArrayAdapter adapter = new ArrayAdapter<String>(mContext,
                android.R.layout.simple_spinner_dropdown_item, consoles);
        spConsoles.setAdapter(adapter);


        String game = getArguments().getString("game");
        String platform = getArguments().getString("platform");

        Toast.makeText(mContext, game, Toast.LENGTH_SHORT).show();




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
            shapeDrawable.getPaint().setColor(Color.GREEN);
        } else if (background instanceof GradientDrawable) {
            // cast to 'GradientDrawable'
            GradientDrawable gradientDrawable = (GradientDrawable) background;
            //gradientDrawable.setColor(ContextCompat.getColor(mContext,R.color.colorPrimary));
            gradientDrawable.setColor(Color.GREEN);
        } else if (background instanceof ColorDrawable) {
            // alpha value may need to be set again after this call
            ColorDrawable colorDrawable = (ColorDrawable) background;
            colorDrawable.setColor(Color.GREEN);
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
            shapeDrawable.getPaint().setColor(Color.GREEN);
        } else if (background instanceof GradientDrawable) {
            // cast to 'GradientDrawable'
            GradientDrawable gradientDrawable = (GradientDrawable) background;
            //gradientDrawable.setColor(ContextCompat.getColor(mContext,R.color.colorPrimary));
            gradientDrawable.setColor(Color.GREEN);
        } else if (background instanceof ColorDrawable) {
            // alpha value may need to be set again after this call
            ColorDrawable colorDrawable = (ColorDrawable) background;
            colorDrawable.setColor(Color.GREEN);
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


}
