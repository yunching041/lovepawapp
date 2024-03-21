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

import com.example.lovepaw.Cat.CatAdapter;
import com.example.lovepaw.Cat.CatUploadActivity;
import com.example.lovepaw.Cat.DataClass3;
import com.example.lovepaw.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CatFragment extends Fragment {
    FloatingActionButton fab3;
    RecyclerView recyclerView3;
    List<DataClass3> dataClass3List;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    CatAdapter catAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab3 = requireActivity().findViewById(R.id.fab3);
        recyclerView3 = view.findViewById(R.id.recyclerView3);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        recyclerView3.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataClass3List = new ArrayList<>();
        catAdapter = new CatAdapter(requireContext(), dataClass3List);
        recyclerView3.setAdapter(catAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Android cat");
        dialog.show();



        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataClass3List.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    DataClass3 dataClass3 = itemSnapshot.getValue(DataClass3.class);
                    dataClass3List.add(dataClass3);
                }
                catAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

        fab3 = view.findViewById(R.id.fab3);
        if (fab3 != null) {
            fab3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(requireContext(),CatUploadActivity.class);
                    startActivity(intent);
                }
            });
        }
    }


}
