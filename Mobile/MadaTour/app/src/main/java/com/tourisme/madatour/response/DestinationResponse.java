package com.tourisme.madatour.response;

import com.google.gson.annotations.SerializedName;
import com.tourisme.madatour.model.Destination;

import java.util.List;

public class DestinationResponse {

    @SerializedName("destinations")
    private List<Destination> mDestinations;

    public List<Destination> getDestinations() {
        return mDestinations;
    }

    public void setDestinations(List<Destination> destinations) {
        mDestinations = destinations;
    }

    @Override
    public String toString() {
        return "DestinationResponse{" +
                "mDestinations=" + mDestinations +
                '}';
    }
}
