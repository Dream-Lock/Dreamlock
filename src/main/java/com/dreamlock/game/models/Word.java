package com.dreamlock.game.models;

public class Word {
    private String description;
    private String type;
    private Integer id;

    public Word(String description, String type, Integer id) {
        this.description = description;
        this.type = type;
        this.id = id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
