package com.tourisme.madatour.network;

import com.tourisme.madatour.response.DestinationResponse;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.Call;

public interface RestApiServiceDestination {

    @GET("destination/allDestinations")
    Call<DestinationResponse> getDestinationList();
}
