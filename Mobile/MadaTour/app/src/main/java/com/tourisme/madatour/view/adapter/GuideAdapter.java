package com.tourisme.madatour.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tourisme.madatour.R;
import com.tourisme.madatour.model.Destination;
import com.tourisme.madatour.model.Guide;

import java.util.ArrayList;
import java.util.List;

public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.RecyclerViewHolder> implements Filterable {

    private GuideAdapter.OnItemClickListener onItemClickListener;
    private List<Guide> guideList;
    private List<Guide> filteredGuideList;
    private Context mcontext;

    public GuideAdapter(List<Guide> guideList, Context mcontext) {
        this.guideList = guideList;
        this.filteredGuideList = new ArrayList<>(guideList);
        this.mcontext = mcontext;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String searchString = charSequence.toString();

                if (searchString.isEmpty()) {

                    filteredGuideList = guideList;

                } else {

                    ArrayList<Guide> tempFilteredList = new ArrayList<>();

                    for (Guide guide : guideList) {

                        // search for user title
                        if (guide.getNom().toLowerCase().contains(searchString.toLowerCase())) {

                            tempFilteredList.add(guide);
                        }
                    }

                    filteredGuideList = tempFilteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredGuideList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredGuideList = (ArrayList<Guide>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(GuideAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public GuideAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new GuideAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuideAdapter.RecyclerViewHolder holder, int position) {
        Guide recyclerData = filteredGuideList.get(position);
        holder.guideTV.setText(recyclerData.getNom());
        Picasso.get().load(recyclerData.getPhotos().get(0)).into(holder.guideIV);
    }

    @Override
    public int getItemCount() {
        if (filteredGuideList != null && filteredGuideList.size() > 0) {
            return filteredGuideList.size();
        } else {
            return 0;
        }
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView guideTV;
        private ImageView guideIV;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            guideTV = itemView.findViewById(R.id.idTVdestination);
            guideIV = itemView.findViewById(R.id.idIVdestination);
            guideIV.setOnClickListener(this);
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
