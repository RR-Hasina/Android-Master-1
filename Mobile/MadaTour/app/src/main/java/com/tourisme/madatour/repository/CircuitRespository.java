package com.tourisme.madatour.repository;
import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.tourisme.madatour.model.Circuit;
import com.tourisme.madatour.model.Destination;
import com.tourisme.madatour.network.RestApiServiceCircuit;
import com.tourisme.madatour.network.RestApiServiceDestination;
import com.tourisme.madatour.network.RetrofitInstance;
import com.tourisme.madatour.response.CircuitResponse;
import com.tourisme.madatour.response.DestinationResponse;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class CircuitRespository {
    private ArrayList<Circuit> circuits = new ArrayList<>();
    private MutableLiveData<List<Circuit>> mCircuitList = new MutableLiveData<>();
    private Application application;
    public CircuitRespository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Circuit>> getCircuitList() {
        RestApiServiceCircuit apiService = RetrofitInstance.getApiServiceCircuit();
        Call<CircuitResponse> call = apiService.getCircuitList();
        call.enqueue(new Callback<CircuitResponse>() {
            @Override
            public void onResponse(Call<CircuitResponse> call, Response<CircuitResponse> response) {

                CircuitResponse circuitWrapper = response.body();
                if (circuitWrapper != null && circuitWrapper.getCircuit() != null) {
                    circuits = (ArrayList<Circuit>) circuitWrapper.getCircuit();
                    mCircuitList.setValue(circuits);
                }
            }
            @Override
            public void onFailure(Call<CircuitResponse> call, Throwable t) {
                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mCircuitList;
    }

}
