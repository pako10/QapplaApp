package com.example.paco.qapplaapp.collapse;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.paco.qapplaapp.Objects.Qapla;
import com.example.paco.qapplaapp.R;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by mertsimsek on 31/08/15.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RadioViewHolder> {

    private Context context;

    private List<Qapla> qaplaList;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
        qaplaList = new ArrayList<>();
    }

    public void setQaplaList(List<Qapla> qaplaList){
        this.qaplaList = qaplaList;
        notifyDataSetChanged();
    }

    @Override
    public RadioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_card, parent, false);
        RadioViewHolder viewHolder = new RadioViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RadioViewHolder holder, final int position) {

        Qapla qapla = qaplaList.get(position);
        holder.tvUserName.setText(qapla.getUserName());
        holder.tvWins.setText(qapla.getUserWins());
        holder.tvLoses.setText(qapla.getUserLoses());
    }

    @Override
    public int getItemCount() {
        return qaplaList.size();
    }

    public class RadioViewHolder extends RecyclerView.ViewHolder{

        TextView tvUserName;
        TextView tvWins;
        TextView tvLoses;
        ImageView imgLogo;

        public RadioViewHolder(View itemView) {
            super(itemView);
            tvUserName = (TextView) itemView.findViewById(R.id.tvUserNameSearch);
            tvWins = (TextView) itemView.findViewById(R.id.tvWins);
            tvLoses = (TextView) itemView.findViewById(R.id.tvLose);
            imgLogo = (ImageView) itemView.findViewById(R.id.imgUserSearch);


        }
    }

}
