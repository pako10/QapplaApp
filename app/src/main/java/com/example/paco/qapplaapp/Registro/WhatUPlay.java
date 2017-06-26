package com.example.paco.qapplaapp.Registro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paco.qapplaapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WhatUPlay extends AppCompatActivity {

    ScrollView rvGamesXbox;
    ScrollView rvGamesPc;
    RelativeLayout rvGamesPs4;
    Button btSiguiente,btOmitir;
    ImageView imgXbox,imgPs4,imgPc;
    TextView tvRegresar;

    ImageView imgGowXbox,imgFifaXbox,imgHaloXbox,imgOverXbox;
    ImageView imgFifaPs4,imgOverPs4;
    ImageView imgOverpc,imgHeartPc,imgLolPc,imgGowPc,imgHaloPc;


    int contador = 0;

    List<String> games;

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mUsersDatabaseReference;

    String userUid;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_uplay);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**INSTANCIA DE FIREBASE**/
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mUsersDatabaseReference = mFirebaseDatabase.getReference().child("Users");


        rvGamesXbox = (ScrollView) findViewById(R.id.rvGamesXbox);
        rvGamesPc = (ScrollView) findViewById(R.id.rvGamesPc);
        rvGamesPs4 = (RelativeLayout) findViewById(R.id.rvGamesPs4);
        btSiguiente = (Button) findViewById(R.id.btSiguiente);
        btOmitir = (Button) findViewById(R.id.btOmitr);
        imgXbox = (ImageView) findViewById(R.id.imageView24);
        imgPs4 = (ImageView) findViewById(R.id.imageView23);
        imgPc = (ImageView) findViewById(R.id.imageView25);
        tvRegresar = (TextView) findViewById(R.id.tvRegresar);

        /**IMAGENES DE XBOX**/
        imgGowXbox = (ImageView) findViewById(R.id.imgSelectGearsXbox);
        imgFifaXbox = (ImageView) findViewById(R.id.imgSelectFifaXbox);
        imgHaloXbox = (ImageView) findViewById(R.id.imgSelectHaloXbox);
        imgOverXbox = (ImageView) findViewById(R.id.imgSelectOverXbox);

        /**IMAGENES DE PS4**/
        imgFifaPs4 = (ImageView) findViewById(R.id.imgSelectFifaPs4);
        imgOverPs4 = (ImageView) findViewById(R.id.imgSelectOverPs4);

        /**IMAGENES DE PC**/
        imgOverpc = (ImageView) findViewById(R.id.imgSelectOverPc);
        imgHeartPc = (ImageView) findViewById(R.id.imgSelectHearth);
        imgLolPc = (ImageView) findViewById(R.id.imgSelectLol);
        imgGowPc = (ImageView) findViewById(R.id.imgSelectGowPc);
        imgHaloPc = (ImageView) findViewById(R.id.imgSelectHaloPc);

        games = new ArrayList<String>();

        Intent intent = getIntent();
        userUid = intent.getStringExtra("userUid");




        selectPc();
        selectXbox();
        selectPs4();


        btSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contador == 0) {
                    contador = contador + 1;
                    imgXbox.setImageResource(R.drawable.xbox);
                    imgPs4.setImageResource(R.drawable.ps4activo);
                    imgPc.setImageResource(R.drawable.pc);
                    rvGamesXbox.setVisibility(View.GONE);
                    rvGamesPs4.setVisibility(View.VISIBLE);
                    tvRegresar.setVisibility(view.VISIBLE);
                }


                else if (contador == 1){
                    contador = contador +1;
                    imgPs4.setImageResource(R.drawable.ps4);
                    imgPc.setImageResource(R.drawable.pcactivo);
                    rvGamesXbox.setVisibility(View.GONE);
                    rvGamesPs4.setVisibility(View.GONE);
                    rvGamesPc.setVisibility(View.VISIBLE);
                    tvRegresar.setVisibility(view.VISIBLE);

                }

                else if (contador == 2){
                    mUsersDatabaseReference.child(userUid).child("games").setValue(games).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){

                                Intent i = new Intent(WhatUPlay.this,GamerTagActivity.class);
                                i.putExtra("userUid",userUid);
                                startActivity(i);
                                finish();
                            }
                        }
                    });
                }
            }
        });

        btOmitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contador == 0) {
                    contador = contador + 1;
                    imgXbox.setImageResource(R.drawable.xbox);
                    imgPs4.setImageResource(R.drawable.ps4activo);
                    imgPc.setImageResource(R.drawable.pc);
                    rvGamesXbox.setVisibility(View.GONE);
                    rvGamesPs4.setVisibility(View.VISIBLE);
                    tvRegresar.setVisibility(view.VISIBLE);
                }


                else if (contador == 1){
                    contador = contador +1;
                    imgPs4.setImageResource(R.drawable.ps4);
                    imgPc.setImageResource(R.drawable.pcactivo);
                    rvGamesXbox.setVisibility(View.GONE);
                    rvGamesPs4.setVisibility(View.GONE);
                    rvGamesPc.setVisibility(View.VISIBLE);
                    tvRegresar.setVisibility(view.VISIBLE);

                }
            }
        });

        tvRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contador = contador - 1;

                if (contador == 0) {
                    imgXbox.setImageResource(R.drawable.xboxactivo);
                    imgPs4.setImageResource(R.drawable.ps4);
                    imgPc.setImageResource(R.drawable.pc);
                    rvGamesXbox.setVisibility(View.VISIBLE);
                    rvGamesPs4.setVisibility(View.GONE);
                    rvGamesPc.setVisibility(View.GONE);
                    tvRegresar.setVisibility(view.GONE);
                }


                else if (contador == 1){
                    imgXbox.setImageResource(R.drawable.xbox);
                    imgPs4.setImageResource(R.drawable.ps4activo);
                    imgPc.setImageResource(R.drawable.pc);
                    rvGamesXbox.setVisibility(View.GONE);
                    rvGamesPs4.setVisibility(View.VISIBLE);
                    rvGamesPc.setVisibility(View.GONE);
                    tvRegresar.setVisibility(view.VISIBLE);

                }
            }
        });

    }

    public void selectXbox(){
        imgGowXbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgGowXbox.setImageResource(R.drawable.gowactivo);
                games.add("xGears");
            }
        });
        imgFifaXbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgFifaXbox.setImageResource(R.drawable.fifaactivo);
                games.add("xFifa");
            }
        });
        imgHaloXbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgHaloXbox.setImageResource(R.drawable.haloactivo);
                games.add("xHalo");
            }
        });
        imgOverXbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgOverXbox.setImageResource(R.drawable.overactivo);
                games.add("xOver");
            }
        });
    }

    public void selectPs4(){
        imgFifaPs4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgFifaPs4.setImageResource(R.drawable.fifaactivo);
                games.add("psFifa");
            }
        });
        imgOverPs4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgOverPs4.setImageResource(R.drawable.overactivo);
                games.add("psOver");
            }
        });
    }

    public void selectPc(){

        imgOverpc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgOverpc.setImageResource(R.drawable.overactivo);
                games.add("pOver");

            }
        });
        imgHeartPc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgHeartPc.setImageResource(R.drawable.hearthactivo);
                games.add("pHearth");

            }
        });
        imgLolPc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgLolPc.setImageResource(R.drawable.lolactivo);
                games.add("pcLol");
            }
        });
        imgGowPc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgGowPc.setImageResource(R.drawable.gowactivo);
                games.add("pGears");
            }
        });
        imgHaloPc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgHaloPc.setImageResource(R.drawable.haloactivo);
                games.add("pHalo");
            }
        });

    }

}
