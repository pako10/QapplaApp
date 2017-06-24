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
import com.example.paco.qapplaapp.Fragments.Matches.MatchGames.MatchPcFindFragment;
import com.example.paco.qapplaapp.Fragments.Matches.MatchGames.MatchPs4FindFragment;
import com.example.paco.qapplaapp.Fragments.Matches.MatchGames.MatchXboxFindFragment;
import com.example.paco.qapplaapp.R;

/**
 * Created by paco on 23/06/2017.
 */

public class SearchMatchesFragment extends Fragment {

    TabLayout tabs;

    ViewPager viewPager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_search_matches, container, false);

        tabs = (TabLayout) view.findViewById(R.id.tabsGame);

        tabs.addTab(tabs.newTab().setIcon(R.drawable.xboxactivo));
        tabs.addTab(tabs.newTab().setIcon(R.drawable.ps4));
        tabs.addTab(tabs.newTab().setIcon(R.drawable.pc));

        viewPager = (ViewPager) view.findViewById(R.id.containerGame);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));


        PagerMatchesGame adapter = new PagerMatchesGame(getChildFragmentManager(),tabs.getTabCount());

        viewPager.setAdapter(adapter);

        tabs.setOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        // ...
                        viewPager.setCurrentItem(tab.getPosition());


                        int position = tab.getPosition();
                        if (position == 0){
                            tab.setIcon(R.drawable.xboxactivo);
                            tabs.getTabAt(1).setIcon(R.drawable.ps4);
                            tabs.getTabAt(2).setIcon(R.drawable.pc);
                        }
                        if (position == 1){
                            tab.setIcon(R.drawable.ps4activo);
                            tabs.getTabAt(0).setIcon(R.drawable.xbox);
                            tabs.getTabAt(2).setIcon(R.drawable.pc);
                        }
                        if (position == 2){
                            tab.setIcon(R.drawable.pcactivo);
                            tabs.getTabAt(0).setIcon(R.drawable.xbox);
                            tabs.getTabAt(1).setIcon(R.drawable.ps4);
                        }

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


    public static class PagerMatchesGame extends FragmentPagerAdapter {

        //integer to count number of tabs
        int tabCount;

        //Constructor to the class
        public PagerMatchesGame(android.support.v4.app.FragmentManager fm, int tabCount) {
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
                    MatchXboxFindFragment matchXboxFindFragment = new MatchXboxFindFragment();
                    return matchXboxFindFragment;

                case 1:
                    MatchPs4FindFragment matchPs4FindFragment = new MatchPs4FindFragment();
                    return matchPs4FindFragment;
                case 2:
                    MatchPcFindFragment matchPcFindFragment = new MatchPcFindFragment();
                    return matchPcFindFragment;

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
