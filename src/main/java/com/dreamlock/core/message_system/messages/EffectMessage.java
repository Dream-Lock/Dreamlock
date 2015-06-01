package com.dreamlock.core.message_system.messages;

import com.dreamlock.core.message_system.constants.PrintStyle;

public class EffectMessage extends Message {
    protected String effect;

    public EffectMessage(String name, String description, String effect) {
        super(name, description, PrintStyle.DEFAULT);
        this.effect = effect;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }
}
