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
import com.parth.iitktimesstudent.adapters.NoticeAdapter;
import com.parth.iitktimesstudent.objects.NoticeData;


public class NoticeFragment extends Fragment {
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private NoticeAdapter noticeAdapter;
    @Override
    public void onStart() {
        super.onStart();
        noticeAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        noticeAdapter.stopListening();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice,container,false);
        recyclerView = view.findViewById(R.id.rv_notice);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Notice");
        FirebaseRecyclerOptions<NoticeData> options
                = new FirebaseRecyclerOptions.Builder<NoticeData>()
                .setQuery(databaseReference,NoticeData.class)
                .build();
        noticeAdapter= new NoticeAdapter(options);
        recyclerView.setAdapter(noticeAdapter);
        // Inflate the layout for this fragment
        return view;
    }
}