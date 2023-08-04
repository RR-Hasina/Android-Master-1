package com.tourisme.madatour.network;
import com.tourisme.madatour.model.Client;
import com.tourisme.madatour.response.ClientResponse;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.POST;

public interface RestApiServiceClient {
    @GET("client/all")
    Call<ClientResponse> getClientsList();

    @POST("client/login")
    Call<ClientResponse> clientLogin(@Body Client client);

    @POST("client/inscription")
    Call<ClientResponse> clientInscription(@Body Client client);

}
