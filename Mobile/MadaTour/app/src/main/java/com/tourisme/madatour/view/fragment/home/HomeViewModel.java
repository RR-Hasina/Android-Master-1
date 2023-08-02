package com.tourisme.madatour.view.fragment.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tourisme.madatour.model.Circuit;
import com.tourisme.madatour.repository.CircuitRespository;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private CircuitRespository circuitRespository;
    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public MutableLiveData<List<Circuit>> getCircuitList() {
        return circuitRespository.getCircuitList();
    }

    public LiveData<String> getText() {
        return mText;
    }
}