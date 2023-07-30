package com.tourisme.madatour.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tourisme.madatour.R;
import com.tourisme.madatour.model.Destination;

import java.util.List;

public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.RecyclerViewHolder> {

    private List<Destination> destinationList;
    private Context mcontext;
    private OnItemClickListener onItemClickListener; // Listener for click events

    // Interface for the click listener
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // Setter method to set the click listener from the Activity/Fragment
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }


    public DestinationAdapter(List<Destination> destinationList, Context mcontext) {
        this.destinationList = destinationList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DestinationAdapter.RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        Destination recyclerData = destinationList.get(position);
        holder.destinationTV.setText(recyclerData.getNom());
        Picasso.get().load(recyclerData.getPhotos().get(0)).into(holder.destinationIV);
    }

    @Override
    public int getItemCount() {
        if (destinationList != null && destinationList.size() > 0) {
            return destinationList.size();
        } else {
            return 0;
        }
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView destinationTV;
        private ImageView destinationIV;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            destinationTV = itemView.findViewById(R.id.idTVdestination);
            destinationIV = itemView.findViewById(R.id.idIVdestination);
            destinationIV.setOnClickListener(this);
        }
        // onClick method for the ImageView click event
        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                // Get the position of the clicked item
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    // Call the onItemClick method of the click listener
                    onItemClickListener.onItemClick(position);
                }
            }
        }
    }

}
