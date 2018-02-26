package com.lkbcteam.tranlinh.chatvnlaw.model.apiresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tranlinh on 26/02/2018.
 */

public class LawyerNameResponse {
    @SerializedName("names")
    @Expose
    private List<Name> names = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Name> getNames() {
        return names;
    }

    public void setNames(List<Name> names) {
        this.names = names;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public class Name {

        @SerializedName("id")
        @Expose
        private Object id;
        @SerializedName("name")
        @Expose
        private String name;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}

