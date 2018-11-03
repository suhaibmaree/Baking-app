package com.example.suhaib.bakingapp.Utils;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
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
import com.example.suhaib.bakingapp.DetailActivity;
import com.example.suhaib.bakingapp.JsonFiles.Baking;
import com.example.suhaib.bakingapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context mContext;
    private List<Baking> bakingList;

    public Adapter(Context mContext, List<Baking> bakingList) {
        this.mContext = mContext;
        this.bakingList = bakingList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.baking_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(bakingList.get(holder.getAdapterPosition()).getName());
        Glide.with(mContext)
                .load(bakingList.get(position).getImage())
                .placeholder(R.drawable.load).into(holder.cardImage);
        Log.d("onbind","susumeme ");

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public ImageView cardImage;


        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.baking_name);
            cardImage = itemView.findViewById(R.id.card_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        Intent intent = new Intent(mContext, DetailActivity.class);
                        intent.putExtra("baking", (Parcelable) bakingList.get(pos));
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        mContext.startActivity(intent);
                        Toast.makeText(view.getContext(),bakingList.get(pos).getName(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
