package com.tourisme.madatour.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unused")
public class Destination implements Serializable {
    @SerializedName("carte")
    private Carte mCarte;
    @SerializedName("nom")
    private String mNom;
    @SerializedName("descriptions")
    private String mDescription;
    @SerializedName("photos")
    private List<String> mPhotos;
    @SerializedName("video")
    private List<String> mVideo;
    @SerializedName("tags")
    private List<String> mTags;

    public Carte getCarte() {
        return mCarte;
    }

    public void setCarte(Carte mCarte) {
        this.mCarte = mCarte;
    }

    public String getNom() {
        return mNom;
    }

    public void setNom(String mNom) {
        this.mNom = mNom;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public List<String> getPhotos() {
        return mPhotos;
    }

    public void setPhotos(List<String> mPhotos) {
        this.mPhotos = mPhotos;
    }

    public List<String> getVideo() {
        return mVideo;
    }

    public void setVideo(List<String> mVideo) {
        this.mVideo = mVideo;
    }

    public List<String> getTags() {
        return mTags;
    }

    public void setTags(List<String> mTags) {
        this.mTags = mTags;
    }

    @Override
    public String toString() {
        return "Destination{" +
                "mCarte=" + mCarte +
                ", mNom=" + mNom +
                ", mDescription='" + mDescription + '\'' +
                ", mPhotos=" + mPhotos +
                ", mVideo=" + mVideo +
                ", mTags=" + mTags +
                '}';
    }
}
