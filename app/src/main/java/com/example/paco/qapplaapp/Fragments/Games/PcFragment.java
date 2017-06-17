package com.example.paco.qapplaapp.Fragments.Games;

import android.app.Fragment;
import android.content.Context;
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

public class PcFragment extends Fragment {

    RelativeLayout rvHearthstone;
    RelativeLayout rvLol;
    RelativeLayout rvOverwatch;
    RelativeLayout rvCs;
    RelativeLayout rvFifa;

    Context mContext;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_pc, container, false);



        rvCs = (RelativeLayout) view.findViewById(R.id.rvCsPc);
        rvHearthstone = (RelativeLayout) view.findViewById(R.id.rvHearthStonePc);
        rvOverwatch = (RelativeLayout) view.findViewById(R.id.rvOwerwatchPc);
        rvFifa = (RelativeLayout) view.findViewById(R.id.rvFifaPc);
        rvLol = (RelativeLayout) view.findViewById(R.id.rvLolPc);

        gameSearchClick();
        return view;
    }

    public void gameSearchClick(){
        rvCs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("platform", "pc");
                bundle.putString("game", "CounterStrike");

                GameSearchFragment gameSearchFragment = new GameSearchFragment();
                gameSearchFragment.setArguments(bundle);
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.content, gameSearchFragment,null)
                        .addToBackStack(null)
                        .commit();
            }
        });

        rvHearthstone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("platform", "pc");
                bundle.putString("game", "Hearthstone");

                GameSearchFragment gameSearchFragment = new GameSearchFragment();
                gameSearchFragment.setArguments(bundle);
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.content, gameSearchFragment,null)
                        .addToBackStack(null)
                        .commit();
            }
        });


        rvOverwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("platform", "pc");
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
                bundle.putString("platform", "pc");
                bundle.putString("game", "Fifa");

                GameSearchFragment gameSearchFragment = new GameSearchFragment();
                gameSearchFragment.setArguments(bundle);
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.content, gameSearchFragment,null)
                        .addToBackStack(null)
                        .commit();
            }
        });

        rvLol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("platform", "pc");
                bundle.putString("game", "LoL");

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
