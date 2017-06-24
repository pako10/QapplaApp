package com.example.paco.qapplaapp.Fragments.Matches.MatchGames;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.paco.qapplaapp.Objects.QapplaUser;
import com.example.paco.qapplaapp.Objects.RecyclerAdapter;
import com.example.paco.qapplaapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paco on 23/06/2017.
 */

public class MatchXboxFindFragment extends Fragment {

    private List<QapplaUser> stringArrayList;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    Context mContext;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_match_find_xbox, container, false);


        mContext = getActivity();


        stringArrayList = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerFindXbox);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);


        adapter = new RecyclerAdapter(mContext);
        recyclerView.setAdapter(adapter);

        stringArrayList.add(new QapplaUser("haloMaster"));
        stringArrayList.add(new QapplaUser("haloMaster"));
        stringArrayList.add(new QapplaUser("haloMaster"));
        adapter.setFriendsList(stringArrayList);

        return view;
    }
}
