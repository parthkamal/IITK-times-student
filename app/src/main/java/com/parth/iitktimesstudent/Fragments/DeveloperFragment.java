package com.parth.iitktimesstudent.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.parth.iitktimesstudent.R;
import com.parth.iitktimesstudent.adapters.CreatorAdapter;
import com.parth.iitktimesstudent.objects.Creator;


public class DeveloperFragment extends Fragment {
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private CreatorAdapter creatorAdapter;


    @Override
    public void onStart() {
        super.onStart();
        creatorAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        creatorAdapter.stopListening();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_developer,container,false);
        recyclerView = view.findViewById(R.id.rv_creators);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Creators");
        FirebaseRecyclerOptions<Creator> options
                = new FirebaseRecyclerOptions.Builder<Creator>()
                .setQuery(databaseReference,Creator.class)
                .build();
        creatorAdapter = new CreatorAdapter(options);
        recyclerView.setAdapter(creatorAdapter);
        // Inflate the layout for this fragment
        return view;
    }
}