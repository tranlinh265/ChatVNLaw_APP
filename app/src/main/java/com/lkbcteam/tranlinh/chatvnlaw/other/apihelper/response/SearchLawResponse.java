package com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tranlinh on 03/05/2018.
 */

public class SearchLawResponse {
    @SerializedName("number_articles")
    @Expose
    private Integer numberArticles;
    @SerializedName("current_page")
    @Expose
    private Integer currentPage;
    @SerializedName("limit_page")
    @Expose
    private Integer limitPage;
    @SerializedName("articles")
    @Expose
    private List<Article> articles = null;

    public Integer getNumberArticles() {
        return numberArticles;
    }

    public void setNumberArticles(Integer numberArticles) {
        this.numberArticles = numberArticles;
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

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public class Article {

        @SerializedName("article_type")
        @Expose
        private String articleType;
        @SerializedName("numerical_symbol")
        @Expose
        private String numericalSymbol;
        @SerializedName("effect_day")
        @Expose
        private String effectDay;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("public_day")
        @Expose
        private String publicDay;
        @SerializedName("effect_status")
        @Expose
        private String effectStatus;

        public String getArticleType() {
            return articleType;
        }

        public void setArticleType(String articleType) {
            this.articleType = articleType;
        }

        public String getNumericalSymbol() {
            return numericalSymbol;
        }

        public void setNumericalSymbol(String numericalSymbol) {
            this.numericalSymbol = numericalSymbol;
        }

        public String getEffectDay() {
            return effectDay;
        }

        public void setEffectDay(String effectDay) {
            this.effectDay = effectDay;
        }

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

        public String getPublicDay() {
            return publicDay;
        }

        public void setPublicDay(String publicDay) {
            this.publicDay = publicDay;
        }

        public String getEffectStatus() {
            return effectStatus;
        }

        public void setEffectStatus(String effectStatus) {
            this.effectStatus = effectStatus;
        }

    }
}
