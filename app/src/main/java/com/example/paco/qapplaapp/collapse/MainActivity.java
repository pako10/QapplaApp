package com.example.paco.qapplaapp.collapse;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.Toast;

import com.example.paco.qapplaapp.Fragments.Matches.DialogCreateMatches;
import com.example.paco.qapplaapp.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Fragment {

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;


    Toolbar toolbar;

    Context mContext;


    CollapsingToolbarLayout collapsingToolbarLayout;

    RecyclerView recyclerView;

    private RecyclerViewAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle sevedInstanceState){
        final View view = inflater.inflate(R.layout.activity_collapse, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        toolbar = (Toolbar) view.findViewById(R.id.toolbarProfile);


        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle(null);
        setHasOptionsMenu(true);



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

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                DialogCreateMatches frag = new DialogCreateMatches();
                frag.show(ft,"txn_tag");
            }
        });

        return view;

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
