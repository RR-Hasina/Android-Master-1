package com.tourisme.madatour.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
public class Circuit implements Serializable{
    @SerializedName("_id")
    String _id;
    @SerializedName("nom")
    String nom;
    @SerializedName("description")
    Description description;
    @SerializedName("photos")
    List<String> photos;
    @SerializedName("tags")
    List<String> tags;
    @SerializedName("itineraires")
    Itineraire itineraire;
    @SerializedName("disponibilite")
    Disponibilite disponibilite;
    @SerializedName("reservation")
    List<String> listeReservation;

    public Circuit(String _id, String nom, Description description, List<String> photos, List<String> tags, Itineraire itineraire) {
        this._id = _id;
        this.nom = nom;
        this.description = description;
        this.photos = photos;
        this.tags = tags;
        this.itineraire = itineraire;
    }

    public Disponibilite getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Disponibilite disponibilite) {
        this.disponibilite = disponibilite;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Itineraire getItineraire() {
        return itineraire;
    }

    public void setItineraire(Itineraire itineraire) {
        this.itineraire = itineraire;
    }

    public List<String> getListeReservation() {
        return listeReservation;
    }

    public void setListeReservation(List<String> listeReservation) {
        this.listeReservation = listeReservation;
    }
}
