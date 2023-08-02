package com.tourisme.madatour.view.fragment.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.tourisme.madatour.R;
import com.tourisme.madatour.databinding.FragmentAttractionBinding;
import com.tourisme.madatour.databinding.FragmentHomeBinding;
import com.tourisme.madatour.model.Circuit;
import com.tourisme.madatour.model.Guide;
import com.tourisme.madatour.view.adapter.GuideAdapter;
import com.tourisme.madatour.view.fragment.attraction.AttractionViewModel;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private RecyclerView recyclerView;
    GuideAdapter guideAdapter;
    private ProgressBar pgsBar;
    private FragmentHomeBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        setHasOptionsMenu(true);
        View root = binding.getRoot();
        recyclerView=binding.idCircuitRV;
        pgsBar = binding.pBarCi;
        getCircuitList();
        return root;
    }

    public void getCircuitList() {


    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}