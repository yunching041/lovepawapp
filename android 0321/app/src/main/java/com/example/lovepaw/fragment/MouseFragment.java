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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MouseFragment extends Fragment {

    FloatingActionButton fab4;
    RecyclerView recyclerView4;
    List<DataClass4> data4List;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mouse, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Find the FloatingActionButton by its ID
        fab4 = view.findViewById(R.id.fab4);
        recyclerView4 = view.findViewById(R.id.recyclerView4);

        // Initialize databaseReference
        databaseReference = FirebaseDatabase.getInstance().getReference("Android mouse");

        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        recyclerView4.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        data4List = new ArrayList<>();

        MouseAdapter adapter = new MouseAdapter(requireContext(), data4List);
        recyclerView4.setAdapter(adapter);

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data4List.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    DataClass4 dataClass4 = itemSnapshot.getValue(DataClass4.class);
                    data4List.add(dataClass4);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

        // Check if fab4 is not null before setting OnClickListener
        if (fab4 != null) {
            fab4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(requireContext(), mouseUploadActivity.class);
                    startActivity(intent);
                }
            });

        }
    }

}
