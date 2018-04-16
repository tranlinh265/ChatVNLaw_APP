package com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tranlinh on 09/04/2018.
 */

public class ProfileResponse {
    @SerializedName("lawyer_info")
    @Expose
    private LawyerInfo lawyerInfo;

    public LawyerInfo getLawyerInfo() {
        return lawyerInfo;
    }

    public void setLawyerInfo(LawyerInfo lawyerInfo) {
        this.lawyerInfo = lawyerInfo;
    }

    /**
     * Created by tranlinh on 09/04/2018.
     */

    public static class LawyerInfo {
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

    /**
     * Created by tranlinh on 09/04/2018.
     */

    public static class LawyerProfile {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("achievement")
        @Expose
        private String achievement;
        @SerializedName("cardNumber")
        @Expose
        private String cardNumber;
        @SerializedName("certificate")
        @Expose
        private String certificate;
        @SerializedName("education")
        @Expose
        private String education;
        @SerializedName("intro")
        @Expose
        private String intro;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("exp")
        @Expose
        private String exp;
        @SerializedName("rate")
        @Expose
        private String rate;
        @SerializedName("workPlace")
        @Expose
        private String workPlace;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getAchievement() {
            return achievement;
        }

        public void setAchievement(String achievement) {
            this.achievement = achievement;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public String getCertificate() {
            return certificate;
        }

        public void setCertificate(String certificate) {
            this.certificate = certificate;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getExp() {
            return exp;
        }

        public void setExp(String exp) {
            this.exp = exp;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getWorkPlace() {
            return workPlace;
        }

        public void setWorkPlace(String workPlace) {
            this.workPlace = workPlace;
        }
    }

    /**
     * Created by tranlinh on 09/04/2018.
     */

    public static class Specialize {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }
    }

    /**
     * Created by tranlinh on 09/04/2018.
     */

    public static class BaseProfile {
        @SerializedName("userName")
        @Expose
        private String userName;
        @SerializedName("displayName")
        @Expose
        private String displayName;
        @SerializedName("avatar")
        @Expose
        private RoomListResponse.Avatar avatar;
        @SerializedName("birthday")
        @Expose
        private String birthday;

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

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public RoomListResponse.Avatar getAvatar() {
            return avatar;
        }

        public void setAvatar(RoomListResponse.Avatar avatar) {
            this.avatar = avatar;
        }
    }
}
