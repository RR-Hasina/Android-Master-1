package com.tourisme.madatour.model;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
public class Disponibilite implements Serializable{
    @SerializedName("date_debut")
    String date_debut;
    @SerializedName("date_fin")
    String date_fin;
    @SerializedName("disponible")
    int disponible;
    @SerializedName("prix")
    int prix;
    @SerializedName("statut")
    boolean statut;

    public Disponibilite(String date_debut, String date_fin, int disponible, int prix, boolean statut) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.disponible = disponible;
        this.prix = prix;
        this.statut = statut;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }
}
