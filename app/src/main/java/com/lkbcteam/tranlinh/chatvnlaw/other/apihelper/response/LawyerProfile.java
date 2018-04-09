package com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tranlinh on 09/04/2018.
 */

public class LawyerProfile {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("achievement")
    @Expose
    private Object achievement;
    @SerializedName("cardNumber")
    @Expose
    private Object cardNumber;
    @SerializedName("certificate")
    @Expose
    private Object certificate;
    @SerializedName("education")
    @Expose
    private Object education;
    @SerializedName("intro")
    @Expose
    private Object intro;
    @SerializedName("price")
    @Expose
    private Object price;
    @SerializedName("exp")
    @Expose
    private Object exp;
    @SerializedName("rate")
    @Expose
    private Object rate;
    @SerializedName("workPlace")
    @Expose
    private Object workPlace;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getAchievement() {
        return achievement;
    }

    public void setAchievement(Object achievement) {
        this.achievement = achievement;
    }

    public Object getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Object cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Object getCertificate() {
        return certificate;
    }

    public void setCertificate(Object certificate) {
        this.certificate = certificate;
    }

    public Object getEducation() {
        return education;
    }

    public void setEducation(Object education) {
        this.education = education;
    }

    public Object getIntro() {
        return intro;
    }

    public void setIntro(Object intro) {
        this.intro = intro;
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public Object getExp() {
        return exp;
    }

    public void setExp(Object exp) {
        this.exp = exp;
    }

    public Object getRate() {
        return rate;
    }

    public void setRate(Object rate) {
        this.rate = rate;
    }

    public Object getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(Object workPlace) {
        this.workPlace = workPlace;
    }
}
