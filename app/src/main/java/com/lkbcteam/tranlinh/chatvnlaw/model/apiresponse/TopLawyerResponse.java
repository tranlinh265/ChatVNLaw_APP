package com.lkbcteam.tranlinh.chatvnlaw.model.apiresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lkbcteam.tranlinh.chatvnlaw.model.Lawyer;

import java.util.List;

/**
 * Created by tranlinh on 26/02/2018.
 */

public class TopLawyerResponse {
    @SerializedName("top_lawyers")
    @Expose
    private List<Lawyer> topLawyers = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Lawyer> getTopLawyers() {
        return topLawyers;
    }

    public void setTopLawyers(List<Lawyer> topLawyers) {
        this.topLawyers = topLawyers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

