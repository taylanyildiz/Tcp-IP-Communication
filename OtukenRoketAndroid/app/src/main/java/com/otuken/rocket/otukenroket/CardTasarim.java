package com.otuken.rocket.otukenroket;


import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardTasarim  extends RecyclerView.Adapter<CardTasarim.NesneTutucu>{
    private Context context;
    private List<Data> dataList;

    public CardTasarim(Context context, List<Data> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public NesneTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_view,parent,false);
        return new NesneTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NesneTutucu holder, int position) {
        Data data = dataList.get(position);
        holder.imageViewData.setImageResource(context.getResources().getIdentifier(data.getImageDataName(),"mipmap",context.getOpPackageName()));
        holder.textViewValue.setText(data.getValue());
        holder.textViewData.setText(data.getImageValueName());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class NesneTutucu extends RecyclerView.ViewHolder{


        public ConstraintLayout constraintLayout;
        public TextView textViewValue,textViewData;
        public ImageView imageViewData;
        public NesneTutucu(@NonNull View itemView) {
            super(itemView);

            constraintLayout = itemView.findViewById(R.id.constraintLayout);
            textViewData = itemView.findViewById(R.id.textViewData);
            textViewValue = itemView.findViewById(R.id.textViewValue);
            imageViewData = itemView.findViewById(R.id.imageViewData);
        }
    }
}
