package com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tranlinh on 03/05/2018.
 */

public class ArticleDetailResponse {
    @SerializedName("full_html")
    @Expose
    private String fullHtml;
    @SerializedName("index_html")
    @Expose
    private String indexHtml;
    @SerializedName("detail")
    @Expose
    private Detail detail;

    @SerializedName("neighbors")
    @Expose
    private List<Neighbor> neighbors = null;

    public String getFullHtml() {
        return fullHtml;
    }

    public void setFullHtml(String fullHtml) {
        this.fullHtml = fullHtml;
    }

    public String getIndexHtml() {
        return indexHtml;
    }

    public void setIndexHtml(String indexHtml) {
        this.indexHtml = indexHtml;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public List<Neighbor> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Neighbor> neighbors) {
        this.neighbors = neighbors;
    }

    public class Neighbor {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("numerical_symbol")
        @Expose
        private String numericalSymbol;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNumericalSymbol() {
            return numericalSymbol;
        }

        public void setNumericalSymbol(String numericalSymbol) {
            this.numericalSymbol = numericalSymbol;
        }

    }

    public class Detail {

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("numerical_symbol")
        @Expose
        private String numericalSymbol;
        @SerializedName("public_day")
        @Expose
        private String publicDay;
        @SerializedName("day_report")
        @Expose
        private String dayReport;
        @SerializedName("article_type")
        @Expose
        private String articleType;
        @SerializedName("source")
        @Expose
        private String source;
        @SerializedName("agency_issued")
        @Expose
        private String agencyIssued;
        @SerializedName("the_signer")
        @Expose
        private String theSigner;
        @SerializedName("signer_title")
        @Expose
        private String signerTitle;
        @SerializedName("scope")
        @Expose
        private String scope;
        @SerializedName("effect_day")
        @Expose
        private String effectDay;
        @SerializedName("effect_status")
        @Expose
        private String effectStatus;
        @SerializedName("topics")
        @Expose
        private String topics;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNumericalSymbol() {
            return numericalSymbol;
        }

        public void setNumericalSymbol(String numericalSymbol) {
            this.numericalSymbol = numericalSymbol;
        }

        public String getPublicDay() {
            return publicDay;
        }

        public void setPublicDay(String publicDay) {
            this.publicDay = publicDay;
        }

        public String getDayReport() {
            return dayReport;
        }

        public void setDayReport(String dayReport) {
            this.dayReport = dayReport;
        }

        public String getArticleType() {
            return articleType;
        }

        public void setArticleType(String articleType) {
            this.articleType = articleType;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getAgencyIssued() {
            return agencyIssued;
        }

        public void setAgencyIssued(String agencyIssued) {
            this.agencyIssued = agencyIssued;
        }

        public String getTheSigner() {
            return theSigner;
        }

        public void setTheSigner(String theSigner) {
            this.theSigner = theSigner;
        }

        public String getSignerTitle() {
            return signerTitle;
        }

        public void setSignerTitle(String signerTitle) {
            this.signerTitle = signerTitle;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public String getEffectDay() {
            return effectDay;
        }

        public void setEffectDay(String effectDay) {
            this.effectDay = effectDay;
        }

        public String getEffectStatus() {
            return effectStatus;
        }

        public void setEffectStatus(String effectStatus) {
            this.effectStatus = effectStatus;
        }

        public String getTopics() {
            return topics;
        }

        public void setTopics(String topics) {
            this.topics = topics;
        }
    }
}
