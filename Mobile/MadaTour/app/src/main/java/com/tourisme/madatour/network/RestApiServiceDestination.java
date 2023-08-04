package com.tourisme.madatour.network;

import com.tourisme.madatour.response.DestinationResponse;

import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Query;

public interface RestApiServiceDestination {
    @GET("destination/allDestinations")
    Call<DestinationResponse> getDestinationList(@Query("page") int page,@Query("limite") int limite);
    @GET("destination/searchDestinations")
    Call<DestinationResponse> getDestinationListBysearch(@Query("keyWord") String keyword);
}
