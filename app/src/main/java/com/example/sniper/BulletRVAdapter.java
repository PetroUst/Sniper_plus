package com.example.sniper;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BulletRVAdapter extends RecyclerView.Adapter<BulletRVAdapter.ViewHolder>  {

    // variable for our array list and context
    private ArrayList<BulletModel> bulletModelArrayList;
    private Context context;


    // constructor
    public BulletRVAdapter(ArrayList<BulletModel> bulletModelArrayList, Context context) {
        this.bulletModelArrayList = bulletModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public BulletRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bullet_rv_item, parent, false);
        return new BulletRVAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull BulletRVAdapter.ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        BulletModel modal = bulletModelArrayList.get(position);
        holder.bulletCalTV.setText(modal.getCaliber());
        holder.bulletG1TV.setText(String.valueOf(modal.getG1()));
        holder.bulletG7TV.setText(String.valueOf(modal.getG7()));
        holder.bulletWeightTV.setText(String.valueOf(modal.getWeight()));
        holder.bulletStart_speedTV.setText(String.valueOf(modal.getStart_speed()));
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return bulletModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView bulletCalTV, bulletG1TV, bulletG7TV, bulletWeightTV, bulletStart_speedTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            bulletCalTV = itemView.findViewById(R.id.idTVBulletCal);
            bulletG1TV = itemView.findViewById(R.id.idTVBulletG1);
            bulletG7TV = itemView.findViewById(R.id.idTVBulletG7);
            bulletWeightTV = itemView.findViewById(R.id.idTVBulletWeight);
            bulletStart_speedTV = itemView.findViewById((R.id.idTVBulletStart_speed));
        }
    }
}
