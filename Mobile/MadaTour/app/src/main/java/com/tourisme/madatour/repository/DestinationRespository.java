package com.tourisme.madatour.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.tourisme.madatour.model.Destination;
import com.tourisme.madatour.network.RestApiServiceDestination;
import com.tourisme.madatour.network.RetrofitInstance;
import com.tourisme.madatour.response.DestinationResponse;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DestinationRespository {
    private ArrayList<Destination> destinations = new ArrayList<>();
    private MutableLiveData<List<Destination>> mDestinationList = new MutableLiveData<>();
    private Application application;

    public DestinationRespository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Destination>> getDestinationList() {
        RestApiServiceDestination apiService = RetrofitInstance.getApiService();
        Call<DestinationResponse> call = apiService.getDestinationList();
        call.enqueue(new Callback<DestinationResponse>() {
            @Override
            public void onResponse(Call<DestinationResponse> call, Response<DestinationResponse> response) {

                DestinationResponse destinationWrapper = response.body();
                if (destinationWrapper != null && destinationWrapper.getDestinations() != null) {
                    destinations = (ArrayList<Destination>) destinationWrapper.getDestinations();
                    mDestinationList.setValue(destinations);
                }
            }
            @Override
            public void onFailure(Call<DestinationResponse> call, Throwable t) {
                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mDestinationList;
    }
}
