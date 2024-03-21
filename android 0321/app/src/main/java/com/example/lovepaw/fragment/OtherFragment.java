package com.example.lovepaw.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lovepaw.Other.DataClass6;
import com.example.lovepaw.Other.OtherAdapter;
import com.example.lovepaw.Other.OtherUploadActivity;
import com.example.lovepaw.R;
import com.example.lovepaw.Rabbit.DataClass5;
import com.example.lovepaw.Rabbit.RabbitAdapter;
import com.example.lovepaw.Rabbit.RabbitUploadActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OtherFragment extends Fragment {

    FloatingActionButton fab6;
    RecyclerView recyclerView6;
    List<DataClass6> data6List;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    OtherAdapter otherAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_other, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Find the FloatingActionButton by its ID
        fab6 = view.findViewById(R.id.fab6);
        recyclerView6 = view.findViewById(R.id.recyclerView6);

        // Initialize databaseReference
        databaseReference = FirebaseDatabase.getInstance().getReference("Android other");

        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        recyclerView6.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        data6List = new ArrayList<>();

        otherAdapter = new OtherAdapter(requireContext(), data6List);
        recyclerView6.setAdapter(otherAdapter);

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data6List.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    DataClass6 dataClass6 = itemSnapshot.getValue(DataClass6.class);
                    data6List.add(dataClass6);
                }
                otherAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

        // Check if fab4 is not null before setting OnClickListener
        if (fab6 != null) {
            fab6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(requireContext(), OtherUploadActivity.class);
                    startActivity(intent);
                }
            });

        }
    }

    public void updateRecyclerView(List<DataClass6> newDataList) {
        data6List.clear(); // 清空原始数据
        data6List.addAll(newDataList); // 添加新数据
        otherAdapter.notifyDataSetChanged(); // 刷新适配器
    }
}
