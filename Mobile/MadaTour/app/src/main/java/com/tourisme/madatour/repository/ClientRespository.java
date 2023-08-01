package com.tourisme.madatour.repository;
import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.tourisme.madatour.model.Client;
import com.tourisme.madatour.model.Destination;
import com.tourisme.madatour.network.RestApiServiceClient;
import com.tourisme.madatour.network.RetrofitInstance;
import com.tourisme.madatour.response.ClientResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ClientRespository {
    private ArrayList<Client> client = new ArrayList<>();
    private MutableLiveData<List<Client>> clientLive = new MutableLiveData<>();
    private Application application;


    public MutableLiveData<List<Client>> loginClient(Client verification){
        RestApiServiceClient apiServiceClient = RetrofitInstance.getApiServiceClient();
        Call<ClientResponse> call = apiServiceClient.clientLogin(verification);
        call.enqueue(new Callback<ClientResponse>() {
            @Override
            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                ClientResponse clientWrapper = response.body();
                client= (ArrayList<Client>) clientWrapper.getClient();
                clientLive.setValue(client);
            }

            @Override
            public void onFailure(Call<ClientResponse> call, Throwable t) {
                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        System.out.println(client.get(0).getEmail());
        return clientLive;
    }
}
