package com.dreamlock.new_message_system.messages;

import com.dreamlock.new_message_system.constants.PrintStyle;

public class EffectSoundMessage extends SoundMessage {
    private String effect;
    private String effectPath;

    public EffectSoundMessage(String name, String description, PrintStyle printStyle, String effect, String effectPath) {
        super(name, description, printStyle);
        this.effect = effect;
        this.effectPath = effectPath;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getEffectPath() {
        return effectPath;
    }

    public void setEffectPath(String effectPath) {
        this.effectPath = effectPath;
    }
}
