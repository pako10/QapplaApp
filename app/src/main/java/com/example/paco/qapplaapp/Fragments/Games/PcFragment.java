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

import com.example.paco.qapplaapp.Fragments.DialogSearchFragment;
import com.example.paco.qapplaapp.Fragments.GameSearchFragment;
import com.example.paco.qapplaapp.MainActivity;
import com.example.paco.qapplaapp.R;

/**
 * Created by paco on 24/05/2017.
 */

public class PcFragment extends Fragment {

    RelativeLayout rvHearthstone;
    RelativeLayout rvLol;
    RelativeLayout rvOverwatch;
    RelativeLayout rvGears;
    RelativeLayout rvHalo;

    TextView tvGears,tvHalo,tvLol,tvHearth,tvOver;
    Context mContext;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_pc, container, false);

        // Font path
        String fontPath = "fonts/Lato-Bold.ttf";

        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);
        // Applying font

        mContext = getContext();

        tvGears = (TextView) view.findViewById(R.id.tvGearsPc);
        tvHalo = (TextView) view.findViewById(R.id.tvHaloPc);
        tvLol = (TextView) view.findViewById(R.id.tvLolPc);
        tvHearth = (TextView) view.findViewById(R.id.tvHearthPc);
        tvOver = (TextView) view.findViewById(R.id.tvOverPc);
        rvGears = (RelativeLayout) view.findViewById(R.id.rvCsPc);
        rvHearthstone = (RelativeLayout) view.findViewById(R.id.rvHearthStonePc);
        rvLol = (RelativeLayout) view.findViewById(R.id.rvOwerwatchPc);
        rvHalo = (RelativeLayout) view.findViewById(R.id.rvFifaPc);
        rvOverwatch = (RelativeLayout) view.findViewById(R.id.rvLolPc);

        tvGears.setTypeface(tf);
        tvHalo.setTypeface(tf);
        tvLol.setTypeface(tf);
        tvHearth.setTypeface(tf);
        tvOver.setTypeface(tf);

        gameSearchClick();
        return view;
    }

    public void gameSearchClick(){

        rvGears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("code", "pGears");
                bundle.putString("game", "gow");

                /*GameSearchFragment gameSearchFragment = new GameSearchFragment();
                gameSearchFragment.setArguments(bundle);
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.content, gameSearchFragment,null)
                        .addToBackStack(null)
                        .commit();*/

                Intent i = new Intent(mContext, MainActivity.class);
                i.putExtra("code","pGears");
                i.putExtra("game","gow");
                startActivity(i);
                getActivity().finish();

                /*FragmentManager fragmentManager;
                FragmentTransaction fragmentTransaction;
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                GameSearchFragment gameSearchFragment2 = new GameSearchFragment();
                gameSearchFragment2.setArguments(bundle);
                fragmentTransaction.replace(R.id.content, gameSearchFragment2);
                fragmentTransaction.commit();*/
            }
        });

        rvHearthstone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(mContext, MainActivity.class);
                i.putExtra("code","pHearth");
                i.putExtra("game", "hearth");
                startActivity(i);
                getActivity().finish();


            }
        });


        rvOverwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("code", "pOver");
                bundle.putString("game", "overwatch");

                Intent i = new Intent(mContext, MainActivity.class);
                i.putExtra("platform","pc");
                i.putExtra("game", "overwatch");
                startActivity(i);
                getActivity().finish();
            }
        });

        rvHalo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(mContext, MainActivity.class);
                i.putExtra("code","pHalo");
                i.putExtra("game", "halo");
                startActivity(i);
                getActivity().finish();

            }
        });

        rvLol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(mContext, MainActivity.class);
                i.putExtra("code","pcLol");
                i.putExtra("game", "lol");
                startActivity(i);
                getActivity().finish();

            }
        });
    }

    
}
