package com.dreamlock.new_message_system.messages;

import com.dreamlock.new_message_system.constants.PrintStyle;

public class Message implements IMessage {
    protected String name;
    protected String description;
    protected PrintStyle printStyle;

    public Message() {}

    public Message(String name, String description) {
        this.name = name;
        this.description = description;
        this.printStyle = PrintStyle.ONLY_TITLE;
    }

    public Message(String name, String description, PrintStyle printStyle) {
        this.name = name;
        this.description = description;
        this.printStyle = printStyle;
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

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }
}
