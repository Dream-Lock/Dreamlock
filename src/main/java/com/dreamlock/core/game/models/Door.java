package com.dreamlock.core.game.models;

import java.io.Serializable;

public class Door implements Serializable {
    private String name;
    private String direction;
    private String description;
    private String type;
    private int id;
    private int requiredKey;
    private boolean locked;

    public Door(String name, String direction, int id, int requiredKey) {
        this.name = name;
        this.direction = direction;
        this.id = id;
        this.locked = true;
        this.requiredKey = requiredKey;
        this.description = (direction + "ern" + " " + name.toLowerCase());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String description) {
        this.direction = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLocked() {
        return locked;
    }

    public int getRequiredKey() {
        return requiredKey;
    }

    public void setRequiredKey(int requiredKey) {
        this.requiredKey = requiredKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void unlock () {
        locked = false;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
