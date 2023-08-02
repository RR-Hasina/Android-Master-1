package com.tourisme.madatour.view.fragment.home;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tourisme.madatour.R;

public class Circuit_detailsFragment extends Fragment {

    private CircuitDetailsViewModel mViewModel;

    public static Circuit_detailsFragment newInstance() {
        return new Circuit_detailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_circuit_details, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CircuitDetailsViewModel.class);
        // TODO: Use the ViewModel
    }

}