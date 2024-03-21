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
import android.widget.EditText;

import com.example.lovepaw.Dog.DataClass2;
import com.example.lovepaw.Dog.DogAdapter;
import com.example.lovepaw.Dog.DogUploadActivity;
import com.example.lovepaw.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DogFragment extends Fragment {

    FloatingActionButton fab2;
    RecyclerView recyclerView2;
    List<DataClass2> dataClass2List;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    DogAdapter dogAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dog, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab2 = requireActivity().findViewById(R.id.fab2);
        recyclerView2 = view.findViewById(R.id.recyclerView2);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        recyclerView2.setLayoutManager(gridLayoutManager);
        dataClass2List = new ArrayList<>();
        dogAdapter = new DogAdapter(DogFragment.this, dataClass2List);
        recyclerView2.setAdapter(dogAdapter);
        databaseReference = FirebaseDatabase.getInstance().getReference("Android dog");
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataClass2List.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    DataClass2 dataClass2 = itemSnapshot.getValue(DataClass2.class);
                    dataClass2List.add(dataClass2);
                }
                dogAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

        fab2 = view.findViewById(R.id.fab2);
        if (fab2 != null) {
            fab2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(requireContext(), DogUploadActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        databaseReference.removeEventListener(eventListener);
    }
}
