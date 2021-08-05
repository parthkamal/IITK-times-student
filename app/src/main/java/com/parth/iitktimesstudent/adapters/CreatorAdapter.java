package com.parth.iitktimesstudent.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.parth.iitktimesstudent.objects.Creator;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class CreatorAdapter extends FirebaseRecyclerAdapter<Creator,CreatorAdapter.ViewHolder> {
    private Context context;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public CreatorAdapter(@NonNull @NotNull FirebaseRecyclerOptions<Creator> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull CreatorAdapter.ViewHolder holder, int position, Creator model) {
        Log.e("yha pe","ON BIND VIEW HOLDER");
        DatabaseReference getImageReference = FirebaseDatabase.getInstance().getReference().child("Creators").child(model.getUniquekey()).child("downloadUrl");
        holder.getTvName().setText(model.getName());
        holder.getTvEmail().setText(model.getEmail());
        holder.getTvPost().setText(model.getDesign());
        holder.getTvPhone().setText(model.getPhone());
        getImageReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                String link = dataSnapshot.getValue(String.class);
                Picasso.get().load(link).into(holder.getImageView());
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(holder.getImageView().getContext(), "error loading image",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.creators_items,parent,false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvPost, tvEmail, tvPhone;
        private MaterialCardView materialCardView;
        private CircleImageView imageView;

        public ViewHolder(@NonNull @NotNull View view) {
            super(view);
            this.imageView = view.findViewById(R.id.profile_image);
            this.tvName = view.findViewById(R.id.tvCreatorsName);
            this.tvPost = view.findViewById(R.id.tvCreatorPost);
            this.tvEmail = view.findViewById(R.id.tvCreatorEmail);
            this.tvPhone = view.findViewById(R.id.tvCreatorContacts);
            this.materialCardView = view.findViewById(R.id.itemCard);
        }

        public TextView getTvName() {
            return tvName;
        }

        public void setTvName(TextView tvName) {
            this.tvName = tvName;
        }

        public TextView getTvPost() {
            return tvPost;
        }

        public void setTvPost(TextView tvPost) {
            this.tvPost = tvPost;
        }

        public TextView getTvEmail() {
            return tvEmail;
        }

        public void setTvEmail(TextView tvEmail) {
            this.tvEmail = tvEmail;
        }

        public TextView getTvPhone() {
            return tvPhone;
        }

        public void setTvPhone(TextView tvPhone) {
            this.tvPhone = tvPhone;
        }

        public MaterialCardView getMaterialCardView() {
            return materialCardView;
        }

        public void setMaterialCardView(MaterialCardView materialCardView) {
            this.materialCardView = materialCardView;
        }

        public CircleImageView getImageView() {
            return imageView;
        }

        public void setImageView(CircleImageView imageView) {
            this.imageView = imageView;
        }
    }
}
