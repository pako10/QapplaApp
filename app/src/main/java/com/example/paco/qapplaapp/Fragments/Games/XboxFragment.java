package com.example.paco.qapplaapp.Fragments.Games;



import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.paco.qapplaapp.Fragments.GameSearchFragment;
import com.example.paco.qapplaapp.MainActivity;
import com.example.paco.qapplaapp.R;

/**
 * Created by paco on 24/05/2017.
 */

public class XboxFragment extends Fragment {

    RelativeLayout rvOverwatch;
    RelativeLayout rvFifa;
    RelativeLayout rvGears;
    RelativeLayout rvhalo;
    Context mContext;

    TextView tvOver,tvFifa,tvGears,tvHalo;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_xbox, container, false);

        // Font path
        String fontPath = "fonts/Lato-Bold.ttf";

        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);


        rvOverwatch = (RelativeLayout) view.findViewById(R.id.rvOverwatchXbox);
        rvFifa = (RelativeLayout) view.findViewById(R.id.rvFifaXbox);
        rvGears = (RelativeLayout) view.findViewById(R.id.rvGearsXbox);
        rvhalo = (RelativeLayout) view.findViewById(R.id.rvStreetXbox);
        tvOver = (TextView) view.findViewById(R.id.tvOverXbox);
        tvFifa = (TextView) view.findViewById(R.id.tvFifaXbox);
        tvGears = (TextView) view.findViewById(R.id.tvGearsxbox);
        tvHalo = (TextView) view.findViewById(R.id.tvHaloXbox);

        // Applying font
        tvGears.setTypeface(tf);
        tvHalo.setTypeface(tf);
        tvFifa.setTypeface(tf);
        tvOver.setTypeface(tf);

        mContext = getActivity();


        gameSearchClick();

        return  view;
    }

    public void gameSearchClick(){

        rvOverwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("platform", "xBox");
                bundle.putString("game", "Overwatch");

               /* GameSearchFragment gameSearchFragment = new GameSearchFragment();
                gameSearchFragment.setArguments(bundle);
                getActivity().getFragmentManager().popBackStack();
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.content, gameSearchFragment,null)
                        .addToBackStack(null)
                        .commit();

                FragmentManager fragmentManager;
                FragmentTransaction fragmentTransaction;
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                GameSearchFragment gameSearchFragment2 = new GameSearchFragment();
                gameSearchFragment2.setArguments(bundle);
                fragmentTransaction.replace(R.id.content, gameSearchFragment2);
                fragmentTransaction.commit();*/

                Intent i = new Intent(mContext, MainActivity.class);
                i.putExtra("code","xOver");
                i.putExtra("game", "overwatch");
                startActivity(i);
                getActivity().finish();
            }
        });

        rvFifa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(mContext, MainActivity.class);
                i.putExtra("code","xFifa");
                i.putExtra("game", "fifa");
                startActivity(i);
                getActivity().finish();
            }
        });

        rvGears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(mContext, MainActivity.class);
                i.putExtra("code","xGears");
                i.putExtra("game","gow");
                startActivity(i);
                getActivity().finish();


            }
        });


        rvhalo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(mContext, MainActivity.class);
                i.putExtra("code","xHalo");
                i.putExtra("game", "halo");
                startActivity(i);
                getActivity().finish();
            }
        });
    }
}
