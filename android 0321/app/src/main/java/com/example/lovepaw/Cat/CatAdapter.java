package com.example.lovepaw.Cat;

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

public class CatAdapter extends RecyclerView.Adapter<catViewHolder> {
    private Context context;
    private List<DataClass3> dataClass3List;

    public CatAdapter(Context context, List<DataClass3> dataClass3List) {
        this.context = context;
        this.dataClass3List = dataClass3List;
    }

    @NonNull
    @Override
    public catViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.petrecycler_item,parent,false);
        return new catViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull catViewHolder holder, int position) {
        Glide.with(context).load(dataClass3List.get(position).getDataImage3()).into(holder.recImage2);
        holder.recName.setText(dataClass3List.get(position).getDataname2());
        holder.recArea.setText(dataClass3List.get(position).getDatalocation2());
        holder.recMoney.setText(dataClass3List.get(position).getDatasalary2());

        holder.recCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PetdetailActivity.class);
                intent.putExtra("Image",dataClass3List.get(holder.getAdapterPosition()).getDataImage3());
                intent.putExtra("Need",dataClass3List.get(holder.getAdapterPosition()).getDataDemand2());
                intent.putExtra("Variety",dataClass3List.get(holder.getAdapterPosition()).getDatavariety2());
                intent.putExtra("Name",dataClass3List.get(holder.getAdapterPosition()).getDataname2());
                intent.putExtra("Area",dataClass3List.get(holder.getAdapterPosition()).getDatalocation2());
                intent.putExtra("salary",dataClass3List.get(holder.getAdapterPosition()).getDatasalary2());
                intent.putExtra("detail",dataClass3List.get(holder.getAdapterPosition()).getDatadetail2());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataClass3List.size();
    }
}
    class catViewHolder extends RecyclerView.ViewHolder {
        ImageView recImage2;
        TextView recName, recArea, recMoney;
        CardView recCard2;

        public catViewHolder(@NonNull View itemView) {
            super(itemView);

            recImage2=itemView.findViewById(R.id.recImage2);
            recName=itemView.findViewById(R.id.recName);
            recArea=itemView.findViewById(R.id.recArea);
            recMoney=itemView.findViewById(R.id.recMoney);
            recCard2=itemView.findViewById(R.id.recCard2);
        }
    }
