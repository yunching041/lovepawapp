package com.example.lovepaw.Other;

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

public class OtherAdapter extends RecyclerView.Adapter<OtherViewHolder> {

    private Context context;
    private List<DataClass6> data6List;

    public OtherAdapter(Context context, List<DataClass6> data6List) {
        this.context = context;
        this.data6List = data6List;
    }

    @NonNull
    @Override
    public OtherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.petrecycler_item, parent, false);
        return new OtherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OtherViewHolder holder, int position) {
        Glide.with(context).load(data6List.get(position).getDataImage5()).into(holder.recImage6);
        holder.recName5.setText(data6List.get(position).getDataname5());
        holder.recArea5.setText(data6List.get(position).getDatalocation5());
        holder.recMoney5.setText(data6List.get(position).getDatasalary5());

        holder.recCard6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedPosition = holder.getAdapterPosition(); // 获取被点击的位置
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    DataClass6 clickedItem = data6List.get(clickedPosition);
                    Intent intent = new Intent(context, PetdetailActivity.class);
                    intent.putExtra("Image", clickedItem.getDataImage5());
                    intent.putExtra("Need", clickedItem.getDataDemand5());
                    intent.putExtra("Variety", clickedItem.getDatavariety5());
                    intent.putExtra("Name", clickedItem.getDataname5());
                    intent.putExtra("Area", clickedItem.getDatalocation5());
                    intent.putExtra("salary", clickedItem.getDatasalary5());
                    intent.putExtra("detail", clickedItem.getDatadetail5());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data6List.size();
    }
}

class OtherViewHolder extends RecyclerView.ViewHolder {

    ImageView recImage6;
    TextView recName5, recArea5, recMoney5;
    CardView recCard6;

    public OtherViewHolder(@NonNull View itemView) {
        super(itemView);

        recImage6 = itemView.findViewById(R.id.recImage2);
        recName5 = itemView.findViewById(R.id.recName);
        recArea5 = itemView.findViewById(R.id.recArea);
        recMoney5 = itemView.findViewById(R.id.recMoney);
        recCard6 = itemView.findViewById(R.id.recCard2);
    }
}
