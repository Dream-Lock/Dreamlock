package com.dreamlock.message_system.messages;

import com.dreamlock.message_system.constants.PrintStyle;

public interface IMessage {

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    PrintStyle getPrintStyle();

    void setPrintStyle(PrintStyle printStyle);
}
