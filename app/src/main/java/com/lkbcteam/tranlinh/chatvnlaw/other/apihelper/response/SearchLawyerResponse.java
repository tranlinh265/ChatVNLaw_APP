package com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.RoomListResponse;

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

    public class Lawyer {

        @SerializedName("intro")
        @Expose
        private String intro;
        @SerializedName("price")
        @Expose
        private Integer price;
        @SerializedName("rate")
        @Expose
        private Integer rate;
        @SerializedName("specializations")
        @Expose
        private List<Specialization> specializations = null;
        @SerializedName("profile")
        @Expose
        private Profile profile;

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        public Integer getRate() {
            return rate;
        }

        public void setRate(Integer rate) {
            this.rate = rate;
        }

        public List<Specialization> getSpecializations() {
            return specializations;
        }

        public void setSpecializations(List<Specialization> specializations) {
            this.specializations = specializations;
        }

        public Profile getProfile() {
            return profile;
        }

        public void setProfile(Profile profile) {
            this.profile = profile;
        }

    }

    public class Profile {

        @SerializedName("userName")
        @Expose
        private String userName;
        @SerializedName("displayName")
        @Expose
        private String displayName;
        @SerializedName("avatar")
        @Expose
        private RoomListResponse.Avatar avatar;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public RoomListResponse.Avatar getAvatar() {
            return avatar;
        }

        public void setAvatar(RoomListResponse.Avatar avatar) {
            this.avatar = avatar;
        }

    }

    public class Specialization {

        @SerializedName("name")
        @Expose
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }


}
