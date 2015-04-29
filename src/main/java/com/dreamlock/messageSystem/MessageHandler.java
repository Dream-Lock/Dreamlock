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
        if (messageIds.contains(10000)) {
            for (Integer messageId : messageIds) {
                System.out.print(messages.get(messageId).getName());
            }
        }
        else if (messageIds.contains(10001)) {
            for (Integer messageId : messageIds) {
                System.out.print(messages.get(messageId).getDescription());
            }
        }
        else {
            for (Integer messageId : messageIds) {
                System.out.println(messages.get(messageId).getName());
                System.out.print(messages.get(messageId).getDescription());
            }
        }
    }
}
