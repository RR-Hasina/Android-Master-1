package com.tourisme.madatour.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class Client implements Serializable{

    @SerializedName("_id")
    private String _id;
    @SerializedName("nom")
    private String nom;
    @SerializedName("prenom")
    private String prenom;
    @SerializedName("email")
    private String email;
    @SerializedName("mdp")
    private String mdp;
    @SerializedName("telephone")
    private String telephone;

    public Client(){}
    public Client(String email, String mdp) {
        this.email = email;
        this.mdp = mdp;
    }

    public Client(String id, String nom, String prenom, String email, String mdp, String telephone) {
        this._id=id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.telephone = telephone;
    }

    public String getId() {return _id;}
    public void setId(String _id){ this._id=_id;}
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
