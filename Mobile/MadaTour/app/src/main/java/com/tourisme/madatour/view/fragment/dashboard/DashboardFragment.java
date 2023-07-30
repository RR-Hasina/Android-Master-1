package com.tourisme.madatour.view.fragment.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tourisme.madatour.databinding.FragmentDashboardBinding;
import com.tourisme.madatour.model.Destination;
import com.tourisme.madatour.view.activity.DetailsDestinationActivity;
import com.tourisme.madatour.view.activity.SettingsActivity;
import com.tourisme.madatour.view.adapter.DestinationAdapter;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private RecyclerView recyclerView;
    private ArrayList<Destination> destinationList;
    private DashboardViewModel dashboardViewModel;
    DestinationAdapter destinationAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView=binding.idDesinationRV;
        getDestinationList();
        return root;
    }

    public void getDestinationList() {
        dashboardViewModel.getDestinationList().observe(this, new Observer<List<Destination>>() {
            @Override
            public void onChanged(@Nullable List<Destination> destinations) {
                setRecyclerView(destinations);
            }
        });

    }

    private void setRecyclerView(List<Destination> destinationList) {
        destinationAdapter = new DestinationAdapter(destinationList,this.getContext());
        // setting grid layout manager to implement grid view.
        // in this method '2' represents number of columns to be displayed in grid view.
        GridLayoutManager layoutManager=new GridLayoutManager(this.getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        destinationAdapter.setOnItemClickListener(new DestinationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Handle the click event for the image at the given position
                Destination clickedDestination = destinationList.get(position);
                Log.d("clik"," - > postion    "+ position);
                // Start the DestinationDetailsActivity when an image is clicked
                Intent intent = new Intent(getActivity(), DetailsDestinationActivity.class);
                Log.d("ggbb"," - > postion    "+ clickedDestination.getDescription());
                intent.putExtra("destination", clickedDestination); // Pass any data you want to the new activity
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(destinationAdapter);
        destinationAdapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}