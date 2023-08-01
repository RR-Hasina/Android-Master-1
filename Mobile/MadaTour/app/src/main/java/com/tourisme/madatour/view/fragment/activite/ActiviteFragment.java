package com.tourisme.madatour.view.fragment.activite;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.tourisme.madatour.R;
import com.tourisme.madatour.databinding.FragmentActiviteBinding;
import com.tourisme.madatour.model.Guide;
import com.tourisme.madatour.view.activity.DetailsActiviteActivity;
import com.tourisme.madatour.view.adapter.GuideAdapter;

import java.util.List;

public class ActiviteFragment extends Fragment {

    private ActiviteViewModel mViewModel;

    private FragmentActiviteBinding binding;

    private RecyclerView recyclerView;

    GuideAdapter guideAdapter;
    private ProgressBar pgsBar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel =
                new ViewModelProvider(this).get(ActiviteViewModel.class);
        binding = FragmentActiviteBinding.inflate(inflater, container, false);
        setHasOptionsMenu(true);
        View root = binding.getRoot();
        recyclerView=binding.idActiviteRV;
        pgsBar = binding.pBarA;
        getActiviteList();
        return root;
    }

    public void getActiviteList() {
        mViewModel.getActiviteList().observe(this, new Observer<List<Guide>>() {
            @Override
            public void onChanged(@Nullable List<Guide> attractions) {
                setRecyclerView(attractions);
            }
        });

    }

    private void setRecyclerView(List<Guide> activiteList) {
        guideAdapter = new GuideAdapter(activiteList,this.getContext());
        // setting grid layout manager to implement grid view.
        // in this method '2' represents number of columns to be displayed in grid view.
        GridLayoutManager layoutManager=new GridLayoutManager(this.getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        guideAdapter.setOnItemClickListener(new GuideAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Handle the click event for the image at the given position
                Guide clickedAttraction = activiteList.get(position);
                Log.d("clik"," - > postion    "+ position);
                // Start the DestinationDetailsActivity when an image is clicked
                Intent intent = new Intent(getActivity(), DetailsActiviteActivity.class);
                Log.d("ggbb"," - > postion    "+ clickedAttraction.getDescription());
                intent.putExtra("activite", clickedAttraction); // Pass any data you want to the new activity
                startActivity(intent);
            }
        });
        pgsBar.setVisibility(View.GONE);
        recyclerView.setAdapter(guideAdapter);
        guideAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //guideAdapter.getFilter().filter(newText);
                if(newText.compareTo("") != 0 )  pgsBar.setVisibility(View.VISIBLE);
                mViewModel.getActiviteListBysearch(newText);
                return true;
            }
        });

        super.onCreateOptionsMenu(menu,inflater);
    }
}