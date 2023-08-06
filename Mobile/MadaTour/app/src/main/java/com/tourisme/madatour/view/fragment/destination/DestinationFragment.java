package com.tourisme.madatour.view.fragment.destination;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tourisme.madatour.R;
import com.tourisme.madatour.constant.Constant;
import com.tourisme.madatour.databinding.FragmentDashboardBinding;
import com.tourisme.madatour.model.Destination;
import com.tourisme.madatour.view.activity.DetailsDestinationActivity;
import com.tourisme.madatour.view.adapter.DestinationAdapter;

import java.util.ArrayList;
import java.util.List;

public class DestinationFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private RecyclerView recyclerView;
    private ArrayList<Destination> destinationsList;
    private DestinationViewModel destinationViewModel;
    DestinationAdapter destinationAdapter;
    private ProgressBar pgsBar;
    private int page = 1;

    private int pageSearch = 1;
    private boolean isSearch = false;
    private String textSearch = "";

    ProgressBar scrollpBar;
    NestedScrollView nestedScrollView;
    TextView noData;

    boolean state = true;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        destinationViewModel =
                new ViewModelProvider(this).get(DestinationViewModel.class);
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        setHasOptionsMenu(true);
        View root = binding.getRoot();
        nestedScrollView = binding.NestedScroll;
        recyclerView=binding.idDesinationRV;
        pgsBar = binding.pBar;
        scrollpBar = binding.scrollpBar;
        noData = binding.labelPBar;
        nestedScrollView.setOnScrollChangeListener(new
           NestedScrollView.OnScrollChangeListener() {
               @Override
               public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                   if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                       page++;
                       if(isSearch)
                           pageSearch++;
                       scrollpBar.setVisibility(View.VISIBLE);
                       state = true;
                       getDestinationList();
                   }
               }
           });
        state = false;
        getDestinationList();
        return root;
    }

    public void getDestinationList() {
        if(!state && !isSearch){
            destinationViewModel.getDestinationList(page, Constant.LIMITE_DATA_PAGINATION).observe(this, new Observer<List<Destination>>() {
                @Override
                public void onChanged(@Nullable List<Destination> destinations) {
                    if(state){
                        destinationsList.addAll(destinations);
                    }else{
                        destinationsList = new ArrayList<>(destinations);
                    }
                    setRecyclerView(destinations);
                }
            });
        } if(state && !isSearch) {
            destinationViewModel.getDestinationList(page, Constant.LIMITE_DATA_PAGINATION);
        }
        if(state && isSearch) {
            destinationViewModel.getDestinationListBysearch(textSearch,pageSearch,Constant.LIMITE_DATA_PAGINATION);
        }


    }

    private void setRecyclerView(List<Destination> destinationList) {
        if(state){
            destinationAdapter = new DestinationAdapter(this.destinationsList,this.getContext());
        }else{
            destinationAdapter = new DestinationAdapter(destinationList,this.getContext());
        }
        GridLayoutManager layoutManager=new GridLayoutManager(this.getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        destinationAdapter.setOnItemClickListener(new DestinationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Handle the click event for the image at the given position
                Destination clickedDestination = new Destination();
                if(state) clickedDestination = destinationsList.get(position);
                else clickedDestination = destinationList.get(position);
                Intent intent = new Intent(getActivity(), DetailsDestinationActivity.class);
                intent.putExtra("destination", clickedDestination);
                startActivity(intent);
            }
        });
        if(state){
            scrollpBar.setVisibility(View.INVISIBLE);
        }else{
            pgsBar.setVisibility(View.GONE);
            if(!destinationList.isEmpty()) recyclerView.setVisibility(View.VISIBLE);
            else noData.setVisibility(View.VISIBLE);
        }
        recyclerView.setAdapter(destinationAdapter);
        destinationAdapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        SearchView finalSearchView = searchView;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                state = false;
                pageSearch = 1;
                isSearch = true;
                pgsBar.setVisibility(View.VISIBLE);
                if(noData.getVisibility() == View.VISIBLE ) noData.setVisibility(View.GONE);
                query = query.replace("\u200B","");
                textSearch = query;
                recyclerView.setVisibility(View.GONE);
                destinationViewModel.getDestinationListBysearch(query,pageSearch,Constant.LIMITE_DATA_PAGINATION);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // destinationAdapter.getFilter().filter(newText);
                if(newText.isEmpty()){
                    finalSearchView.setQuery("\u200B", false);
                }
                return true;
            }
        });

        super.onCreateOptionsMenu(menu,inflater);
    }
}