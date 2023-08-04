package com.tourisme.madatour.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Reservation implements Serializable {
    @SerializedName("circuitNom")
    private String circuitNom;
    @SerializedName("idClient")
    private String idClient;

    public Reservation(String circuitNom, String idClient) {
        this.circuitNom = circuitNom;
        this.idClient = idClient;
    }
}
