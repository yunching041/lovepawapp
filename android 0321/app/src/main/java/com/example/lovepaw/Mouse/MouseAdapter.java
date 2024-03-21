package com.example.lovepaw.Mouse;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lovepaw.PetdetailActivity;
import com.example.lovepaw.R;

import java.util.List;

public class MouseAdapter extends RecyclerView.Adapter<MouseViewHolder> {

    private Context context;
    private List<DataClass4> data4List;

    public MouseAdapter(Context context, List<DataClass4> data4List) {
        this.context = context;
        this.data4List = data4List;
    }

    @NonNull
    @Override
    public MouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.petrecycler_item,parent,false);
        return new MouseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MouseViewHolder holder, int position) {
        Glide.with(context).load(data4List.get(position).getDataImage4()).into(holder.recImage4);
        holder.recName3.setText(data4List.get(position).getDataname3());
        holder.recArea3.setText(data4List.get(position).getDatalocation3());
        holder.recMoney3.setText(data4List.get(position).getDatasalary3());

        holder.recCard4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PetdetailActivity.class);
                intent.putExtra("Image",data4List.get(holder.getAdapterPosition()).getDataImage4());
                intent.putExtra("Need",data4List.get(holder.getAdapterPosition()).getDataDemand3());
                intent.putExtra("Variety",data4List.get(holder.getAdapterPosition()).getDatavariety3());
                intent.putExtra("Name",data4List.get(holder.getAdapterPosition()).getDataname3());
                intent.putExtra("Area",data4List.get(holder.getAdapterPosition()).getDatalocation3());
                intent.putExtra("salary",data4List.get(holder.getAdapterPosition()).getDatasalary3());
                intent.putExtra("detail",data4List.get(holder.getAdapterPosition()).getDatadetail3());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data4List.size();
    }
}

class MouseViewHolder extends RecyclerView.ViewHolder{

    ImageView recImage4;
    TextView recName3,recArea3,recMoney3;
    CardView recCard4;

    public MouseViewHolder(@NonNull View itemView) {
        super(itemView);

        recImage4=itemView.findViewById(R.id.recImage2);
        recName3=itemView.findViewById(R.id.recName);
        recArea3=itemView.findViewById(R.id.recArea);
        recMoney3=itemView.findViewById(R.id.recMoney);
        recCard4=itemView.findViewById(R.id.recCard2);
    }
}
