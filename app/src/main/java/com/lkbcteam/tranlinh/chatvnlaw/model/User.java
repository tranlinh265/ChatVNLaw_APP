package com.lkbcteam.tranlinh.chatvnlaw.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by tranlinh on 29/01/2018.
 */
@IgnoreExtraProperties
public class User {
    private String uid;
    private String displayName;
    private String email;
    private boolean isDeleted;
    private String photoURL;
    private String role;
    private String status;
    private String username;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email,String displayName, String photoURL, String role,String status, boolean isDeleted) {
        this.username = username;
        this.email = email;
        this.displayName = displayName;
        this.photoURL = photoURL;
        this.role = role;
        this.isDeleted = isDeleted;
        this.status = status;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
