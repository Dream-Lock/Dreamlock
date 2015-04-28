package com.dreamlock;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum CommandMessages {

    INSTANCE;

    CommandMessages() {
        commandMessages = new HashMap<>();

        commandMessages.put(0, "");
        // Go messages

    }

    private final Map<Integer, String> commandMessages;

    public String getCommandMessage(Integer messageId) {
        return this.commandMessages.get(messageId);
    }

    public Set<Integer> getDefinedCommandMessages() {
        return this.commandMessages.keySet();
    }
}
