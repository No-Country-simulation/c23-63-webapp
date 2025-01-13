package com.noCountry.social_media_backend.core.domain.model;

public class User {
    private String id;
    private String names;
    private String surnames;

    public User() {
    }

    public User(String id, String names, String surnames) {
        this.id = id;
        this.names = names;
        this.surnames = surnames;
    }

    public User(String names, String surnames) {
        this.names = names;
        this.surnames = surnames;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }
}
