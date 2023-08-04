package com.tourisme.madatour.view.fragment.attraction;

import androidx.appcompat.widget.SearchView;
import androidx.core.widget.NestedScrollView;
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
import com.tourisme.madatour.constant.Constant;
import com.tourisme.madatour.databinding.FragmentAttractionBinding;
import com.tourisme.madatour.model.Destination;
import com.tourisme.madatour.model.Guide;
import com.tourisme.madatour.view.activity.DetailsAttractionActivity;
import com.tourisme.madatour.view.activity.DetailsDestinationActivity;
import com.tourisme.madatour.view.adapter.GuideAdapter;

import java.util.List;

public class AttractionFragment extends Fragment {

    private AttractionViewModel mViewModel;
    private FragmentAttractionBinding binding;

    private RecyclerView recyclerView;

    GuideAdapter guideAdapter;
    private ProgressBar pgsBar;
    private int page = 1;

    ProgressBar scrollpBar;
    NestedScrollView nestedScrollView;

    boolean state = true;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel =
                new ViewModelProvider(this).get(AttractionViewModel.class);
        binding = FragmentAttractionBinding.inflate(inflater, container, false);
        setHasOptionsMenu(true);
        View root = binding.getRoot();
        nestedScrollView = binding.NestedScroll;
        recyclerView=binding.idAttractionRV;
        pgsBar = binding.pBarAt;
        scrollpBar = binding.scrollpBar;
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
               @Override
               public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                   if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                       page++;
                       scrollpBar.setVisibility(View.VISIBLE);
                       state = true;
                       getAttractionList();
                   }
               }
           });
        state = false;
        getAttractionList();
        return root;
    }

    public void getAttractionList() {
        if(!state) {
            mViewModel.getAttractionList(page, Constant.LIMITE_DATA_PAGINATION).observe(this, new Observer<List<Guide>>() {
                @Override
                public void onChanged(@Nullable List<Guide> attractions) {
                    setRecyclerView(attractions);
                }
            });
        }else{
            mViewModel.getAttractionList(page, Constant.LIMITE_DATA_PAGINATION);
        }

    }

    private void setRecyclerView(List<Guide> attractionList) {
        guideAdapter = new GuideAdapter(attractionList,this.getContext());
        // setting grid layout manager to implement grid view.
        // in this method '2' represents number of columns to be displayed in grid view.
        GridLayoutManager layoutManager=new GridLayoutManager(this.getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        guideAdapter.setOnItemClickListener(new GuideAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Handle the click event for the image at the given position
                Guide clickedAttraction = attractionList.get(position);
                Log.d("clik"," - > postion    "+ position);
                // Start the DestinationDetailsActivity when an image is clicked
                Intent intent = new Intent(getActivity(), DetailsAttractionActivity.class);
                Log.d("ggbb"," - > postion    "+ clickedAttraction.getPhotos());
                intent.putExtra("attraction", clickedAttraction); // Pass any data you want to the new activity
                startActivity(intent);
            }
        });
        if(state){
            scrollpBar.setVisibility(View.GONE);
        }else{
            pgsBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        recyclerView.setAdapter(guideAdapter);
        guideAdapter.notifyDataSetChanged();
    }

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
                if(newText.compareTo("") != 0 ){
                    state = false;
                    pgsBar.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    mViewModel.getAttractionListBysearch(newText);
                }
                return true;
            }
        });

        super.onCreateOptionsMenu(menu,inflater);
    }


}