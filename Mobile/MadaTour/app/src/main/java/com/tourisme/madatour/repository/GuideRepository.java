package com.tourisme.madatour.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.tourisme.madatour.model.Destination;
import com.tourisme.madatour.model.Guide;
import com.tourisme.madatour.network.RestApiServiceDestination;
import com.tourisme.madatour.network.RestApiServiceGuide;
import com.tourisme.madatour.network.RetrofitInstance;
import com.tourisme.madatour.response.DestinationResponse;
import com.tourisme.madatour.response.GuideResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuideRepository {

    private ArrayList<Guide> activites = new ArrayList<>();
    private ArrayList<Guide> attractions = new ArrayList<>();
    private MutableLiveData<List<Guide>> mActiviteList = new MutableLiveData<>();
    private MutableLiveData<List<Guide>> mAttractionList = new MutableLiveData<>();
    private Application application;

    public GuideRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Guide>> getActiviteList(int page,int limite) {
        RestApiServiceGuide apiService = RetrofitInstance.getApiServiceGuide();
        Call<GuideResponse> call = apiService.getActiviteList(page,limite);
        call.enqueue(new Callback<GuideResponse>() {
            @Override
            public void onResponse(Call<GuideResponse> call, Response<GuideResponse> response) {

                GuideResponse attractionWrapper = response.body();
                if (attractionWrapper != null && attractionWrapper.getGuides() != null) {
                    activites = (ArrayList<Guide>) attractionWrapper.getGuides();
                    mActiviteList.setValue(activites);
                }
            }
            @Override
            public void onFailure(Call<GuideResponse> call, Throwable t) {
                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mActiviteList;
    }

    public MutableLiveData<List<Guide>> getAttractionList(int page,int limite) {
        RestApiServiceGuide apiService = RetrofitInstance.getApiServiceGuide();
        Call<GuideResponse> call = apiService.getAttractionList(page,limite);
        call.enqueue(new Callback<GuideResponse>() {
            @Override
            public void onResponse(Call<GuideResponse> call, Response<GuideResponse> response) {

                GuideResponse attractionWrapper = response.body();
                if (attractionWrapper != null && attractionWrapper.getGuides() != null) {
                    attractions = (ArrayList<Guide>) attractionWrapper.getGuides();
                    mAttractionList.setValue(attractions);
                }
            }
            @Override
            public void onFailure(Call<GuideResponse> call, Throwable t) {
                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mAttractionList;
    }

    public MutableLiveData<List<Guide>> getActiviteListBysearch(String keyWord,int page,int limite) {
        RestApiServiceGuide apiService = RetrofitInstance.getApiServiceGuide();
        Call<GuideResponse> call = apiService.getActiviteListBysearch(keyWord,page,limite);
        call.enqueue(new Callback<GuideResponse>() {
            @Override
            public void onResponse(Call<GuideResponse> call, Response<GuideResponse> response) {

                GuideResponse attractionWrapper = response.body();
                if (attractionWrapper != null && attractionWrapper.getGuides() != null) {
                    activites = (ArrayList<Guide>) attractionWrapper.getGuides();
                    mActiviteList.setValue(activites);
                }
            }
            @Override
            public void onFailure(Call<GuideResponse> call, Throwable t) {
                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mActiviteList;
    }

    public MutableLiveData<List<Guide>> getAttractionListBysearch(String keyWord,int page,int limite) {
        RestApiServiceGuide apiService = RetrofitInstance.getApiServiceGuide();
        Call<GuideResponse> call = apiService.getAttractionListBysearch(keyWord,page,limite);
        call.enqueue(new Callback<GuideResponse>() {
            @Override
            public void onResponse(Call<GuideResponse> call, Response<GuideResponse> response) {

                GuideResponse attractionWrapper = response.body();
                if (attractionWrapper != null && attractionWrapper.getGuides() != null) {
                    attractions = (ArrayList<Guide>) attractionWrapper.getGuides();
                    mAttractionList.setValue(attractions);
                }
            }
            @Override
            public void onFailure(Call<GuideResponse> call, Throwable t) {
                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mAttractionList;
    }
}
