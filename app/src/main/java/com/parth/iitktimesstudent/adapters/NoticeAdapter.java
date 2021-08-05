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
import com.parth.iitktimesstudent.objects.NoticeData;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class NoticeAdapter extends FirebaseRecyclerAdapter<NoticeData,NoticeAdapter.ViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public NoticeAdapter(@NonNull @NotNull FirebaseRecyclerOptions<NoticeData> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull NoticeAdapter.ViewHolder holder, int position, @NonNull @NotNull NoticeData model) {
        holder.date.setText(model.getNoticeDate());
        holder.time.setText(model.getNoticeTime());
        holder.desc.setText(model.getNoticeDescription());
        holder.title.setText(model.getNoticeTitle());
        DatabaseReference getImageReference = FirebaseDatabase.getInstance().getReference().child("Notice").child(model.getUniqueKey()).child("downloadUrl");
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_item,parent,false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView date, title, time, desc;
        private MaterialCardView materialCardView;
        private ImageView imageView;

        public ViewHolder(@NonNull @NotNull View view) {
            super(view);
            this.imageView = view.findViewById(R.id.img_notice);
            this.date = view.findViewById(R.id.date);
            this.title = view.findViewById(R.id.notice_title);
            this.time = view.findViewById(R.id.time);
            this.desc = view.findViewById(R.id.notice_desc);
            this.materialCardView = view.findViewById(R.id.itemCard);
        }
    }
}
