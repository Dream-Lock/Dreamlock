package com.dreamlock.messageSystem;

import com.dreamlock.game.models.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageHandler {
    private Map<Integer, Message> messages;

    public MessageHandler() {
        messages = new HashMap<>();
    }

    public void register(Map<Integer, Message> messages) {
        this.messages.putAll(messages);
    }

    public void print(List<Integer> messageIds) {
        for (Integer messageId : messageIds) {
            System.out.print(messages.get(messageId).getName());
            System.out.print(messages.get(messageId).getDescription());
            System.out.println("");
        }
    }
}
