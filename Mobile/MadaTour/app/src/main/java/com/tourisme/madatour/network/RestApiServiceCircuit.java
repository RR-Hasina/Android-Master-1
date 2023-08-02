package com.tourisme.madatour.network;
import com.tourisme.madatour.model.Client;
import com.tourisme.madatour.response.CircuitResponse;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.POST;
public interface RestApiServiceCircuit {
    @GET("circuits/all")
    Call<CircuitResponse> getCircuitList();
}

