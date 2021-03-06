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

public class Ps4Fragment extends Fragment {

    RelativeLayout rvOverwatch;
    RelativeLayout rvFifa;

    TextView tvFifa,tvOver;

    Context mContext;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_ps4, container, false);

        mContext = getActivity();

        // Font path
        String fontPath = "fonts/Lato-Bold.ttf";

        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);

        rvOverwatch = (RelativeLayout) view.findViewById(R.id.rvStreetPs4);
        rvFifa = (RelativeLayout) view.findViewById(R.id.rvFifaPs4);

        tvFifa = (TextView) view.findViewById(R.id.tvFifaPs4);
        tvOver = (TextView) view.findViewById(R.id.tvOverPs4);

        // Applying font
        tvFifa.setTypeface(tf);
        tvOver.setTypeface(tf);

        gameSearchClick();

        return view;
    }

    public void gameSearchClick(){


        rvOverwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("platform", "ps4");
                bundle.putString("game", "Overwatch");

                /*GameSearchFragment gameSearchFragment = new GameSearchFragment();
                gameSearchFragment.setArguments(bundle);
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
                i.putExtra("code","psOver");
                i.putExtra("game", "overwatch");
                startActivity(i);
                getActivity().finish();
            }
        });

        rvFifa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(mContext, MainActivity.class);
                i.putExtra("code","psFifa");
                i.putExtra("game", "fifa");
                startActivity(i);
                getActivity().finish();
            }
        });
    }
}
