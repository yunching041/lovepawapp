package com.example.lovepaw.Dog;

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

import com.example.lovepaw.DetailActivity;
import com.example.lovepaw.PetdetailActivity;
import com.example.lovepaw.R;
import com.example.lovepaw.fragment.DogFragment;

import java.util.ArrayList;
import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<DogViewHolder> {
    private Context context;
    private List<DataClass2> dataClass2List;
    public DogAdapter(DogFragment fragment,List<DataClass2> dataClass2List) {
        this.context = fragment.requireContext();
        this.dataClass2List = dataClass2List;
    }
    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.petrecycler_item,parent,false);
        return new DogViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {
        Glide.with(context).load(dataClass2List.get(position).getDataImage2()).into(holder.recImage2);
        holder.recNeed.setText(dataClass2List.get(position).getDataDemand());
        holder.recArea.setText(dataClass2List.get(position).getDataArea());
        holder.recMoney.setText(dataClass2List.get(position).getDataMoney());
        holder.recCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PetdetailActivity.class);
                intent.putExtra("Image",dataClass2List.get(holder.getAdapterPosition()).getDataImage2());
                intent.putExtra("Need",dataClass2List.get(holder.getAdapterPosition()).getDataDemand());
                intent.putExtra("Name",dataClass2List.get(holder.getAdapterPosition()).getDataPetname());
                intent.putExtra("Variety",dataClass2List.get(holder.getAdapterPosition()).getDataVariety());
                intent.putExtra("Area",dataClass2List.get(holder.getAdapterPosition()).getDataArea());
                intent.putExtra("Money",dataClass2List.get(holder.getAdapterPosition()).getDataMoney());
                intent.putExtra("Detail",dataClass2List.get(holder.getAdapterPosition()).getDataDetail());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return dataClass2List.size();
    }

    public void searchDataList(ArrayList<DataClass2> searchList){
        dataClass2List = searchList;
        notifyDataSetChanged();
    }
}

class DogViewHolder extends RecyclerView.ViewHolder {

    ImageView recImage2;
    TextView recNeed,recArea,recMoney;
    CardView recCard2;
    public DogViewHolder(@NonNull View itemView) {
        super(itemView);

        recImage2 = itemView.findViewById(R.id.recImage2);
        recCard2 = itemView.findViewById(R.id.recCard2);
        recNeed = itemView.findViewById(R.id.recNeed);
        recArea = itemView.findViewById(R.id.recArea);
        recMoney = itemView.findViewById(R.id.recMoney);
    }
}
