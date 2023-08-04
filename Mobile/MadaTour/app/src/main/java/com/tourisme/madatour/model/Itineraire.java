package com.tourisme.madatour.model;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
public class Itineraire implements Serializable{
    @SerializedName("titre")
    String titre;
    @SerializedName("nbrJours")
    String nbrJours;
    @SerializedName("trajets")
    List<Trajet> trajet;

    public Itineraire(String titre, String nbrJours, List<Trajet> trajet) {
        this.titre = titre;
        this.nbrJours = nbrJours;
        this.trajet = trajet;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNbrJours() {
        return nbrJours;
    }

    public void setNbrJours(String nbrJours) {
        this.nbrJours = nbrJours;
    }

    public List<Trajet> getTrajet() {
        return trajet;
    }

    public void setTrajet(List<Trajet> trajet) {
        this.trajet = trajet;
    }
}
