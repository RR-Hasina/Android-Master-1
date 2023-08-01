package com.tourisme.madatour.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Guide implements Serializable {

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
    @SerializedName("type")
    private String mType;

    public Carte getCarte() {
        return mCarte;
    }

    public void setCarte(Carte carte) {
        mCarte = carte;
    }

    public String getNom() {
        return mNom;
    }

    public void setNom(String nom) {
        mNom = nom;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public List<String> getPhotos() {
        return mPhotos;
    }

    public void setPhotos(List<String> photos) {
        mPhotos = photos;
    }

    public List<String> getVideo() {
        return mVideo;
    }

    public void setVideo(List<String> video) {
        mVideo = video;
    }

    public List<String> getTags() {
        return mTags;
    }

    public void setTags(List<String> tags) {
        mTags = tags;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    @Override
    public String toString() {
        return "Guide{" +
                "mCarte=" + mCarte +
                ", mNom='" + mNom + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mPhotos=" + mPhotos +
                ", mVideo=" + mVideo +
                ", mTags=" + mTags +
                ", mType='" + mType + '\'' +
                '}';
    }
}
