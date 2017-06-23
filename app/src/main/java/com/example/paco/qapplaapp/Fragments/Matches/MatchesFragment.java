package com.example.paco.qapplaapp.Fragments.Matches;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.paco.qapplaapp.Fragments.Games.PcFragment;
import com.example.paco.qapplaapp.Fragments.Games.Ps4Fragment;
import com.example.paco.qapplaapp.Fragments.Games.XboxFragment;
import com.example.paco.qapplaapp.Fragments.HomeFragment;
import com.example.paco.qapplaapp.R;

/**
 * Created by paco on 18/06/2017.
 */

public class MatchesFragment extends Fragment {

    TabLayout tabs;
    
    ViewPager viewPager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.layout_matches,container,false);


        tabs = (TabLayout) view.findViewById(R.id.tabs);

        tabs.addTab(tabs.newTab().setText("Buscar"));
        tabs.addTab(tabs.newTab().setText("Programadas"));
        tabs.addTab(tabs.newTab().setText("Historial"));

        viewPager = (ViewPager) view.findViewById(R.id.containerF);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));


        PagerMatches adapter = new PagerMatches(getFragmentManager(),tabs.getTabCount());

        viewPager.setAdapter(adapter);

        tabs.setOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        // ...
                        viewPager.setCurrentItem(tab.getPosition());

                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        // ...
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        // ...
                    }
                }
        );

        return view;
    }


    public static class PagerMatches extends FragmentPagerAdapter {

        //integer to count number of tabs
        int tabCount;

        //Constructor to the class
        public PagerMatches(android.support.v4.app.FragmentManager fm, int tabCount) {
            super(fm);
            //Initializing tab count
            this.tabCount= tabCount;
        }

        //Overriding method getItem
        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            //Returning the current tabs
            switch (position) {
                case 0:
                    XboxFragment xbox = new XboxFragment();
                    return xbox;

                case 1:
                    Ps4Fragment ps4 = new Ps4Fragment();
                    return ps4;
                case 2:
                    PcFragment pc = new PcFragment();
                    return pc;

                default:
                    return null;
            }
        }

        //Overriden method getCount to get the number of tabs
        @Override
        public int getCount() {
            return tabCount;
        }
    }
}
