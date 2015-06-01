package com.dreamlock.message_system.messages;

import com.dreamlock.message_system.constants.PrintStyle;

public class EffectMessage extends Message {
    protected String effect;

    public EffectMessage(String name, String description, PrintStyle printStyle, String effect) {
        super(name, description, printStyle);
        this.effect = effect;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }
}
