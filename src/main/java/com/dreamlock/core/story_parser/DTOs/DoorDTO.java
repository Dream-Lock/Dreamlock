package com.dreamlock.core.story_parser.DTOs;

public class DoorDTO {
    private String name;
    private String direction;
    private String path;
    private int id;
    private int requiredKey;


    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getRequiredKey() {
        return requiredKey;
    }

    public String getDirection() {
        return direction.toLowerCase();
    }

    public String getPath() {
        return path;
    }
}
