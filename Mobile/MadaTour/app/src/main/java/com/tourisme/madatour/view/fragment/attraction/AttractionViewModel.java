package com.tourisme.madatour.view.fragment.attraction;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tourisme.madatour.model.Guide;
import com.tourisme.madatour.repository.GuideRepository;

import java.util.List;

public class AttractionViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private GuideRepository attractionRepository;

    public AttractionViewModel(@NonNull Application application) {
        super(application);
        attractionRepository = new GuideRepository(application);
    }

    public LiveData<List<Guide>> getAttractionList(int page,int limite) {
        return attractionRepository.getAttractionList(page,limite);
    }

    public LiveData<List<Guide>> getAttractionListBysearch(String keyWord,int page,int limite) {
        return attractionRepository.getAttractionListBysearch(keyWord,page,limite);
    }

}