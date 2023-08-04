package com.tourisme.madatour.response;
import com.tourisme.madatour.model.Circuit;
import com.tourisme.madatour.model.Client;
import com.google.gson.annotations.SerializedName;
import com.tourisme.madatour.model.Destination;

import java.util.ArrayList;
import java.util.List;
public class CircuitResponse {
    @SerializedName("circuits")
    private List<Circuit> circuit;

    public List<Circuit> getCircuit(){
        return this.circuit;
    }

    public void setCircuit(List<Circuit> nouveau){this.circuit=nouveau;}




}
