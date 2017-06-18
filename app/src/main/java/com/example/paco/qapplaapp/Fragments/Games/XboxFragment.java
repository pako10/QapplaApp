package com.example.paco.qapplaapp.Fragments.Games;



import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


import com.example.paco.qapplaapp.Fragments.GameSearchFragment;
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

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_xbox, container, false);

        rvOverwatch = (RelativeLayout) view.findViewById(R.id.rvOverwatchXbox);
        rvFifa = (RelativeLayout) view.findViewById(R.id.rvFifaXbox);
        rvGears = (RelativeLayout) view.findViewById(R.id.rvGearsXbox);
        rvhalo = (RelativeLayout) view.findViewById(R.id.rvStreetXbox);

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
                        .commit();*/

                FragmentManager fragmentManager;
                FragmentTransaction fragmentTransaction;
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                GameSearchFragment gameSearchFragment2 = new GameSearchFragment();
                gameSearchFragment2.setArguments(bundle);
                fragmentTransaction.replace(R.id.content, gameSearchFragment2);
                fragmentTransaction.commit();
            }
        });

        rvFifa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("platform", "xBox");
                bundle.putString("game", "Fifa");

               /* GameSearchFragment gameSearchFragment = new GameSearchFragment();
                gameSearchFragment.setArguments(bundle);
                getActivity().getFragmentManager().popBackStack();
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.content, gameSearchFragment,null)
                        .addToBackStack(null)
                        .commit();*/
                FragmentManager fragmentManager;
                FragmentTransaction fragmentTransaction;
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                GameSearchFragment gameSearchFragment2 = new GameSearchFragment();
                gameSearchFragment2.setArguments(bundle);
                fragmentTransaction.replace(R.id.content, gameSearchFragment2);
                fragmentTransaction.commit();
            }
        });

        rvGears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("platform", "xBox");
                bundle.putString("game", "Gears of War");

               /* GameSearchFragment gameSearchFragment = new GameSearchFragment();
                gameSearchFragment.setArguments(bundle);
                getActivity().getFragmentManager().popBackStack();
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.content, gameSearchFragment,null)
                        .addToBackStack(null)
                        .commit();*/

                FragmentManager fragmentManager;
                FragmentTransaction fragmentTransaction;
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                GameSearchFragment gameSearchFragment2 = new GameSearchFragment();
                gameSearchFragment2.setArguments(bundle);
                fragmentTransaction.replace(R.id.content, gameSearchFragment2);
                fragmentTransaction.commit();


            }
        });


        rvhalo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("platform", "xBox");
                bundle.putString("game", "Halo");

               /* GameSearchFragment gameSearchFragment = new GameSearchFragment();
                gameSearchFragment.setArguments(bundle);
                getActivity().getFragmentManager().popBackStack();
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.content, gameSearchFragment,null)
                        .addToBackStack(null)
                        .commit();*/
                FragmentManager fragmentManager;
                FragmentTransaction fragmentTransaction;
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                GameSearchFragment gameSearchFragment2 = new GameSearchFragment();
                gameSearchFragment2.setArguments(bundle);
                fragmentTransaction.replace(R.id.content, gameSearchFragment2);
                fragmentTransaction.commit();
            }
        });
    }
}
