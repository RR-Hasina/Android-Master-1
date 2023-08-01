package com.tourisme.madatour.view.fragment.profile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tourisme.madatour.model.Client;
import com.tourisme.madatour.repository.ClientRespository;

import java.util.List;

public class ProfileViewModel /*extends AndroidViewModel*/ {

    /*private final MutableLiveData<String> mText;
    private ClientRespository clientRespository;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        clientRespository=new ClientRespository(application);
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<List<Client>> checkClient(Client client){
        return clientRespository.loginClient(client);
    }
    public LiveData<String> getText() {
        return mText;
    }*/
}
