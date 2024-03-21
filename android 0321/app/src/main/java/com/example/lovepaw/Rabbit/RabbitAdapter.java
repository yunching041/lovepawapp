package com.example.lovepaw.Rabbit;

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

public class RabbitAdapter extends RecyclerView.Adapter<RabbitViewHolder> {

    private Context context;
    private List<DataClass5> data5List;

    public RabbitAdapter(Context context, List<DataClass5> data5List) {
        this.context = context;
        this.data5List = data5List;
    }

    @NonNull
    @Override
    public RabbitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.petrecycler_item,parent,false);
        return new RabbitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RabbitViewHolder holder, int position) {
        Glide.with(context).load(data5List.get(position).getDataImage4()).into(holder.recImage5);
        holder.recName4.setText(data5List.get(position).getDataname4());
        holder.recArea4.setText(data5List.get(position).getDatalocation4());
        holder.recMoney4.setText(data5List.get(position).getDatasalary4());

        holder.recCard5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PetdetailActivity.class);
                intent.putExtra("Image",data5List.get(holder.getAdapterPosition()).getDataImage4());
                intent.putExtra("Need",data5List.get(holder.getAdapterPosition()).getDataDemand4());
                intent.putExtra("Variety",data5List.get(holder.getAdapterPosition()).getDatavariety4());
                intent.putExtra("Name",data5List.get(holder.getAdapterPosition()).getDataname4());
                intent.putExtra("Area",data5List.get(holder.getAdapterPosition()).getDatalocation4());
                intent.putExtra("salary",data5List.get(holder.getAdapterPosition()).getDatasalary4());
                intent.putExtra("detail",data5List.get(holder.getAdapterPosition()).getDatadetail4());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data5List.size();
    }
}

class RabbitViewHolder extends RecyclerView.ViewHolder{

    ImageView recImage5;
    TextView recName4,recArea4,recMoney4;
    CardView recCard5;

    public RabbitViewHolder(@NonNull View itemView) {
        super(itemView);

        recImage5=itemView.findViewById(R.id.recImage2);
        recName4=itemView.findViewById(R.id.recName);
        recArea4=itemView.findViewById(R.id.recArea);
        recMoney4=itemView.findViewById(R.id.recMoney);
        recCard5=itemView.findViewById(R.id.recCard2);
    }
}

