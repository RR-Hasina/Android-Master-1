package com.tourisme.madatour.view.fragment.activite;

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
import android.widget.TextView;

import com.tourisme.madatour.R;
import com.tourisme.madatour.constant.Constant;
import com.tourisme.madatour.databinding.FragmentActiviteBinding;
import com.tourisme.madatour.model.Guide;
import com.tourisme.madatour.view.activity.DetailsActiviteActivity;
import com.tourisme.madatour.view.adapter.GuideAdapter;

import java.util.ArrayList;
import java.util.List;

public class ActiviteFragment extends Fragment {

    private ActiviteViewModel mViewModel;

    private FragmentActiviteBinding binding;

    private RecyclerView recyclerView;

    GuideAdapter guideAdapter;
    private ProgressBar pgsBar;
    private int page = 1;

    ProgressBar scrollpBar;
    NestedScrollView nestedScrollView;
    private int pageSearch = 1;
    private boolean isSearch = false;
    private String textSearch = "";
    private ArrayList<Guide> activitesList;
    boolean state = true;

    TextView noData;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel =
                new ViewModelProvider(this).get(ActiviteViewModel.class);
        binding = FragmentActiviteBinding.inflate(inflater, container, false);
        setHasOptionsMenu(true);
        View root = binding.getRoot();
        nestedScrollView = binding.NestedScroll;
        recyclerView=binding.idActiviteRV;
        pgsBar = binding.pBarA;
        scrollpBar = binding.scrollpBar;
        noData = binding.labelPBar;
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
           @Override
           public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
               if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                   page++;
                   if(isSearch)
                       pageSearch++;
                   scrollpBar.setVisibility(View.VISIBLE);
                   state = true;
                   getActiviteList();
               }
           }
       });
        state = false;
        getActiviteList();
        return root;
    }

    public void getActiviteList() {
        if(!state  && !isSearch) {
            mViewModel.getActiviteList(page, Constant.LIMITE_DATA_PAGINATION).observe(this, new Observer<List<Guide>>() {
                @Override
                public void onChanged(@Nullable List<Guide> attractions) {
                    if(state){
                        activitesList.addAll(attractions);
                    }else{
                        activitesList = new ArrayList<>(attractions);
                    }
                    setRecyclerView(attractions);
                }
            });
        } if(state && !isSearch){
            mViewModel.getActiviteList(page, Constant.LIMITE_DATA_PAGINATION);
        }
        if(state && isSearch) {
            mViewModel.getActiviteListBysearch(textSearch,pageSearch,Constant.LIMITE_DATA_PAGINATION);
        }

    }

    private void setRecyclerView(List<Guide> activiteList) {
        if(state){
            guideAdapter = new GuideAdapter(activitesList,this.getContext());
        }else{
            guideAdapter = new GuideAdapter(activiteList,this.getContext());
        }
        GridLayoutManager layoutManager=new GridLayoutManager(this.getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        guideAdapter.setOnItemClickListener(new GuideAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Handle the click event for the image at the given position
                Guide clickedActivite = new Guide();
                if(state)  clickedActivite = activitesList.get(position);
                else clickedActivite = activiteList.get(position);
                Intent intent = new Intent(getActivity(), DetailsActiviteActivity.class);
                intent.putExtra("activite", clickedActivite);
                startActivity(intent);
            }
        });
        if(state){
            scrollpBar.setVisibility(View.INVISIBLE);
        }else{
            pgsBar.setVisibility(View.GONE);
            if(!activiteList.isEmpty()) recyclerView.setVisibility(View.VISIBLE);
            else noData.setVisibility(View.VISIBLE);
        }
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
                mViewModel.getActiviteListBysearch(query,pageSearch,Constant.LIMITE_DATA_PAGINATION);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //guideAdapter.getFilter().filter(newText);
                if(newText.isEmpty()){
//                    if(activitesList != null ) activitesList.clear();
                    finalSearchView.setQuery("\u200B", false);
                }
                return true;
            }
        });

        super.onCreateOptionsMenu(menu,inflater);
    }
}