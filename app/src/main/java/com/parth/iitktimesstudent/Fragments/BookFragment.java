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
import com.parth.iitktimesstudent.adapters.BookAdapter;
import com.parth.iitktimesstudent.objects.book;


public class BookFragment extends Fragment {
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private BookAdapter bookAdapter;

    @Override
    public void onStart() {
        super.onStart();
        bookAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        bookAdapter.stopListening();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_book, container, false);
        recyclerView = view.findViewById(R.id.rc_books);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Books");
        FirebaseRecyclerOptions<book> options = new FirebaseRecyclerOptions.Builder<book>()
                .setQuery(databaseReference,book.class)
                .build();
        bookAdapter = new BookAdapter(options);
        recyclerView.setAdapter(bookAdapter);
         return view;
    }
}