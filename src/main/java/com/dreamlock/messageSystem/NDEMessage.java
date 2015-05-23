package com.dreamlock.messageSystem;

import java.io.Serializable;

public class NDEMessage implements Serializable,IMessage {
    private String name;
    private String description;
    private String effect;

    public NDEMessage(String name, String description, String effect) {
        this.name = name;
        this.description = description;
        this.effect = effect;
    }

    public NDEMessage() {
        name = "";
        description = "";
        effect = "";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getEffect() {
        return effect;
    }

    @Override
    public void setEffect(String effect) {
        this.effect = effect;
    }
}
