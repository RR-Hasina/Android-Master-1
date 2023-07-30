package com.tourisme.madatour.view.fragment.dashboard;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tourisme.madatour.model.Destination;
import com.tourisme.madatour.repository.DestinationRespository;

import java.util.List;

public class DashboardViewModel extends AndroidViewModel {

    private DestinationRespository destinationRepository;

    public DashboardViewModel(@NonNull Application application) {
        super(application);
        destinationRepository = new DestinationRespository(application);
    }
    public LiveData<List<Destination>> getDestinationList() {
        return destinationRepository.getDestinationList();
    }
}