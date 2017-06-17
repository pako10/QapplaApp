package com.example.paco.qapplaapp.Fragments.Games;

import android.app.Fragment;
import android.os.Bundle;
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
    RelativeLayout rvStreet;
    RelativeLayout rvhalo;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_xbox, container, false);

        rvOverwatch = (RelativeLayout) view.findViewById(R.id.rvOverwatchXbox);
        rvFifa = (RelativeLayout) view.findViewById(R.id.rvFifaXbox);
        rvGears = (RelativeLayout) view.findViewById(R.id.rvGearsXbox);
        rvStreet = (RelativeLayout) view.findViewById(R.id.rvStreetXbox);
        rvhalo = (RelativeLayout) view.findViewById(R.id.rvHalo);


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

                GameSearchFragment gameSearchFragment = new GameSearchFragment();
                gameSearchFragment.setArguments(bundle);
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.content, gameSearchFragment,null)
                        .addToBackStack(null)
                        .commit();
            }
        });

        rvFifa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("platform", "xBox");
                bundle.putString("game", "Fifa");

                GameSearchFragment gameSearchFragment = new GameSearchFragment();
                gameSearchFragment.setArguments(bundle);
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.content, gameSearchFragment,null)
                        .addToBackStack(null)
                        .commit();
            }
        });

        rvGears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("platform", "xBox");
                bundle.putString("game", "Gears of War");

                GameSearchFragment gameSearchFragment = new GameSearchFragment();
                gameSearchFragment.setArguments(bundle);
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.content, gameSearchFragment,null)
                        .addToBackStack(null)
                        .commit();
            }
        });

        rvStreet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("platform", "xBox");
                bundle.putString("game", "Street Fighter");

                GameSearchFragment gameSearchFragment = new GameSearchFragment();
                gameSearchFragment.setArguments(bundle);
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.content, gameSearchFragment,null)
                        .addToBackStack(null)
                        .commit();
            }
        });

        rvhalo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("platform", "xBox");
                bundle.putString("game", "Halo");

                GameSearchFragment gameSearchFragment = new GameSearchFragment();
                gameSearchFragment.setArguments(bundle);
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.content, gameSearchFragment,null)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
