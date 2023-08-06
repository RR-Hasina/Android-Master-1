package com.tourisme.madatour.network;

import com.tourisme.madatour.model.Guide;
import com.tourisme.madatour.response.DestinationResponse;
import com.tourisme.madatour.response.GuideResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApiServiceGuide {

    @GET("guide/allActivites")
    Call<GuideResponse> getActiviteList(@Query("page") int page,@Query("limite") int limite);

    @GET("guide/allAttractions")
    Call<GuideResponse> getAttractionList(@Query("page") int page,@Query("limite") int limite);

    @GET("guide/searchActivites")
    Call<GuideResponse> getActiviteListBysearch(@Query("keyWord") String keyword,@Query("page") int page,@Query("limite") int limite);

    @GET("guide/searchAttractions")
    Call<GuideResponse> getAttractionListBysearch(@Query("keyWord") String keyword,@Query("page") int page,@Query("limite") int limite);

}
