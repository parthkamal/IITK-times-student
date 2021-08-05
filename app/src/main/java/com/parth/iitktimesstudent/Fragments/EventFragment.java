package com.parth.iitktimesstudent.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.parth.iitktimesstudent.R;
import com.parth.iitktimesstudent.adapters.EventsAdapter;
import com.parth.iitktimesstudent.objects.eventsData;

import org.jetbrains.annotations.NotNull;


public class EventFragment extends Fragment {
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private EventsAdapter eventsAdapter;

    @Override
    public void onStart() {
        super.onStart();
        eventsAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        eventsAdapter.stopListening();
    }
    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_event, container, false);
        recyclerView = view.findViewById(R.id.rv_events);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Events");
        FirebaseRecyclerOptions<eventsData> options = new FirebaseRecyclerOptions.Builder<eventsData>()
                .setQuery(databaseReference,eventsData.class)
                .build();
        eventsAdapter = new EventsAdapter(options);
        recyclerView.setAdapter(eventsAdapter);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.drawer_view,menu);
    }


}