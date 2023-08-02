package com.tourisme.madatour.view.fragment.activite;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tourisme.madatour.model.Destination;
import com.tourisme.madatour.model.Guide;
import com.tourisme.madatour.repository.GuideRepository;

import java.util.List;


public class ActiviteViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private GuideRepository activiteRepository;

    public ActiviteViewModel(@NonNull Application application) {
        super(application);
        activiteRepository = new GuideRepository(application);
    }

    public LiveData<List<Guide>> getActiviteList() {
        return activiteRepository.getActiviteList();
    }

    public LiveData<List<Guide>> getActiviteListBysearch(String keyWord) {
        return activiteRepository.getActiviteListBysearch(keyWord);
    }

}