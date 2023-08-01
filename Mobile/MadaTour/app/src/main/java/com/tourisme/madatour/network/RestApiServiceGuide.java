package com.tourisme.madatour.network;

import com.tourisme.madatour.model.Guide;
import com.tourisme.madatour.response.DestinationResponse;
import com.tourisme.madatour.response.GuideResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApiServiceGuide {

    @GET("guide/allActivites")
    Call<GuideResponse> getActiviteList();

    @GET("guide/allAttractions")
    Call<GuideResponse> getAttractionList();

    @GET("guide/searchActivites")
    Call<GuideResponse> getActiviteListBysearch(@Query("keyWord") String keyword);

    @GET("guide/searchAttractions")
    Call<GuideResponse> getAttractionListBysearch(@Query("keyWord") String keyword);

}
