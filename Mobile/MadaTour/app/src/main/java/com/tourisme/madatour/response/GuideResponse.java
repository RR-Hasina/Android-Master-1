package com.tourisme.madatour.response;

import com.google.gson.annotations.SerializedName;
import com.tourisme.madatour.model.Guide;

import java.util.List;

public class GuideResponse {

    @SerializedName("guides")
    private List<Guide> mGuides;

    public List<Guide> getGuides() {
        return mGuides;
    }

    public void setGuides(List<Guide> guides) {
        mGuides = guides;
    }

    @Override
    public String toString() {
        return "GuideResponse{" +
                "mGuides=" + mGuides +
                '}';
    }
}
