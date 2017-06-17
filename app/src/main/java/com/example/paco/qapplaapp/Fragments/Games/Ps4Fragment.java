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

public class Ps4Fragment extends Fragment {

    RelativeLayout rvOverwatch;
    RelativeLayout rvFifa;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_ps4, container, false);


        rvOverwatch = (RelativeLayout) view.findViewById(R.id.rvStreetPs4);
        rvFifa = (RelativeLayout) view.findViewById(R.id.rvFifaPs4);

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
                bundle.putString("platform", "ps4");
                bundle.putString("game", "Fifa");

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
