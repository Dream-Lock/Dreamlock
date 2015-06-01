package com.dreamlock.core.message_system.messages;

import com.dreamlock.core.message_system.constants.PrintStyle;

public class SoundMessage extends Message {
    protected String titlePath;
    protected String descriptionPath;

    public SoundMessage() {}

    public SoundMessage(String name, String description, PrintStyle printStyle, String titlePath, String descriptionPath) {
        super(name, description, printStyle);
        this.titlePath = titlePath;
        this.descriptionPath = descriptionPath;
    }

    public String getTitlePath() {
        return titlePath;
    }

    public void setTitlePath(String titlePath) {
        this.titlePath = titlePath;
    }

    public String getDescriptionPath() {
        return descriptionPath;
    }

    public void setDescriptionPath(String descriptionPath) {
        this.descriptionPath = descriptionPath;
    }
}
