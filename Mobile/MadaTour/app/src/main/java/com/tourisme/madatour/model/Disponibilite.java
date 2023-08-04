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
    String disponible;
    @SerializedName("prix")
    String prix;
    @SerializedName("statut")
    boolean statut;

    public Disponibilite(String date_debut, String date_fin, String disponible, String prix, boolean statut) {
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

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public String getValueStatut(){
        if(this.statut==true){
            return "Actif";
        }
        return "Non actif";
    }
}
