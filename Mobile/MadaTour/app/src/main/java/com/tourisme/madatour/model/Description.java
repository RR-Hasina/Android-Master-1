package com.tourisme.madatour.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
public class Description implements Serializable{
    @SerializedName("titre")
    String titre;
    @SerializedName("info")
    String info;

    public Description(String titre, String info) {
        this.titre = titre;
        this.info = info;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
