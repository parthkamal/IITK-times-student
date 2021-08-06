package com.parth.iitktimesstudent.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.parth.iitktimesstudent.R;
import com.parth.iitktimesstudent.objects.book;
import com.parth.iitktimesstudent.viewPdf;

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
    public BookAdapter(@NonNull @NotNull FirebaseRecyclerOptions<book> options,Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull BookAdapter.ViewHolder holder, int position, @NonNull @NotNull book model) {
        holder.title.setText(model.getTitle());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),model.getTitle(),Toast.LENGTH_SHORT).show();
                CharSequence options[] = new CharSequence[]{
                        "View",
                        "Cancel"
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Choose Your Options");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // we will be downloading the pdf
                        // We will view the pdf
                        if (which == 0) {
                            Toast.makeText(view.getContext(),"Open file",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, viewPdf.class);
                            Log.e("URl",model.getDownloadUrl());
                            intent.putExtra("url",model.getDownloadUrl());
                            context.startActivity(intent);
                        }
                    }
                });
                builder.show();
            }
        });
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
        private CardView cardView;
        public ViewHolder(@NonNull @NotNull View view) {
            super(view);
            this.title = view.findViewById(R.id.book_title);
            this.cardView = view.findViewById(R.id.book_card);
        }
    }

}
