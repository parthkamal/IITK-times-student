package com.parth.iitktimesstudent.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.parth.iitktimesstudent.R;
import com.parth.iitktimesstudent.objects.eventsData;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class EventsAdapter extends FirebaseRecyclerAdapter<eventsData, EventsAdapter.ViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public EventsAdapter(@NonNull @NotNull FirebaseRecyclerOptions<eventsData> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull EventsAdapter.ViewHolder holder, int position, @NonNull @NotNull eventsData model) {
        holder.date.setText(model.getEventDate());
        holder.time.setText(model.getEventTime());
        holder.category.setText(model.getEventsCategory());
        DatabaseReference getImageReference = FirebaseDatabase.getInstance().getReference().child("Events").child(model.getUniqueKey()).child("downloadUrl");
        getImageReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                String link = dataSnapshot.getValue(String.class);
                Picasso.get().load(link).into(holder.imageView);
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(holder.imageView.getContext(), "error loading image",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item,parent,false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView date,time,category;
        private MaterialCardView materialCardView;
        private ImageView imageView;
        public ViewHolder(@NonNull @NotNull View view) {
            super(view);
            this.date = view.findViewById(R.id.events_date);
            this.time = view.findViewById(R.id.events_time);
            this.imageView = view.findViewById(R.id.img_events);
            this.category = view.findViewById(R.id.category);
        }
    }
}
