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
            for (int i=0;i<messageIds.size();i++) {
                int messageId = messageIds.get(i);
                if(asItself(messageIds,i,messageId))
                    System.out.print(messageId);
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
        }else if (messageIds.contains(10005)) {
            messageIds.add(10003);
            messageIds.add(10003);
            System.out.print("Health: "+ messageIds.get(0));
            System.out.print("Attack: "+ messageIds.get(0));
            System.out.print("Defense: "+ messageIds.get(0));
            System.out.print("Stamina: "+ messageIds.get(0));
            System.out.print("Strength: "+ messageIds.get(0));
            System.out.print("Agility: "+ messageIds.get(0));
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

    private boolean asItself(List<Integer> messageIds, int i, int messageId){
        int next_message_id_idx = i+1;
        if(next_message_id_idx < messageIds.size() && messageIds.contains(1301) && messageIds.get(next_message_id_idx) == 1309)
            return true;
        else if( next_message_id_idx < messageIds.size() && messageIds.get(next_message_id_idx) == 1308)
            return true;

        return false;
    }
}
