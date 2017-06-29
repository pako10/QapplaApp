package com.example.paco.qapplaapp.Objects;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paco.qapplaapp.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<QapplaUser> friends;
    private Context context;

    public RecyclerAdapter(Context context) {
        notifyDataSetChanged();
        this.context = context;
    }

    public void setFriendsList(List<QapplaUser> friends){
        this.friends = friends;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        //inflate your layout and pass it to view holder


        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_card, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder viewHolder, int position) {

//        viewHolder.tvUserName.setText(friends.get(position).getNombre());
      //  viewHolder.item.setText(friends.get(position));
    }

    @Override
    public int getItemCount() {
        return (null != friends ? friends.size() : 0);
    }

    /**
     * View holder to display each RecylerView item
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvUserName;
        ImageView imgUserSearch;
        TextView tvUserWins;
        TextView tvUserLoses;
        TextView tvUserLvl;
        TextView tvUserExp;

        public ViewHolder(View view) {
            super(view);
           /* tvUserName = (TextView) view.findViewById(R.id.tvUserNameSearch);
            imgUserSearch = (ImageView) view.findViewById(R.id.imgUserSearch);
            tvUserWins = (TextView) view.findViewById(R.id.tvWins);
            tvUserLoses = (TextView) view.findViewById(R.id.tvLose);
            tvUserLvl = (TextView) view.findViewById(R.id.tvLvl);
            tvUserExp = (TextView) view.findViewById(R.id.tvExperience);*/


        }
    }
}