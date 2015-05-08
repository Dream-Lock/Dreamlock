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
            messageIds.add(10002);
            messageIds.add(10002);
            for (Integer messageId : messageIds) {
                if(messageIds.indexOf(messageId) == 3 && messageIds.contains(1201))
                    System.out.print(messageId + " points of damage.");
                else if( messageIds.indexOf(messageId) == 7 &&  messageIds.contains(1202))
                    System.out.print(messageId + ".");
                else
                    System.out.print(messages.get(messageId).getName());
            }
        }
        else if (messageIds.contains(10001)) {
            messageIds.add(10003);
            messageIds.add(10003);
            for (Integer messageId : messageIds) {
                System.out.print(messages.get(messageId).getDescription());
            }
        }
        else if (messageIds.contains(10004)) {
            messageIds.add(10003);
            messageIds.add(10003);
            for (Integer messageId : messageIds) {
                if (!messages.get(messageId).getName().equals("")) {
                    System.out.print(messages.get(messageId).getName());
                }
                if (!messages.get(messageId).getDescription().equals("")) {
                    System.out.print(messages.get(messageId).getDescription());
                }
            }
        }
        else {
            messageIds.add(10003);
            messageIds.add(10003);
            for (Integer messageId : messageIds) {
                if (!messages.get(messageId).getName().equals("")) {
                    System.out.println(messages.get(messageId).getName());
                }
                if (!messages.get(messageId).getDescription().equals("")) {
                    System.out.print(messages.get(messageId).getDescription());
                }
            }
        }
    }
}
