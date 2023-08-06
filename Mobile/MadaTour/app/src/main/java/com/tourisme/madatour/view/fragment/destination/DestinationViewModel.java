package com.tourisme.madatour.view.fragment.destination;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tourisme.madatour.model.Destination;
import com.tourisme.madatour.repository.DestinationRespository;

import java.util.List;

public class DestinationViewModel extends AndroidViewModel {

    private DestinationRespository destinationRepository;

    public DestinationViewModel(@NonNull Application application) {
        super(application);
        destinationRepository = new DestinationRespository(application);
    }
    public LiveData<List<Destination>> getDestinationList(int page,int limite) {
        return destinationRepository.getDestinationList(page,limite);
    }

    public LiveData<List<Destination>> getDestinationListBysearch(String keyWord,int page,int limite) {
        return destinationRepository.getDestinationListBysearch(keyWord,page,limite);
    }
}