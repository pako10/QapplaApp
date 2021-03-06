package com.example.paco.qapplaapp.Fragments.Matches;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.example.paco.qapplaapp.Fragments.GameSearchFragment;
import com.example.paco.qapplaapp.R;
import com.example.paco.qapplaapp.Utils.OnSwipeTouchListener;

/**
 * Created by paco on 24/05/2017.
 */

public class DialogFilterMatches extends DialogFragment {

    //STRING PARA SABER QUE JUEGO HA SIDO SELECCIONADO PARA ENVIAR A LA BUSQUEDA
    String selectedGame = "";

    //STRING PARA SABER QUE PLATAFORMA ESTA SELECCIONADA PARA ENVIAR A LA BUSQUEDA
    String selectedPlatform = "";


    Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogFragmentTheme);
        mContext = getActivity();
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog d = getDialog();
        if (d!=null){
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            d.getWindow().setLayout(width, height);
        }
    }

    Button btFilter;
    Button btXbox;
    Button btPs4;
    Button btPc;


    ImageView imgLast1;
    ImageView imgLast2;

    ImageView imgGame1, imgGame2, imgGame3,imgGame4,imgGame5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.filter_matches_dialog, container, false);
        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        toolbar.setTitle("Filtrar");



        imgGame1 = (ImageView) root.findViewById(R.id.imgGearsFilter);
        imgGame2 = (ImageView) root.findViewById(R.id.imgOverwatchFilter);
        imgGame3 = (ImageView) root.findViewById(R.id.imgCallofDutyFilter);
        imgGame4 = (ImageView) root.findViewById(R.id.imgFifaFilter);
        imgGame5 = (ImageView) root.findViewById(R.id.imgStreetFilter);
        btFilter = (Button) root.findViewById(R.id.btFilter);
        btPc = (Button) root.findViewById(R.id.btPcSearch);
        btPs4 = (Button) root.findViewById(R.id.btPs4Search);
        btXbox = (Button) root.findViewById(R.id.btXboxSearch);


        selectGame();

        onXboxSelected();

        btFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent i = new Intent(mContext, MainActivity.class);
                i.putExtra("platform",selectedPlatform);
                i.putExtra("game",selectedGame);
                startActivity(i);*/


                Bundle bundle = new Bundle();
                bundle.putString("platform", selectedPlatform);
                bundle.putString("game", selectedGame

                );

                /*GameSearchFragment gameSearchFragment = new GameSearchFragment();
                gameSearchFragment.setArguments(bundle);
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
                dismiss();
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Handle the menu item
                int id = item.getItemId();
                switch (id){
                    case R.id.close:
                        dismiss();
                }
                return true;
            }
        });
        toolbar.inflateMenu(R.menu.menu_search);

        btXbox.setOnClickListener(
                new View.OnClickListener() {
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

        btPc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPcSelected();
            }
        });

        root.setOnTouchListener(new OnSwipeTouchListener(mContext) {
            public void onSwipeTop() {

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


            }
            public void onSwipeBottom() {

            }

        });


        return root;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return new Dialog(getActivity(), getTheme()){
            @Override
            public void onBackPressed() {
                // On backpress, do your stuff here.
                dismiss();
            }
        };
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
    }

    public void onXboxSelected(){
        imgGame3.setVisibility(View.VISIBLE);
        imgGame4.setVisibility(View.VISIBLE);
        imgGame5.setVisibility(View.INVISIBLE);
        selectedPlatform = "xbox";
        imgGame4.setImageResource(R.drawable.halolog);


        Drawable background = btXbox.getBackground();
        if (background instanceof ShapeDrawable) {
            // cast to 'ShapeDrawable'
            ShapeDrawable shapeDrawable = (ShapeDrawable) background;
            shapeDrawable.getPaint().setColor(getResources().getColor(R.color.accent));
        } else if (background instanceof GradientDrawable) {
            // cast to 'GradientDrawable'
            GradientDrawable gradientDrawable = (GradientDrawable) background;
            //gradientDrawable.setColor(ContextCompat.getColor(mContext,R.color.colorPrimary));
            gradientDrawable.setColor(getResources().getColor(R.color.accent));
        } else if (background instanceof ColorDrawable) {
            // alpha value may need to be set again after this call
            ColorDrawable colorDrawable = (ColorDrawable) background;
            colorDrawable.setColor(getResources().getColor(R.color.accent));
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

        imgGame3.setVisibility(View.INVISIBLE);
        imgGame4.setVisibility(View.INVISIBLE);
        imgGame5.setVisibility(View.GONE);
        selectedPlatform = "ps4";

        Drawable background2 = btPs4.getBackground();
        if (background2 instanceof ShapeDrawable) {
            ((ShapeDrawable)background2).getPaint().setColor(getResources().getColor(R.color.accent));
        } else if (background2 instanceof GradientDrawable) {
            ((GradientDrawable)background2).setColor(getResources().getColor(R.color.accent));
        } else if (background2 instanceof ColorDrawable) {
            ((ColorDrawable)background2).setColor(getResources().getColor(R.color.accent));
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

        imgGame3.setVisibility(View.VISIBLE);
        imgGame4.setVisibility(View.VISIBLE);
        imgGame5.setVisibility(View.VISIBLE);
        imgGame4.setImageResource(R.drawable.lolog);
        selectedPlatform = "pc";

        Drawable background3 = btPc.getBackground();
        if (background3 instanceof ShapeDrawable) {
            ((ShapeDrawable)background3).getPaint().setColor(getResources().getColor(R.color.accent));
        } else if (background3 instanceof GradientDrawable) {
            ((GradientDrawable)background3).setColor(getResources().getColor(R.color.accent));
        } else if (background3 instanceof ColorDrawable) {
            ((ColorDrawable)background3).setColor(getResources().getColor(R.color.accent));
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
    }

    public void selectGame(){

        imgGame1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btFilter.setVisibility(View.VISIBLE);
                selectedGame = "fifa";

                imgGame1.setImageResource(R.drawable.fifaactivo);
                imgGame2.setImageResource(R.drawable.overlog);
                imgGame3.setImageResource(R.drawable.gowlog);
                imgGame5.setImageResource(R.drawable.hearthlog);

                if (btXbox.isSelected()){
                    imgGame4.setImageResource(R.drawable.halolog);
                }else{
                    imgGame4.setImageResource(R.drawable.lolog);
                }

            }
        });

        imgGame2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btFilter.setVisibility(View.VISIBLE);
                selectedGame = "overwatch";

                imgGame1.setImageResource(R.drawable.fifalog);
                imgGame2.setImageResource(R.drawable.overactivo);
                imgGame3.setImageResource(R.drawable.gowlog);
                imgGame5.setImageResource(R.drawable.hearthlog);

                if (btXbox.isSelected()){
                    imgGame4.setImageResource(R.drawable.halolog);
                }else{
                    imgGame4.setImageResource(R.drawable.lolog);
                }
            }
        });

        imgGame3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btFilter.setVisibility(View.VISIBLE);
                selectedGame = "gow";

                imgGame1.setImageResource(R.drawable.fifalog);
                imgGame2.setImageResource(R.drawable.overlog);
                imgGame3.setImageResource(R.drawable.gowactivo);
                imgGame5.setImageResource(R.drawable.hearthlog);

                if (btXbox.isSelected()){
                    imgGame4.setImageResource(R.drawable.halolog);

                }else{

                    imgGame4.setImageResource(R.drawable.lolog);
                }
            }
        });

        imgGame4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btFilter.setVisibility(View.VISIBLE);

                imgGame1.setImageResource(R.drawable.fifalog);
                imgGame2.setImageResource(R.drawable.overlog);
                imgGame3.setImageResource(R.drawable.gowlog);
                imgGame5.setImageResource(R.drawable.hearthlog);

                if (btXbox.isSelected()){
                    imgGame4.setImageResource(R.drawable.haloactivo);
                    selectedGame = "halo";
                }else{
                    selectedGame = "lol";
                    imgGame4.setImageResource(R.drawable.lolactivo);
                }
            }
        });

        imgGame5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btFilter.setVisibility(View.VISIBLE);
                selectedGame = "hearth";
                imgGame1.setImageResource(R.drawable.fifalog);
                imgGame2.setImageResource(R.drawable.overlog);
                imgGame3.setImageResource(R.drawable.gowlog);
                imgGame4.setImageResource(R.drawable.lolog);
                imgGame5.setImageResource(R.drawable.hearthactivo);
            }
        });


    }
}
