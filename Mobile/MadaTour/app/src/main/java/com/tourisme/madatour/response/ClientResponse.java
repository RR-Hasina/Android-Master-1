package com.tourisme.madatour.response;

import com.tourisme.madatour.model.Client;
import com.google.gson.annotations.SerializedName;
import com.tourisme.madatour.model.Destination;

import java.util.List;

public class ClientResponse {

    @SerializedName("clients")
    private List<Client>  client;

    /*GET SET ClientResponse*/
    public List<Client> getClient(){
     return this.client;
    }

    public void setClient(List<Client> nouveau){
        this.client=nouveau;
    }



}
