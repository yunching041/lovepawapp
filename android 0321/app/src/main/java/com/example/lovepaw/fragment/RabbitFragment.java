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

import com.example.lovepaw.Mouse.DataClass4;
import com.example.lovepaw.Mouse.MouseAdapter;
import com.example.lovepaw.R;
import com.example.lovepaw.Mouse.mouseUploadActivity;
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


public class RabbitFragment extends Fragment {

    FloatingActionButton fab5;
    RecyclerView recyclerView5;
    List<DataClass5> data5List;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rabbit, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Find the FloatingActionButton by its ID
        fab5 = view.findViewById(R.id.fab5);
        recyclerView5 = view.findViewById(R.id.recyclerView5);

        // Initialize databaseReference
        databaseReference = FirebaseDatabase.getInstance().getReference("Android rabbit");

        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        recyclerView5.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        data5List = new ArrayList<>();

        RabbitAdapter rabbitadapter = new RabbitAdapter(requireContext(), data5List);
        recyclerView5.setAdapter(rabbitadapter);

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data5List.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    DataClass5 dataClass5 = itemSnapshot.getValue(DataClass5.class);
                    data5List.add(dataClass5);
                }
                rabbitadapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

        // Check if fab4 is not null before setting OnClickListener
        if (fab5 != null) {
            fab5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(requireContext(), RabbitUploadActivity.class);
                    startActivity(intent);
                }
            });

        }
    }

}
