package com.dreamlock.models;

/**
 * Created by Odin on 26/4/2015.
 */
public class Word {
    String description;
    String type;

    public Word(String description, String type) {
        this.description = description;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
