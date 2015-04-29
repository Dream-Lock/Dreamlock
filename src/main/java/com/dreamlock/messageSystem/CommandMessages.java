package com.dreamlock.messageSystem;

import com.dreamlock.game.models.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum CommandMessages {

    INSTANCE;

    CommandMessages() {
        commandMessages = new HashMap<>();

        commandMessages.put(0, new Message());
        // for message handling
        commandMessages.put(10000, new Message("",""));     // print only title
        commandMessages.put(10001, new Message("",""));     // print only description
        commandMessages.put(10002, new Message("\n",""));   // title new line
        commandMessages.put(10003, new Message("","\n"));   // description new line
        // Go messages
        commandMessages.put(1001, new Message("I can not go to ", ""));
        commandMessages.put(1002, new Message("west", ""));
        commandMessages.put(1003, new Message("north", ""));
        commandMessages.put(1004, new Message("east", ""));
        commandMessages.put(1005, new Message("south", ""));


    }

    private final Map<Integer, Message> commandMessages;

    public Message getCommandMessage(Integer messageId) {
        return this.commandMessages.get(messageId);
    }

    public Map<Integer, Message> getCommandMessages() {
        return commandMessages;
    }

    public Set<Integer> getDefinedCommandMessages() {
        return this.commandMessages.keySet();
    }
}
