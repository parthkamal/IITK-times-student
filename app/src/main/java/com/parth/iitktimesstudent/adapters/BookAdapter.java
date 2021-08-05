package com.parth.iitktimesstudent.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.parth.iitktimesstudent.R;
import com.parth.iitktimesstudent.objects.book;

import org.jetbrains.annotations.NotNull;

public class BookAdapter extends FirebaseRecyclerAdapter<book,BookAdapter.ViewHolder> {
    private String message="";
    private Context context;


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public BookAdapter(@NonNull @NotNull FirebaseRecyclerOptions<book> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull BookAdapter.ViewHolder holder, int position, @NonNull @NotNull book model) {
        holder.title.setText(model.getTitle());
        // Initialising the reference to database

    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item,parent,false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        public ViewHolder(@NonNull @NotNull View view) {
            super(view);
            this.title = view.findViewById(R.id.book_title);
        }
    }

}
