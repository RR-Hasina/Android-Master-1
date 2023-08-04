package com.tourisme.madatour.network;
import com.tourisme.madatour.model.Circuit;
import com.tourisme.madatour.model.Client;
import com.tourisme.madatour.model.Reservation;
import com.tourisme.madatour.response.CircuitResponse;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.POST;
public interface RestApiServiceCircuit {
    @GET("circuit/all")
    Call<CircuitResponse> getCircuitList();

    @POST("circuit/addReservation")
    Call<CircuitResponse> addReservation(@Body Reservation reservation);

    @POST("circuit/deleteReservation")
    Call<CircuitResponse> deleteReservation(@Body Reservation reservation);

    @POST("circuit/checkReservation")
    Call<CircuitResponse> checkReservation(@Body Reservation reservation);
}

