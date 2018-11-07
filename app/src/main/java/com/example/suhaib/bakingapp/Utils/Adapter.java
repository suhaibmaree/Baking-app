package com.example.suhaib.bakingapp.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.suhaib.bakingapp.Details.BakinListActivity;
import com.example.suhaib.bakingapp.JsonFiles.Baking;
import com.example.suhaib.bakingapp.JsonFiles.Step;
import com.example.suhaib.bakingapp.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context mContext;
    private List<Baking> bakingList;

    public Adapter(Context mContext, List<Baking> bakingList) {
        this.mContext = mContext;
        this.bakingList = bakingList;
        Log.d(bakingList.size()+" Adapter constructor","Check Me");
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.baking_card,parent,false);
        Log.d("onCreateViewHolder","Check Me");
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(bakingList.get(holder.getAdapterPosition()).getName());
        Glide.with(mContext)
                .load(bakingList.get(position).getImage())
                .placeholder(R.drawable.load1).into(holder.cardImage);
        Log.d("onBind","Check Me");

    }

    @Override
    public int getItemCount() {
        return bakingList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public ImageView cardImage;


        public MyViewHolder(View itemView) {
            super(itemView);
            cardImage = itemView.findViewById(R.id.card_image);
            name = itemView.findViewById(R.id.baking_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        Intent intent = new Intent(mContext, BakinListActivity.class);


                        Log.d(".Adapter","Size List is "+bakingList.get(pos).getSteps().size());
                        intent.putExtra("baking", bakingList.get(pos));
                        ArrayList<Step> steps;
                        steps = bakingList.get(pos).getSteps();
                        intent.putParcelableArrayListExtra("steps", steps);


                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        //Toast.makeText(view.getContext(),steps.size()+"k",Toast.LENGTH_SHORT).show();
                        mContext.startActivity(intent);

                    }
                }
            });
        }
    }
}
