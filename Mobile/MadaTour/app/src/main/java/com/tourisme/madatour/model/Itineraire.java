package com.tourisme.madatour.model;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
public class Itineraire implements Serializable{
    @SerializedName("titre")
    String titre;
    @SerializedName("nbrJours")
    int nbrJours;
    @SerializedName("trajets")
    Trajet trajet;

    public Itineraire(String titre, int nbrJours, Trajet trajet) {
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

    public int getNbrJours() {
        return nbrJours;
    }

    public void setNbrJours(int nbrJours) {
        this.nbrJours = nbrJours;
    }

    public Trajet getTrajet() {
        return trajet;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }
}
