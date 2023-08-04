package com.tourisme.madatour.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
public class Trajet implements Serializable{
    @SerializedName("lieu_depart")
    String lieu_depart;
    @SerializedName("lieu_arrivee")
    String lieu_arrivee;
    @SerializedName("duree")
    String duree;
    @SerializedName("distance")
    String distance;
    @SerializedName("transport")
    String transport;
    @SerializedName("description")
    String description;

    public Trajet(String lieu_depart, String lieu_arrivee, String duree, String distance, String transport, String description) {
        this.lieu_depart = lieu_depart;
        this.lieu_arrivee = lieu_arrivee;
        this.duree = duree;
        this.distance = distance;
        this.transport = transport;
        this.description = description;
    }

    public String getLieu_depart() {
        return lieu_depart;
    }

    public void setLieu_depart(String lieu_depart) {
        this.lieu_depart = lieu_depart;
    }

    public String getLieu_arrivee() {
        return lieu_arrivee;
    }

    public void setLieu_arrivee(String lieu_arrivee) {
        this.lieu_arrivee = lieu_arrivee;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
