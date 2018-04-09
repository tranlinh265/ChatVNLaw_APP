package com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tranlinh on 09/04/2018.
 */

public class LawyerInfo {
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("base_profile")
    @Expose
    private BaseProfile baseProfile;
    @SerializedName("lawyer_profile")
    @Expose
    private LawyerProfile lawyerProfile;
    @SerializedName("specializes")
    @Expose
    private List<Specialize> specializes = null;
    @SerializedName("status")
    @Expose
    private String status;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BaseProfile getBaseProfile() {
        return baseProfile;
    }

    public void setBaseProfile(BaseProfile baseProfile) {
        this.baseProfile = baseProfile;
    }

    public LawyerProfile getLawyerProfile() {
        return lawyerProfile;
    }

    public void setLawyerProfile(LawyerProfile lawyerProfile) {
        this.lawyerProfile = lawyerProfile;
    }

    public List<Specialize> getSpecializes() {
        return specializes;
    }

    public void setSpecializes(List<Specialize> specializes) {
        this.specializes = specializes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
