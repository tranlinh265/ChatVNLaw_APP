package com.lkbcteam.tranlinh.chatvnlaw.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tranlinh on 22/02/2018.
 */

public class SearchLawyerResponse {
    @SerializedName("number_lawyers")
    @Expose
    private Integer numberLawyers;
    @SerializedName("current_page")
    @Expose
    private Integer currentPage;
    @SerializedName("limit_page")
    @Expose
    private Integer limitPage;
    @SerializedName("suggest")
    @Expose
    private Boolean suggest;
    @SerializedName("suggest_name")
    @Expose
    @Nullable
    private String suggestName;
    @SerializedName("lawyers")
    @Expose
    @Nullable
    private List<Lawyer> lawyers = null;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getNumberLawyers() {
        return numberLawyers;
    }

    public void setNumberLawyers(Integer numberLawyers) {
        this.numberLawyers = numberLawyers;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getLimitPage() {
        return limitPage;
    }

    public void setLimitPage(Integer limitPage) {
        this.limitPage = limitPage;
    }

    public Boolean getSuggest() {
        return suggest;
    }

    public void setSuggest(Boolean suggest) {
        this.suggest = suggest;
    }

    public String getSuggestName() {
        return suggestName;
    }

    public void setSuggestName(String suggestName) {
        this.suggestName = suggestName;
    }

    public List<Lawyer> getLawyers() {
        return lawyers;
    }

    public void setLawyers(List<Lawyer> lawyers) {
        this.lawyers = lawyers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
