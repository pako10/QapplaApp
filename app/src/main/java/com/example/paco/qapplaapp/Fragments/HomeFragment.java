package com.example.paco.qapplaapp.Fragments;





import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;


import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.example.paco.qapplaapp.Fragments.Games.PcFragment;
import com.example.paco.qapplaapp.Fragments.Games.Ps4Fragment;
import com.example.paco.qapplaapp.Fragments.Games.XboxFragment;
import com.example.paco.qapplaapp.MainActivity;
import com.example.paco.qapplaapp.R;
import com.example.paco.qapplaapp.SearchActivity;
import com.example.paco.qapplaapp.Utils.OnSwipeTouchListener;

/**
 * Created by paco on 24/04/2017.
 */

public class HomeFragment extends Fragment {

   /* Button btXbox;
    Button btPs4;
    Button btPc;*/
    RelativeLayout rvContent;
    RelativeLayout rvSearchBox;
    ImageView imgSearch;

    FloatingSearchView floatingSearchView;

    TabLayout tabs;
    ViewPager viewPager;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.layout_home,container,false);

       /* btXbox = (Button) view.findViewById(R.id.btXbox);
        btPs4 = (Button) view.findViewById(R.id.btPs4);
        btPc = (Button) view.findViewById(R.id.btPc);*/
        final Context mContext = getActivity().getApplicationContext();
        floatingSearchView = (FloatingSearchView) view.findViewById(R.id.searchViewQappla);
        rvContent = (RelativeLayout) view.findViewById(R.id.rvContent);
        rvSearchBox = (RelativeLayout) view.findViewById(R.id.rvSearchBox);
        imgSearch = (ImageView) view.findViewById(R.id.imgSearch);

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                DialogSearchFragment frag = new DialogSearchFragment();
                frag.show(ft,"txn_tag");
            }
        });

        tabs = (TabLayout) view.findViewById(R.id.tabs);

        tabs.addTab(tabs.newTab().setIcon(R.drawable.xboxactivo));
        tabs.addTab(tabs.newTab().setIcon(R.drawable.ps4));
        tabs.addTab(tabs.newTab().setIcon(R.drawable.pc));

        viewPager = (ViewPager) view.findViewById(R.id.containerF);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));


        PagerPeda adapter = new PagerPeda(getFragmentManager(),tabs.getTabCount());

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

       /* btXbox.setSelected(true);

        rvSearchBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                DialogSearchFragment frag = new DialogSearchFragment();
                frag.show(ft, "txn_tag");
              /*  Intent i = new Intent(mContext, SearchActivity.class);
                startActivity(i);

            }
        });


        onXboxSelected();
        setFragment(2);



        btXbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onXboxSelected();

            }
        });

        btPs4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPs4Selected();
            }
        });

        btPc.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPcSelected();
            }
        });

        view.setOnTouchListener(new OnSwipeTouchListener(mContext) {
            public void onSwipeTop()
            {
                Toast.makeText(mContext, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                if (btPc.isSelected()){
                    onPs4Selected();
                }else if (btPs4.isSelected()){
                    onXboxSelected();
                }
                Toast.makeText(mContext, "right", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeLeft() {
                if (btXbox.isSelected()) {
                    onPs4Selected();

                }else if (btPs4.isSelected()){
                    onPcSelected();
                }

                Toast.makeText(mContext, "left", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeBottom() {
                Toast.makeText(mContext, "bottom", Toast.LENGTH_SHORT).show();
            }

        });*/



        return view;
    }

    public static class PagerPeda extends FragmentPagerAdapter {

        //integer to count number of tabs
        int tabCount;

        //Constructor to the class
        public PagerPeda(android.support.v4.app.FragmentManager fm, int tabCount) {
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

   /* public void onXboxSelected(){
        setFragment(2);
        Drawable background = btXbox.getBackground();
        if (background instanceof ShapeDrawable) {
            // cast to 'ShapeDrawable'
            ShapeDrawable shapeDrawable = (ShapeDrawable) background;
            shapeDrawable.getPaint().setColor(Color.GREEN);
        } else if (background instanceof GradientDrawable) {
            // cast to 'GradientDrawable'
            GradientDrawable gradientDrawable = (GradientDrawable) background;
            //gradientDrawable.setColor(ContextCompat.getColor(mContext,R.color.colorPrimary));
            gradientDrawable.setColor(Color.GREEN);
        } else if (background instanceof ColorDrawable) {
            // alpha value may need to be set again after this call
            ColorDrawable colorDrawable = (ColorDrawable) background;
            colorDrawable.setColor(Color.GREEN);
        }


        Drawable background2 = btPs4.getBackground();
        if (background2 instanceof ShapeDrawable) {
            ((ShapeDrawable)background2).getPaint().setColor(Color.WHITE);
        } else if (background2 instanceof GradientDrawable) {
            ((GradientDrawable)background2).setColor(Color.WHITE);
        } else if (background2 instanceof ColorDrawable) {
            ((ColorDrawable)background2).setColor(Color.WHITE);
        }

        Drawable background3 = btPc.getBackground();
        if (background3 instanceof ShapeDrawable) {
            ((ShapeDrawable)background3).getPaint().setColor(Color.WHITE);
        } else if (background3 instanceof GradientDrawable) {
            ((GradientDrawable)background3).setColor(Color.WHITE);
        } else if (background3 instanceof ColorDrawable) {
            ((ColorDrawable)background3).setColor(Color.WHITE);
        }

        btXbox.setSelected(true);
        btPs4.setSelected(false);
        btPc.setSelected(false);
    }

    public void onPs4Selected(){
        setFragment(1);
        Drawable background2 = btPs4.getBackground();
        if (background2 instanceof ShapeDrawable) {
            ((ShapeDrawable)background2).getPaint().setColor(Color.GREEN);
        } else if (background2 instanceof GradientDrawable) {
            ((GradientDrawable)background2).setColor(Color.GREEN);
        } else if (background2 instanceof ColorDrawable) {
            ((ColorDrawable)background2).setColor(Color.GREEN);
        }

        Drawable background = btXbox.getBackground();
        if (background instanceof ShapeDrawable) {
            ((ShapeDrawable)background).getPaint().setColor(Color.WHITE);
        } else if (background instanceof GradientDrawable) {
            ((GradientDrawable)background).setColor(Color.WHITE);
        } else if (background instanceof ColorDrawable) {
            ((ColorDrawable)background).setColor(Color.WHITE);
        }

        Drawable background3 = btPc.getBackground();
        if (background3 instanceof ShapeDrawable) {
            ((ShapeDrawable)background3).getPaint().setColor(Color.WHITE);
        } else if (background3 instanceof GradientDrawable) {
            ((GradientDrawable)background3).setColor(Color.WHITE);
        } else if (background3 instanceof ColorDrawable) {
            ((ColorDrawable)background3).setColor(Color.WHITE);
        }

        btPs4.setSelected(true);
        btXbox.setSelected(false);
        btPc.setSelected(false);
    }

    public void onPcSelected(){
        setFragment(0);
        Drawable background3 = btPc.getBackground();
        if (background3 instanceof ShapeDrawable) {
            ((ShapeDrawable)background3).getPaint().setColor(Color.GREEN);
        } else if (background3 instanceof GradientDrawable) {
            ((GradientDrawable)background3).setColor(Color.GREEN);
        } else if (background3 instanceof ColorDrawable) {
            ((ColorDrawable)background3).setColor(Color.GREEN);
        }

        Drawable background2 = btPs4.getBackground();
        if (background2 instanceof ShapeDrawable) {
            ((ShapeDrawable)background2).getPaint().setColor(Color.WHITE);
        } else if (background2 instanceof GradientDrawable) {
            ((GradientDrawable)background2).setColor(Color.WHITE);
        } else if (background2 instanceof ColorDrawable) {
            ((ColorDrawable)background2).setColor(Color.WHITE);
        }

        Drawable background = btXbox.getBackground();
        if (background instanceof ShapeDrawable) {
            ((ShapeDrawable)background).getPaint().setColor(Color.WHITE);
        } else if (background instanceof GradientDrawable) {
            ((GradientDrawable)background).setColor(Color.WHITE);
        } else if (background instanceof ColorDrawable) {
            ((ColorDrawable)background).setColor(Color.WHITE);
        }

        btPc.setSelected(true);
        btXbox.setSelected(false);
        btPs4.setSelected(false);
    }*/





   /* public void setFragment(int position){
        android.app.FragmentManager fragmentManager;
        android.app.FragmentTransaction fragmentTransaction;
        switch (position){
            case 0:
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                PcFragment pcFragment = new PcFragment();
                fragmentTransaction.replace(R.id.frGames,pcFragment);
                fragmentTransaction.commit();
                break;
            case 1:
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                Ps4Fragment ps4Fragment = new Ps4Fragment();
                fragmentTransaction.replace(R.id.frGames, ps4Fragment);
                fragmentTransaction.commit();
                break;
            case 2:
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                XboxFragment xboxFragment = new XboxFragment();
                fragmentTransaction.replace(R.id.frGames,xboxFragment);
                fragmentTransaction.commit();
                break;
        }
    }*/

    /*@Override
    public void onResume() {

        setFragment(2);
        super.onResume();
    }*/




}
